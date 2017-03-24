var edit = function () {
    $('.input_data').removeAttr("readonly");
    $('.input_radio_data').removeAttr('disabled');
    $('#check_btn_div').css('display', 'none');
    $('#edit_btn_div').css('display', 'block');
};

var resetPWD = function () {
    if ($('#pwd_btn').hasClass('active')) {
        return false;
    }
    else {
        $('#pwd_btn').addClass('active');
        $('#data_btn').removeClass('active');
        $('#info_div').css('display', 'none');
        $('#password_div').css('display', 'block');
    }
};

var diff_flag = false;

$(document).ready(function () {
    //个人信息与重置密码切换
    switchArea();
    //个人信息面板编辑与展示的切换
    dataPanelDetail();
    //处理两次密码不同
    handlePwdDifferent();
    //处理新旧密码一样
    handlePwdSame();
});

/**
 * 个人信息与重置密码切换
 */
function switchArea() {
    //个人信息按钮
    $('#data_btn').click(function () {
        if ($(this).hasClass('active')) {
            return false;
        }
        else {
            $(this).addClass('active');
            $('#pwd_btn').removeClass('active');
            $('#password_div').css('display', 'none');
            $('#info_div').css('display', 'block');
        }
    });
    //重置密码按钮
    $('#pwd_btn').click(resetPWD);
}

/**
 * 个人信息面板编辑与展示的切换
 */
function dataPanelDetail() {
    $('#edit_btn').click(edit);

}

/**
 * 提交修改信息
 */
function updatePhone(vid) {
    //校验
    var url = "user-updatePhone";
    var phone = $('#phone').val();
    if (validatePhone(phone)){
        $('#phone_error').text('');
        var args = {"vid": vid, "phone": phone};
        $.post(url, args, function (data) {
            // //表示可用
            alert("update completed");
        });
    }
    else{
        $('#phone').val('');
        $('#phone_error').text('phone number wrong');
    }

}

/**
 * 修改客栈电话
 */
function updateHostelPhone(hid,phoneDB) {
    var url = "hostel-updatePhone";
    var phone = $('#phone').val();
    if (validatePhone(phone)){
        $('#phone_error').text('');
        var args = {"hid": hid, "phone": phone};
        $.post(url, args, function (data) {
            // //表示可用
            alert("changes will be approved by the manager");
            $('#phone').val(phoneDB);
        });
    }
    else{
        $('#phone').val('');
        $('#phone_error').text('phone number wrong');
    }
}

/**
 * 处理两次密码输入不同
 */
function handlePwdDifferent() {
    var newPWD = document.getElementById('new_password');
    var againPWD = document.getElementById('again_password');
    var againPrompt = document.getElementById('again_pwd_prompt');
    againPWD.onkeyup = function () {
        if (this.value != newPWD.value && this.value != '') {
            againPrompt.innerHTML = 'two different passwords';
            diff_flag = true;
        }
        else {
            againPrompt.innerHTML = '';
            diff_flag = false;
        }
    }

    newPWD.onkeyup = function () {
        if (this.value != againPWD.value && this.value != '') {
            againPrompt.innerHTML = 'two different passwords';
            diff_flag = true;
        }
        else {
            againPrompt.innerHTML = '';
            diff_flag = false;
        }
    }
}

/**
 * 根据密码是否一致行动
 */
function confirmPwd() {
    var prompt = document.getElementById('new_pwd_prompt');
    if (!diff_flag) {
        var newPWD = $('#new_password').val()
        if (validatePwd(newPWD)){
            document.password_form.submit();
        }
        else{
            prompt.innerHTML = 'new password wrong';
        }
    }
}

/**
 * 处理新旧密码一样
 */
function handlePwdSame() {
    var oldPWD = document.getElementById('old_password');
    var newPWD = document.getElementById('new_password');
    var prompt = document.getElementById('new_pwd_prompt');
    newPWD.onchange = function () {
        if (this.value == oldPWD.value && this.value != '') {
            prompt.innerHTML = 'new password same as the old one';
            this.value = '';
        }
        else {
            prompt.innerHTML = '';
        }
    };

    oldPWD.onchange = function () {
        if (this.value == newPWD.value && this.value != '') {
            prompt.innerHTML = 'new password same as the old one';
            newPWD.value = '';
        }
        else {
            prompt.innerHTML = '';
        }
    };
}