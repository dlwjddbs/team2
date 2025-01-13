package com.itwillbs.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.MemberDTO;
import com.itwillbs.entity.Member;
import com.itwillbs.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class MemberController {

    private final MemberService memberService;
    
    @Value("${file.upload-dir}")
    private String uploadDir;  // 이미지 저장 경로

    @GetMapping("/mypage")
    public String info(@AuthenticationPrincipal User user, Model model) {

        if (user == null) {
            return "redirect:/login"; 
        }

        String id = user.getUsername();
        
        Optional<Member> member = memberService.findById(id);

        if (member.isPresent()) {
            Member m = member.get(); 
            model.addAttribute("member", m);

            // 자격증과 교육 데이터를 쉼표로 분리하여 리스트로 변환함
            List<String> certificateList = m.getCertificate() != null ? Arrays.asList(m.getCertificate().split(",")) : new ArrayList<>();
            List<String> educationList = m.getEducation() != null ? Arrays.asList(m.getEducation().split(",")) : new ArrayList<>();

            model.addAttribute("certificateList", certificateList);
            model.addAttribute("educationList", educationList);

            // 입사일만 추출 (시간 제외)
//            LocalDate joinDateWithoutTime = m.getJoinDateWithoutTime();
//            model.addAttribute("joinDate", joinDateWithoutTime);  
        } else {
            return "redirect:/login";  
        }

        return "/mypage";  
    }



    @PostMapping("/mypage")
    public String updateMemberInfo(@AuthenticationPrincipal User user,
                                   @ModelAttribute MemberDTO memberDTO) {
        try {
        	 String id = user.getUsername();
        	 
            memberDTO.setId(id);

            // 프로필 이미지 처리
            MultipartFile profilePicFile = memberDTO.getProfilePicFile();
            if (profilePicFile != null && !profilePicFile.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + profilePicFile.getOriginalFilename();
                Path uploadPath = Paths.get("./uploads");
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath); // 업로드 폴더 생성
                }
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(profilePicFile.getInputStream(), filePath);

                memberDTO.setProfilePic(fileName); // 파일 이름 설정
            }

            // 회원 정보 업데이트
            memberService.updateMember(memberDTO);

            return "redirect:/mypage"; 
            
        } catch (IllegalStateException e) {
        	
            log.warning("로그인이 필요합니다.");
            return "redirect:/login";
            
        } catch (IOException e) {
            log.severe("파일 업로드 실패: " + e.getMessage());
            return "redirect:/mypage?error=uploadFailed";
        } catch (IllegalArgumentException e) {
            log.warning(e.getMessage());
            return "redirect:/mypage?error=updateFailed"; 
        }
    }
    
    @PostMapping("/mypage/education")
    public String updateEducation(@RequestParam("certificate_merged") String certificates,
                                                 @RequestParam("education_merged") String education,
                                                 @AuthenticationPrincipal User user,
                                                 RedirectAttributes redirectAttributes) {
        try {
        	String id = user.getUsername();
        	
            if (id == null) {
                redirectAttributes.addFlashAttribute("error", "로그인이 필요합니다.");
                return "redirect:/login";
            }

            // 자격증 및 교육 정보 업데이트
            memberService.updateEducation(id, certificates, education);

            redirectAttributes.addFlashAttribute("success", "자격증 및 교육 정보가 성공적으로 업데이트되었습니다.");
            return "redirect:/mypage?tab=edu";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "업데이트 실패: " + e.getMessage());
            return "redirect:/mypage";
        }
    }
}
