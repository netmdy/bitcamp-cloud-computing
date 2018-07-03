# 프로그래밍 준비
## 개발 도구 설치
- openjdk 10.0.1
- eclipse photon
- visual studio code
- git client
- scoop (package manager)
- scoop install gradle
- scoop install mariadb
- scoop install mysql

## mysql 설정

- mysql -uroot -p

- root 암호 설정

update user set authentication_string=password('1111') where user='root';

암호 변경 후 권한 갱신
flush privileges;
quit

다시 접속

- mysql -uroot -p
- password : 1111

사용자 추가

create user 'study'@'localhost' identified by '1111';

study 사용자가 사용할 데이터베이스 생성

create database studydb character set utf8 collate utf8_general_ci;


studydb 데이터베이스의 사용권한을 study사용자에서 권한 부여
grant all privileges on studydb.* to 'study'@'localhost';

study 사용자가 사용할 수 있는 데이터베이스 목록 보기
show databases;

사용할 디비 설정
use studydb;

pms-ddl.sql 속 table 쿼리를 복사하여 붙여넣기

## 웹 프로젝트 폴더 준비

예제 프로젝트 복사
java106-java-project를 bitcamp-cloud-computing 폴더로 복사 후 
pms-project로 이름 바꾸기