package com.example.apigateway.jwt.provider;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Getter
@RequiredArgsConstructor
public class JwtProviderImpl implements JwtProvider {

    private final long accessTokenExpireTime;
    private final long refreshTokenExpireTime;
    private final String secretKey;

    private final String TOKEN_KEY = "com/example/memberservice/common/jwt";

    public final String GRANT_TYPE = "Bearer ";


    @Override
    public String generateToken(String subject, Long expired) {
        return Jwts.builder()
                .subject(subject)
                .expiration(new Date(System.currentTimeMillis() + expired))
                .issuedAt(new Date())
                .signWith(getSecretKey())
                .compact();
    }

    @Override
    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey));
    }

    @Override
    public String parseSubjectWithoutSecure(String jwt) {
        try {
            return Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
    }

    @Override
    public void validateJwt(String jwt) {
        Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload()
                .getExpiration();
    }
}
