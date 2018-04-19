package app.configuration;

import redis.clients.jedis.Jedis;

public class ServeurConf {
	
	public static Jedis getJedis(){
		return new Jedis("localhost", 6979);
	}

}
