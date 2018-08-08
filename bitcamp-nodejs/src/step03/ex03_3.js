/* 주제 : 클라이언트에게 출력하기 - url 꺼내기
*/

// 1) 모듈 로딩
const  http = require('http')

// url 분석에 사용할 모듈 로딩
const  url = require('url')

const server = http.createServer(function (req, res) {
    console.log('요청 받음 ㅇㅅㅇ')

// 출력하는 콘텐트 타입을 설정하면 한글이 깨지지 않는다.
// 응답 해더로 Content-Type을 지정하자
    res.writeHead(200,{'Content-Type': 'text/plain;charset=UTF-8'});

    // 클라이언트가 요청한 url 보기
    // http://localhost:8000/aaa/bbb/ccc?name=yong&age=2
    res.write(`${req.url}\n`) // /aaa/bbb/ccc?name=yong&age=2

    // url 분석기를 이용하여 url을 분석하기
    // query 객체에서 파라미터 명을 사용하여 값을 꺼낼때는
    // 두번째 파라미터의 값을 true로 설정
    // parse() 함수가 파라미터 값을 꺼내기 쉽도록
    // query 객체에 프로퍼티 값으로 담아준다.
    var urlInfo = url.parse(req.url, true);
// 파라미터 값 꺼낸때는 그냥 query.파라미터 값을 적으면 된다
    res.write(`name= ${urlInfo.query.name}\n`);
    res.write(`ame= ${urlInfo.query.age}\n`);
    res.end(); // end()에서 꼭 출력할 필요는 없다
})

// 3) HTTP 서버 실행
// listen(포트번호, [서버가 시작된 후 호출될 함수]);
server.listen(8000, () => {
    console.log('서버 시작 ㅇㅇ')
})

