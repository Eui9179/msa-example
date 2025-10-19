package com.example.apigateway.jwt.provider;

import javax.crypto.SecretKey;

public interface JwtProvider {
    String generateToken(String subject, Long expired);

    SecretKey getSecretKey();

    /**
     * 만료 여부와 관계없이 JWT의 subject를 가져옴, 필요할 때만 사용하고 이외에는 parseSubject() 사용
     * @param jwt accessToken
     * @return subject (사용자 이름, name)
     */
    String parseSubjectWithoutSecure(String jwt);

    void validateJwt(String jwt);
}
