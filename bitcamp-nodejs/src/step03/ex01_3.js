/* 주제 : HTTP 서버 만들기 - 요청을 처리하는 스레드
nodejs는 single thred로 동작한다
한 클라이언트의 요청을 처리할 때까지 다른 클라이언트는 기다려야한다
그래서  nodejs의 htttp 서버는 사간이 오래 걸리는
동시요청을 처리하기에 적합하지 않다.*/

// 1) 모듈 로딩
const  http = require('http')

const server = http.createServer(function (req, res) {
    console.log('요청 받음 ㅇㅅㅇ')

    setTimeout(() =>{
        res.end
    },10000)
})
    // HTTP 응답을 완료해서 웨 브라우저의 기다림이 멈춤다.

// 3) HTTP 서버 실행
// listen(포트번호, [서버가 시작된 후 호출될 함수]);
server.listen(8000, () => {
    console.log('서버 시작 ㅇㅇ')
})

