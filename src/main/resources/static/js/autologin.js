$(function(){
    //when click loginbutton
    $("#loginBtn").click(function(){
        if(!$("#auto").prop("checked")) {
            $.cookie("isAutoLogin", "false", {expire:-1});
            $.cookie("username", "", {expire:-1});
            $.cookie("password", "", {expire:-1});
        } else{
            //if user checked auto login
            let username = $("#username").val();
            let password = $("#password").val();
            $.cookie("isAutoLogin","true",{expire:7});
            $.cookie("username",username,{expire:7});
            $.cookie("password",password,{expire:7});
        }
    });

    //when page is loading
    if($.cookie("isAutoLogin")==="true") {
        //if user checked auto login
        $("#auto").prop("checked", true);
        $("#username").val($.cookie("username"));
        $("#password").val($.cookie("password"));
    }
});