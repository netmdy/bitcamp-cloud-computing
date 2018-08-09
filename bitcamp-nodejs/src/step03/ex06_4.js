/* 주제 : 템플릿 엔진 사용 - 외부 템플릿 파일 사용
*/

const express = require('express');
const app  = express();

// 외부 파일의 경로를 다룰 때 사용할 모듈을 로딩
const path = require('path');
//C:\Users\user888\git\bitcamp-cloud-computing\bitcamp-nodejs\src\step03
console.log(__dirname)

// 외부 템플릿 파일의 경로 설정하기
// C:\Users\user888\git\bitcamp-cloud-computing\bitcamp-nodejs\src\step03\ex06_4_template.html

var templatePath = path.join(__dirname, 'ex06_4_template.html')
console.log(templatePath)

// 템플릿 엔진 모듈을 로딩
const handlebars = require('handlebars');
// 템플릿 파일을 읽을 모듈을 로딩
const fs =require('fs')

// 템플릿

// 템플릿 소스 정의 후 동기 방식으로 파일의 내용을 읽어 들인다.
// 동기방식이기 때문에 파일을 다 읽은 후에 리턴하며
// 리턴값은 파일의 데이터이다.
//  템플릿 소스에 데이터를 삽입하여 최종 결과를 만들어 낼 함수를 준비
var templateSrc = fs.readFileSync(templatePath)
// buffer라서 toString해줘야함
var templateFn = handlebars.compile(templateSrc.toString());
app.get('/hello',(req,res) => {
    res.writeHead(200, {'Content-Type': 'text/html;charset=UTF-8'});
// 템플릿 함수를 호출하여 소스로 부터 결과를 얻음
// 소스에 삽입될 데이터를 파라미터로 넘김
//     var resultStr = templateFn({name:'용아'})
    var resultStr = templateFn(req.query)
    console.log(req.query)
    res.write(resultStr);
    res.end();
});

app.listen(8000,()=> {
    console.log('서버 실행중')
})