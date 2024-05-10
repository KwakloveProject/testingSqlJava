create table subject
(
    no number PRIMARY KEY,
    s_num varchar2(2) UNIQUE not null,
    s_name varchar2(24) not null
    
);

commit;

create sequence subject_seq
START WITH 1
INCREMENT BY 1;

insert into subject(no, s_num , s_name) VALUES(subject_seq.nextval,01,' 컴퓨터학과');
insert into subject(no, s_num , s_name) VALUES(subject_seq.nextval,02,' 교육학과');
insert into subject(no, s_num , s_name) VALUES(subject_seq.nextval,03,' 신문방송학과');
insert into subject(no, s_num , s_name) VALUES(subject_seq.nextval,04,'인터넷비즈니스과');
insert into subject(no, s_num , s_name) VALUES(subject_seq.nextval,05,' 기술경영과');
SELECT * from subject;
commit;
create table student
(
no number PRIMARY KEY,
sd_num varchar2(8) UNIQUE not null,
sd_name varchar2(12) not null,
sd_id varchar2(12) UNIQUE not null,
sd_password varchar2(12)not null,
sd_birthday varchar2(8)not null,
sa_phone varchar2(15)not null,
sd_address varchar2(80)not null,
sd_email varchar2(40)not null,
sd_date date

);
ALTER TABLE student
ADD CONSTRAINT student_S_NUM_FK
FOREIGN KEY(S_NUM) REFERENCES subject(S_NUM); 
ALTER TABLE student ADD S_NUM VARCHAR(2);
ALTER TABLE student RENAME COLUMN sa_phone TO sd_phone;
commit;








create sequence student_seq
START WITH 1
INCREMENT BY 1;



insert into student(no,sd_num,sd_name, sd_id,sd_password,sd_birthday,sd_phone,sd_address,sd_email,sd_date,s_num ) values(student_seq.nextval,'21010001','김정수','Javajsp1234','java','050505','010-5555-1111','서울시 서대문구 창전동','tommythesuper@gmail.com',sysdate,01);
insert into student(no,sd_num,sd_name, sd_id,sd_password,sd_birthday,sd_phone,sd_address,sd_email,sd_date,s_num ) values(student_seq.nextval,'21010002','김수현','jdbcmania','jdbc','050506','010-2222-3333','서울시 서초구 양재동','kwakthelegend@gmail.com',sysdate,02);
insert into student(no,sd_num,sd_name, sd_id,sd_password,sd_birthday,sd_phone,sd_address,sd_email,sd_date,s_num ) values(student_seq.nextval,'21010003','공지영','gonji1234','gonji','050507','010-3333-4444','부산시 해운대구 반송동','tommy@gmail.com',sysdate,03);
insert into student(no,sd_num,sd_name, sd_id,sd_password,sd_birthday,sd_phone,sd_address,sd_email,sd_date,s_num ) values(student_seq.nextval,'21010004','조수영','water1234','water','050508','010-4444-5555','대전시 중구 은행동','kwak@gmail.com',sysdate,04);
insert into student(no,sd_num,sd_name, sd_id,sd_password,sd_birthday,sd_phone,sd_address,sd_email,sd_date,s_num ) values(student_seq.nextval,'21010005','최경란','novel1234','novel','050509','010-5555-6666','경기도 수원시 장안구 이목동','tommythe@gmail.com',sysdate,05);
insert into student(no,sd_num,sd_name, sd_id,sd_password,sd_birthday,sd_phone,sd_address,sd_email,sd_date,s_num ) values(student_seq.nextval,'21010006','안익태','korea1234','korea','050510','010-6666-7777','서울시 성동구 왕십리로','kwakthe@gmail.com',sysdate,01);
rollback;
SELECT * from student;

commit;

create table lesson
(
    no number not null,
    L_abbre varchar2(2) not null,
    L_name varchar2(20) not null,
    primary key(no),
    unique(L_abbre)
);

create SEQUENCE lesson_seq
start with 1 
INCREMENT by 1;

insert into lesson(no, L_abbre , L_name) VALUES(lesson_seq.nextval,'K','국어');
insert into lesson(no, L_abbre , L_name) VALUES(lesson_seq.nextval,'M','수학');
insert into lesson(no, L_abbre , L_name) VALUES(lesson_seq.nextval,'E','영어');
insert into lesson(no, L_abbre , L_name) VALUES(lesson_seq.nextval,'H','역사');
insert into lesson(no, L_abbre , L_name) VALUES(lesson_seq.nextval,'P','프로그래밍');
insert into lesson(no, L_abbre , L_name) VALUES(lesson_seq.nextval,'D','데이터베이스');
insert into lesson(no, L_abbre , L_name) VALUES(lesson_seq.nextval,'ED','교육학이론');
SELECT * from lesson;


create table trainee
(
    no number not null,
    sd_num varchar2(8) not null,
    L_abbre varchar2(2) not null,
    t_section varchar2(20) not null,
    t_date date default sysdate,
    primary key(no),
    FOREIGN KEY(sd_num) REFERENCES student(sd_num),
    FOREIGN KEY(L_abbre) REFERENCES lesson(L_abbre)
);

create SEQUENCE trainee_seq
start with 1 
INCREMENT by 1;

insert into trainee(no,sd_num, L_abbre,) VALUES(lesson_seq.nextval,'K','국어');
