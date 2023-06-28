$(function() {
    let url = $(location).attr('href').split("/")[5];

    let updateUser = new Vue({
        // el : 연결할 영역을 지정
        el: "#viewResult",
        // data : 데이터 값
        data : {
            user : {},
            statusList : {}
        },
        method : {
            checkStatus: function (status) {
                for (let i in updateUser.statusList) {
                    if (status == updateUser.statusList[i]) {
                        alert(status);
                        console.log($("#statusList"));
                        $("#text").text(status)
                        // $("#statusList").val(status).prop("selected", true);
                        $(this).prop("selected", true);
                        // alert(status);
                        // $("#"+status+"").prop('selected', true);
                    }
                }
            }
        }
    });

    getStatus();

    function searchUsers() {
        axios({
            url: '/am/user/'+url, // 통신할 웹문서
            method: 'get' // 통신 방식
        }).then(function (response) {
            console.dir(response.data);
            updateUser.user = response.data;

            // let status = updateUser.user.status;
            // checkStatus(status, updateUser);
        });
    }


    function getStatus() {
        axios({
            url: '/am/getStatusList', // 통신할 웹문서
            method: 'get' // 통신 방식
        }).then(function (response) {
            console.dir(response.data);
            updateUser.statusList = response.data;

            searchUsers();
        });
    }



    $("input#close").on("click", function() {
        window.open('','_self').close();
    })

    $("input#update").on("click", function() {
        location.href="/am/searchUser/";
    })
})

// function checkStatus(status, updateUser) {
//     for (let i in updateUser.statusList){
//         if (status == updateUser.statusList[i]) {
//             alert(status);
//             console.log($("#statusList"));
//             $("#text").val(status)
//             $("#statusList").val(status).prop("selected", true);
//             // alert(status);
//             // $("#"+status+"").prop('selected', true);
//         }
//     }
// }