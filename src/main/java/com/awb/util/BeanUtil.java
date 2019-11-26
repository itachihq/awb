package com.awb.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BeanUtil {
    private final static Logger logger = LoggerFactory.getLogger(BeanUtil.class);
    protected BeanUtil() {
    }

    /**
     * 通过propertyName在object中查找相应Field的类型（如果字段不存在，则查找getter方法的返回值类型） <br>
     * 注意：支持嵌套属性:类似：“user.id”；object有个属性user，user又有一个属性id，当然也支持普通属性
     *
     * @param object
     * @param propertyName
     * @return
     */
    public static Class getPrivateNestedFieldType(Object object,
                                                  String propertyName) {
        if (object == null) {
            return null;
        }
        if (StringUtils.isEmpty(propertyName)) {
            return null;
        }
        if (!propertyName.contains(".")) {
            // 非嵌套的属性
            return getPrivateFieldType(object.getClass(), propertyName);
        } else {
            int firstDotIndex = propertyName.indexOf(".");
            String currentFieldName = propertyName.substring(0, firstDotIndex);
            Object innerObj = getPrivateProperty(object, currentFieldName);
            return getPrivateNestedFieldType(innerObj,
                    propertyName.substring(firstDotIndex + 1));
        }
    }

    /**
     * 通过propertyName在clazz中查找相应Field的类型（如果字段不存在，则查找getter方法的返回值类型） <br>
     * 注意：支持嵌套属性:类似：“user.id”；object有个属性user，user又有一个属性id，当然也支持普通属性
     *
     * @param object
     * @param propertyName
     * @return
     */
    public static Class getPrivateFieldType(Object object, String propertyName) {
        if (object == null) {
            return null;
        }
        if (StringUtils.isEmpty(propertyName)) {
            return null;
        }
        Class clazz = object.getClass();
        Class fieldType = null;
        if (propertyName.contains(".")) {
            // 含有嵌套属性
            return getPrivateNestedFieldType(object, propertyName);
        }
        Field field = null;
        try {
            field = clazz.getDeclaredField(propertyName);
        } catch (Exception e) {
            field = null;
        }
        if (field != null) {
            fieldType = field.getType();
        } else {
            // 通过getter来查找
            Method getter = null;
            try {
                getter = clazz.getMethod("get" + initCap(propertyName));
            } catch (NoSuchMethodException e) {
                try {
                    getter = clazz.getMethod("is" + initCap(propertyName));
                } catch (NoSuchMethodException e2) {
                    logger.error("getPrivateFieldType error");
                }
            }
            if (getter == null) {
                // getter 也为null，则该抛出异常了
                throw new RuntimeException("in " + clazz
                        + " neither field named \"" + propertyName
                        + "\", nor method named \"get/is"
                        + initCap(propertyName) + "\"!");
            } else {
                try {
                    fieldType = getter.getReturnType();
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
        return fieldType;
    }

    /**
     * 获取当前类声明的private/protected属性/字段（或者是对应的get方法的返回值（比如获取父类的的属性（
     * 必需拥有public的getter方法））） <br>
     * 兼容嵌套属性（id.name）<br>
     * 支持类的静态属性（把objectOrClass设置成Class即可）
     *
     * @param objectOrClass
     *            将被执行的对象
     * @param propertyName
     *            属性名
     * @return 属性对应的object value
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static Object getPrivateProperty(Object objectOrClass,
                                            String propertyName) {
        if (objectOrClass == null) {
            return null;
        }
        Class clazz = ((objectOrClass instanceof Class) ? (Class) objectOrClass
                : objectOrClass.getClass());
        if (StringUtils.isEmpty(propertyName)) {
            return null;
        }
        if (propertyName.contains(".")) {
            // 含有嵌套属性
            return getPrivateNestedProperty(objectOrClass, propertyName);
        }
        Field field = null;
        try {
            field = clazz.getDeclaredField(propertyName);
        } catch (NoSuchFieldException e) {
            field = null;
        }
        Object val = null;
        if (field != null) {
            field.setAccessible(true);
            try {
                val = field.get(objectOrClass);
            } catch (IllegalArgumentException e) {
                throw e;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.getMessage());
            }
        } else {
            Method getter = null;
            try {
                getter = clazz.getMethod("get"
                        + initCap(propertyName));
            } catch (NoSuchMethodException e) {
                try {
                    getter = clazz.getMethod("is"
                            + initCap(propertyName));
                } catch (NoSuchMethodException e2) {
                    logger.error("getPrivateProperty error");
                }
            }
            if (getter == null) {
                // getter 也为null，则该抛出异常了
                throw new RuntimeException("in " + clazz
                        + " neither field named \"" + propertyName
                        + "\", nor method named \"get/is"
                        + initCap(propertyName) + "\"!");
            } else {
                try {
                    val = getter.invoke(objectOrClass);
                } catch (IllegalArgumentException e) {
                    throw e;
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e.getMessage());
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
        return val;
    }

    /**
     * 获取当前类声明的private/protected属性/字段（或者是对应的get方法的返回值（比如获取父类的的属性（
     * 必需拥有public的getter方法）））<br>
     * 注意：支持嵌套属性:类似：“user.id”；object有个属性user，user又有一个属性id，当然也支持普通属性<br>
     * 支持类的静态属性（把objectOrClass设置成Class即可）
     *
     * @param objectOrClass
     *            将被执行的对象
     * @param propertyName
     *            属性名
     * @return 属性对应的object value
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static Object getPrivateNestedProperty(Object objectOrClass,
                                                  String propertyName) {
        if (objectOrClass == null) {
            return null;
        }
        if (StringUtils.isEmpty(propertyName)) {
            return null;
        }
        if (!propertyName.contains(".")) {
            // 非嵌套的属性
            return getPrivateProperty(objectOrClass, propertyName);
        } else {
            int firstDotIndex = propertyName.indexOf(".");
            String currentFieldName = propertyName.substring(0, firstDotIndex);
            Object innerObj = getPrivateProperty(objectOrClass,
                    currentFieldName);
            return getPrivateNestedProperty(innerObj,
                    propertyName.substring(firstDotIndex + 1));
        }
    }

    /**
     * 设置当前类声明的private/protected变量 <br>
     * 支持类的静态属性（把objectOrClass设置成Class即可）
     *
     * @param objectOrClass
     *            将被执行的对象 可以是个Class
     * @param propertyName
     *            属性名
     * @param newValue
     *            属性对应的object value
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static void setPrivateProperty(Object objectOrClass,
                                          String propertyName, Object newValue) {
        if (objectOrClass == null) {
            return;
        }
        if (StringUtils.isEmpty(propertyName)) {
            return;
        }
        Class clazz = ((objectOrClass instanceof Class) ? (Class) objectOrClass
                : objectOrClass.getClass());
        try {
            Field field = clazz.getDeclaredField(propertyName);
            field.setAccessible(true);
            field.set(objectOrClass, newValue);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 调用当前类声明/继承的private/protected函数<br>
     * 可以是静态方法
     *
     * @param objectOrClass
     *            将被执行的对象、Class
     * @param methodName
     *            方法名
     * @param argsTypes
     *            放置params中的参数向下转型，导致无法找到方法，则自定义寻找方法的第二个参数
     * @param params
     *            方法参数对应值数组
     * @return 返回返回值
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Object invokePrivateMethod(Object objectOrClass, String methodName,
                                             Class[] argsTypes, Object... params) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException {
        if (objectOrClass == null) {
            return null;
        }
        if (StringUtils.isEmpty(methodName)) {
            return null;
        }
        Class[] types = null;
        if (argsTypes == null || argsTypes.length <= 0) {
            if (params == null) {
                types = new Class[0];
            } else {
                types = new Class[params.length];
                for (int i = 0; i < params.length; i++) {
                    types[i] = params[i].getClass();
                }
            }
        } else {
            types = argsTypes;
        }
        Method method = null;
        Class clazz = null;
        if (objectOrClass instanceof Class) {
            clazz = (Class) objectOrClass;
        } else {
            clazz = objectOrClass.getClass();
        }
        while (method == null) {
            try {
                method = clazz.getDeclaredMethod(methodName, types);
            } catch (NoSuchMethodException e) {
                method = null;
                Class superClazz = clazz.getSuperclass();
                if (superClazz != null) {
                    // 还有父类，则继续在父类中寻找这个方法
                    clazz = superClazz;
                } else {
                    throw e;
                }
            }
        }
        method.setAccessible(true);
        return method.invoke(objectOrClass, params);
    }

    /**
     * 调用当前类声明/继承的private/protected函数<br>
     * 可以是静态方法
     *
     * @param object
     *            将被执行的对象、Class
     * @param methodName
     *            方法名
     * @return 返回返回值
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Object invokeNoArgsPrivateMethod(Object object,
                                                   String methodName) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException {
        return invokePrivateMethod(object, methodName, null, new Object[] {});
    }

    /**
     * 调用当前类声明的private/protected函数
     *
     * @param object
     *            将被执行的对象、Class
     * @param methodName
     *            方法名
     * @param params
     *            方法参数对应值数组
     * @return 返回返回值
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Object invokePrivateMethod(Object object, String methodName,
                                             Object... params) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException {
        return invokePrivateMethod(object, methodName, null, params);
    }

    /**
     * 获得一个对象所有 属性
     *
     * @param obj
     * @return
     */
    public static List<Field> getAllDeclaredFields(Object obj) {
        if (obj == null) {
            return new ArrayList<Field>(0);
        }
        return getAllDeclaredFields(obj.getClass());
    }

    /**
     * 获得一个Class所有 属性
     *
     * @param clazz
     * @return
     */
    public static List<Field> getAllDeclaredFields(Class clazz) {
        if (clazz == null) {
            return new ArrayList<Field>(0);
        }
        List<Field> allGoodFieldList = new ArrayList<Field>();
        Field[] fs = clazz.getDeclaredFields();
        if (fs != null && fs.length > 0) {
            allGoodFieldList = Arrays.asList(fs);
        } else {
            allGoodFieldList = new ArrayList<Field>();
        }
        return allGoodFieldList;
    }

    /**
     * 得到object中为null的字段的字段名称的数组
     *
     * @param object
     *            对象
     * @return String 数组
     */
    public static String[] getNullFieldNameArray(Object object) {
        List<String> nullFieldNameList = new ArrayList<String>();
        if (object == null) {
            return new String[0];
        }
        Class clazz = object.getClass();
        List<Field> allFsNames = getAllDeclaredFields(clazz);
        if (allFsNames != null) {
            int fsSize = allFsNames.size();
            for (int i = 0; i < fsSize; i++) {
                String fieldName = allFsNames.get(i).getName();
                try {
                    if (getPrivateProperty(object, fieldName) == null) {
                        nullFieldNameList.add(fieldName);
                    }
                } catch (Exception e) {
                    logger.error("getNullFieldNameArray error");
                }
            }
        }
        String[] fsNameArr = new String[nullFieldNameList.size()];
        nullFieldNameList.toArray(fsNameArr);
        return fsNameArr;
    }

    /**
     * 把第一个字符转换成大写
     *
     * @param src
     * @return
     */
    public static String initCap(String src) {
        if (src == null) {
            return null;
        }
        if (src.length() > 1) {
            return src.substring(0, 1).toUpperCase() + src.substring(1);
        } else {
            return src.toUpperCase();
        }
    }


}

