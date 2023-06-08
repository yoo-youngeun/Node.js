$(function() {
    // 마지막 비어있는 Menu 높이 조정
    let height = $("#header").height();
    let menu = $("#menu_order").height() * 5;
    let lastMenu = height - menu;
    $(".list-group-item:last-child").css({"height" : lastMenu});

    // Menu 클릭 시 이벤트
    $(".menu").on("click", function () {
        let id = $(this).attr("id");
        $(".menu").removeClass("active");
        $(".menu").css({"font-weight" : "lighter"});

        $("#" + id + "").addClass("active");
        $("#" + id + "").css({
            "font-weight" : "bold"
        });

        $("#content").attr("src", "am/" + id.substr(5));
    });
});

