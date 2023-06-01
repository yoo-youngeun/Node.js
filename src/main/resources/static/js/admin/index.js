$(function() {
    let height = $("#header").height();
    let menu = $("#menu_order").height() * 5;
    let lastMenu = height - menu;
    $("#content").attr("src", "/am/hiddenForm");
    $("#hiddenIframe").attr("src", "/am/hiddenForm");
    // 마지막 비어있는 Menu 높이 조정
    $(".list-group-item:last-child").css({"height" : lastMenu});

    // Menu 클릭 시 이벤트
    $(".menu").on("click", function () {
        let id = $(this).attr("id");
        $(".menu").removeClass("active");
        $(".menu").css({"font-weight" : "lighter"});
        // console.log("id::::"+id.substr(5));

        let hiddenForm = $("#hiddenIframe").contents().find("#hiddenForm");
        pgLoad(id.substr(5), hiddenForm);

        $("#" + id + "").addClass("active");
        $("#" + id + "").css({
            "font-weight" : "bold"
        });
    });
});

// content 변경
function pgLoad(url, hiddenForm) {
    console.log(url);
    $("#content").contents().find("#"+url+"").submit();
    $("#content").after(hiddenForm);
}