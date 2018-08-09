/* 주제 : 템플릿 엔진 사용
*/

const express = require('express');
const app  = express();

// 템플릿 엔진 모듈을 로딩
const handlebars = require('handlebars')
// 템플릿 소스 정의
var templateSrc = '{{name}}님 안녕하세요';
//  템플릿 소스에 데이터를 삽입하여 최종 결과를 만들어 낼 함수를 준비
var templateFn = handlebars.compile(templateSrc);
app.get('/hello',(req,res) => {
    res.writeHead(200, {'Content-Type': 'text/plain;charset=UTF-8'});
// 템플릿 함수를 호출하여 소스로 부터 결과를 얻음
// 소스에 삽입될 데이터를 파라미터로 넘김
    var resultStr = templateFn({name:'용아'})
    console.log(resultStr);
    res.end();
});

app.listen(8000,()=> {
    console.log('서버 실행중')
})