/**
 * Created by dongyibo on 2017/1/11.
 */
$(document).ready(function () {

});

/**
 * 选择状态
 * @param tid
 * @param select
 */
function selectState(tid, select) {
    // alert(tid);
    var state = $(select).val();
    var url = "register-updateState";
    var args = {"tid": tid, "state": state};
    $.post(url, args, function (data) {
        // //表示可用
        //alert(data);
    });
}

/**
 * 登记验证
 */
function submitRegister() {
    var name = $('#name').val();
    var IdCardNo = $('#IdCardNo').val();
    var days = $('#days').val();
    if (validateName(name) && validateCard(IdCardNo) && validateDays(days)) {
        document.register_register_form.submit();
    }
    else {
        $('#name_error').text('');
        $('#IdCardNo_error').text('');
        $('#days_error').text('');
        if (!validateName(name)) {
            $('#name_error').text('name wrong');
            $('#name').val('');
        }
        if (!validateCard(IdCardNo)) {
            $('#IdCardNo_error').text('IdCard No. wrong');
            $('#IdCardNo').val('');
        }
        if (!validateDays(days)){
            $('#days_error').text('days wrong');
            $('#days').val('');
        }
    }
    // alert(name + " " + idCardNo + " " + days + " " + addition);
}