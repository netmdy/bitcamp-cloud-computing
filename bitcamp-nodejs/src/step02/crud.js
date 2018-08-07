const mysql = require('mysql');

var sql,namev,values;

var con = mysql.createConnection({
    host: 'aws.yddragon.com', // 호스트가 localhost면 생략가능
//    port:'3306', // 포트 번호가 3306이면 생략가능
    database:'studydb',
    user:'study',
    password:'1111'
});

module.exports = function () {

    return{
        readAll: function(value) {con.connect(function (err) {
        // 만약 연결에 실패했다면, err 파라미터 값이 존재할 것이다.
        if (err) throw err; // 예외를 던져 오류가 있음을 알리기
        console.log('연결 성공 ㅇㅅㅇ!');

        switch (value){
            case 'board':
                sql = 'select * from pms2_board';
                break;
            case 'member':
                sql = 'select * from pms2_member';
                break;
            case 'class':
                sql = 'select * from pms2_classroom';
                break;
            case 'team':
                sql = 'select * from pms2_team';
                break;
        }
            con.query(sql, function (err, results) {
                if (err) throw err;
                // results 파라미터에 결과가 들어있다.
                    for (var i in results) {
                    switch (value){
                        case 'board':
                        console.log('번호:',results[i].bno, '제목:',results[i].titl,'날짜', results[i].cdt);
                        break;
                        case 'member':
                        console.log('이메일',results[i].email, '아이디:',results[i].mid,'비밀번호:', results[i].pwd)
                            break;
                        case 'class':
                        console.log('번호:',results[i].crno, '강의명:',results[i].titl, '강의실:',results[i].room,'기간 :',results[i].sdt,'~',results[i].edt)
                            break;
                        case 'team':
                        console.log('이름:',results[i].name, '팀원수:',results[i].max_qty,'시작일:',results[i].sdt, '종료일 :',results[i].edt)
                            break;
                        }
                    }

                con.end(function (err) {
                    if (err) throw err; // 만약 연결을 끊다가 오류가 있다면 예외를 발생시킨다.
                    console.log('연결을 끊었어요')
                    return results;
                    }); // 지금 당장 연결을 끊으라는 것이 아님
                });
        })},
        readOne: function(namev,value) {con.connect(function (err) {
        // 만약 연결에 실패했다면, err 파라미터 값이 존재할 것이다.
        if (err) throw err; // 예외를 던져 오류가 있음을 알리기
        console.log('연결 성공 ㅇㅅㅇ!');

        switch (namev){
            case 'board':
                sql = 'select * from pms2_board where bno=?';
                break;
            case 'member':
                sql = 'select * from pms2_member where mid=?';
                break;
            case 'class':
                sql = 'select * from pms2_classroom where crno=?';
                break;
            case 'team':
                sql = 'select * from pms2_team where name=?';
                break;
        }

        // var sqlAll = if(board == 'board' ? boardS : memberS);
            con.query(sql,[value], function (err, results) {
                if (err) throw err;
                // results 파라미터에 결과가 들어있다.
                    switch (namev){
                        case 'board':
                        console.log('번호:',results[0].bno, '제목:',results[0].titl,'날짜', results[0].cdt);
                        break;
                        case 'member':
                        console.log('이메일',results[0].email, '아이디:',results[0].mid,'비밀번호:', results[0].pwd)
                            break;
                        case 'class':
                        console.log('번호:',results[0].crno, '강의명:',results[0].titl, '강의실:',results[0].room,'기간 :',results[0].sdt,'~',results[0].edt)
                            break;
                        case 'team':
                        console.log('이름:',results[0].name, '팀원수:',results[0].max_qty,'시작일:',results[0].sdt, '종료일 :',results[0].edt)
                            break;
                        }
                con.end(function (err) {
                    if (err) throw err; // 만약 연결을 끊다가 오류가 있다면 예외를 발생시킨다.
                    console.log('연결을 끊었어요')
                    return results;
                    }); // 지금 당장 연결을 끊으라는 것이 아님
                });
        })},
        create: function(value) {con.connect(function (err) {
         values = value.split(';');
         namev = values[0];
            // 만약 연결에 실패했다면, err 파라미터 값이 존재할 것이다.
        if (err) throw err; // 예외를 던져 오류가 있음을 알리기
        console.log('연결 성공 ㅇㅅㅇ!');
        console.log(values);

        switch (namev){
            case 'board':
                sql = 'insert into pms2_board(titl,cont,cdt) values(?,?,now())';
                break;
            case 'member':
                sql = 'insert into pms2_member(email,mid,pwd) values(?,?,password(?))';
                break;
            case 'class':
                sql = 'insert into pms2_classroom(titl,sdt,edt,room) values(?,?,?,?)';
                break;
            case 'team':
                sql = 'insert into pms2_team(name,dscrt,max_qty,sdt,edt) values(?,?,?,?,?)';
                break;
        }

            con.query(sql,[values[1],values[2],values[3],values[4],values[5]], function (err, results) {
                if (err) throw err;
                // results 파라미터에 결과가 들어있다.
                    console.log('입력성공 ㅇㅅㅇ');
                con.end(function (err) {
                    if (err) throw err; // 만약 연결을 끊다가 오류가 있다면 예외를 발생시킨다.
                    console.log('연결을 끊었어요')
                    return results;
                    }); // 지금 당장 연결을 끊으라는 것이 아님
                });
        })},
        update: function(value) {con.connect(function (err) {
         values = value.split(';');
         namev = values[0];
            // 만약 연결에 실패했다면, err 파라미터 값이 존재할 것이다.
        if (err) throw err; // 예외를 던져 오류가 있음을 알리기
        console.log('연결 성공 ㅇㅅㅇ!');
        console.log(values);

        switch (namev){
            case 'board':
                sql ='update pms2_board set titl=?, cont=?, cdt=now() where bno=?';
                break;
            case 'member':
                sql = 'update pms2_member set email=? where mid=?';
                break;
            case 'class':
                sql = 'update pms2_classroom set titl=?, sdt=?, edt=?, room=? where crno=?';
                break;
            case 'team':
                sql = 'update pms2_team set dscrt=? max_qty=? sdt=? edt=? where name=?';
                break;
        }
            con.query(sql,[values[1],values[2],values[3],values[4],values[5]], function (err, results) {
                if (err) throw err;
                // results 파라미터에 결과가 들어있다.
                    console.log('수정성공 ㅇㅅㅇ');
                con.end(function (err) {
                    if (err) throw err; // 만약 연결을 끊다가 오류가 있다면 예외를 발생시킨다.
                    console.log('연결을 끊었어요')
                    return results;
                    }); // 지금 당장 연결을 끊으라는 것이 아님
                });
        })},
        delete: function(namev,value) {con.connect(function (err) {
            // 만약 연결에 실패했다면, err 파라미터 값이 존재할 것이다.
        if (err) throw err; // 예외를 던져 오류가 있음을 알리기
        console.log('연결 성공 ㅇㅅㅇ!');

        switch (namev){
            case 'board':
                sql ='delete from pms2_board where bno=?';
                break;
            case 'member':
                sql = 'delete from pms2_member where mid=?';
                break;
            case 'class':
                sql = 'delete from pms2_classroom where crno=?';
                break;
            case 'team':
                sql = 'delete from pms2_team where name=?';
                break;
        }

            con.query(sql,[value], function (err, results) {
                if (err) throw err;
                // results 파라미터에 결과가 들어있다.
                    console.log('삭제성공 ㅇㅅㅇ');
                con.end(function (err) {
                    if (err) throw err; // 만약 연결을 끊다가 오류가 있다면 예외를 발생시킨다.
                    console.log('연결을 끊었어요')
                    return results;
                    }); // 지금 당장 연결을 끊으라는 것이 아님
                });
        })}
    };

};
