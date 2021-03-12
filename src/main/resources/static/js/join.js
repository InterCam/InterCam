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
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),

        }).done(function(data){
            alert("회원가입에 성공하셨습니다!");
            window.location.href="/login";
        }).fail(function(error){
            alert(JSON.stringify(error));
            window.location.href="/join";
        })
    }
};

main2.init();
