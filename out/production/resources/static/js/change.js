var main2 = {
    init : function () {
        var _this = this;
        $('#btn').on('click', function () {
            _this.save();
        });
    },
    save : function () {
        var data = {
            username: $('#useremail').val(),
            name: $('#name').val(),
            phone: $('#phonenum').val()
        };

        $.ajax({
            type: 'POST',
            url: '/change',
            dataType: 'application/json; charset=utf-8',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
            success : function(){
                alert("해당 아이디에 변경된 비밀번호가 전송되었습니다!");
                window.location.href="/";
            },
            error:function(error){
                alert("없는 계정이거나 이름, 연락처가 맞지 않습니다!");
                window.location.href="/change";
            }
        });
    }
};

main2.init();
