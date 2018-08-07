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

var mid = "netmdy6";
// var mid = "user002' or 1=1 or ''='";
// in-parameter 사용하기
// 인 파라미터 방식에서는 사용자가 입력한 값으로 sql을 만드는 것이 아니기
// 때문에 sql 조작이 불가능하다
con.query(`delete from pms2_member where mid=?`,
    [mid],// ?(인 파라미터) 갯수 만큼 배열에 값을 담아 놓으면 된다.
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