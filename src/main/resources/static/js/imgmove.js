$(function () {
    //set up size
    $(".move-img").hover(function () {
        $(this).animate({
            "background-size": "110%",
        },"fast")
    }, function () {
        $(this).animate({
            "background-size": "100%",
        },"fast")
    })
});