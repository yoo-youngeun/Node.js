'use constrict';

$(function() {
    let buttHeight = $("#searchTable").height();
    $("#button.btn").css("height", buttHeight);

    let userList = new Vue({
        // el : 연결할 영역을 지정
        el: "#searchResult",
        // data : 데이터 값
        data : {
            userList : {}
        },
        methods: {
            viewPage : function(url) {
                window.open(url, "_target", "width=860,height=600");
            }
        }
    });

    searchUsers();

    function searchUsers() {
        axios({
            url: '/am/searchUsers', // 통신할 웹문서
            method: 'get' // 통신 방식
        }).then(function (response) {
            // console.dir(response.data);
            userList.userList = response.data;
        });
    }

    $("#searchButt").on("click", function() {
        let userid = $("#userid").val().trim();
        let name = $("#name").val().trim();
        let status = $("#status").val().trim();
        if (userid.length == 0 && name.length == 0 && status.length == 0) {
            alert("검색조건을 지정하세요");
        } else {
            searchUser(userid, name, status);
        }
    });

    function searchUser(userid, name, status) {
        const frm = new FormData();
        frm.append('userid', userid);
        frm.append('name', name);
        frm.append('status', status);

        axios.post('/am/searchUser', frm)
            .then((response) => {
                if (response.data.length == 0) {
                    alert("검색 결과가 없습니다.");
                } else {
                    alert("검색결과 : " + response.data.length + "건")
                    userList.userList = response.data;
                }
            })
            .catch((error) => {
                // 예외 처리
            });
    }

    $("#reset").on("click", function() {
        $("#userid").val("");
        $("#name").val("");
        $("#status").val("REGISTERED");
        searchUsers();
    })

    $("input#close").on("click", function() {
        window.open('','_self').close();
    })

    $("#createUser").on("click", function() {
        window.open("/am/createUserPage", '_target', "width=860,height=600");
    })


})