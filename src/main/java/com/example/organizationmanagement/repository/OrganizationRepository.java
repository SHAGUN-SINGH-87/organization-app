package com.example.organizationmanagement.repository;

import com.example.organizationmanagement.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization,Long> {

}
