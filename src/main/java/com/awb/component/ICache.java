package com.awb.component;


import java.util.Set;

/**
 * 缓存接口
 * @author yu.li
 */
public interface ICache {
    /**
     * 是否存在此缓存 key
     * @param key
     * @return
     */
    boolean exists(String key);

    /**
     * 根据Key获取缓存中的值
     *
     * @param key
     * @return
     */
    <T> T get(String key, Class<T> t);

    /**
     * 根据Key获取缓存中的String值
     * @param key
     * @return
     */
    String getString(String key);

    /**
     * 往缓存中放入key-value，返回缓存中之前的值
     *
     * @param key
     * @param value
     * @return 缓存中之前的值
     */
    <T>boolean put(String key, T value);

    /**
     * 往缓存中放入key-value,并设定有效失效时间
     * @param key
     * @param value
     * @param timeOut
     * @return
     */
    <T> boolean put(String key, T value, int timeOut);
    /**
     * 往缓存中放入key-value，返回缓存中之前的值
     * @param key
     * @param value
     * @return
     */
    String  putString(String key, String value);

    /**
     * 往缓存中放入key-value,并设定有效失效时间
     * @param key
     * @param value
     * @param expired 失效时间（秒）
     * @return
     */
    boolean  putString(String key, String value, int expired);
    /**
     * 移除缓存中key对应的值
     * @param key
     * @return key对应的值
     */
    boolean remove(String key);

    /**
     * 清空整个缓存
     */
    void clear();

    /**
     * 返回缓存大小
     * @return 缓存大小
     */
    int size();

    /**
     * 获取缓存中所有的key
     * @return 所有的key
     */
    Set<String> keys();


    /**
     * 获取事务锁
     * @param eventId
     * @param accessOutTime
     * @return lockId
     */
     String acquireLock(String eventId, long accessOutTime);

    /**
     * 释放锁
     * @param eventId
     * @param lockId
     * @return
     */
    void releaseLock(String eventId, String lockId);


    /**
     * 是否超过单位时间内的限制次数
     * @param source 用户来源
     * @param eventId 事件id
     * @param range 时间范围（秒）
     * @param times 有限次数
     * @return 是，超过，否不超过
     */
     boolean isOverTimes(String source, String eventId, int range, int times);

     void close();
}