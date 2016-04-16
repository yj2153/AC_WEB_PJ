drop table hanbit_account;

--ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
CREATE TABLE hanbit_ac
(
	ac_id                 NUMBER(30) primary key ,
	ac_bankname           VARCHAR2(20)  NOT NULL ,
	ac_balance            NUMBER(20)  NOT NULL 
);
select * from hanbit_ac;
insert into hanbit_ac values(1,'ÇÑºûÀºÇà',100000);
commit;
--ï¿½Å·ï¿½ ï¿½ï¿½ï¿½ï¿½
CREATE TABLE hanbit_ac_type
(
	type_id               NUMBER  primary key ,
	type_name             varchar2(20)  NOT NULL 
);
delete hanbit_ac_type
insert into hanbit_ac_type values(1,'ÀÔ±Ý');
insert into hanbit_ac_type values(2,'Ãâ±Ý');

--ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ìºï¿½
CREATE TABLE hanbit_ac_category
(
	category_id           NUMBER  primary key ,
	category_name         varchar2(20)  NOT NULL 
);
delete hanbit_ac_category
insert into hanbit_ac_category values(seq_ac_category.NEXTVAL,'±âÀÚÀç');
insert into hanbit_ac_category values(seq_ac_category.NEXTVAL,'ÀâÈ­');
insert into hanbit_ac_category values(seq_ac_category.NEXTVAL,'µµ¼­');
--ï¿½Å·ï¿½ï¿½ï¿½ï¿½ï¿½
CREATE TABLE hanbit_ac_deal
(
	deal_id               NUMBER  primary key ,
	deal_note             varchar2(20)  NULL ,
	deal_sum              NUMBER(20)  NOT NULL ,
	deal_date             DATE  NOT NULL ,
	ac_id                 NUMBER(30)  NOT NULL ,
	category_id           NUMBER  NOT NULL ,
	type_id               NUMBER  NOT NULL 
);

insert into hanbit_ac_deal values(1,'¾ÆÀÌÅÛ1',100,sysdate, 1,1,1);
insert into hanbit_ac_deal values(2,'¾ÆÀÌÅÛ2',200,sysdate, 1,2,1);
insert into hanbit_ac_deal values(3,'¾ÆÀÌÅÛ3',300,sysdate, 1,3,2);
insert into hanbit_ac_deal values(4,'¾ÆÀÌÅÛ4',400,sysdate, 1,1,2);
insert into hanbit_ac_deal values(5,'¾ÆÀÌÅÛ5',500,sysdate, 1,1,1);

delete hanbit_ac_deal
select * from hanbit_ac_deal;

select d.deal_note, d.deal_sum, d.deal_date, c.category_Name, t.type_Name
from	hanbit_ac_deal d, hanbit_ac_type t, hanbit_ac_category c
where d.type_id = t.type_id and d.category_id = c.category_id and rownum between 0 and 10
order by deal_date desc

	update hanbit_ac_deal
		set deal_note = 'chang1',
			deal_sum = 1234567,
			deal_date = sysdate,
			category_id = (select category_id 
									from hanbit_ac_category 
									where category_name = 'ÀâÈ­'),
			type_id = (select type_id 
									from hanbit_ac_type
									where type_name = 'ÀÔ±Ý')
		where deal_id = 14;
		

		select * from hanbit_ac_deal;
create sequence seq_ac_deal;
create sequence seq_ac_category;

--ï¿½ï¿½ï¿½ï¿½
CREATE TABLE hanbit_emp_position
(
	position_id           NUMBER  primary key ,
	position_name         varchar2(20)  NOT NULL 
);

select * from hanbit_emp_position
select * from hanbit_emp
drop table hanbit_emp

alter table hanbit_emp add constraint fk_emp foreign key(position_id) references
hanbit_emp_position(position_id)


--ï¿½ï¿½ï¿½ï¿½
CREATE TABLE hanbit_emp
(
	emp_id                NUMBER  primary key ,
	emp_password		  number(20) not null,
	emp_name              varchar2(10)  NOT NULL ,
	emp_birth			  DATE  NOT NULL,
	emp_hiredate          DATE  NOT NULL ,
	emp_leavedate         DATE  NULL ,
	emp_email             varchar2(20)  NOT NULL ,
	emp_phone             NUMBER  NOT NULL ,
	emp_img               varchar2(30)  NOT NULL ,
	position_id           NUMBER  NOT NULL 
);

--ï¿½ï¿½ï¿½Þ³ï¿½ï¿½ï¿½
CREATE TABLE hanbit_sal
(
	sal_id                NUMBER  primary key ,
	sal_sum               NUMBER(20)  NOT NULL ,
	sal_date              DATE  NOT NULL ,
	ac_id                 NUMBER(30)  NOT NULL ,
	emp_id                NUMBER  NOT NULL 
);


















