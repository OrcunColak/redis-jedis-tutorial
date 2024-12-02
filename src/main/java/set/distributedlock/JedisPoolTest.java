package set.distributedlock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisPoolTest {

    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 6379;
        boolean ssl = false;

        try (JedisPool jedisPool = new JedisPool(host, port, ssl)) {
            try (Jedis jedis = jedisPool.getResource()) {
                String key = "lock_key";
                String value = "distributed_lock";

                // Set without expiry parameters
                jedis.set(key, value);
            }

        }
    }
}
