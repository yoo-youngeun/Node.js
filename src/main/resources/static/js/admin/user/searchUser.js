'use constrict';

$(function() {
    let userList = new Vue({
        // el : 연결할 영역을 지정
        el: "#searchResult",
        // data : 데이터 값
        data : {
            userList : {}
        }
    })

    searchUsers();
    let buttHeight = $("#searchTable").height();
    $("#button.btn").css("height", buttHeight);

    function searchUsers() {
        axios({
            url: '/am/searchUsers', // 통신할 웹문서
            method: 'get' // 통신 방식
        }).then(function (response) {
            console.dir(response.data);
            userList.userList = response.data;
        });
    }

    $("#searchButt").on("click", function() {
        let userid = $("#userid").val();
        let name = $("#name").val();
        if (userid.trim().length == 0 && name.trim().length == 0) {
            alert("검색조건을 입력하세요");
        } else {
            searchUser(userid, name);
        }
    })

    function searchUser(userid, name) {
        const frm = new FormData();
        frm.append('userid', userid);
        frm.append('name', name);

        axios.post('/am/searchUser', frm)
            .then((response) => {
                if (response.data.length == 0) {
                    alert("검색 결과가 없습니다.");
                    searchUsers();
                } else {
                    alert("검색결과 : " + response.data.length + "건")
                    userList.userList = response.data;
                }
            })
            .catch((error) => {
                // 예외 처리
            });
    }
})