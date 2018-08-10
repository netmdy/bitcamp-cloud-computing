"use strict"

var data = null;

$(addBtn).click(function () {
    console.log($(eName).val())
    $.post(serverApiAddr + '/json/member/add', {
        name: $(eName).val(),
        password: $(ePassword).val(),
        email: $(eEmail).val()},
    function(data) {
        if (data.status == 'success'){
            location.href = `login.html`;
        }else{
            alert("오류 입니다.")
            console.log(data.error);
        }
    },'json')
});
