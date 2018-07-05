## bitcamp-web-01 : 웹 프로젝트 폴더 준비
- gradle을 이용하여 프로젝트 폴더를 만드는 방법

## 1) 프로젝트 폴더 생성

## 2) Maven 기본 디렉토리 구조를 생성
- 자바 애플리케이션 프로젝트를 위한 기본 폴더 및 예제 파일을 생성

> gradle init --type java-application

- 웹 관련 폴더 추가

    - src/main 폴더에 resources 폴더를 추가 하기
    - src/main 폴더에 webapp 폴더를 추가 하기

## 3) 이클립스  IDE 관련 설정 파일을 추가

    build.gradle 설정 파일에 eclipse 플러그인 추가

        plugins {
        // 자바 관련 빌드 명령 플러그인 추가
        id 'java'

        // 이클립스 관련  빌드 명령 플러그인 추가
        id 'eclipse-wtp' //= eclipse + wtp
        
        // web archive 관련 빌드 명령을 추가해야 한다
        id 'war'
    }

    이클립스 설정 파일 만들기 
    > gradle eclipse

## 4) 이클립스에서 프로젝트를 import

## 5) 웹 애플리케이션을 테스트할 톰캣 실행 환경 추가

## 6) 웹 애플리케이션 
