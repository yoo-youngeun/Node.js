'use strict';

$(function() {
    // let id = $("#userid")
    // let pw = $("#userpw")
    // let width = Number($(".msg").width())+30;
    // let height = Number(id.height());
    //
    // let topId = Number(id.offset().top) + Number(height);
    // let leftId = Number(id.offset().left);
    // let topPw = Number(pw.offset().top) + Number(height);
    // let leftPw = Number(pw.offset().left);

    // $("span#msgCheckId").offset({
    //     "top" : topId,
    //     "left" : leftId
    // })
    // $("span#msgCheckPw").offset({
    //     "top" : topPw,
    //     "left" : leftPw
    // })

    let createUser = new Vue({
        // el : 연결할 영역을 지정
        el : "#createUser",
        // data : 데이터 값
        data : {
            genderList : {}
        },
        methods : {
            resetCheckId: function(event) {
                checkIdFail();

                let id = event.currentTarget.id;
                alert("resetCheckId::"+id);
                let targetid = $("#userid")
                let pw = $("#userpw")
                // let width = $("table#viewResult td").width();

            },
            checkId: function() {
                let userid = $("#userid");
                if (isId(userid.val())) {
                    let url = "/am/checkUserid?userid="+userid.val();
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
                    alert("아이디 형식을 확인하세요.");
                    userid.focus();
                }
            },
            resetCheckPw: function(event) {
                let id = event.currentTarget.id;

                alert("resetCheckPw::"+id);
                let userpw = $("#userpw");
                let userpw_re = $("#userpw_re");
                if (userpw.val().length != 0) {
                    if (isPw(userpw.val())) {
                        hidePopup(id);
                        if (userpw.val() == userpw_re.val()) {
                            checkPwSuccess();
                        } else {
                            checkPwFail();
                        }
                    } else {
                        showPopup(id);
                    }
                } else {
                    hidePopup(id);
                }
            },
            submitForm: function() {
                if (checkParam()){
                    let url = '/am/createUser/';

                    let userid = $("#userid").val();
                    let userpw = $("#userpw").val();
                    let gender = $("input[name='gender']").is(":selected").val();
                    alert(gender);
                    let name = $("#name").val();
                    let email = $("#email").val();
                    let hp = $("#hp").val();

                    const frm = new FormData();
                    frm.append('userid', userid);
                    frm.append('userpw', userpw);
                    frm.append('name', name);
                    frm.append('email', email);
                    frm.append('gender', gender);
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

    function showPopup(id) {
        if (id == "userid" || id == "userpw") {
            let obj = $("#"+id);
            obj.addClass("borderRed");
            // obj.focus(function() {
            //     $(this).css("display", "block");
            // })
            // if (id == "userid") {
            //     $("#msgCheckId").css("display", "block");
            //     $("#"+id).addClass("borderRed");
            //     $(".borderRed").focus(function() {
            //         $(this).css("display", "block");
            //     })
            // } else {
            //     $("#msgCheckPw").css("display", "block");
            //     $("#"+id).addClass("borderRed");
            //     $(".borderRed").focus(function() {
            //         $(this).css("display", "block");
            //     })
            //
            //     $("#"+id).addClass("borderRed");
            // }
        }
    }

    function hidePopup(id) {
        if (id == "userid" || id == "userpw") {
            if (id == "userid") {
                $("#msgCheckId").css("display", "none");
                $("#"+id).removeClass("borderRed");

            } else {
                $("#msgCheckPw").css("display", "none");
                $("#"+id).removeClass("borderRed");
            }
        }
    }




    getGender();

    function getGender() {
        axios.get("/am/getGenderList")
            .then((response) => {
                // console.dir(response.data);
                createUser.genderList = response.data;
            })
            .catch((error) => {
                // 예외 처리
                console.dir(error);
            });
    }

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
        let userpw = $("#userpw");

        if (checkIdState != 'y' && checkIdState == 'n') {
            alert("아이디 체크 버튼을 클릭하세요.");
            return false;
        }
        
        if (checkPwState != 'y' && checkPwState == 'n') {
            alert("비밀번호가 불일치합니다.");
            return false;
        }

        if (!isPw(userpw.val())) {
            alert("비밀번호 형식을 확인하세요.");
            return false;
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

        return true;
    }

    /* 정규식 시작 */
    // 아이디 정규식
    function isId(asValue) {
        const regExp = /^[a-zA-Z](?=.*[a-zA-Z])(?=.*[0-9]).{6,20}$/g; // 6~20자 영문+숫자 조합
        return regExp.test(asValue);
    }

    function isPw(asValue) {
        const regExp = /^[a-zA-Z0-9](?=.*[a-zA-Z])(?=.*[0-9]).{3,12}$/g;
        return regExp.test(asValue);
    }
});

