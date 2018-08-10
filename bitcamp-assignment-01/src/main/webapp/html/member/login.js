"use strict"

var data = null;

$(addBtn).click(function () {
    $.post(serverApiAddr + '/json/member/login', {
        password: $(ePassword).val(),
        email: $(eEmail).val()},
    function(data) {
            console.log(data.loginv);
        if (data.status == 'success'){
            if (data.loginv == 1){
                location.href = `list.html`;
            }else{
                alert("이메일이나 암호가 틀립니다.")
            }
        }else{
            alert("이메일이나 암호가 틀립니다.")
            console.log(data.error);
        }
    },'json')
});
