(function ($) {
    $.getUrlParam = function (name) {
        let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        let match = window.location.search.substring(1).match(reg);
        if (match != null) {
            return decodeURI(match[2]);
        }
        return null;
    };
})(jQuery);
/*
(function ($) {
    $.getUrlParam = function (name) {
        console.log(name);
        let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        let matchString = window.location.search.substr(1).match(reg);
        if (matchString != null){
            return decodeURI(matchString[2]);
        }
        return null;
    }
})(jQuery);*/
