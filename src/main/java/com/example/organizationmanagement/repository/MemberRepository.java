package com.example.organizationmanagement.repository;

import com.example.organizationmanagement.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
