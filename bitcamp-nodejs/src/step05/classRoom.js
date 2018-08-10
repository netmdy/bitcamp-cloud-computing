/* 주제 : DAO 분리
*/

const mysql = require('mysql');
const express = require('express');
const classRoomdao = require('./classRoomdao')
const router  = express.Router();

//    1. DBMS와 연결을 수행할 객체를 준비한다.
var pool = mysql.createPool({
    connectionLimit: 10,
    host: 'aws.yddragon.com', // 호스트가 localhost면 생략가능
//    port:'3306', // 포트 번호가 3306이면 생략가능
    database:'studydb',
    user:'study',
    password:'1111'
});

classRoomdao.setConnectionPool(pool)

router.get('/list',(req, res) => {
    res.writeHead(200, {'Content-Type': 'text/plain;charset=UTF-8'});

    var pageNo = 1;
    var pageSize = 3;
    if (req.query.pageNo){
        pageNo= parseInt(req.query.pageNo);  }
    if (req.query.pageSize){
        pageSize= parseInt(req.query.pageSize); }

    classRoomdao.list(pageNo, pageSize, (err, results) => {
        if (err) {
            res.end('db 조회 중 예외 발생');
            return;
        }

        for (var row of results){
            res.write(`번호 : ${row.crno},강의명 : ${row.titl},강의실 : ${row.room},기간 :,${row.sdt}~${row.edt}\n`);
        }
        res.end();
    });


});
router.get('/add',(req, res) => {
    res.writeHead(200, {'Content-Type': 'text/plain;charset=UTF-8'});

    classRoomdao.add(req.query, (err, result) => {
        if (err) {
            res.end('db 생성 중 예외 발생');
            return;
        }
        res.write(`${req.query.title}가 추가 되었습니다.`)
        res.end();
    })



});
router.get('/update',(req, res) => {
    res.writeHead(200, {'Content-Type': 'text/plain;charset=UTF-8'});

    classRoomdao.update(req.query, (err, result) => {
        if (err) {
            res.end('db 수정 중 예외 발생');
            return;
        }
        res.write(`${req.query.title} 수정이 완료 되었습니다.`)
        res.end();
    })
});
router.get('/delete',(req, res) => {
    res.writeHead(200, {'Content-Type': 'text/plain;charset=UTF-8'});

    classRoomdao.remove(req.query, (err, result) => {
        if (err) {
            res.end('db 삭제 중 예외 발생');
            return;
        }
        res.write(`삭제가 완료 되었습니다.`)
        res.end();
    });
});

module.exports = router;
