var main1 = {
    init : function () {
        var _this = this;
        $('#btn').on('click', function () {
            _this.send();
        });
    },
    send : function () {
        var data = {
            title: $("#title").val(),
            content: $("#content").val()
        };

        $.ajax({
            type: 'POST',
            url: '/asking',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
        }).done(function(){
            alert("질문이 보내졌습니다!");
            window.location.href="/";
        }).fail(function(error){
            alert("어떠한 이유로 질문이 보내지지 않았습니다!");
            window.location.href="/";
        })
    }
};

main1.init();
