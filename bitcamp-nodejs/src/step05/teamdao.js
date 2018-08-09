/* 주제 : DAO 모듈 만들기
*/
var pool;
exports.setConnectionPool = (connectionPool) => {
    pool = connectionPool;
}

exports.list = (pageNo = 1, pageSize = 3, handler) => {
    var startIndex = (pageNo -1) * pageSize;

    pool.query('select * from pms2_team limit ?,?',[startIndex, pageSize],
        function (err, results) {
        handler(err, results);
    });
}

exports.add = (data, handler) => {
    pool.query('insert into pms2_team(name,dscrt,max_qty,sdt,edt) values(?,?,?,?,?)',
        [data.name,data.dscrt,data.max_qty,data.sdt,data.edt],
        function (err, results) {
        handler(err, results);
    });
};

exports.update = (data, handler) => {
    pool.query('update pms2_team set dscrt=? max_qty=? sdt=? edt=? where name=?',
        [data.dscrt,data.max_qty,data.sdt,data.edt,data.name],
        function (err, result) {
            handler(err, result);
    });
};

exports.remove = (data, handler) => {
    pool.query('delete from pms2_team where name=?',[data.name], function (err, result) {
        handler(err, result);
    });
};