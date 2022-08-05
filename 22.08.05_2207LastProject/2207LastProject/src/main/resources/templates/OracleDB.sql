-- 회원 및 관리자 계정 테이블
CREATE SEQUENCE member_idx_seq;
CREATE TABLE MEMBER(
	id varchar2(100) PRIMARY KEY,
	idx NUMBER,
	password varchar2(100) NOT NULL,
	name varchar2(100) NOT NULL,
	phone NUMBER(11) NOT NULL,
	email varchar2(100) NOT NULL,
	gender char(1) check(gender IN('0','1'))NOT NULL,
	col1 varchar2(100),
	col2 NUMBER,
	col3 varchar2(100)
);
INSERT INTO JSPUSER."MEMBER"
(ID, IDX, PASSWORD, NAME, PHONE, EMAIL, GENDER, COL1, COL2, COL3)
VALUES('user01', member_idx_seq.nextval, '1234', '한사람', 01053244561, 'ㅁㄴㅇㄹ@asdf.com', '1', '', 0, '');
INSERT INTO JSPUSER."MEMBER"
(ID, IDX, PASSWORD, NAME, PHONE, EMAIL, GENDER, COL1, COL2, COL3)
VALUES('user02', member_idx_seq.nextval, '1234', '두사람', 12434532663, 'ㅁㄴㅇㄹ@asdf.com', '1', '', 0, '');
INSERT INTO JSPUSER."MEMBER"
(ID, IDX, PASSWORD, NAME, PHONE, EMAIL, GENDER, COL1, COL2, COL3)
VALUES('user03', member_idx_seq.nextval, '1234', '세사람', 12434532663, 'ㅁㄴㅇㄹ@asdf.com', '1', '', 0, '');

select m.name, m.id, m.phone, m.email, m.gender, member_role.role from MEMBER m full outer join member_role on m.id=member_role.id and role='user' order by m.idx DESC;

-- 회원 및 관리자 계정 권한 나누는 테이블
CREATE SEQUENCE member_role_idx_seq;
CREATE TABLE member_role(
	idx NUMBER PRIMARY KEY,
	id varchar2(100) NOT NULL,			-- 회원 및 관리자 계정 테이블의 id와 연결
	ROLE varchar2(100) NOT NULL,
	col1 varchar2(100),
	col2 NUMBER
);
INSERT INTO JSPUSER.MEMBER_ROLE
(IDX, ID, "ROLE", COL1, COL2)
VALUES(member_role_idx_seq.nextval, 'user01', 'user', '', 0);
INSERT INTO JSPUSER.MEMBER_ROLE
(IDX, ID, "ROLE", COL1, COL2)
VALUES(member_role_idx_seq.nextval, 'user02', 'user', '', 0);
INSERT INTO JSPUSER.MEMBER_ROLE
(IDX, ID, "ROLE", COL1, COL2)
VALUES(member_role_idx_seq.nextval, 'user03', 'dormancy', '', 0);

SELECT * FROM member;

-- 업체 테이블

CREATE SEQUENCE company_idx_seq;
DROP SEQUENCE company_idx_seq;
DROP TABLE COMPANY;
CREATE table company(
	idx NUMBER PRIMARY KEY,
	name varchar2(100) NOT null,
	add1 varchar2(100) NOT NULL,
	add2 varchar2(100) NOT NULL,
	postcode number(20) NOT NULL,
	eco varchar2(100) NOT NULL,
	roomtype varchar2(100) NOT NULL,
	theme varchar2(100) NOT NULL,
	areacode number(10) NOT NULL,		-- 전국지도 
	detailcode number(10) NOT NULL,     -- 세부지역  
	Latitude float(30) NOT NULL,
	logitude float(30) NOT NULL,
	col1 varchar2(100),
	col2 number
);

-- 업체 방 테이블

CREATE SEQUENCE company_room_roomidx_seq;
DROP TABLE COMPANY_ROOM;

CREATE TABLE company_room(
	roomidx number PRIMARY KEY,
	idx NUMBER NOT NULL,				-- 업체 테이블의 idx와 연결
	roomname varchar2(100) NOT NULL,
	minpeople number(2) NOT NULL,
	maxpeople number(2) NOT NULL,
	price number(10) NOT NULL,
	r_check char(1) check(r_check IN('0','1'))NOT NULL,
	content varchar2(4000) NOT NULL,
	col1 varchar2(100),
	col2 number
);

-- 방 예약 테이블
DROP TABLE reservation;
CREATE TABLE reservation(
	id varchar2(100),					-- 회원 및 관리자 계정 테이블의 id와 연결
	roomidx number NOT null,			-- 업체방 테이블의 roomidx와 연결
	email varchar2(100) NOT NULL,
	col1 varchar2(100),
	col2 NUMBER
);

-- 광고 테이블
CREATE SEQUENCE AD_idx_seq;
CREATE TABLE AD(
	idx NUMBER PRIMARY KEY,
	id varchar2(100) NOT NULL,			-- 회원 및 관리자 계정 테이블의 id와 연결
	subject varchar2(500) NOT NULL,
	content varchar2(4000) NOT NULL,
	regDate timestamp DEFAULT sysdate,
	col1 varchar2(100),
	col2 NUMBER
);

-- 게시판 테이블
CREATE SEQUENCE fileBoard_idx_seq;
CREATE TABLE fileBoard(
 	idx NUMBER PRIMARY KEY,
	id varchar2(100) NOT NULL, 			-- 회원 및 관리자 계정 테이블의 id와 연결
	subject varchar2(500) NOT NULL,
	content varchar2(4000) NOT NULL,
	regDate timestamp DEFAULT sysdate,
	clickCount NUMBER(20) DEFAULT 0
);

DROP TABLE fileboard;
DROP SEQUENCE fileBoard_idx_seq;

INSERT INTO JSPUSER.FILEBOARD
(IDX, ID, SUBJECT, CONTENT, REGDATE, CLICKCOUNT)
VALUES(fileBoard_idx_seq.nextval, 'admin', 'testsubject', 'testcontent', sysdate, 0
);
INSERT INTO JSPUSER.FILEBOARD
(IDX, ID, SUBJECT, CONTENT, REGDATE, CLICKCOUNT)
VALUES(fileBoard_idx_seq.nextval, 'admin', 'testsubject2', 'testcontent2', sysdate, 0
);
INSERT INTO JSPUSER.FILEBOARD
(IDX, ID, SUBJECT, CONTENT, REGDATE, CLICKCOUNT)
VALUES(fileBoard_idx_seq.nextval, 'admin', 'testsubject3', 'testcontent3', sysdate, 0
);


COMMIT;

SELECT * FROM TAB;
DROP SEQUENCE member_idx_seq;
DROP TABLE reservation ;
SELECT * FROM company;

