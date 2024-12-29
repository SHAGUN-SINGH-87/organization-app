package com.example.organizationmanagement.repository;

import com.example.organizationmanagement.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository <Team,Long> {
}
