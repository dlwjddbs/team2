package com.itwillbs.domain;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString 
public class MemberDTO {

	private Integer id;
	private String name;
	private String residentRegistNum;
	private String addressNum;
	private String address1;	
	private String address2;
	private String email;
	private String profilePic;
	private MultipartFile profilePicFile;  
	private Timestamp joinDate;
	private Timestamp resignDate;
	private String passwd;	
	private String useYn;
	private Timestamp createDate;
	private Timestamp updateDate;
	private Integer salary;
	private Integer bankCode;
	private String acount;
	private String acountHolder;
	private String certificate;
	private String education;
	private String deptId;
	private String gradeId;
	private String authority;
}
