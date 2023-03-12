package com.tya;

import com.tya.jdeis.util.JedisConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * <h4>jedisdemo</h4>
 *
 * @author : 唐永澳
 * @date : 2023-03-10 17:25
 **/
public class jedis {
    private Jedis jedis;

    @BeforeEach
     public void setUp() {
        //建立连接
        //jedis = new Jedis("192.168.121.121", 6379);
        //配置完成后从池子里获取连接
        jedis = JedisConnectionFactory.getJedis();
        //设置密码
        jedis.auth("tya");
        //选择库
        jedis.select(0);
    }

    @Test
    void testString() {
        //存入数据
        String set = jedis.set("name", "tya");
        System.out.println(set);
        //获取数据
        String name = jedis.get("name");
        System.out.println(name);
    }
    @Test
    void testHash() {
        jedis.hset("user:1","name","txl");
        jedis.hset("user:1","age","tya");
        Map<String, String> map = jedis.hgetAll("user:1");
        System.out.println(map );
    }


    @AfterEach
    void teardown() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
