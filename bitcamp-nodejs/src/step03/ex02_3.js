/* 주제 : 클라이언트에게 출력하기 - 콘텐트 타입 설정하기
*/

// 1) 모듈 로딩
const  http = require('http')

const server = http.createServer(function (req, res) {
    console.log('요청 받음 ㅇㅅㅇ')
// 출력하는 콘텐트 타입을 설정하면 한글이 깨지지 않는다.
// 응답 해더로 Content-Type을 지정하자
    res.writeHead(200,{'Content-Type': 'text/html;charset=UTF-8'});
    res.write('<html>\n')
    res.write('<head>\n')
    res.write('<title>Node.js</title>\n')
    res.write('</head>\n')
    res.write('<body>\n')
    res.write('<h1>안녕 ㅇㅅㅇ</h1>\n')
    res.write('</body>\n')
    res.write('</html>\n')
    res.end(); // end()에서 꼭 출력할 필요는 없다
})

// 3) HTTP 서버 실행
// listen(포트번호, [서버가 시작된 후 호출될 함수]);
server.listen(8000, () => {
    console.log('서버 시작 ㅇㅇ')
})

