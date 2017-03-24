// 全局变量
var current_item = 0;

//隐藏时间
var section_hide_time = 2000;

$(document).ready(function () {
    //导航栏事件处理
    //handleNavigation();
    //主按钮事件处理
    //handleButton();
    //用户名注册时异步处理
    //进入首页判断cookie用户名
    //judgeUsername();
});

/**
 * 导航栏事件处理
 */
function handleNavigation() {
    $("a", '.mainmenu').click(function () {
        //alert('dsadasd');
        if (!$(this).hasClass('active')) {
            current_item = this;
            // 根据按钮显示内容
            if ($(current_item).attr('id') == 'login_link') {
                $('.section#head').fadeOut(section_hide_time);
                $('#register').hide();
                $('#login').fadeIn();
            }
            else if ($(current_item).attr('id') == 'register_link') {
                $('.section#head').fadeOut(section_hide_time);
                $('#login').hide();
                $('#register').fadeIn();
            }
            else if ($(current_item).attr('id') == 'back_link') {
                $('#login').hide();
                $('#register').hide();
                $('.section#head').fadeIn();
            }
            else {
            }
            $('a', '.mainmenu').removeClass('active');
            $(current_item).addClass('active');
        }

        return false;
    });
}

/**
 * 主按钮时间处理
 */
function handleButton() {
    $('.btn').click(function () {
        //alert('dasdas');
        if ($(this).attr('id') == 'login_btn') {
            $('.section#head').fadeOut(section_hide_time);
            $('#register').hide();
            $('#login').fadeIn();
        }
        else if ($(this).attr('id') == 'register_btn') {
            $('.section#head').fadeOut(section_hide_time);
            $('#login').hide();
            $('#register').fadeIn();
        }
        else {
        }
        $('a', '.mainmenu').removeClass('active');
    });
}


/**
 * 提交用户注册信息
 */
function submitRegister() {
    var name = $('#form-name').val();
    var password = $('#form-password').val();
    var phone = $('#form-phone').val();
    var idCardNo = $('#form-idCardNo').val();
    if (validateName(name) && validatePwd(password) && validatePhone(phone) && validateCard(idCardNo)) {
        document.register_form.submit();
        // alert("success");
    }
    else{
        // alert('worng');
        $('#head-text').hide();
        $('#error-panel').show();
        $('#error-list').empty();
        if(!validateName(name)){
            $('#error-list').append('<li>name wrong</li>');
            $('#form-name').val('');
        }
        if(!validatePwd(password)){
            $('#error-list').append('<li>password wrong</li>');
            $('#form-password').val('');
        }
        if(!validatePhone(phone)){
            $('#error-list').append('<li>phone wrong</li>');
            $('#form-phone').val('');
        }
        if(!validateCard(idCardNo)){
            $('#error-list').append('<li>idCardNo wrong</li>');
            $('#form-idCardNo').val('');
        }
    }
}

/**
 * 客栈注册
 */
function submitRegisterHostel(){
    var name = $('#form-name-hostel').val();
    var password = $('#form-password-hostel').val();
    var phone = $('#form-phone-hostel').val();
    var address = $('#form-address-hostel').val();
    if (validateName(name) && validatePwd(password) && validatePhone(phone) && validateAddress(address)) {
        document.register_form_hostel.submit();
    }
    else{
        // alert('worng');
        $('#head-text-hostel').hide();
        $('#error-panel-hostel').show();
        $('#error-list-hostel').empty();
        if(!validateName(name)){
            $('#error-list-hostel').append('<li>name wrong</li>');
            $('#form-name-hostel').val('');
        }
        if(!validatePwd(password)){
            $('#error-list-hostel').append('<li>password wrong</li>');
            $('#form-password-hostel').val('');
        }
        if(!validatePhone(phone)){
            $('#error-list-hostel').append('<li>phone wrong</li>');
            $('#form-phone-hostel').val('');
        }
        if(!validateCard(address)){
            $('#error-list-hostel').append('<li>address wrong</li>');
            $('#form-address-hostel').val('');
        }
    }
}

