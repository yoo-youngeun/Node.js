$(function() {
    // 마지막 비어있는 Menu 높이 조정
    let height = $("#header").height();
    let menu = $("#menu_order").height() * 5;
    let lastMenu = height - menu;
    $(".list-group-item:last-child").css({"height" : lastMenu});

    $("#content").attr("src", "/am/users/home");
    $("#logo").click(function() {
        window.location.reload();
    })

    // Menu 클릭 시 이벤트
    $(".menu").bind("click", function () {
        let id = $(this).attr("id");
        let menu = id.substr(5).replace("_","/");
        $(".menu").removeClass("active");
        $(".menu").css({"font-weight" : "lighter"});

        $("#" + id + "").addClass("active");
        $("#content").attr("src", "/am/" + menu);
    });
});

