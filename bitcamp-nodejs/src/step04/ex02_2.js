// 주제 : express 사용하기 - 정적 HTML 파일을 서비스 하기

// express 모듈 로딩
const express = require('express')
// 웹서버 객체 준비
const app = express();
// post 요청 데이터를 처리할 모듈 로딩
const bodyParser = require('body-parser')
// post 요청 파라미터 분석기를 express 웹서버 객체에 등록
app.use(bodyParser.urlencoded({extended: false}));

// 정적 HTML 파일 처리
app.use(express.static('public'))

// URL에 대해 함수를 연결한다.
app.get('/test01', (req,res) => {
    res.writeHead(200, {'Content-Type': 'text/plain;charset=UTF-8'});
    res.write(`name=${req.query.name}\n`);
    res.write(`age=${req.query.age}\n`);
    res.end();
});

app.post('/test02', (req,res) => {
    res.writeHead(200, {'Content-Type': 'text/plain;charset=UTF-8'});
    res.write(`name=${req.body.name}\n`);
    res.write(`age=${req.body.age}\n`);
    res.end();
});

// 서버 실행하기
app.listen(8000, () => {
    console.log('서버 실행중 ㅇㅇ')
});