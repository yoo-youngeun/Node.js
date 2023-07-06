'use strict';
$(function() {
    let CateStatus;
    let idx = $(location).attr('href').split("/")[5];

    let updateCate = new Vue({
        // el : 연결할 영역을 지정
        el : "#updateCate",
        // data : 데이터 값
        data : {
            Cate : {},
            statusList : {}
        },
        methods : {
            checkStatus : function(state) {
                $("#statusList").trigger("click");
            },
            select: function(event) {
                const targetId = event.currentTarget.id;
                $("#"+targetId+"").val(CateStatus).prop("selected", true);
            },
            viewPage : function(url) {
                window.open(url, "_self", "width=860,height=600");
            },
            submitForm: function() {
                if (checkParam()){
                    let url = '/am/cate/updateCate/'+ idx;

                    let Cateid = $("#Cateid").val();
                    let name = $("#name").val();
                    let email = $("#email").val();
                    let hp = $("#hp").val();
                    let regDate = $("#regDate").val();
                    let status = $("#statusList").val();

                    const frm = new FormData();
                    frm.append('Cateid', Cateid);
                    frm.append('name', name);
                    frm.append('email', email);
                    frm.append('hp', hp);
                    frm.append('regDate', regDate);
                    frm.append('status', status);

                    axios.post(url, frm)
                        .then((response) => {
                            // console.dir(response);
                            let Cateid = response.data;
                            if (Cateid != "" && Cateid != null && Cateid.length != 0) {
                                location.href = '/am/cate/searchCate/'+idx;
                                alert("수정 완료");
                                // 부모창 유저 리스트 다시 로드
                                opener.document.getElementById("reset").click();
                            } else {
                                alert("수정 실패");
                            }
                        })
                        .catch((error) => {
                            // 예외 처리
                            console.dir(error);
                        });
                }
            }
        },
        mounted() {

        },
        updated() { // 데이터가 변경되어 가상 DOM이 다시 렌더링되고 패치된 후 호출
            $("#statusList").trigger("click");
        }
    });

    $("#result").trigger("click");

    getStatus();

    /* 사용자 정보 리턴 */
    function searchCate() {
        axios({
            url: '/am/cate/cate/'+idx, // 통신할 웹문서
            method: 'get' // 통신 방식
        }).then(function (response) {
            // console.dir(response.data);
            updateCate.Cate = response.data;
            CateStatus = updateCate.Cate.status;
        });
    }

    /* 상태값 리스트 리턴 */
    function getStatus() {
        axios({
            url: '/am/cate/getStatusList', // 통신할 웹문서
            method: 'get' // 통신 방식
        }).then(function (response) {
            // console.dir(response.data);
            updateCate.statusList = response.data;

            searchCate();
        });
    }

    /* Button 영역 시작*/
    $("input#close").on("click", function() {
        window.open('','_self').close();
    })

    $("input#update").on("click", function() {
        location.href="/am/cate/searchCate/";
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

