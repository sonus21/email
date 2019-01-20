package com.email.repository.redis;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/** @author Sonu Kumar, created on 20-Jan-2019 */
abstract class AbstractRedisRepository<K, V extends Serializable> {

  protected RedisTemplate<K, V> redisTemplate;

  @Autowired protected RedisConnectionFactory redisConnectionFactory;

  @PostConstruct
  private void init() {
    this.redisTemplate = new RedisTemplate<>();
    this.redisTemplate.setConnectionFactory(this.redisConnectionFactory);

    this.redisTemplate.setKeySerializer(new StringRedisSerializer());
    this.redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

    this.redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    this.redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

    this.redisTemplate.afterPropertiesSet();
  }

  protected V get(K key) {
    return this.redisTemplate.opsForValue().get(key);
  }

  protected void set(K key, V value) {
    this.redisTemplate.opsForValue().set(key, value);
  }

  protected void set(K key, V value, Integer time) {
    this.redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
  }
}
