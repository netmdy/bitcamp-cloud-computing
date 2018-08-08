/* 주제 : 클라이언트에게 출력하기 */

// 1) 모듈 로딩
const  http = require('http')

const server = http.createServer(function (req, res) {
    console.log('요청 받음 ㅇㅅㅇ')
// 출력하는 데이터의 콘텐트 타입을 웹 브라우저에게 알려주지 않아서
// 한글이 깨진다.
        res.end('안녕 ~')
})

// 3) HTTP 서버 실행
// listen(포트번호, [서버가 시작된 후 호출될 함수]);
server.listen(8000, () => {
    console.log('서버 시작 ㅇㅇ')
})

