/**
 * Created by dongyibo on 2017/1/14.
 */
$(document).ready(function () {

});

/**
 * 显示充值
 */
function showRecharge() {
    $('#recharge_btn').hide();
    $('#recharge_div').show();
}

/**
 * 隐藏充值
 */
function hideRecharge() {
    $('#recharge_div').hide();
    $('#recharge_btn').show();
}

/***
 * 积分兑换
 */
function convertible(vcid) {
    var point = parseInt($('#point_span').text());
    var deposit = parseFloat($('#deposit_span').text());
    // alert(vcid + "  "+ point);
    var url = "card-convert";
    var args = {"vcid": vcid};
    $.post(url, args, function (data) {
        var k = parseInt(data);
        if (k > 0) {
            var nowPoint = point - k * 500;
            var nowDeposit = deposit + 40 * k;
            $('#point_span').text(nowPoint);
            $('#deposit_span').text(nowDeposit);
        }
        else {
            alert("insufficient points");
        }
        // alert(k + "  " + point +"  "+deposit);
    });
}

/**
 * 充值
 */
function recharge(vcid) {
    var money = $('#money_input').val();
    if (validatePrice(money)) {
        // 提交
        var url = "card-recharge";
        var args = {"vcid": vcid, "money": money};
        $.post(url, args, function (data) {
            var deposit = parseFloat(data);
            if (deposit >= 0) {
                $('#deposit_span').text(deposit);
                hideRecharge();
            }
            else {
                alert("insufficient bank card balance");
            }
        });
    }
    else {
        alert("wrong format");
    }

}