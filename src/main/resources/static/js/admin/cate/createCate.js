'use strict';

$(function() {

    let createCate = new Vue({
        // el : 연결할 영역을 지정
        el : "#createCate",
        // data : 데이터 값
        data : {
            cateTypeList : {},
            adminList : {}
        },
        methods : {
            getCateType: function() {
                getCateTypeList();
                $("#cateList").val("ETC").prop("selected", true);
            },
            getAdmin: function() {
                getAdminList();
            },
            submitForm: function() { // 유저 등록
                if (checkParam()){
                    let url = '/am/cate/createCate';

                    let type = $("#cateList").val();
                    let title = $("#title").val();
                    let adminId = $("#adminList").val();

                    const frm = new FormData();
                    frm.append('type', type);
                    frm.append('title', title);
                    frm.append('adminId', adminId);

                    axios.post(url, frm)
                        .then((response) => {
                            let createId = response.data;
                            if (createId != null) {
                                alert("등록 완료");
                                // location.href = '/am/cate/searchCate/'+cateId;
                                // // 부모창 유저 리스트 다시 로드
                                // opener.document.getElementById("reset").click();
                            } else {
                                alert("등록 실패");
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
            let today = new Date();
            today = today.toISOString().slice(0, 10);

            $("input[type=date]").val(today);
            $("#cateList").trigger("click");
        }
    });

    /* gender list 가져와 select option 추가 시작 */

    /* 상태값 리스트 리턴 */
    function getCateTypeList() {
        axios({
            url: '/am/cate/getCateTypeList', // 통신할 웹문서
            method: 'get' // 통신 방식
        }).then(function (response) {
            createCate.cateTypeList = response.data;
        });
    }

    getAdminList();
    /* 상태값 리스트 리턴 */
    function getAdminList() {
        axios({
            url: '/am/cate/getAdmin', // 통신할 웹문서
            method: 'get' // 통신 방식
        }).then(function (response) {
            // console.dir(response.data);
            createCate.adminList = response.data;
        });
    }

    /* parameter check 시작 */
    function checkParam() {
        let type = $("select#cateList");
        let title = $("input#title");

        if (type.val() == null || type.val().length == 0) {
            alert("유형을 선택하세요.");
            return false;
        }

        if (title.val() == null || title.val().length <= 0) {
            alert("카테고리명을 입력하세요.");
            return false;
        }

        return true;
    }
    /* parameter check 끝 */
    
    /* Button 영역 시작*/
    $("input#close").on("click", function() {
        window.open('','_self').close();
    })
    /* Button 영역 끝*/
});

