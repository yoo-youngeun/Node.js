'use constrict';

$(function() {
    let buttHeight = $("#searchTable").height();
    $("#button.btn").css("height", buttHeight);

    let cateList = new Vue({
        // el : 연결할 영역을 지정
        el: "#searchResult",
        // data : 데이터 값
        data : {
            cateList : {}
        },
        methods: {
            viewPage : function(url) {
                window.open(url, "_target", "width=860,height=600");
            }
        }
    });

    searchCategory();

    function searchCategory() {
        axios({
            url: '/am/cate/searchCate', // 통신할 웹문서
            method: 'get' // 통신 방식
        }).then(function (response) {
            console.dir(response.data);
            cateList.cateList = response.data;
        });
    }

    $("#searchButt").on("click", function() {
        let Cateid = $("#Cateid").val().trim();
        let name = $("#name").val().trim();
        let status = $("#status").val().trim();
        if (Cateid.length == 0 && name.length == 0 && status.length == 0) {
            alert("검색조건을 지정하세요");
        } else {
            searchCate(Cateid, name, status);
        }
    });

    function searchCate(Cateid, name, status) {
        const frm = new FormData();
        frm.append('Cateid', Cateid);
        frm.append('name', name);
        frm.append('status', status);

        axios.post('/am/cate/searchCate', frm)
            .then((response) => {
                if (response.data.length == 0) {
                    alert("검색 결과가 없습니다.");
                } else {
                    alert("검색결과 : " + response.data.length + "건")
                    CateList.CateList = response.data;
                }
            })
            .catch((error) => {
                // 예외 처리
            });
    }

    $("#reset").on("click", function() {
        $("#Cateid").val("");
        $("#name").val("");
        $("#status").val("REGISTERED");
        searchCate();
    })

    $("input#close").on("click", function() {
        window.open('','_self').close();
    })

    $("#createCate").on("click", function() {
        window.open("/am/cate/createCatePage", '_target', "width=700,height=250");
    })


})