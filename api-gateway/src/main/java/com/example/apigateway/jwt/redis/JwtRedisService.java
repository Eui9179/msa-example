package com.example.apigateway.jwt.redis;

public interface JwtRedisService {
    boolean isBlockedAccessToken(String jwt);
}

