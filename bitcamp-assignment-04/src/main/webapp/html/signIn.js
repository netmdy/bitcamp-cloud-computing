'use strict'

$('#loginBtn').click(() => {
	$.post(`${serverApiAddr}/json/auth/signIn`,
	  { 'email': $('#fEmail').val(),
        'password': $('#fPassword').val(),
        'saveEmail': $('#fSaveEmail').prop('checked')},
        function(result) {
		console.log(result);
	},'json').fail(() =>{
		alert('이메일이나 암호가 틀렸습니다.')
	});
});