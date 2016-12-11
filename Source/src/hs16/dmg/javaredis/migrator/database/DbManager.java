package hs16.dmg.javaredis.migrator.database;

import redis.clients.jedis.Jedis;

/**
 * Created by silvio on 10.12.16.
 */
public class DbManager {
    public static Jedis getDb(){
        return new Jedis("192.168.99.100", 32768);
    }
}
