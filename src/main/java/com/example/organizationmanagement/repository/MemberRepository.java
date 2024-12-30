package com.example.organizationmanagement.repository;

import com.example.organizationmanagement.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {
    List<Member> findByTeamId(Long id);
}
