package com.wj.taotao.test;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Demo01 {


    /*public static void main(String[] args) {

    }*/

    @Test
    public void testJedis(){
        Jedis jedis = new Jedis("10.0.0.117",7002);
       /* jedis.set("key1","value1");*/
        System.out.println(jedis.get("key1"));
//        jedis.close();
    }

    @Test
    public void testJedisPool(){
        JedisPool jedisPool = new JedisPool("10.0.0.116",6379);
        Jedis jedis = jedisPool.getResource();
        jedis.set("key2","value2");
        System.out.println(jedis.get("key2"));
        jedis.close();
        jedisPool.close();
    }

    //测试集群版
    @Test
    public void testJedisCluster() throws IOException {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("10.0.0.117",7001));
        nodes.add(new HostAndPort("10.0.0.117",7002));
        nodes.add(new HostAndPort("10.0.0.117",7003));
        nodes.add(new HostAndPort("10.0.0.117",7004));
        nodes.add(new HostAndPort("10.0.0.117",7005));
        nodes.add(new HostAndPort("10.0.0.117",7006));
        JedisCluster jedisCluster = new JedisCluster(nodes);

        jedisCluster.set("key1","value1");
        System.out.println(jedisCluster.get("key1"));
        jedisCluster.close();
    }



}
