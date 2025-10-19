package com.example.memberservice.domain.repository;

import com.example.memberservice.domain.entity.Member;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @NonNull Optional<Member> findById(@NonNull Long id);

    Optional<Member> findByEmail(String Email);
}
