// 주제 : HTTP 서버 만들기 - 서버 실행하기

// 1) 모듈 로딩
const  http = require('http')

// 2) HTTP 서버 객체 생성
const server = http.createServer(function (req, res) {
    console.log('요청 받음 ㅇㅅㅇ')
//웹 브라우저의 요청을 받았지만 응답을 하지 않아서
// 웹 브라우저는 계속 응답을 기다리는 상태에 있을것이다
});

// 3) HTTP 서버 실행
// listen(포트번호, [서버가 시작된 후 호출될 함수]);
server.listen(8000, () => {
    console.log('서버 시작 ㅇㅇ')
})

