// 주제: 데이터 베이스 프로그래밍 - 커넥션 풀 사용 II
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

// connection Pool 객체를 이용해 바로 질의를 수행한다
// pool.getConnection() -> con.query() -> con.release()의 단축 사용법
pool.query('select * from pms2_member', function (err, results) {
        if (err) throw err;

        for (var row of results){
            console.log(row.email, row.mid, row.pwd)
        }
//  이 예제에서는 sql을 수행한 후에 프로그램을 종료해야 하기 때문에
//     커넥션 풀을 닫는다.
        pool.end();
});

console.log('select 실행 ㅇㅅㅇ');

/*ex11.js 와 ex12.js의 비교
-    ex12.js는 커넥션풀에 대해 바로 query()를 호출할 수 있어
    코드가 간결해져서 좋다.
    하지만 query()를 호출할 때마다 새 커넥션을 만들거나 꺼내서
    사용하기 때문에 한 번에 여러개의 sql을 실행할 때 불리하다.
    ex) pool.query(); // 커넥션1
    ex) pool.query(); // 커넥션2
-    ex11.js는 sql을 실행할 때 마다 매번 커넥션을 얻어야 하기
    때문에 ex12.js보다 코드가 복잡하다
    하지만 한 번 커넥션을 얻으면 여러번 사용할 수 있어
    ex12.js 보다는 여러번 실행시 유리하다.
    ex) pool.getConnection(function (err, con){
        con.query(); //커넥션1
        con.query(); //커넥션2    }
    */
