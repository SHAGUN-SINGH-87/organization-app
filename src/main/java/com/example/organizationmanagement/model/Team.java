package com.example.organizationmanagement.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Team {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;

        @ManyToOne
        @JoinColumn(name = "organization_id")
        private Organization organization;

        @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
        private List<Member> members;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Organization getOrganization() {
                return organization;
        }

        public void setOrganization(Organization organization) {
                this.organization = organization;
        }
    }

