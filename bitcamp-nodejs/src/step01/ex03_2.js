// 주제: 모듈을 정의하고 사용하기 II

// 리턴 값 destructuring
// require()가 리턴한 export 객체를 분해해서 받을 수 있다.
// 모듈의 경로는 현재 파일의 위치에 상대적이다.
var {plus, minus} = require('./ex03_m.js');

console.log(plus(10, 20));
console.log(minus(10, 20));