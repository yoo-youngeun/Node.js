'use strict';
let userStatus;
$(function() {
    let url = $(location).attr('href').split("/")[5];

    let updateUser = new Vue({
        // el : 연결할 영역을 지정
        el : "#updateUser",
        // data : 데이터 값
        data : {
            user : {},
            statusList : {}
        },
        methods : {
            checkStatus : function(state) {
                $("#statusList").trigger("click");
            },
            select: function(event) {
                const targetId = event.currentTarget.id;
                $("#"+targetId+"").val(userStatus).prop("selected", true);
            },
            viewPage : function(url) {
                // window.open(url, "_self", "width=860,height=600");
                location.href = url;
            },
        },
        mounted() {

        },
        updated() { // 데이터가 변경되어 가상 DOM이 다시 렌더링되고 패치된 후에 호출된다.
            $("#statusList").trigger("click");
        }
    });

    $("#result").trigger("click");

    getStatus();

    function searchUsers() {
        axios({
            url: '/am/user/'+url, // 통신할 웹문서
            method: 'get' // 통신 방식
        }).then(function (response) {
            // console.dir(response.data);
            updateUser.user = response.data;

            userStatus = updateUser.user.status;
        });
    }
    
    function getStatus() {
        axios({
            url: '/am/getStatusList', // 통신할 웹문서
            method: 'get' // 통신 방식
        }).then(function (response) {
            // console.dir(response.data);
            updateUser.statusList = response.data;

            searchUsers();
        });
    }

    function update() {
        alert("update 실행");
        let check = false;

        if (checkParam()) {
            check = true;
        } else {
            check = false;
        }
        if (check) {
            location.href = "/am/viewPage/"+url;
        }

        return check;
    }

    /* Button 영역 시작*/
    $("input#close").on("click", function() {
        window.open('','_self').close();
    })

    $("input#update").on("click", function() {
        location.href="/am/searchUser/";
    })
    /* Button 영역 끝*/

    /* parameter check */
    function checkParam() {
        let email = $("input#email").val();
        let hp = $("input#hp").val();
        let status = $("select#statusList").val();

        if (email.length == 0 || email == null) {
            alert("이메일을 입력하세요.");
            $("input#email").focus();
            return false;
        }
        if (hp.length == 0 || hp == null || hp.length != 13) {
            alert("휴대폰 번호 형식을 확인하세요.(000-0000-0000)");
            $("input#hp").focus();
            return false;
        }
        if(status.length == 0 || status == null) {
            return false;
        }

        return true;
    }
})

