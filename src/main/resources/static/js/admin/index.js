$(function() {
    // 마지막 비어있는 Menu 높이 조정
    let height = $("#header").height();
    let menu = $("#menu_order").height() * 5;
    let lastMenu = height - menu;
    $(".list-group-item:last-child").css({"height" : lastMenu});
    console.log($(".menu").first());

    $(".menu").first().trigger("click", true);

    // Menu 클릭 시 이벤트
    $(".menu").on("click", function () {
        let id = $(this).attr("id");
        alert(id);
        let addr = id.substr(5);
        $(".menu").removeClass("active");
        $(".menu").css({"font-weight" : "lighter"});

        $("#" + id + "").addClass("active");
        $("#content").attr("src", "/am/" + addr);
    });
});

