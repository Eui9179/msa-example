package com.example.apigateway.jwt.redis;

import com.example.apigateway.jwt.provider.JwtProvider;
import org.springframework.data.redis.core.RedisTemplate;

public class JwtRedisServiceImpl implements JwtRedisService {
    private final RedisRepository redisRepository;
    private final JwtProvider jwtProvider;

    private final String BLOCKLIST_PREFIX = "blocklist:";
    private final String REFRESH_TOKEN_PREFIX = "refresh-token:";

    public JwtRedisServiceImpl(RedisTemplate<String, String> redisTemplate, JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
        this.redisRepository = new RedisRepositoryImpl(redisTemplate);
    }

    @Override
    public boolean isBlockedAccessToken(String jwt) {
        return redisRepository.get(BLOCKLIST_PREFIX + jwt) != null;
    }
}
