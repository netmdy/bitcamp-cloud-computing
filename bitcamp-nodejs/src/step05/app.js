// 과제 : 회원관리, 팀관리, 게시판, 강좌관리 웹 애플리케이션 만들기

// express 모듈 로딩
const express = require('express')
// 웹서버 객체 준비
const app = express();
// post 요청 데이터를 처리할 모듈 로딩
const bodyParser = require('body-parser')
// post 요청 파라미터 분석기를 express 웹서버 객체에 등록
app.use(bodyParser.urlencoded({extended: false}));
const path = require('path')

// 정적 HTML 파일 처리
app.use(express.static('public'))

// 통합 템플릿 엔진 관리자 모듈 로딩
// 템플릿 엔진이 아니라 템플릿 엔진을 중간에서 관리해 주는 역할을 수행한다.
const consolidate = require('consolidate')

// express에 템플릿 엔진을 등록 하기
// consolidate에 대해 handlebars를 지정하면
// 이 템플릿 관리자는 node 모듈에서 handlebars를 찾아 리턴한다
// express에 여러 개의 엔진을 등록할 수 있다.
app.engine('html', consolidate.handlebars)

// 등록된 템플릿 엔진 중에 사용할 엔진을 지정한다.
// 이름변경 불가이며 확장자를 지정하지 않으면 기본확장자를 html로 지정
app.set('view engine', 'html')

// 템플릿 파일이 있는 디렉토리 경로를 지정
// 이름변경 불가
app.set('views', path.join(__dirname, 'template'))

// "/member/* url을 처리할 라우터와 '/team/*' url을 처리할 라우터를 로딩
var memberRouter = require('./member')

// 라우터를 express의 웹서버에 등록
app.use('/member', memberRouter)
app.use('/team', require('./team'))
app.use('/team', require('./board'))
app.use('/classroom', require('./classRoom'))

// URL에 대해 함수를 연결한다.
app.get('/hello', (req,res) => {
    res.writeHead(200, {'Content-Type': 'text/plain;charset=UTF-8'});
    res.write('안녕 ㅇㅅㅇ');
    res.end();
});
app.get('/test03', (req,res) => {
    res.render('list', req.query)
});

// 서버 실행하기
app.listen(8000, () => {
    console.log('서버 실행중 ㅇㅇ')
});