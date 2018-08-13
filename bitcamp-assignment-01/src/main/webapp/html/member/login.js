"use strict"

var data = null;

$(loginBtn).click(function () {
    $.post('../../json/member/login', {
        password: $(ePassword).val(),
        email: $(eEmail).val()},
    function(data) {
        if (data.status == 'success'){
        	console.log(data.member);
        	console.log(data.member.no);
                location.href = `list.html?no=${data.member.no}`;
        }else{
            alert("이메일이나 암호가 틀립니다.")
            console.log(data.error);
        }
    },'json')
});
$(addBtn).click(function () {
	location.href = 'add.html';
});
