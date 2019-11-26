package com.awb.component;

import com.awb.util.JSONBeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.*;

/**
 * * description: Redis缓存
 * * auth.:yu.li
 * * created:2018/1/12 0012
 **/
public class RedisCache implements ICache{
    private static  final Logger logger = LoggerFactory.getLogger(RedisCache.class);
    private static final int CONNECT_OUT_TIME=60000;//连接超时
    private static final boolean IS_SSL=false;//是否https
    private static String redisPrekey=null;
    private static ShardedJedisPool pool;//连接池
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static JedisShardInfo jedisShardInfo;

    public RedisCache(String redisIp, Integer redisPort, String redisPassword, String redisPrekey) throws Exception {
        if (redisIp==null||redisPort==null) {
            logger.error("**配置Ip和端口不可为空**");
            throw new Exception("配置Ip和端口不可为空");
        }
        this.redisPrekey=redisPrekey;
        //连接信息
        jedisShardInfo=new JedisShardInfo(redisIp, redisPort, CONNECT_OUT_TIME,"http");
        if ((redisPassword!=null)&&(redisPassword.length()>0))
        {
            jedisShardInfo.setPassword(redisPassword);
        }
        initPool(jedisShardInfo);
    }

    /**
     * 是否存在此缓存 key
     *
     * @param key
     * @return
     */
    @Override
    public boolean exists(String key) {
        try {
            return getJedis().exists(redisPrekey+key);
        } catch (Exception e) {
            logger.error("redis出错",e);
            return false;
        }
    }

    /**
     * 根据Key获取缓存中的值
     *
     * @param key
     * @return
     */
    @Override
    public <T> T get(String key,Class<T> t) {
        try {
            String data= getJedis().get(redisPrekey+key);
            return JSONBeanUtil.json2Bean(data,t);
        } catch (Exception e) {
            logger.error("redis出错",e);
        }
        return null;
    }

    /**
     * 根据Key获取缓存中的String值
     *
     * @param key
     * @return
     */
    @Override
    public String getString(String key) {
        try {
            return getJedis().get(redisPrekey+key);
        } catch (Exception e) {
            logger.error("redis出错",e);
            return null;
        }
    }

    /**
     * 往缓存中放入key-value，返回缓存中之前的值
     *
     * @param key
     * @param value
     * @return 缓存中之前的值
     */
    @Override
    public<T> boolean put(String key, T value) {
        try {
             String val=JSONBeanUtil.parse(value);
             getJedis().set(redisPrekey+key,val);
             return true;
        } catch (Exception e) {
            logger.error(value+"是数据无效的字符串",e);
        }
        return false;
    }


    public<T>  boolean put(String key, T value,int timeOut) {
        try {
            String val=JSONBeanUtil.parse(value);
            getJedis().setex(redisPrekey+key, timeOut,val);
            return true;
        } catch (Exception e) {
            logger.error(value+"是数据无效的json字符串",e);
        }
        return false;
    }

    /**
     * 往缓存中放入key-value，返回缓存中之前的值
     * @param key
     * @param value
     * @return
     */
    @Override
    public String putString(String key, String value) {

        try {
             return  getJedis().set(redisPrekey+key,value);
        } catch (Exception e) {
           logger.error("redis出错",e.getMessage());
            return null;
        }
    }

    /**
     * 往缓存中放入key-value，并设置过期时间
     * @param key
     * @param value
     * @param timeOut
     * @return
     */
    public boolean putString(String key, String value,int timeOut) {
        try {
            getJedis().setex(redisPrekey+key,timeOut,value);
            return  true;
        } catch (Exception e) {
            logger.error("redis出错",e);
            return false;
        }
    }

    /**
     * 移除缓存中key对应的值
     *
     * @param key
     * @return key对应的值
     */
    @Override
    public boolean remove(String key) {
        try {
            key = redisPrekey+key;
            if (getJedis().exists(key))
            {
                long count=getJedis().del(key);
                return count>0;
            }
            logger.error(key+"不存在");
            return true;
        } catch (Exception e) {
            logger.error("redis出错",e);
            return false;
        }
    }

    /**
     * 清空整个缓存
     */
    @Override
    public void clear() {
        try {
            Set<String> set = keys();
            Iterator<String> it = set.iterator();
            while(it.hasNext()){
                String keyStr = it.next();
                getJedis().del(keyStr);
            }
        } catch (Exception e) {
            logger.error("redis出错",e);
        }
    }

