/**
 * Created by dongyibo on 2017/1/12.
 */

/**
 * 验证姓名
 * @param name
 * @returns {boolean}
 */
function validateName(name) {
    var regName = /^([\u4e00-\u9fa5]{1,10}|[a-zA-Z ]{1,20})$/;
    var r = name.match(regName);
    if (r == null) {
        return false;
    }
    else {
        return true;
    }
}

/**
 * 验证地址
 * @param address
 * @returns {boolean}
 */
function validateAddress(address) {
    var regAddress= /^\S{1,20}$/;
    var r = address.match(regAddress);
    if (r == null) {
        return false;
    }
    else {
        return true;
    }
}

/**
 * 验证密码
 * @param password
 * @returns {boolean}
 */
function validatePwd(password) {
    var regPwd = /^(\w){6,20}$/;
    var r = password.match(regPwd);
    if (r == null) {
        return false;
    }
    else {
        return true;
    }
}

/**
 * 验证手机号
 * @param phone
 * @returns {boolean}
 */
function validatePhone(phone) {
    var regPhone = /^[0-9]{2}$/;
    var r = phone.match(regPhone);
    if (r == null) {
        return false;
    }
    else {
        return true;
    }
}

/**
 * 验证身份证号
 * @param card
 * @returns {boolean}
 */
function validateCard(card) {
    var regCard = /^[0-9]{2}$/;
    var r = card.match(regCard);
    if (r == null) {
        return false;
    }
    else {
        return true;
    }
}

/**
 * 验证房间号
 * @param address
 * @returns {boolean}
 */
function validateRoom(roomNo) {
    var regRoom = /^[^0][0-9]{2,3}$/;
    var r = roomNo.match(regRoom);
    if (r == null) {
        return false;
    }
    else {
        return true;
    }
}

/**
 * 验证价格
 * @param name
 * @returns {boolean}
 */
function validatePrice(price) {
    var regPrice = /(^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$)|(^[1-9]\d*$)/;
    var r = price.match(regPrice);
    if (r == null) {
        return false;
    }
    else {
        return true;
    }
}

/**
 * 验证天数
 * @param address
 * @returns {boolean}
 */
function validateDays(days) {
    var regDays = /^[1-9][0-9]?$/;
    var r = days.match(regDays);
    if (r == null) {
        return false;
    }
    else {
        return true;
    }
}
