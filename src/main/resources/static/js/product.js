$(function () {
    //set up css style
    $(".img-small").hover(function () {
        $(this).css("border", "1px solid #4288c3");
    }, function () {
        $(this).css("border", "");
    })

    //display big picture when click
    $(".img-small").click(function () {
        let data = $(this).attr("data");
        $(".img-big").hide();
        $(".img-big[data='" + data + "']").show();
    });

    //add one to number of purchases
    $("#numUp").click(function () {
        let num = parseInt($("#num").val());
        $("#num").val(num + 1);
    });

    //minus one to number of purchases
    $("#numDown").click(function () {
        let num = parseInt($("#num").val());
        if (1 == num) {
            return;
        }
        $("#num").val(num-1);
    });

    //go to cart page
    $(".go-cart").click(function () {
        location.href = "cart.html";
    });

    //display picture equals 0
    $(".img-big:eq(0)").show();
});