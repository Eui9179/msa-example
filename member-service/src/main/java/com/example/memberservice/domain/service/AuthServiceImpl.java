package com.example.memberservice.domain.service;

import com.example.memberservice.common.exception.DuplicatedException;
import com.example.memberservice.common.jwt.provider.JwtProvider;
import com.example.memberservice.common.jwt.redis.JwtRedisService;
import com.example.memberservice.domain.entity.Member;
import com.example.memberservice.domain.web.dto.SignupRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final MemberService memberService;
    private final JwtProvider jwtProvider;
    private final JwtRedisService jwtRedisService;

    @Value("${jwt.expired.access-token}")
    private long accessTokenExpireTime;

    @Override
    @Transactional
    public void signup(SignupRequest signupRequest) {
        Optional<Member> member = memberService.getOpMemberByName(signupRequest.getEmail());
        if (member.isPresent()) {
            throw new DuplicatedException("Duplicated Member", signupRequest.getEmail());
        }
        memberService.createMember(signupRequest);
    }

    @Override
    @Transactional
    public String refreshAuthorization(String accessToken, HttpServletResponse response) {
        Long memberId = jwtProvider.parseMemberIdWithoutSecure(accessToken);
        Member member = memberService.getMember(memberId);
        String refreshToken = jwtRedisService.getRefreshToken(member.getId());

        jwtProvider.validateJwt(refreshToken);

        accessToken = jwtProvider.generateToken(String.valueOf(member.getId()), accessTokenExpireTime);
        jwtProvider.setJwtInCookie(accessToken, response);
        return accessToken;
    }

    @Override
    @Transactional
    public void updateRefreshToken(Long memberId, String refreshToken) {
        jwtRedisService.saveRefreshToken(memberId, refreshToken);
    }

}
