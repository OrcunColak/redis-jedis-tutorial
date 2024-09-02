package distributedlock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

class DistributedLock {

    // Lock expiry time in milliseconds
    private static final int LOCK_EXPIRY = 10000;

    private static final String LOCK_KEY = "lock_key";

    public static void main(String[] args) {
        try (Jedis jedis = new Jedis("localhost", 6379)) {

            // This should be a unique identifier for your lock (e.g., UUID)
            String lockValue = "distributed_lock";
            if (acquireLock(jedis, lockValue)) {
                releaseLock(jedis, lockValue);
            }
        }
    }

    private static boolean acquireLock(Jedis jedis, String lockValue) {
        SetParams params = new SetParams().nx().px(LOCK_EXPIRY);
        String result = jedis.set(LOCK_KEY, lockValue, params);
        return "OK".equals(result);
    }

    private static void releaseLock(Jedis jedis, String lockValue) {
        String lockValueStored = jedis.get(LOCK_KEY);

        if (lockValue.equals(lockValueStored)) {
            jedis.del(LOCK_KEY); // Release the lock
        }
    }
}
