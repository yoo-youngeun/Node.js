'use strict';
let status;
$(function() {
    let url = $(location).attr('href').split("/")[5];
    let selectBox;
    let updateUser = new Vue({
        // el : 연결할 영역을 지정
        el: "#viewResult",
        // data : 데이터 값
        data : {
            user : {},
            statusList : {}
        },
        methods : {
            checkStatus : function(state) {
                alert("checkStatus 실행됨")
                $("#statusList").trigger("click")
            },
            select: function(event) {
                alert("select 실행됨")
                const targetId = event.currentTarget.id;
                selectBox = $("#"+targetId+"");

                selectState(targetId);
            },
        },
        mounted() {
            alert("mount 실행");
            const firstEl = $("#statusList");
            console.log(firstEl)
            // this.select();


            selectBox.dispatchEvent(new Event("click"));
        }
    });

    // function clickzz(object) {
    //     console.log(object);
    // }

    getStatus();

    function clickzz(object) {
        console.log(object);
    }

    function selectState(targetId) {
        alert("selectState 실행:::::::::::::" + targetId)
        $("#"+targetId+"").val(status).prop("selected", true);

        const selectBox = $("#"+targetId+"");

        console.log(selectBox);
    }

    function searchUsers() {
        axios({
            url: '/am/user/'+url, // 통신할 웹문서
            method: 'get' // 통신 방식
        }).then(function (response) {
            // console.dir(response.data);
            updateUser.user = response.data;

            status = updateUser.user.status;
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
    
    
    /* Button 영역 시작*/
    $("input#close").on("click", function() {
        window.open('','_self').close();
    })

    $("input#update").on("click", function() {
        location.href="/am/searchUser/";
    })
    /* Button 영역 끝*/


})

