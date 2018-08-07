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

// query(sql, 실행 후 호출될 함수);
// SQL을 바로 실행하는 것이 아니라 실행을 예약한다
// DBMS와 연결되면 이렇게 예약한 SQL문을 실행할 것이다.
// 문제는 연결 오류에 상관없이 무조건 예약한다는 것이다.
// connect()에 등록한 함수에서 예외를 던지지 않으면 이 sql문을 실행한다.
con.query('select * from pms2_member', function (err, results) {
    if (err) throw err;
    // results 파라미터에 결과가 들어있다.
    for (var row of results){
        console.log(row.email, row.mid, row.pwd)
    }
});
// 3. DBMS와의 연결 해제를 예약한다.
// 다음 코드를 실행하는 것은 con 객체가 SQL 작업이 끝나면
// 서버와의 연결을 끊으라고 예약하는 것이다.
// end(DBMS와 연결을 끊은 후 호출될 함수);
con.end(function (err) {
    if (err) throw err; // 만약 연결을 끊다가 오류가 있다면 예외를 발생시킨다.
    console.log('연결을 끊었어요')
}); // 지금 당장 연결을 끊으라는 것이 아님
console.log('select 실행 ㅇㅅㅇ');