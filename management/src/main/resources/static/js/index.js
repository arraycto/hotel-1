$(document).ready(function () {
    login = function () {
        console.log(resultLoginInfo());
    };
    register = function () {
        let content = resultRegisterInfo();
        if (content===null||content===undefined||content===""){
            return;
        }
        $.ajax({
            type: "POST",
            url: "/user/register",
            headers:{
                'Content-Type':"application/json;charset=utf-8"
            },
            data: JSON.stringify(content),
            success:function (e) {
                console.log("OK",e)
            },
            fail:function (e) {
                console.log("ERROR")
            }
        })
    };

    function resultLoginInfo() {
        let username = $(".fragment .login-fragment .username").val();
        let password = $(".fragment .login-fragment .password").val();
        return {
            "username": username,
            "password": password
        }
    }

    function resultRegisterInfo() {
        let username = $(".fragment .register-fragment .username").val();
        let password = $(".fragment .register-fragment .password").val();
        let repeatPassword = $(".fragment .register-fragment .repeat-password").val();
        let sex = $(".fragment .register-fragment input[name='sex']:checked").val();
        if (username===""||password===""||repeatPassword==="") {
            alert("请输入完整信息");
        } else if (password !== repeatPassword) {
            alert("两次密码不一致");
        } else {
            return {
                "username": username,
                "password": password,
                "sex": sex
            };
        }
    }
});