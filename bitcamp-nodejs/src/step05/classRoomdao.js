/* 주제 : DAO 모듈 만들기
*/
var pool;
exports.setConnectionPool = (connectionPool) => {
    pool = connectionPool;
}

exports.list = (pageNo = 1, pageSize = 3, handler) => {
    var startIndex = (pageNo -1) * pageSize;

    pool.query('select mid, email from pms2_classroom limit ?,?',[startIndex, pageSize],
        function (err, results) {
        handler(err, results);
    });
}

exports.add = (data, handler) => {
    pool.query('insert into pms2_classroom(titl,sdt,edt,room) values(?,?,?,?)',
        [data.title, data.sdt, data.edt, data.room],
        function (err, results) {
        handler(err, results);
    });
};

exports.update = (data, handler) => {
    pool.query('update pms2_classroom set titl=?, sdt=?, edt=?, room=? where crno=?',
        [data.title, data.sdt, data.edt, data.room, data.crno],
        function (err, result) {
            handler(err, result);
    });
};

exports.remove = (data, handler) => {
    pool.query('delete from pms2_classroom where crno=?',[data.crno], function (err, result) {
        handler(err, result);
    });
};