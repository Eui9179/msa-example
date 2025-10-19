package com.example.memberservice.domain.service;

import com.example.memberservice.domain.web.dto.SignupRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    void signup(SignupRequest signupRequest);

    String refreshAuthorization(String accessToken, HttpServletResponse response);

    void updateRefreshToken(Long memberId, String refreshToken);
}
