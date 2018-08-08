/* 주제 : 코드를 모듈로 분리 - 요청 핸들러를 호출하는 코드 분리
*/

const mysql = require('mysql');
const express = require('./express02');
const app  = express();
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

app.add('/hello',(urlInfo, req,res) => {
    res.write(`${urlInfo.query.name}님 안녕하세요`);
    res.end();
});

app.add('/member/list',(urlInfo, req, res) => {
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
});
app.add('/member/add',(urlInfo, req, res) => {
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
});
app.add('/member/update',(urlInfo, req, res) => {
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
});
app.add('/member/delete',(urlInfo, req, res) => {
    mid = urlInfo.query.id;

    pool.query('delete from pms2_member where mid=?',[mid], function (err, results) {
        if (err) {
            res.end('db 삭제 중 예외 발생');
            return;
        }
        res.write(`${mid} 삭제가 완료 되었습니다.`)
        res.end();
    });
});

app.listen(8000,()=> {
    console.log('서버 실행중')
})