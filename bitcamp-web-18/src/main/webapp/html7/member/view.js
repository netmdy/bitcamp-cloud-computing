"use strict"

var data = null;

/* 자주 사용하는거라 common.js 파일 만들어 모듈화하기
var paramMap ={};
var qs = location.href.split('?');
if (qs.length > 1){
    var params = qs[1].split('&');
    for (var param of params){
        var kv = param.split('=')
        paramMap[kv[0]] = kv[1]; 
    }
    
}
console.log(paramMap);
*/
var {id, page, size} = $.parseQuery(location.href);

if (id == undefined){// 아이디를 지정하지 않으면 입력폼
    $('.viewform').css('display', 'none');
    $(eId).removeAttr("readonly");
}
else{
    $('.newform').css('display','none');
    
    $.getJSON(serverApiAddr + `/json/member/view/${id}`,function(result){
        
        data = result;
        if (data.member == null){
            alert('아이디가 없습니다.');
            location.href = "list.html";
            return;
        }
        $(eId).val(data.member.id);
        $(eEmail).val(data.member.email);
    });
}
$(eListBtn).click(function() {
    if (page) {
       location.href = `list.html?page=${page}&size=${size}`;
    } else {
        location.href = 'list.html';
    }
});

$(eUpdateBtn).click(function () {
    $.post(serverApiAddr + '/json/member/update', {
            id: $(eId).val(),
            password: $(ePassword).val(),
            email: $(eEmail).val()},
        function(data) {
            if (data.status == 'success'){
                location.href = `list.html?page=${page}&size=${size}`;
            }else{
                alert("변경 오류 입니다.")
                console.log(data.error);
            }
        },'json')
});

$(eDeleteBtn).click(function () {
   $.getJSON(serverApiAddr + `/json/member/delete?id=${eId.value}`,
           function(data){
            if (data.status == 'success'){
                location.href = `list.html?page=${page}&size=${size}`;
            }else{
                alert("삭제 오류 입니다.")
                console.log(data.error);
            }
   });
});

$(eAddBtn).click(function () {
    $.post(serverApiAddr + '/json/member/add', {
        id: $(eId).val(),
        password: $(ePassword).val(),
        email: $(eEmail).val()},
    function(data) {
        if (data.status == 'success'){
            location.href = `list.html`;
        }else{
            alert("오류 입니다.")
            console.log(data.error);
        }
    },'json')
});
