'use strict';

$(function() {
    let createUser = new Vue({
        // el : 연결할 영역을 지정
        el : "#createUser",
        // data : 데이터 값
        data : {
            genderList : {}
        },
        methods : {
            resetCheckPw: function(event) { // 비밀번호 형식 체크
                checkPw(event);
            },
            checkId: function() { // 아이디 중복 확인
                checkId();
            },
            submitForm: function() { // 유저 등록
                if (checkParam()){
                    let url = '/am/users/createUser/';

                    let userid = $("#userid").val();
                    let userpw = $("#userpw").val();
                    let genderList = $("input[name='gender']");
                    let gender = "";
                    genderList.each(function() {if($(this).is(":checked")){gender = $(this).val();}})
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
                            let userid = response.data;
                            if (userid != null) {
                                alert("등록 완료");
                                location.href = '/am/users/searchUser/'+userid;
                                // 부모창 유저 리스트 다시 로드
                                opener.document.getElementById("reset").click();
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
            $("input[name='gender']").first().prop("checked", true); // 첫번째 성별 체크
        }
    });

    /* gender list 가져와 select option 추가 시작 */
    getGender();

    function getGender() {
        axios.get("/am/users/getGenderList")
            .then((response) => {
                // console.dir(response.data);
                createUser.genderList = response.data;
            })
            .catch((error) => {
                // 예외 처리
                console.dir(error);
            });
    }
    /* gender list select option 추가 끝 */
    
    /* 비밀번호 형식 메시지 - show, hide 시작*/
    // 비밀번호 형식 메시지 - show
    function showPopup(id) {
        let msg = $("#"+id).parent().next();
        if (msg != null) {
            if (id == "userid" || id == "userpw" || id == "userpw_re") {
                msg.css("display", "inline-block");
            }
        }
    }

    // 비밀번호 형식 메시지 - hide
    function hidePopup(id) {
        let msg = $("#"+id).parent().next("span");
        if (msg != null) {
            if (id == "userid" || id == "userpw" || id == "userpw_re") {
                msg.css("display", "none");
            }
        }
    }
    /*비밀번호 형식 메시지 - show, hide 끝*/


    /* 아이디 중복확인 시작 */
    function checkId() {
        let userid = $("#userid");
        if (userid.val().length != 0) {
            if (isId(userid.val())) {
                let url = "/am/users/checkUserid?userid="+userid.val();
                axios.get(url).then((response) => {
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
        } else {
            alert("아이디를 입력하세요.");
            userid.focus();
        }
    }
    /* 아이디 중복확인 끝 */

    /* 비밀번호 keyup event 시작*/
    function checkPw(evnet) {
        let id = event.currentTarget.id;

        let userpw = $("#userpw");
        let userpw_re = $("#userpw_re");
        let obj = $("#"+id);

        if (obj.val().length > 0) {
            if (isPw(obj.val())) {
                hidePopup(id);
            } else {
                showPopup(id);
            }
            if(userpw_re.val().length <= 0) {
                checkPwReset();
            } else {
                if (userpw.val() == userpw_re.val() && userpw.val().length == userpw_re.val().length && isPw(userpw_re.val())) {
                    checkPwSuccess();
                    hidePopup(id);
                } else {
                    checkPwFail();
                }
            }
        }
    }
    /* 비밀번호 keyup event 끝*/

    /* id, 비밀번호 형식 체크여부 시작*/
    // 아이디 체크 성공
    function checkIdSuccess() {
        $("#checkId").val("✔");
        $("#checkId").removeClass("checkIdFail");
        $("#checkId").addClass("checkIdSuccess");
        $("#checkIdState").val("y");
    }

    // 아이디 체크 실패
    function checkIdFail() {
        $("#checkId").val("중복체크");
        $("#checkId").addClass("checkIdFail");
        $("#checkId").removeClass("checkIdSuccess");
        $("#checkIdState").val("n");
    }

    // 비밀번호 체크 성공
    function checkPwSuccess() {
        $("#checkPwS").css({
            "display" : "inline-block",
            "color" : "forestgreen"
        });
        $("#checkPwF").css("display", "none");
        $("#checkPwState").val("y");
    }

    // 비밀번호 체크 실패
    function checkPwFail() {
        $("#checkPwF").css("display", "inline-block");
        $("#checkPwS").css("display", "none");
        $("#checkPwState").val("n");
    }

    // 비밀번호 체크 초기화
    function checkPwReset() {
        $("#checkPwF").css("display", "none");
        $("#checkPwS").css("display", "none");
        $("#checkPwState").val("n");
    }
    /* id, 비밀번호 형식 체크여부 표시 끝*/

    /* parameter check 시작 */
    function checkParam() {
        let checkIdState = $("input#checkIdState").val();
        let checkPwState = $("input#checkPwState").val();
        let email = $("input#email");
        let name = $("input#name");
        let hp = $("input#hp");
        let userpw = $("#userpw");

        if (checkIdState != 'y' && checkIdState == 'n') {
            alert("아이디 체크 버튼을 클릭하세요.");
            return false;
        }

        if (name.val().length == 0 || name == null) {
            alert("이름을 입력하세요.");
            name.focus();
            return false;
        }

        if (!isName(name.val())) {
            alert("이름 형식을 확인하세요.");
            name.focus();
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

        if (email.val().length == 0 || email == null) {
            alert("이메일을 입력하세요.");
            email.focus();
            return false;
        }

        if (!isEmail(email.val())) {
            alert("이메일 형식을 확인하세요.");
            email.focus();
            return false;
        }

        if (!isHp(hp.val())) {
            alert("연락처 형식을 확인하세요.");
            hp.focus();
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

    /* 정규식 시작 */
    // 아이디 정규식
    function isId(asValue) {
        const regExp = /^([A-za-z]{0,0})(?=.*[a-zA-Z])(?=.*[0-9]).{6,20}$/g; // 6~20자 영문+숫자 조합
        return regExp.test(asValue);
    }

    function isName(asValue) {
        const regExp = /^[가-힣]{2,8}$/;
        return regExp.test(asValue);
    }

    // 비밀번호 정규식
    function isPw(asValue) {
        const regExp = /^.*(?=^.{6,20}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;; // 6~20자 영문+숫자+특수문자 조합
        return regExp.test(asValue);
    }

    // 이메일 정규식
    function isEmail(asValue) {
        const regExp = /(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))/;
        return regExp.test(asValue);
    }

    // 연락처 정규식
    function isHp(asValue) {
        const regExp = /^\d{3}-\d{4}-\d{4}$/;
        return regExp.test(asValue);
    }
});

