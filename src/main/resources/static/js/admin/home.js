$(function() {
    let href = $(location).attr('href');
    let userList = new Vue({
        // el : 연결할 영역을 지정
        el: "#userTab",
        // data : 데이터 값
        data : {
            userList : {},
            series : {},
            labels : {}
        },
        methods: {
            viewPage : function(url) {
                window.open(url, "_target", "width=860,height=600");
            }
        },
        mounted() {

        },
        updated() { // 데이터가 변경되어 가상 DOM이 다시 렌더링되고 패치된 후 호출

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

    $(".moreLink").on("click", function() {
        let menu = $(this).attr("id");
        $(".menu", window.parent.document).removeClass("active");
        $("#"+menu, window.parent.document).addClass('active');
    })



    getStatusList();

    function getStatusList() {
        axios.get("/am/users/getStatusList")
            .then((response) => {
                userList.labels = response.data;
            })
            .catch((error) => {
                // 예외 처리
                console.dir(error);
            });
    }

    /* 비율 차트 시작 */
    let genderChart = new ApexCharts(document.querySelector("#pieChart"), {
        series: [2, 4],
        chart: {
            height: 270,
            type: 'pie',
            toolbar: {
                show: true
            }
        },
        labels : ["가입", "탈퇴"]
    }).render();
    /* 비율 차트 끝 */
})