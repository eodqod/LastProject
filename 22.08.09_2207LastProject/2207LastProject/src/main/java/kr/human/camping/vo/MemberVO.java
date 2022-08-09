package kr.human.camping.vo;

import lombok.Data;

/*
 	id varchar2(100) PRIMARY KEY,
	idx NUMBER,
	password varchar2(100) NOT NULL,
	name varchar2(100) NOT NULL,
	phone NUMBER(11) NOT NULL,
	email varchar2(100) NOT NULL,
	gender char(1) check(gender IN('0','1'))NOT NULL,
 */

@Data
public class MemberVO {

	private String id;
	private long idx;
	private String password;
	private String name;
	private String phone;
	private String email;
	private short gender;
	private String role;
	
}
