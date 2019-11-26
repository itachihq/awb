//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.awb.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JSONBeanUtil {
    private static Gson gson;

    public JSONBeanUtil() {
    }

    public static Gson getGson() {
        return gson;
    }

    public static <T> T json2Bean(String jsonString, Class<T> beanCalss) {
        return gson.fromJson(jsonString, beanCalss);
    }

    public static JsonObject bean2Json(Object bean) {
        return (JsonObject)json2Bean(parse(bean), JsonObject.class);
    }

    public static JsonArray list2JsonArray(Object list) {
        return (JsonArray)json2Bean(parse(list), JsonArray.class);
    }

    public static JsonObject unParse(String jsonString) {
        return (JsonObject)json2Bean(jsonString, JsonObject.class);
    }

    public static String parse(Object bean) {
        return gson.toJson(bean);
    }

    public static JsonArray parseArray(String jsonString) {
        return (JsonArray)json2Bean(jsonString, JsonArray.class);
    }

    public static int getIntVal(JsonObject json, String key) {
        boolean var2 = false;

        try {
            int val = json.has(key)?json.get(key).getAsInt():0;
            return val;
        } finally {
            ;
        }
    }

    public static String getStringVal(JsonObject json, String key) {
        String val = null;

        try {
            val = json.has(key)?json.get(key).getAsString():null;
            return val;
        } finally {
            ;
        }
    }

    public static double getDoubleVal(JsonObject json, String key) {
        double val = 0.0D;

        try {
            val = json.has(key)?json.get(key).getAsDouble():0.0D;
            return val;
        } finally {
            ;
        }
    }

    public static long getLongVal(JsonObject json, String key) {
        long val = 0L;

        try {
            val = json.has(key)?json.get(key).getAsLong():0L;
            return val;
        } finally {
            ;
        }
    }

    public static void main(String[] args) {
    }

    static {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        gson = builder.create();
    }
}
