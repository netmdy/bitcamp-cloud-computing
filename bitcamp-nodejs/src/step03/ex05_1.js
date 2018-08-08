/* 주제 : 여러개 요청 처리하기- 회원 목록조회/등록/변경/삭제하기
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

var email,mid, pwd;

const server = http.createServer(function (req, res) {
    var urlInfo = url.parse(req.url, true);

    if (urlInfo.pathname === '/favicon.ico'){
        res.end();
        return;
    }

// 출력하는 콘텐트 타입을 설정하면 한글이 깨지지 않는다.
// 응답 해더로 Content-Type을 지정하자
    res.writeHead(200,{'Content-Type': 'text/plain;charset=UTF-8'});

    if (urlInfo.pathname === '/member/list') {
        var pageNo = 1;
        var pageSize = 3;
        if (urlInfo.query.pageNo){
            pageNo= parseInt(urlInfo.query.pageNo);  }
        if (urlInfo.query.pageSize){
            pageSize= parseInt(urlInfo.query.pageSize); }

            var startIndex = (pageNo -1) * pageSize;
            pool.query('select mid, email from pms2_member limit ?,?',[startIndex, pageSize], function (err, results) {
                if (err) {
                    res.end('db 조회 중 예외 발생');
                    return;
                }

                for (var row of results){
                    res.write(`${row.email}, ${row.mid}\n`);
                }
            res.end();
            });
    }else if (urlInfo.pathname === '/member/add'){
         email = urlInfo.query.email;
         mid = urlInfo.query.id;
         pwd = urlInfo.query.password;

        pool.query('insert into pms2_member(email,mid,pwd) values(?,?,password(?))',[email,mid,pwd], function (err, results) {
            if (err) {
                res.end('db 생성 중 예외 발생');
                return;
            }
            res.write(`${mid}가 추가 되었습니다.`)
            res.end();
        });
    }else if (urlInfo.pathname === '/member/update'){
         email = urlInfo.query.email;
         mid = urlInfo.query.id;

        pool.query('update pms2_member set email=? where mid=?',[email, mid], function (err, results) {
            if (err) {
                res.end('db 수정 중 예외 발생');
                return;
            }
            res.write(`${mid} 수정이 완료 되었습니다.`)
            res.end();
        });
    }else if (urlInfo.pathname === '/member/delete'){
        mid = urlInfo.query.id;

        pool.query('delete from pms2_member where mid=?',[mid], function (err, results) {
            if (err) {
                res.end('db 삭제 중 예외 발생');
                return;
            }
            res.write(`${mid} 삭제가 완료 되었습니다.`)
            res.end();
        });
    }
    else {
        res.end('해당 url을 지원하지 않습니다.');
    }

})

// 3) HTTP 서버 실행
// listen(포트번호, [서버가 시작된 후 호출될 함수]);
server.listen(8000, () => {
    console.log('서버 시작 ㅇㅇ')
})

