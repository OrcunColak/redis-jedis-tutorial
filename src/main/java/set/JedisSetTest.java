package set;

import redis.clients.jedis.Jedis;

class JedisSetTest {

    public static void main(String[] args) {

        try (Jedis jedis = new Jedis("localhost")) {

            // Adding usernames
            jedis.set("ironman", "exists");

            // Checking usernames
            System.out.println(jedis.exists("ironman")); // true
            System.out.println(jedis.exists("hulk"));    // false
        }
    }
}
