/**
 * Created by dongyibo on 2017/1/9.
 */

$(document).ready(function () {

});

/**
 * 发布计划
 */
function releasePlan() {
    var roomNo = $('#roomNumber').val();
    var price = $('#price').val();

    if (validateRoom(roomNo) && validatePrice(price)) {
        document.release_plan_form.submit();
    }
    else {
        $('#roomNumber_error').text('');
        $('#price_error').text('');
        if (!validateRoom(roomNo)) {
            $('#roomNumber_error').text('room number wrong');
            $('#roomNumber').val('');
        }
        if (!validatePrice(price)) {
            $('#price_error').text('price wrong');
            $('#price').val('');
        }
    }
}


/**
 * 删除计划
 * @param pid
 */
function deletePlan(pid, hid) {
    // alert(hid);
    if(window.confirm("Are you sure you want to delete this plan?")){
        window.location="/HostelWorld/plan-delete?pid="+pid+"&hid="+hid;
    }
}
