// 주제: 모듈을 실행하는 과정 알아보기

// require()를 여러 번 호출하더라도 각 모듈은 한 번만 실행됨
// 그래서 리턴값은 같다.
var obj = require('./ex09_m');
obj.test = 100;
console.log('obj : ', obj);
console.log('==============================');

var obj2 = require('./ex09_m');
obj.test2 = 200;
console.log('obj : ', obj);
console.log('obj2 : ', obj2);
console.log('==============================');

var obj3 = require('./ex09_m');
console.log('obj3 : ', obj3);

/*
모듈은 오직 한 번만 실행되어
리턴 객체는 같음
모듈 변수는 오직 한 개만 존재한다.
*/
