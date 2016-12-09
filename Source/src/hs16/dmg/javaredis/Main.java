package hs16.dmg.javaredis;

import redis.clients.jedis.Jedis;

public class Main {

    public static void main(String[] args) {

        Jedis db = new Jedis("192.168.99.100", 32770);
        String ping = db.ping();

        //db.set("TestString", "Redis");


        System.out.print(ping);
        System.out.print(db.get("TestString"));
    }
}
