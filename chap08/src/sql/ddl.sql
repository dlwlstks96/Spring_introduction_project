--mysql DB에 spring5 계정 생성 (암호로 spring5 사용)
create user 'spring5'@'localhost' identified by 'spring5';

--spring5fs DB 생성
create database spring5s character set = utf8;

--spring5fs DB에 spring5 계정이 접근할 수 있도록 권한 부여
grant all privileges on spring5s.* to 'spring5'@'localhost';

--spring5fs DB에 MEMBER 테이블 생성
create table spring5s.MEMBER (
    ID int auto_increment primary key,
    EMAIL varchar(255),
    PASSWORD varchar(100),
    NAME varchar(100),
    REGDATE datetime,
    unique key (EMAIL)
) engine=InnoDB character set = utf8;
