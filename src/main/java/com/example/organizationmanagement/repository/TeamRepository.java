package com.example.organizationmanagement.repository;

import com.example.organizationmanagement.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository <Team,Long> {
    List<Team> findByOrganizationId(Long id);
}
