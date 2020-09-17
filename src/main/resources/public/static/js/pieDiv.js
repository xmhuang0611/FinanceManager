function drawPie() {
    var getIncomeValue = function (getString) {
        var dIValue = [0,0,0,0];
        for (var i = 0; i < getString.length; i++) {
            switch (getString[i].description) {
                case "Transfer":
                    dIValue[0] += getString[i].value;
                    continue;
                case "Investment income":
                    dIValue[1] += getString[i].value;
                    continue;
                case "Refund":
                    dIValue[2] += getString[i].value;
                    continue;
                case "Wage":
                    dIValue[3] += getString[i].value;
                    continue;
            }
        }
        return dIValue;
    }


    var getCategory = function (string) {
        var dELabel = [];
        for (var i = 0; i < string.length; i++) {
            dELabel[i] = string[i].name;
        }
        return dELabel
    }

    function updateExpensePie(Category) {
        var myChart = echarts.init(document.getElementById('main0'));

        var dataExpensesValue = new Array([Category.length]);
        for (var i = 0; i < Category.length; i++) {
            dataExpensesValue[i] = Math.ceil(Math.random() * 100);
        }
        var dataExpensesLabel = getCategory(Category);
        //console.log(Category);

        var mydata = new Array([dataExpensesLabel.length]);
        for (var i = 0; i < dataExpensesLabel.length; i++) {
            mydata[i] = {
                value: dataExpensesValue[i],
                name: dataExpensesLabel[i],
            }
        }

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: 'Expense Category',
                left: 'center',
                fontSize: 30,
                padding: [0, 0, 0, 0]
            },
            tooltip: {
                trigger: 'item',
                formatter: '{a} <br/>{b} : {c} ({d}%)',

            },
            legend: {
                icon: 'roundRect',
                type: 'scroll',
                orient: 'vertical',
                left: '2%',
                data: dataExpensesLabel,
                padding: [110, 0, 0, 50]
            },
            series: [{
                name: 'Account',
                type: 'pie',
                radius: '70%',
                center: ['60%', '60%'],
                data: mydata,
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }],
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option, true);
        //}
    }

    function updateIncomePie(getIncome) {

        var names = ["Transfer", "Investment income", "Refund", "Wage"];
        var color = ["#e4ee52", "#75e772", "#e55ca2", "#60d4e7"];
        var value = getIncomeValue(getIncome);
        console.log(value);

        var dataIncome = new Array([names.length]);
        for (var i = 0; i < names.length; i++) {
            dataIncome[i] = {
                "label": names[i],
                "value": parseFloat(value[i]),
                "color": color[i]
            }
        };
        console.log(value);
        var pie = new d3pie("main1", {
            "header": {
                "title": {
                    "text": "Account Income",
                    "color": "#2f0c1c",
                    "fontSize": 25,
                    "font": "courier"
                },
                "subtitle": {
                    "color": "#999999",
                    "fontSize": 10,
                    "font": "courier"
                },
                "location": "pie-center",
                "titleSubtitlePadding": 10
            },
            "footer": {
                "color": "#999999",
                "fontSize": 10,
                "font": "open sans",
                "location": "bottom-left"
            },
            "size": {
                "canvasWidth": 750,
                "pieInnerRadius": "75%",
                "pieOuterRadius": "78%"
            },
            "data": {
                "content": dataIncome,
            },
            "labels": {
                "outer": {
                    "format": "label-percentage1",
                    "pieDistance": 20
                },
                "inner": {
                    "format": "none"
                },
                "mainLabel": {
                    "fontSize": 15
                },
                "percentage": {
                    "color": "#868ca3",
                    "fontSize": 15,
                    "decimalPlaces": 0
                },
                "value": {
                    "color": "#cccc43",
                    "fontSize": 15
                },
                "lines": {
                    "enabled": true,
                    "style": "straight"
                },
                "truncation": {
                    "enabled": true
                }
            },
            "effects": {
                "pullOutSegmentOnClick": {
                    "effect": "linear",
                    "speed": 400,
                    "size": 8
                }
            },
            "misc": {
                "colors": {
                    "segmentStroke": "#000000"
                }
            }
        });
        //}
    }


    $.ajax(
        {
            url: "/expenses/details",
            dataType:"json",
            type: "GET",
            // data: formData,
            // processData: false,
            contentType: "application/json",
            success: function (data) {
                // var jsonData = $.parseJSON(data);
                // updatePieChart(jsonData);
                // updateDetail(jsonData);
                console.log(data);
                updateExpensePie(data);

            },
            error: function (error) {
                console.log(error);
            }
        });

    $.ajax(
        {
            url: "/income/details",
            dataType:"json",
            type: "GET",
            // data: formData,
            // processData: false,
            contentType: "application/json",
            success: function (data) {
                // var jsonData = $.parseJSON(data);
                // updatePieChart(jsonData);
                // updateDetail(jsonData);
                console.log(data);
                updateIncomePie(data);

            },
            error: function (error) {
                console.log(error);
            }
        });
}