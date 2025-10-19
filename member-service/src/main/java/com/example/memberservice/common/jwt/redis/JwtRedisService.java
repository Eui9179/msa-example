package com.example.memberservice.common.jwt.redis;

public interface JwtRedisService {
    boolean isBlockedAccessToken(String jwt);

    void blockAccessToken(String jwt);

    void saveRefreshToken(Long memberId, String refreshToken);

    String getRefreshToken(Long memberId);

    void removeRefreshToken(Long id);
}

