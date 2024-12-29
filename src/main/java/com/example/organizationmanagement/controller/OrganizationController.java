package com.example.organizationmanagement.controller;

import com.example.organizationmanagement.model.Member;
import com.example.organizationmanagement.model.Organization;
import com.example.organizationmanagement.model.Team;
import com.example.organizationmanagement.repository.MemberRepository;
import com.example.organizationmanagement.repository.OrganizationRepository;
import com.example.organizationmanagement.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class OrganizationController {

    private static String UPLOAD_DIR="uploads/";

    @Autowired
    private OrganizationRepository organizationRepository;
    private TeamRepository teamRepository;
    private MemberRepository memberRepository;

    @GetMapping("/")
    public String index(Model model){
        List<Organization> organizations=organizationRepository.findAll();
        model.addAttribute("organizations",organizations);
        return "index";
    }

    @PostMapping("/organizations")
    public String addOrganization(@RequestParam String name, @RequestParam String email,@RequestParam String location){
        Organization organization=new Organization();
        organization.setName(name);
        organization.setEmail(email);
        organization.setLocation(location);
        organizationRepository.save(organization);
        return "redirect:/";
    }

    @PostMapping("/teams")
    public String addTeam(@RequestParam Long orgId , @RequestParam String name){
        Organization organization=organizationRepository.findById(orgId).orElseThrow();
        Team team=new Team();
        team.setName(name);
        team.setOrganization(organization);
        teamRepository.save(team);
        return "redirect:/";
    }

    @PostMapping("/members")
    public String addMember(@RequestParam Long teamId , @RequestParam String name, @RequestParam String uniqueId, @RequestParam("image")MultipartFile image){
        Team team=teamRepository.findById(teamId).orElseThrow();
        Member member=new Member();
        member.setName(name);
        member.setUniqueId(uniqueId);
        member.setTeam(team);

        if(!image.isEmpty()){
            try{
                byte[] bytes=image.getBytes();
                Path path= Paths.get(UPLOAD_DIR+ image.getOriginalFilename());
                Files.write(path,bytes);
                member.setImagePath(path.toString());
                member.setImageUploaded(true);
            } catch (IOException e) {
               e.printStackTrace();
            }
        }else{
            member.setImageUploaded(false);
        }

        memberRepository.save(member);
        return"redirect:/";
    }

    // Rest API endpoint to fetch all organizations as JSON
    @GetMapping("/organizations/api")
    @ResponseBody
    public List<Organization>getAllOrganizationAsJson(){
        return organizationRepository.findAll();
    }

    // Rest API endpoint to fetch all teams as JSON
    @GetMapping("/teams/api")
    @ResponseBody
    public List<Team>getAllTeamAsJson(){
        return teamRepository.findAll();
    }

    // Rest API endpoint to fetch all members as JSON
    @GetMapping("/members/api")
    @ResponseBody
    public List<Member>getAllMembersAsJson(){
        return memberRepository.findAll();
    }
}
