// 주제: 데이터 베이스 프로그래밍 - 커넥션 풀 사용
const mysql = require('mysql');

//    1. DBMS와 연결을 수행할 객체를 준비한다.
var pool = mysql.createPool({
    connectionLimit: 10,
    host: 'aws.yddragon.com', // 호스트가 localhost면 생략가능
//    port:'3306', // 포트 번호가 3306이면 생략가능
    database:'studydb',
    user:'study',
    password:'1111'
});

// 2. 연결 객체를 통해 DBMS와 연결한다.
// connection Pool에서 연결 객체를 얻음
pool.getConnection(function (err, con) {
    // 만약 연결에 실패했다면, err 파라미터 값이 존재할 것이다.
    if (err) throw err; // 예외를 던져 오류가 있음을 알리기

    console.log('연결 객체를 얻음 ㅇㅅㅇ!');

    // 연결에 성공 했을때 에만 sql을 실행하라고 예약한다.
    con.query('select * from pms2_member', function (err, results) {
        if (err) throw err;
        // results 파라미터에 결과가 들어있다.
        for (var row of results){
            console.log(row.email, row.mid, row.pwd)
        }
        con.release(); // 커넥션 풀에 연결객체를 반납
     /* 프로그램을 종료하고 싶다면 작업이 끝난 후 커넷션 풀의 모든 커넥션을 닫아야 한다.
        보통 서버로서 실행하다가 종료할 때 커넷션 풀을 닫는다.
        커넥션 풀을 닫지 않으면 Nodejs의 메인 스레드가 종료되지 않는다
        서버로 실행할 때는 종료가 되면 안되지만 이 프로그램 예제처럼 그냥 간단히
        사용하고 종료하고 싶다면 다음과 같이 커넥션풀을 닫으면 된다.
        참고로 이런 단일 프로그램은 커넥션 풀을 사용할 필요가 없다.*/
        pool.end();
    });
});

console.log('select 실행 ㅇㅅㅇ');