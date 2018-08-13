"use strict"
var eTemplateSrc = $('#li-template-src').text();
var evTemplateSrc = $('#template-src').text();
//console.log(evTemplateSrc);
var eTemplateFn = Handlebars.compile(eTemplateSrc);
var evTemplateFn = Handlebars.compile(evTemplateSrc);

let elist = $('#eList > ul');
let eview = $('#eview > form > div#eviewc');
let data = null;

$('.updateF').css('display','none');
$('.addF').css('display','none');

var mno = getParameterByName('no');
console.log(mno);

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

loadList(mno);

$(function() {
    $("#searchName").keyup(function() {

    	console.log($(this).val());
    	var sName = $(this).val();
    	if(sName == ""){
    		loadList(mno);
    	}
    	$.getJSON(`../../json/user/search/${sName}`,
    			function(result) {
    				data = result;
    				console.log(data.list)
    				
    				if (data.list.length == 0){
    					var eListHTML = eTemplateFn({list:[{name:"검색내용이",uno:0},
    						{name:"없습니다.",uno:0}]});
    				}else{
    					var eListHTML = eTemplateFn({
    						list : data.list
    					});
    				}
    				elist.html(eListHTML);
    				if (data.list.length == 0)
    					eview.html('<h1>명함을 추가해주세요</h2>');
    				else{
    					console.log('loadview들어옴');
    					loadView(data.list[0].uno);
    				}
    			})
    })

});

function loadList(no) {
    $.getJSON('../../json/user/list', {
        no : no
    }, function() {
        console.log('로딩 성공');
    }
    ).done(
        function(result) {
            data = result;
//            console.log(data.list)
            if (data.list.length == 0){
            	var eListHTML = eTemplateFn({list:[{name:"명함을",uno:0},
                    {name:"추가해주세요",uno:0}]});
            }else{
            var eListHTML = eTemplateFn({
                list : data.list
            });
            }
            elist.html(eListHTML);
            if (data.list.length == 0)
            	eview.html('<h1>명함을 추가해주세요</h2>');
            else{
            	loadView(data.list[0].uno);
            }
        })
}
;
function loadView(uno) {
    $.getJSON(`../../json/user/view/${uno}`,
        function(result) {
            data = result;
//            console.log(data.user)

            var eHTML = evTemplateFn(data.user);
            eview.html(eHTML);
        })
};

eview.on('click','Button#dBtn' ,function () {
    $('.viewF').css('display','none');
    $('.updateF').css('display','block');
    $('.form-control').css('border','1px solid #ced4da');
    
    $('.form-group > input').removeAttr("readonly");
    $('#memo').removeAttr("readonly");
    $('#eName').removeAttr("readonly");
});

$(cancelBtn).click(() => {
	loadList(mno);
	$('.updateF').css('display','none');
	$('.addF').css('display','none');
	$('.viewF').css('display','block');
});
$(add1Btn).click(() => {
	$('.viewF').css('display','none');
	$('.addF').css('display','block');
	

	var data = {
	        no: "",
	        uno: "",
	        name: "",
	        email: "",
	        hphone: "",
	        phone: "",
	        fax: "",
	        memo: ""
	    }
	
    var eHTML = evTemplateFn(data);
    eview.html(eHTML);

    $('.form-control').css('border','1px solid #ced4da');
    $('.form-group > input').removeAttr("readonly");
    $('#memo').removeAttr("readonly");
    $('#eName').removeAttr("readonly");
	
/*	$('.form-group > input').attr("value","");
	$('#memo').val("");
	$('#eName').attr("value", "");*/
	$('#eName').attr("placeholder","이름");
	
});

$(delBtn).click(function () {
    $.post('../../json/user/delete', {
    	uno : $(uno).val()    
		}, function(data) {
            if (data.status == 'success'){
                loadList(mno);
                $('.updateF').css('display','none');
                $('.addF').css('display','none');
                $('.viewF').css('display','block');
            }else{
                alert("삭제 오류 입니다.")
                console.log(data.error);
            }
        },'json')
});
$(add2Btn).click(function () {
	$.post('../../json/user/add', {
		no: mno,
		name: $(eName).val(),
		hphone: $(hphone).val(),
		phone: $(phone).val(),
		fax: $(fax).val(),
		email: $(email).val(),
		memo: $(memo).val()},
		function(data) {
			if (data.status == 'success'){
				loadView(1);
				loadList(mno);
				$('.updateF').css('display','none');
				$('.addF').css('display','none');
				$('.viewF').css('display','block');
			}else{
				alert("추가 오류 입니다.")
				console.log(data.error);
			}
		},'json')
});

$(updateBtn).click(function () {
	$.post('../../json/user/update', {
		uno : $(uno).val(),
		no: $(no).val(),
		name: $(eName).val(),
		hphone: $(hphone).val(),
		phone: $(phone).val(),
		fax: $(fax).val(),
		email: $(email).val(),
		memo: $(memo).val()},
		function(data) {
			if (data.status == 'success'){
				loadView(data.uno);
				loadList(mno);
				$('.updateF').css('display','none');
				$('.addF').css('display','none');
				$('.viewF').css('display','block');
			}else{
				alert("변경 오류 입니다.")
				console.log(data.error);
			}
		},'json')
});


elist.on('click', 'button.viewLink', function(event) {
    event.preventDefault();
    // 이벤트가 발생된 현재 객체 this
    var no = $(event.target).attr('data-no');
    if (no != 0) loadView(no); 
    
});

$('a#delMember').click(function(event) {
	event.preventDefault();
	console.log('ok')
	$.post('../../json/member/delete', {
    	no : mno    
		}, function(data) {
            if (data.status == 'success'){
            	alert("회원이 삭제 되었습니다.");
            	location.href = 'login.html';
            }else{
                alert("삭제 오류 입니다.")
                console.log(data.error);
            }
        },'json')
	
});

$('a#logOut').click(function(event) {
	event.preventDefault();
	location.href = 'login.html';
});