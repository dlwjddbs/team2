/*
 * package com.itwillbs.service;
 * 
 * import java.util.Optional;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.crypto.password.PasswordEncoder; import
 * org.springframework.stereotype.Service;
 * 
 * import com.itwillbs.domain.MemberDTO; import
 * com.itwillbs.domain.PasswordChangeRequest; import com.itwillbs.entity.Member;
 * import com.itwillbs.repository.MemberRepository;
 * 
 * import jakarta.transaction.Transactional; import
 * lombok.RequiredArgsConstructor;
 * 
 * @Service
 * 
 * @RequiredArgsConstructor public class MemberService {
 * 
 * @Autowired private final MemberRepository memberRepository; private final
 * PasswordEncoder passwordEncoder;
 * 
 * public Optional<Member> findById(String id) { return
 * memberRepository.findById(id); }
 * 
 * 
 * public Member findByIdAndPasswd(MemberDTO memberDTO) { return
 * memberRepository.findByIdAndPasswd(memberDTO.getId(), memberDTO.getPasswd())
 * .orElseThrow(() -> new IllegalArgumentException("Invalid credentials")); }
 * 
 * @Transactional public void updateMember(MemberDTO memberDTO) { if
 * (memberDTO.getId() == null) { throw new
 * IllegalArgumentException("ID가 없습니다."); }
 * 
 * Member member = memberRepository.findById(memberDTO.getId()) .orElseThrow(()
 * -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다. ID: " +
 * memberDTO.getId()));
 * 
 * Optional.ofNullable(memberDTO.getName()).ifPresent(member::setName);
 * Optional.ofNullable(memberDTO.getResidentRegistNum()).ifPresent(member::
 * setResidentRegistNum);
 * Optional.ofNullable(memberDTO.getAddressNum()).ifPresent(member::
 * setAddressNum);
 * Optional.ofNullable(memberDTO.getAddress1()).ifPresent(member::setAddress1);
 * Optional.ofNullable(memberDTO.getAddress2()).ifPresent(member::setAddress2);
 * Optional.ofNullable(memberDTO.getEmail()).ifPresent(member::setEmail);
 * Optional.ofNullable(memberDTO.getProfilePic()).ifPresent(member::
 * setProfilePic);
 * Optional.ofNullable(memberDTO.getPasswd()).ifPresent(member::setPasswd);
 * Optional.ofNullable(memberDTO.getUseYn()).ifPresent(member::setUseYn);
 * Optional.ofNullable(memberDTO.getCertificate()).ifPresent(member::
 * setCertificate);
 * Optional.ofNullable(memberDTO.getEducation()).ifPresent(member::setEducation)
 * ;
 * 
 * memberRepository.save(member); }
 * 
 * @Transactional public void updateEducation(String memberId, String
 * certificate, String education) { Member member =
 * memberRepository.findById(memberId) .orElseThrow(() -> new
 * IllegalArgumentException("회원 정보를 찾을 수 없습니다. ID: " + memberId));
 * 
 * member.setCertificate(certificate); member.setEducation(education);
 * 
 * memberRepository.save(member);
 * 
 * }
 * 
 * // 비밀번호 변경 public void changePassword(String memberId, PasswordChangeRequest
 * request) { // 현재 비밀번호 확인 System.out.println("비밀번호 변경 요청 - memberId: " +
 * memberId);
 * 
 * String currentPasswordHash = memberRepository.findPasswdById(memberId)
 * .orElseThrow(() -> { System.out.println("사용자를 찾을 수 없습니다. memberId: " +
 * memberId); return new IllegalArgumentException("사용자를 찾을 수 없습니다."); });
 * 
 * System.out.println("현재 비밀번호: " + currentPasswordHash);
 * 
 * if (!passwordEncoder.matches(request.getCurrentPassword(),
 * currentPasswordHash)) { System.out.println("현재 비밀번호가 일치하지 않습니다."); throw new
 * IllegalArgumentException("현재 비밀번호가 올바르지 않습니다."); }
 * 
 * // 새 비밀번호 암호화 및 저장 String newPasswordHash =
 * passwordEncoder.encode(request.getNewPassword());
 * System.out.println("새 비밀번호 (암호화): " + newPasswordHash);
 * 
 * try { memberRepository.updatePassword(memberId, newPasswordHash);
 * System.out.println("비밀번호가 성공적으로 변경되었습니다."); } catch (Exception e) {
 * System.out.println("비밀번호 변경 중 에러 발생: " + e.getMessage()); throw new
 * RuntimeException("비밀번호 변경에 실패했습니다.", e); } }
 * 
 * }
 * 
 */

package com.itwillbs.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.repository.MemberMapper;

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;

    // ID로 회원 정보 조회
    public Map<String, Object> findById(String id) {
        return memberMapper.findById(id);
    }

    // 회원 정보 업데이트
    public void updateMember(Map<String, Object> memberData) {
        memberMapper.updateMember(memberData);
    }

    // 자격증 및 교육 정보 업데이트
    public void updateEducation(Map<String, Object> educationData) {
        memberMapper.updateEducation(educationData); 
    }

    // 비밀번호 변경
    public void changePassword(String id, Map<String, String> request) {
        String currentPassword = request.get("currentPassword");
        String newPassword = request.get("newPassword");

        memberMapper.updatePassword(id, newPassword);
    }

	public List<Map<String, Object>> getAllBankNames() {
		return memberMapper.getAllBankNames();
	}
}
