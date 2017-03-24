/**
 * Created by dongyibo on 2017/1/18.
 */
$(document).ready(function () {
    initIncomeAllChart();
});

/**
 * 初始化所有客栈收入统计表
 */
function initIncomeAllChart() {
    var success = function (json) {
        var dataX = [];
        var dataY = [];
        $(json.hostelName).each(function (i, value) {
            // alert(i+ "  " + value);
            dataX.push(value);
        });
        // var i = 0;
        for (var i in json.hostelName) {
            var p = {};
            p.name = json.hostelName[i];
            p.value = parseFloat(json.hostelIncome[i]);
            dataY.push(p);
        }
        var incomeChart = echarts.init(document.getElementById('income_all_chart'));
        option = {
            title : {
                text: 'hostel income',
                subtext: '',
                x:'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'right',
                data: dataX
            },
            series: [
                {
                    name: 'income',
                    type: 'pie',
                    radius: '55%',
                    center: ['50%', '60%'],
                    data: dataY,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        incomeChart.setOption(option);
    };

    var params = {};
    $.ajax({
        type: "POST",
        url: "manager-getIncome",
        data: params,
        dataType: "json", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
        success: success,
        error: function (xhr, type) {
            alert(xhr.status);
            alert(xhr.readyState);
            alert(type);
        }
    });
}