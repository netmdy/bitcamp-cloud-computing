const mysql = require('mysql');

//    1. DBMS와 연결을 수행할 객체를 준비한다.
var con = mysql.createConnection({
    host: 'aws.yddragon.com', // 호스트가 localhost면 생략가능
//    port:'3306', // 포트 번호가 3306이면 생략가능
    database:'studydb',
    user:'study',
    password:'1111'
});

// 2. 연결 객체를 통해 DBMS와 연결한다.
// connect(연결 완료 후 호출될 함수);
con.connect(function (err) {
    // 만약 연결에 실패했다면, err 파라미터 값이 존재할 것이다.
    if (err) throw err; // 예외를 던져 오류가 있음을 알리기

    console.log('연결 성공 ㅇㅅㅇ!');
});

var mid = "user002";
// var mid = "user002' or 1=1 or ''='";

// SQL문을 만들 때 변수의 값을 직접 넣어서 만드는 경우
// 해커의 공격에 노출될 수 있다.
// 위에 주석을 막은 mid 변수의 값 처럼 외부 사용자가 값을 입력한다면
// 조건이 무조건 참이 되기 때문에 전체 데이터가 삭제되는 결과를 낳는다.
// 그래서 SQL문에 변수의 값을 직접 삽입하는 방법을 써서는 안된다.
// 해결책?
// => in-parameter 방법을 사용하라!
con.query(`delete from pms2_member where mid='${mid}'`,
        function (err,_results) {
            if (err) throw err;
            console.log('삭제 성공')
        });

// 3. DBMS와의 연결 해제를 예약한다.
// 다음 코드를 실행하는 것은 con 객체가 SQL 작업이 끝나면
// 서버와의 연결을 끊으라고 예약하는 것이다.
// end(DBMS와 연결을 끊은 후 호출될 함수);
con.end(function (err) {
    if (err) throw err; // 만약 연결을 끊다가 오류가 있다면 예외를 발생시킨다.
    console.log('연결을 끊었어요')
}); // 지금 당장 연결을 끊으라는 것이 아님
console.log('delete 실행 ㅇㅅㅇ');