// 주제: 모듈을 정의하고 사용하기 III

// 리턴 값 destructuring 변수의 이름 바꾸기
// {원래 프로퍼티명:변수명}
var {plus:p, minus:m} = require('./ex03_m.js');

console.log(p(10, 20));
console.log(m(10, 20));