// 주제: 모듈을 정의하고 사용하기

// require(모듈명 또는 모듈의 경로)
// 모듈의 경로는 현재 파일의 위치에 상대적이다.
var obj = require('./ex08_m.js');

obj.plus(100);
obj.minus(7);

console.log(obj.getResult());
