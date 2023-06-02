$(function() {
    // 마지막 비어있는 Menu 높이 조정
    let height = $("#header").height();
    let menu = $("#menu_order").height() * 5;
    let lastMenu = height - menu;
    $(".list-group-item:last-child").css({"height" : lastMenu});
    loadContent();
    let hiddenForm = $("iframe[name='hiddenIframe']").contents().find("#hiddenForm");
    addHiddenForm(hiddenForm);


    // Menu 클릭 시 이벤트
    $(".menu").on("click", function () {
        let id = $(this).attr("id");
        $(".menu").removeClass("active");
        $(".menu").css({"font-weight" : "lighter"});

        $("#" + id + "").addClass("active");
        $("#" + id + "").css({
            "font-weight" : "bold"
        });

        pgLoad(id.substr(5), hiddenForm);
    });
});

// content 변경
function loadContent() {
    // alert("loadContent");
    $("iframe[name='hiddenIframe']").attr("src", "/am/hiddenForm");
    $("iframe[name='content']").attr("src", "/am/home");
    console.log($("iframe[name='content']").attr('src'));
    console.log($("iframe[name='content']").contents().find("div:first-child"));
}
function pgLoad(url, hiddenForm) {
    $("iframe[name='content']").contents().find("#"+url+"").submit();
    addHiddenForm(hiddenForm);

}
function addHiddenForm(hiddenForm) {
    // if ($("iframe[name='content']").contents().find('#hiddenForm')) {
    //     $("iframe[name='content']").contents().find('#hiddenForm').remove();
    // }
    $("iframe[name='content']").contents().find('div:first-child').append(hiddenForm);
    // $("iframe[name='content']").contents().find('div:first').after(hiddenForm);
}

