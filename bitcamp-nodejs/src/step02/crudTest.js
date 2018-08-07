const mysql = require('mysql');
const fn = require('./crud');

//var crud = fn();

//console.log(crud.readAll('board'));
console.log(fn().readAll('member'));
 // console.log(crud.readAll('class'));
 // console.log(crud.readAll('team'));
 // console.log(crud.readOne('member','용이2'));
//console.log(crud.readOne('class',11));
 // console.log(crud.readOne('board',11));
//  console.log(crud.readOne('team','용용이'));
// console.log(crud.create('member;yong@test.com;용이2;1111'));
// console.log(crud.create('board;테스트양1;ㅇㅅㅇ용이2'));
// console.log((crud.update('board;ㅇㅅㅇ1;ㅇㅇv;9')))
// console.log(crud.delete('board',23))

