$(function() {
    let idx = $(location).attr('href').split("/")[5];
    let width = $("body").width();
    $("table#viewResult div#buttArea").css("width", width);

    let viewcate = new Vue({
        // el : 연결할 영역을 지정
        el: "#viewResult",
        // data : 데이터 값
        data : {
            cate : {}
        },
        method:{
            updatePage : function(url) {
                window.open(url, "_self");
            }
        }
    });

    searchCate();

    function searchCate() {
        axios({
            url: '/am/cate/cate/'+idx, // 통신할 웹문서
            method: 'get' // 통신 방식
        }).then(function (response) {
            // console.dir(response.data);
            viewCate.cate = response.data;
        });
    }

    $("input#close").on("click", function() {
        window.open('','_self').close();
    })

    $("input#update").on("click", function() {
        location.href="/am/cate/updatePage/"+idx;
    })
})