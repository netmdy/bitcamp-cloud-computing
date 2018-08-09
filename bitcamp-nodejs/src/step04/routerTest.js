// 주제 : express 사용하기 - routing

// express 모듈 로딩
const express = require('express')
// 웹서버 객체 준비
const app = express();

// "/member/* url을 처리할 라우터와 '/team/*' url을 처리할 라우터를 로딩
var memberRouter = require('./member')
var teamRouter = require('./team')

// 라우터를 express의 웹서버에 등록
app.use('/member', memberRouter)
app.use('/team', teamRouter)

// 서버 실행하기
app.listen(8000, () => {
    console.log('서버 실행중 ㅇㅇ')
});