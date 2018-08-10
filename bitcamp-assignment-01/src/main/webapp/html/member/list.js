"use strict"
var eTemplateSrc = $('#li-template-src').text();
var evTemplateSrc = $('#template-src').text();
console.log(evTemplateSrc);
var eTemplateFn = Handlebars.compile(eTemplateSrc);
var evTemplateFn = Handlebars.compile(evTemplateSrc);

let elist = $('#eList > ul');
let data = null;

function loadList(no) {
    $.getJSON(serverApiAddr + '/json/user/list', {
        no : no
    }, function() {
        console.log('로딩 성공');
    }
    ).done(
        function(result) {
            data = result;
            console.log(data.list)

            var eListHTML = eTemplateFn({
                list : data.list
            });
            elist.html(eListHTML);
        })
}
;
/*function loadView(name) {
    $.getJSON(serverApiAddr + `/json/user/view${name}`,
        function(result) {
            data = result;
            console.log(data.list)

            var eListHTML = evTemplateFn({
                list : data.list
            });
            elist.html(eListHTML);
        })
};
*/
loadList(0);
// loadView('용아');
elist.on('click', 'a.viewLink', function(event) {
    event.preventDefault();
    // 이벤트가 발생된 현재 객체 this
    var id = $(event.target).attr('data-id');
    location.href = `view.html?id=${id}&page=${data.page}&size=${data.size}`;
})