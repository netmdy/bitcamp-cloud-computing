// 주제 : HTTP 서버 만들기 - 클라이언트에게 응답완료하기

// 1) 모듈 로딩
const  http = require('http')

const server = http.createServer(function (req, res) {
    console.log('요청 받음 ㅇㅅㅇ')

    // HTTP 응답을 완료해서 웨 브라우저의 기다림이 멈춤다.
        res.end();
});
// 3) HTTP 서버 실행
// listen(포트번호, [서버가 시작된 후 호출될 함수]);
server.listen(8000, () => {
    console.log('서버 시작 ㅇㅇ')
})