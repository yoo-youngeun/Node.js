$(function() {
    // 마지막 비어있는 Menu 높이 조정
    let height = $("#header").height();
    let menu = $("#menu_order").height() * 5;
    let lastMenu = height - menu;
    $(".list-group-item:last-child").css({"height" : lastMenu});
    loadContent();

    // Menu 클릭 시 이벤트
    $(".menu").on("click", function () {
        let id = $(this).attr("id");
        $(".menu").removeClass("active");
        $(".menu").css({"font-weight" : "lighter"});

        $("#" + id + "").addClass("active");
        $("#" + id + "").css({
            "font-weight" : "bold"
        });

        pgLoad(id.substr(5));
    });
});

// content 변경
function loadContent() {
    $("#hiddenIframe").attr("src", "/am/hiddenForm");
    $("#content").attr("src", "/am/home");

    addHiddenForm();
}

function pgLoad(url) {
    $("#content").contents().find("#"+url+"").submit();
    addHiddenForm();
}

function addHiddenForm() {
    let hiddenForm = $("#hiddenIframe").contents().find("#hiddenForm");
    $("#content").contents().find("div").append(hiddenForm);
}
