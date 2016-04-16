===================테스트용=================================================
drop table student;
drop table usage;
drop table license;
drop table attendance;
drop table material;
drop table counsel;
CREATE unique INDEX stu_autoIndex 
ON  student(stu_id); 
SELECT * FROM USER_INDEXES where student;

insert into corse (cor_num,cor_name,pro_id,cor_startday,cor_endday) values('512','java/jsp','11','2016/03/22','2017/06/22');
===========================================================================
	
3/22일 추가 sql
create sequence seq_stuId
start with 1 increment by 1;

CREATE TABLE corse
(
	cor_num				VARCHAR2(20) NOT NULL,
	cor_name              VARCHAR2(20)  NOT NULL ,
	pro_id              number not NULL ,
	cor_startday             date not NULL ,
	cor_endday                date  NOT NULL,
	CONSTRAINT cor_pk PRIMARY KEY(cor_num),
	CONSTRAINT cor_fk FOREIGN KEY(pro_id)
	REFERENCES hanbit_emp(emp_id) ON DELETE CASCADE
);

==========================================
CONSTRAINT PK_USERS PRIMARY KEY(USERNO)

CONSTRAINT FK_USERS FOREIGN KEY(USERNO)
REFERENCE USERS_INFO(USERNO)


CONSTRAINT 제약명 FOREIGN KEY(deptno)
REFERENCES con_dept(deptno) ON DELETE CASCADE



CREATE TABLE attendance
(
	att_date              date  NOT NULL ,
	stu_name              VARCHAR2(20) not NULL ,
	att_check             VARCHAR2(20) not NULL ,
	stu_id                VARCHAR2(20)  NOT NULL,
	CONSTRAINT att_pk PRIMARY KEY(att_date,stu_id),
	CONSTRAINT att_ref FOREIGN KEY(stu_id)
	REFERENCES student(stu_id) ON DELETE CASCADE
	);


CREATE TABLE counsel
(
	cou_num               VARCHAR2(20)  NOT NULL ,
	stu_id                VARCHAR2(20) not NULL ,
	stu_name              VARCHAR2(20) not NULL ,
	pro_id                VARCHAR2(20) not NULL ,
	pro_name              VARCHAR2(20) not NULL ,
	cou_date              date not NULL ,
	cou_content           VARCHAR2(100) not NULL ,
	CONSTRAINT cou_pk PRIMARY KEY(cou_num)
);


CREATE TABLE license
(
	stu_name              VARCHAR2(20) not NULL ,
	lic_name              VARCHAR2(20) not NULL ,
	lic_num               VARCHAR2(30)  NOT NULL ,
	lic_getday            DATE  NULL ,
	stu_id                VARCHAR2(20)  NOT NULL ,
	CONSTRAINT lic_pk PRIMARY KEY(lic_num,stu_id),
	CONSTRAINT lic_ref FOREIGN KEY(stu_id)
	REFERENCES student(stu_id) ON DELETE CASCADE
);


CREATE TABLE material
(
	mat_id                VARCHAR2(20)  NOT NULL ,
	mat_name              VARCHAR2(20)  NULL ,
	mat_quantity          NUMBER  NULL ,
	mat_state             VARCHAR2(20)  NULL ,
	mat_price             NUMBER  NULL ,
	mat_savepoint         VARCHAR2(20)  NULL ,
	mat_indent            VARCHAR2(20)  NULL ,
	mat_procode           VARCHAR2(20)  NULL ,
	CONSTRAINT mat_pk PRIMARY KEY(mat_id)
);


CREATE TABLE student
(
	stu_id                VARCHAR2(20)  NOT NULL ,
	stu_name              VARCHAR2(20)  NULL ,
	stu_email             VARCHAR2(40)  NULL ,
	stu_phone             VARCHAR2(20)  NULL ,
	stu_addr1             VARCHAR2(20)  NULL ,
	stu_addr2             VARCHAR2(20)  NULL ,
	stu_birth             DATE  NULL ,
	stu_corse             VARCHAR2(20)  NULL ,
	stu_state             VARCHAR2(20)  NULL ,
	stu_startdate         DATE  NULL ,
	stu_enddate           DATE  NULL ,
	stu_professor         VARCHAR2(20)  NULL ,
	stu_iscard            VARCHAR2(10)  NULL ,
	CONSTRAINT stu_pk PRIMARY KEY(stu_id)	
);

CREATE TABLE usage
(
	user_id               VARCHAR2(20)  NULL ,
	user_name             VARCHAR2(20)  NULL ,
	mat_name              VARCHAR2(20)  NULL ,
	mat_quantity          NUMBER  NULL ,
	use_purpose           VARCHAR2(20)  NULL ,
	use_date              DATE  NULL ,
	use_state             VARCHAR2(20)  NULL ,
	mat_id                VARCHAR2(20)  NULL 
);