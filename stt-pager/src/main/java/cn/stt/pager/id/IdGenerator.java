package cn.stt.pager.id;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class IdGenerator {
	
	private static final int DEFAULT_RETRY_TIMES = 1;
	private static final int LUA_EVAL_ARGS_COUNT = 2;// 调用lua时的参数个数
	private static final String DEFAULT_KEY_PREFIX = "wema-uid";

	private JedisPool jedisPool;
	private String luaSHA;
	private String redisAuthPass;
	private int retryTimes = DEFAULT_RETRY_TIMES;
	private String host;
	private int port;
	

	public IdGenerator(String host,int port){
		this.host = host;
		this.port = port;
		jedisPool = new  JedisPool(host, port);
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	public String getLuaSHA() {
		return luaSHA;
	}

	public void setLuaSHA(String luaSHA) {
		this.luaSHA = luaSHA;
	}

	public String getRedisAuthPass() {
		return redisAuthPass;
	}

	public void setRedisAuthPass(String redisAuthPass) {
		this.redisAuthPass = redisAuthPass;
	}

	public int getRetryTimes() {
		return retryTimes;
	}

	public void setRetryTimes(int retryTimes) {
		this.retryTimes = retryTimes;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Jedis getJedisResource() {
		try {
			return jedisPool.getResource();
		} catch (Exception ex) {
			//logger.info("Cannot get Jedis connection:{}", ex.getMessage());
			throw new RuntimeException(ex.getCause());
		}
	}

	/***
	 * 
	 * @param table 用于区分业务的字符串
	 * @return
	 */
	public long next(String table) {
		return next(table, 0);
	}
	
	public String next() {
		return String.valueOf(next(DEFAULT_KEY_PREFIX, 0));
	}

	/***
	 * 
	 * @param table 用于区分业务的字符串
	 * @param shardId 分区id，可用区分业务的id即可
	 * @return
	 */
	public long next(String table, long shardId) {
		for (int i = 0; i < retryTimes; ++i) {
			Long id = innerNext(table, shardId);
			if (id != null) {
				return id;
			}
		}
		throw new RuntimeException("Can not generate id!");
	}
	
	@SuppressWarnings("unchecked")
	Long innerNext(String table, long shardId) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();

			if (StringUtils.isNotBlank(redisAuthPass)) {
				jedis.auth(redisAuthPass);
			}

			List<Long> result = (List<Long>) jedis.evalsha(luaSHA,
					LUA_EVAL_ARGS_COUNT, table, "" + shardId);
			long id = buildId(result.get(0), result.get(1), result.get(2),
					result.get(3));
			return id;
		} catch (JedisConnectionException e) {
			if (jedis != null) {
				jedisPool.returnBrokenResource(jedis);
			}
			//logger.error("generate id error!", e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
		return null;
	}

	public static long buildId(long second, long microSecond, long shardId,
			long seq) {
		long miliSecond = (second * 1000 + microSecond / 1000);
		return (miliSecond << (12 + 10)) + (shardId << 10) + seq;
	}

	public static List<Long> parseId(long id) {
		long miliSecond = id >>> 22;
		// 2 ^ 12 = 0xFFF
		long shardId = (id & (0xFFF << 10)) >> 10;
		long seq = id & 0x3FF;

		List<Long> re = new ArrayList<Long>(4);
		re.add(miliSecond);
		re.add(shardId);
		re.add(seq);
		return re;
	}
}