    /**
     * 返回缓存大小
     *
     * @return 缓存大小
     */
    @Override
    public int size() {
        try {
            return new Long(getJedis().pfcount(redisPrekey +"*")).intValue();
        } catch (Exception e) {
            logger.error("redis出错",e);
            return 0;
        }
    }

    /**
     * 获取缓存中所有的key
     *
     * @return 所有的key
     */
    @Override
    public Set<String> keys() {
        try {
            return getJedis().hkeys(redisPrekey + "*");
        } catch (Exception e) {
            logger.error("redis出错", e);
            return null;
        }
    }
    /**
     * 获取事务锁
     *
     * @param eventId
     * @param accessOutTime
     * @return 锁id
     */
    @Override
    public String acquireLock(String eventId, long accessOutTime) {
        try {
            // 随机生成一个value
            String identifier = UUID.randomUUID().toString();
            synchronized (identifier) {
                // 锁名，即key值
                String lockKey = redisPrekey +"lock:" + eventId;
                // 超时时间2秒，上锁后超过此时间则自动释放锁
                // 获取锁的超时时间超过这个时间则放弃获取锁
                long end = System.currentTimeMillis() + accessOutTime;
                String result = getJedis().set(lockKey, identifier, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, end);
                if (LOCK_SUCCESS.equals(result)) {
                    return identifier;
                }
            }
        } catch (Exception e) {
            logger.error("redis出错",e);
        }
        return null;
    }

    /**
     * 释放锁
     *
     * @param eventId
     * @param lockId
     * @return
     */
    @Override
    public synchronized void releaseLock(String eventId, String lockId) {
        boolean broken=false;
        try {
            String lockKey = redisPrekey +"lock:" + eventId;
            if (lockId.equals(getJedis().get(lockKey))) {
                getJedis().del(lockKey);
            }
        } catch (Exception e) {
            logger.error("redis出错",e);
        }
    }

    /**
     * 限速器，单位时间内的限制次数
     * @param source 来源
     * @param eventId 事件id
     * @param range 时间范围（秒）
     * @param times 有限次数
     * @return
     */
    @Override
    public synchronized boolean isOverTimes(String source, String eventId, int range, int times) {
        try {
            // 锁名，即key值
            String lockKey = redisPrekey+eventId+source;
            // 超时时间时间秒，上锁后超过此时间则自动上锁5秒
            int end = 5;
            long count=getJedis().incr(lockKey);
            if (count==1)
            {
                getJedis().expire(lockKey,range);
            }
            if (count>times)
            {
                getJedis().setex(lockKey,end,""+count);
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("redis出错",e);
        }
        return false;
    }

    /**
     * 释放连接
     * @param jedis
     * @param conectionBroken
     */
    protected synchronized void closeResource(ShardedJedis jedis, boolean conectionBroken) {
        try {
            if (jedis!=null) {
                jedis.close();
            }
            if (!pool.isClosed()) {
                if (conectionBroken) {
                    pool.returnBrokenResource(jedis);
                } else {
                    pool.returnResource(jedis);
                }
            }
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
        }
    }


    public synchronized ShardedJedis getJedis() {
        ShardedJedis jedis = null;
        try {
            if (pool==null||pool.isClosed()) {
                initPool(jedisShardInfo);
            }
            jedis=pool.getResource();
        } catch (Exception e) {
            logger.error("Get jedis error : "+e.getMessage());
        }finally {
            closeResource(jedis,true);
        }
        return jedis;
    }

    /**
     * 获取连接
     * @return
     */
    public synchronized void initPool(JedisShardInfo jedisShardInfo) {
        if (this.pool == null||this.pool.isClosed()) {
            List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
            shards.add(jedisShardInfo);
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(100);
            config.setMaxIdle(100);
            config.setMinIdle(2);//设置最小空闲数
            config.setMaxWaitMillis(1000*5);
            config.setTestOnBorrow(true);
            //Idle时进行连接扫描
            config.setTestWhileIdle(true);
            //表示idle object evitor两次扫描之间要sleep的毫秒数
            config.setTimeBetweenEvictionRunsMillis(1000*30);
            //表示idle object evitor每次扫描的最多的对象数
            config.setNumTestsPerEvictionRun(100);
            //表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；
            config.setMinEvictableIdleTimeMillis(1000*1);
            this.pool = new ShardedJedisPool(config, shards);
        }
    }

    @Override
    public void close()
    {
       if (this.pool!=null)
       {
            pool.close();
       }
    }

}
