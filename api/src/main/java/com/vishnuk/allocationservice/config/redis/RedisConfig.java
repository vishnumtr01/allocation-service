package com.vishnuk.allocationservice.config.redis;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@ComponentScan("com.javasampleapproach.redis")
public class RedisConfig {
    @Value("${redis.host.name}")
    private String redisHostName;
    @Value("${redis.host.port}")
    private int redisHostPort;
    @Value("${redis.max.connection}")
    private int redisMaxConnection;

	@Bean
	JedisConnectionFactory jedisConnectionFactory()
	{
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();

		// Defaults
		redisConnectionFactory.setHostName(redisHostName);
		redisConnectionFactory.setPort(redisHostPort);
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(redisMaxConnection);
		redisConnectionFactory.setPoolConfig(jedisPoolConfig);
		return redisConnectionFactory;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		return template;
	}
}
