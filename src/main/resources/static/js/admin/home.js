$(function() {
    let userList = new Vue({
        // el : 연결할 영역을 지정
        el: "#userTab",
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
            url: '/am/users/searchUsers/top', // 통신할 웹문서
            method: 'get' // 통신 방식
        }).then(function (response) {
            // console.dir(response.data);
            userList.userList = response.data;
        });
    }


})