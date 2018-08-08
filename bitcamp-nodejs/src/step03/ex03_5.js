/* 주제 : 클라이언트에게 출력하기 - 계산기 만들기

100 + 200 : http://localhost:8000/calc?a=100&b=200&op=%2b
100 - 200 : http://localhost:8000/calc?a=100&b=200&op=-
100 * 200 : http://localhost:8000/calc?a=100&b=200&op=*
100 / 200 : http://localhost:8000/calc?a=100&b=200&op=/
출력 결과
300
*/

// 1) 모듈 로딩
const  http = require('http')

// url 분석에 사용할 모듈 로딩
const  url = require('url')

const server = http.createServer(function (req, res) {
    var urlInfo = url.parse(req.url, true);

    if (urlInfo.pathname === '/favicon.ico'){
        res.end();
        return;
    }

    console.log('요청 받음 ㅇㅅㅇ')
// 출력하는 콘텐트 타입을 설정하면 한글이 깨지지 않는다.
// 응답 해더로 Content-Type을 지정하자
    res.writeHead(200,{'Content-Type': 'text/plain;charset=UTF-8'});

    if (urlInfo.pathname !== '/calc') {
        res.write('해당 url을 지원하지 않습니다.')
    }

        var a= parseInt(urlInfo.query.a);
        var b= parseInt(urlInfo.query.b);
        var op = urlInfo.query.op;
        var result = 0;

        switch (op){
            case '+': result = a + b; break;
            case '-': result = a - b; break;
            case '*': result = a * b; break;
            case '/': result = a / b; break;
            default :
                res.write(`${op} 연산자를 지원하지 않습니다.`)
                res.end;
        }
        res.write(`${a} ${op} ${b} = ${result}\n`)

    res.end(); // end()에서 꼭 출력할 필요는 없다
})

// 3) HTTP 서버 실행
// listen(포트번호, [서버가 시작된 후 호출될 함수]);
server.listen(8000, () => {
    console.log('서버 시작 ㅇㅇ')
})

