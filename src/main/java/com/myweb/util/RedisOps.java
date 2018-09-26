package com.myweb.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class RedisOps {
    public static void set(String key,String value){
        Jedis jedis = RedisConnection.getJedis();
        jedis.set(key, value);
        jedis.close();
    }
    public static String get(String key){
        Jedis jedis = RedisConnection.getJedis();
        String value = jedis.get(key);
        jedis.close();
        return value;
    }
    public static void setObject(String key,Object object){
        Jedis jedis = RedisConnection.getJedis();
        jedis.set(key.getBytes(), SerializeUtil.serizlize(object));
        jedis.close();
    }
    public static Object getObject(String key){
        Jedis jedis = RedisConnection.getJedis();
        byte[] bytes = jedis.get(key.getBytes());
        jedis.close();
        return SerializeUtil.deserialize(bytes);
    }
    public static void del(String key) {
		Jedis jedis = RedisConnection.getJedis();
		if (jedis.exists(key)) {
			jedis.del(key);
		}
		jedis.close();
	}
    public static String hmset(String key, Map<String, String> map) {
        Jedis jedis =  RedisConnection.getJedis();
        String result=jedis.hmset(key,map);
        jedis.close();
        return result;
    }

    public static Map<String, String> hmget(String key) {
    	Jedis jedis =  RedisConnection.getJedis();
    	Iterator<String> iter=jedis.hkeys(key).iterator();
    	Map<String, String> result = new HashMap<>();
    	while (iter.hasNext()){  
    		String keyid = iter.next();
    		result.put(keyid, jedis.hmget(key, keyid).get(0));
    	}
        jedis.close();
        return result;
    }
    
    public static void hdel(String key,String field) {
		Jedis jedis = RedisConnection.getJedis();
		if (jedis.hexists(key, field)) {
			jedis.hdel(key,field);
		}
		jedis.close();
	}
    
}
