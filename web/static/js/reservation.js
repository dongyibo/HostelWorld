/**
 * Created by dongyibo on 2017/1/13.
 */

$(document).ready(function () {
    //处理时间
    handleTime();
    //检验时间
    checkTime();
});

/**
 * 时间细节
 */
var timeDetail = function () {
    var year = parseInt($('#year').val());
    var month = parseInt($('#month').val());
    var day = parseInt($('#day').val());
    // alert(year+" "+ month+ " "+day);
    var myDate = new Date();
    myDate.setFullYear(year, month - 1, day);
    var today = new Date();
    if (myDate < today) {
        $('#date_error').text('the date is earlier than today');
        return false;
    }
    else {
        $('#date_error').text('');
        return true;
    }


}

/**
 * 检验时间
 */
function checkTime() {
    $('#year').change(timeDetail);
    $('#month').change(timeDetail);
    $('#day').change(timeDetail);
}

/**
 * 预定计划
 */
function book(btn) {
    var parent = $(btn).parent().parent();
    var pid = $(parent).next().val();
    var price = $(parent).next().next().val();
    var roomNo = $(parent).next().next().next().val();

    $('#roomNo').val(roomNo);
    $('#price').val(price);
    $('#pid').val(pid);
    $('#reservation_form').show();
    window.scroll(0,700);
    // alert(pid+" "+price+" "+roomNo);
}


/**
 * 时间处理
 */
function handleTime() {
    var timeHandler = function () {
        var numOfMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
        var year = $('#year').val();
        var month = $('#month').val();
        var num = "";
        if (month == 2 && isLeapYear(year)) {
            num = numOfMonth[month - 1] + 1;
        }
        else {
            num = numOfMonth[month - 1];
        }
        $('#day').empty();
        for (var i = 1; i <= num; i++) {
            $('#day').append('<option>' + i + '</option>>');

        }
    };
    $('#year').change(timeHandler);
    $('#month').change(timeHandler);
}

/**
 * 判断是否为闰年
 * @param year
 * @returns {boolean}
 */
function isLeapYear(year) {
    return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
}

/**
 * 提交表单
 */
function submitReservation() {
    var days = $('#days').val();
    if (validateDays(days) && timeDetail()) {
        document.reservation_form.submit();
    }
    else{
        $('#days_error').text('');
        if (!validateDays(days)){
            $('#days_error').text('days wrong');
            $('#days').val('');
        }
    }

}