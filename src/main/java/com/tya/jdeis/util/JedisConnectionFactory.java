package com.tya.jdeis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * <h4>jedisdemo</h4>
 *
 * @author : 唐永澳
 * @date : 2023-03-12 20:39
 **/
public class JedisConnectionFactory {
    private static final JedisPool jedispool;
    static {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        //最大连接数
        poolConfig.setMaxTotal(8);
        //最大空闲连接，预备最多有八个，有需要直接拿，不用去创建
        poolConfig.setMaxIdle(8);
        //空闲连接如果一直没人连接，就会归为0
        poolConfig.setMinIdle(0);
        //如果没有连接可以访问，就等待1000ms后报错
        poolConfig.setMaxWaitMillis(1000);
        jedispool =new JedisPool(poolConfig,"192.168.121.121",6379,1000,"tya");

    }
    public static Jedis getJedis(){
        return jedispool.getResource();
    }
}

