package com.taotao.sso.service.impl;

import redis.clients.jedis.Jedis;

public class Demo01 {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("10.0.0.130");
        jedis.set("SESSION:8ed8bf73-c624-4903-bb31-efefb20766cb","value1");
    }
}
