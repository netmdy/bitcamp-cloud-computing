/* 주제 : 클라이언트에게 출력하기 - url 알아내기
*/

// 1) 모듈 로딩
const  http = require('http')

const server = http.createServer(function (req, res) {
    console.log('요청 받음 ㅇㅅㅇ')
// 출력하는 콘텐트 타입을 설정하면 한글이 깨지지 않는다.
// 응답 해더로 Content-Type을 지정하자
    res.writeHead(200,{'Content-Type': 'text/plain;charset=UTF-8'});

    // 클라이언트가 요청한 url 보기
    // http://localhost:8000/aaa/bbb/ccc?name=yong&age=2
    res.write(req.url);// /aaa/bbb/ccc?name=yong&age=2
    res.end(); // end()에서 꼭 출력할 필요는 없다
})

// 3) HTTP 서버 실행
// listen(포트번호, [서버가 시작된 후 호출될 함수]);
server.listen(8000, () => {
    console.log('서버 시작 ㅇㅇ')
})

