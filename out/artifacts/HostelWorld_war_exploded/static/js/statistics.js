/**
 * Created by dongyibo on 2017/1/17.
 */
$(document).ready(function () {
    initIncomeChart(window.hid);

});

/**
 * 初始化客栈收入统计图表
 */
function initIncomeChart(hid) {
    var params = {hid: hid};

    var success = function (json) {
        var date = [];
        var data = [];
        $(json.list1).each(function (i, value) {
            // alert(i+ "  " + value);
            date.push(value);
        });
        $(json.list2).each(function (i, value) {
            // alert(i+ "  " + value);
            data.push(parseFloat(value))
        });
        date.reverse();
        data.reverse();

        var incomeChart = echarts.init(document.getElementById('income_chart'));
        option = {
            color: ['#3398DB'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: date,
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: 'income',
                    type: 'bar',
                    barWidth: '60%',
                    data: data
                }
            ]
        };
        incomeChart.setOption(option);
    }

    $.ajax({
        type: "POST",
        url: "data-getIncomePerMonth",
        data: params,
        dataType: "json", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
        success: success,
        error: function (json) {
            alert("json=" + json);
            return false;
        }
    });

}

/**
 * 选择月份
 */
function selectMonth(hid) {
    var time = $('#month_data').val();
    if (time == "none") {
        return;
    }
    window.location = "/HostelWorld/hostel-monthDetail?hid=" + hid + "&time=" + time;
}