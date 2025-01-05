package com.itwillbs.entity;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.itwillbs.domain.MemberDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "member")	
@Getter
@Setter
@ToString
public class Member {
	
	@Id
	@Column(name = "member_id", length = 10, nullable = false, updatable = false)
	private String id;
	
	@Column(name = "name", length = 100)
	private String name;
	
	@Column(name = "resident_regist_num", length = 20)
	private String residentRegistNum;
	
	@Column(name = "address_num", length = 10)
	private String addressNum;
	
	@Column(name = "address1", length = 200)
	private String address1;	
	
	@Column(name = "address2", length = 200)
	private String address2;
	
	@Column(name = "email", length = 100)
	private String email;
	
	@Column(name = "profile_pic", length = 200)
	private String profilePic;
	
	@Column(name = "join_date")
	private String joinDate;	
	
	@Column(name = "resign_date")
	private String resignDate;		
	
	@Column(name = "passwd", length = 30)
	private String passwd;	
	
	@Column(name = "use_yn", length = 1)
	private String useYn;
	
	@Column(name = "create_date")
	private Timestamp createDate;	
	
	@Column(name = "update_date")
	private Timestamp updateDate;	
	
	@Column(name = "salary")
	private Integer salary;
	
	@Column(name = "bank_code")
	private Integer bankCode;
	
	@Column(name = "account", length = 20)
	private String account;
	
	@Column(name = "account_holder", length = 20)
	private String accountHolder;
	
	@Column(name = "certificate", length = 300)
	private String certificate;
	
	@Column(name = "education", length = 300)
	private String education;
	
	@Column(name = "dept_id", length = 20)
	private String deptId;
	
	@Column(name = "grade_id", length = 20)
	private String gradeId;
	
	@Column(name = "authority", length = 20)
	private String authority;
	
//	@Column(name = "update_date")
//	private Timestamp updateDate;
	
//	public LocalDate getJoinDateWithoutTime() {
//        // Timestamp에서 LocalDate로 변환
//        return joinDate.toLocalDateTime().toLocalDate();
//    }
		
	public static Member setMemberEntity(MemberDTO memberDTO) {
		
		Member member = new Member();
		
		member.setId(memberDTO.getId());
		member.setName(memberDTO.getName());
		member.setResidentRegistNum(memberDTO.getResidentRegistNum());
		member.setAddressNum(memberDTO.getAddressNum());
		member.setAddress1(memberDTO.getAddress1());
		member.setAddress2(memberDTO.getAddress2());
		member.setEmail(memberDTO.getEmail());
		member.setProfilePic(memberDTO.getProfilePic());
		member.setJoinDate(memberDTO.getJoinDate());
		member.setResignDate(memberDTO.getResignDate());
		member.setPasswd(memberDTO.getPasswd());
		member.setUseYn(memberDTO.getUseYn());
		member.setCreateDate(memberDTO.getCreateDate());
		member.setCertificate(memberDTO.getCertificate());
		member.setEducation(memberDTO.getEducation());
//		member.setUpdateDate(memberDTO.getUpdateDate());
		
		return member;
	}
	
}