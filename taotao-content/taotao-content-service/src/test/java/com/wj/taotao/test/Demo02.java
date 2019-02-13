package com.wj.taotao.test;

import com.wj.taotao.content.jedis.JedisClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo02 {

    @Test
    public void test1(){
        //初始化spring容器
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
        JedisClient jedisClient = context.getBean(JedisClient.class);
        jedisClient.set("test1key","test");
        System.out.println(jedisClient.get("test1key"));

    }
}
