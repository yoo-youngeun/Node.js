'use strict';
$(function() {
    let createUser = new Vue({
        // el : 연결할 영역을 지정
        el : "#createUser",
        // data : 데이터 값
        data : {
            user : {},
            statusList : {}
        },
        methods : {
            resetCheckId: function() {
                checkIdFail();
            },
            resetCheckPw: function() {
                let userpw = $("#userpw").val();
                let userpw_re = $("#userpw_re").val();

                if (userpw == userpw_re && userpw.length == userpw_re.length) {
                    checkPwSuccess();
                } else {
                    checkPwFail();
                }
            },
            checkId: function() {
                let userid = $("#userid");
                let url = "/am/checkUserid?userid="+userid.val();
                if (userid.val() != null && userid.val().trim().length != 0 && userid.val() != '') {
                    axios.get(url)
                        .then((response) => {
                            let checkUserid = response.data;
                            if (checkUserid == 'y' && checkUserid != 'n') {
                                alert("사용 가능한 아이디입니다.");
                                checkIdSuccess();
                            } else {
                                alert("사용 불가능한 아이디입니다.");
                                checkIdFail();
                            }
                        })
                } else {
                    alert("아이디를 입력하세요");
                    userid.focus();
                }
            },
            submitForm: function() {
                if (checkParam()){
                    let url = '/am/createUser/'+ idx;

                    let userid = $("#userid").val();
                    let userpw = $("#userpw").val();
                    let name = $("#name").val();
                    let email = $("#email").val();
                    let hp = $("#hp").val();

                    const frm = new FormData();
                    frm.append('userid', userid);
                    frm.append('userpw', userpw);
                    frm.append('name', name);
                    frm.append('email', email);
                    frm.append('hp', hp);

                    axios.post(url, frm)
                        .then((response) => {
                            // console.dir(response);
                            let checkUserid = response.data;
                            if (userid) {
                                location.href = '/am/searchUser/'+idx;
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

        }
    });

    /* Button 영역 시작*/
    $("input#close").on("click", function() {
        window.open('','_self').close();
    })

    /* Button 영역 끝*/

    function checkIdSuccess() {
        $("#checkId").val("✔");
        $("#checkId").removeClass("checkIdFail");
        $("#checkId").addClass("checkIdSuccess");
        $("#checkIdState").val("y");
    }

    function checkIdFail() {
        $("#checkId").val("중복체크");
        $("#checkId").addClass("checkIdFail");
        $("#checkId").removeClass("checkIdSuccess");
        $("#checkIdState").val("n");
    }

    function checkPwSuccess() {
        $("#checkPwS").css({
            "display" : "inline-block",
            "color" : "forestgreen"
        });
        $("#checkPwF").css("display", "none");
        $("#checkPwState").val("y");
    }

    function checkPwFail() {
        $("#checkPwF").css("display", "inline-block");
        $("#checkPwS").css("display", "none");
        $("#checkPwState").val("n");
    }

    /* parameter check */
    function checkParam() {
        let checkIdState = $("input#checkIdState");
        let checkPwState = $("input#checkPwState");
        let email = $("input#email").val();
        let hp = $("input#hp").val();

        if (checkIdState != 'y' && checkIdState == 'n') {
            alert("아이디 체크 버튼을 클릭하세요.");
        }
        
        if (checkPwState != 'y' && checkPwState == 'n') {
            alert("비밀번호가 불일치합니다.");
        }

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

