$(function() {
    let url = $(location).attr('href').split("/")[5];

    let viewUser = new Vue({
        // el : 연결할 영역을 지정
        el: "#viewResult",
        // data : 데이터 값
        data : {
            user : {}
        }
    });

    searchUsers();

    function searchUsers() {
        axios({
            url: '/am/user/'+url, // 통신할 웹문서
            method: 'get' // 통신 방식
        }).then(function (response) {
            console.dir(response.data);
            viewUser.user = response.data;
        });
    }

})