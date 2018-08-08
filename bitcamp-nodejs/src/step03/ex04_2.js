/* 주제 : SQL 요청 처리하기- 회원 등록하기

http://localhost:8000/member/add?id=user100&email=user100@test.com&password=1111
출력 결과
id, 이메일
*/

// 1) 모듈 로딩
const  http = require('http')

// url 분석에 사용할 모듈 로딩
const  url = require('url')
const mysql = require('mysql');

//    1. DBMS와 연결을 수행할 객체를 준비한다.
var pool = mysql.createPool({
    connectionLimit: 10,
    host: 'aws.yddragon.com', // 호스트가 localhost면 생략가능
//    port:'3306', // 포트 번호가 3306이면 생략가능
    database:'studydb',
    user:'study',
    password:'1111'
});

const server = http.createServer(function (req, res) {
    var urlInfo = url.parse(req.url, true);

    if (urlInfo.pathname === '/favicon.ico'){
        res.end();
        return;
    }

// 출력하는 콘텐트 타입을 설정하면 한글이 깨지지 않는다.
// 응답 해더로 Content-Type을 지정하자
    res.writeHead(200,{'Content-Type': 'text/plain;charset=UTF-8'});

    if (urlInfo.pathname !== '/member/add') {
        res.end('해당 url을 지원하지 않습니다.');
        return;
    }
    var email = urlInfo.query.email;
    var mid = urlInfo.query.id;
    var pwd = urlInfo.query.password;

        pool.query('insert into pms2_member(email,mid,pwd) values(?,?,password(?))',[email,mid,pwd], function (err, results) {
            if (err) {
                res.end('db 생성 중 예외 발생');
                return;
            }
            res.write(`${mid}가 추가 되었습니다.`)
        res.end();
        });
})

// 3) HTTP 서버 실행
// listen(포트번호, [서버가 시작된 후 호출될 함수]);
server.listen(8000, () => {
    console.log('서버 시작 ㅇㅇ')
})

