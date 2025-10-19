package com.example.memberservice.domain.service;

import com.example.memberservice.common.exception.UserNotFoundException;
import com.example.memberservice.domain.entity.Member;
import com.example.memberservice.domain.repository.MemberRepository;
import com.example.memberservice.domain.web.dto.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;

    public Optional<Member> getOpMemberByName(String email) {
        return memberRepository.findByEmail(email);
    }

    public Member getMember(String email) {
        return getOpMemberByName(email).orElseThrow(() -> new UserNotFoundException(email));
    }

    public Member getMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.valueOf(id)));
    }

    public void createMember(SignupRequest signupRequest) {
        memberRepository.save(Member.builder()
                .email(signupRequest.getEmail())
                .password(encoder.encode(signupRequest.getPassword()))
                .build());
    }
}
