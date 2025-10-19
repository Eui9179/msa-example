package com.example.memberservice.common.jwt.config;

import com.example.memberservice.common.jwt.provider.JwtProvider;
import com.example.memberservice.common.jwt.provider.JwtProviderImpl;
import com.example.memberservice.common.jwt.redis.JwtRedisService;
import com.example.memberservice.common.jwt.redis.JwtRedisServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class JwtConfig {

    @Value("${jwt.expired.access-token}")
    private long accessTokenExpireTime;

    @Value("${jwt.expired.refresh-token}")
    private long refreshTokenExpireTime;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Bean
    public JwtProvider jwtProvider() {
        return new JwtProviderImpl(accessTokenExpireTime, refreshTokenExpireTime, jwtSecret);
    }

    @Bean
    public JwtRedisService jwtRedisService(StringRedisTemplate redisTemplate, JwtProvider jwtProvider) {
        return new JwtRedisServiceImpl(redisTemplate, jwtProvider);
    }
}
