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
            password: $('#userpassword').val(),
            name: $('#name').val(),
            phone: $('#phonenum').val(),
            birth: $('#datetime').val()
        };

        $.ajax({
            type: 'POST',
            url: '/join',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
            success : function(data){
                alert("회원가입에 성공하셨습니다!");
                window.location.href="/login";
            },
            error:function(error){
                alert("이미 있는 아이디거나 입력을 누락하신 부분이 있습니다!");
                window.location.href="/join";
            }
        });
    }
};

main2.init();
