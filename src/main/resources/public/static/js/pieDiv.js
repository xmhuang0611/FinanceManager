var monthTime;
function drawPie(month) {

    var getIncomeValue = function (getString) {
        var year = 2020;
        var incomeValueString = [];
        for(let i=0, len=getString.length; i<len; i++) {
            let itemDate = new Date(getString[i].date);
            if(itemDate.getFullYear() === year && itemDate.getMonth() === (month-1)){
                var _obj = JSON.stringify(getString[i]);
                incomeValueString.push(JSON.parse(_obj));
            }
        }

        var dIValue = [0, 0, 0, 0];
        for (var i = 0; i < incomeValueString.length; i++) {
            switch (getString[i].description) {
                case "Transfer":
                    dIValue[0] += incomeValueString[i].value;
                    continue;
                case "Investment income":
                    dIValue[1] += incomeValueString[i].value;
                    continue;
                case "Refund":
                    dIValue[2] += incomeValueString[i].value;
                    continue;
                case "Wage":
                    dIValue[3] += incomeValueString[i].value;
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

    function updateExpensesCategory(CategoryValue) {

        $.ajax(
            {
                url: "/expenses/categories",
                dataType: "json",
                type: "GET",
                // data: formData,
                // processData: false,
                contentType: "application/json",
                success: function (data) {
                    // var jsonData = $.parseJSON(data);
                    // updatePieChart(jsonData);
                    // updateDetail(jsonData);
                    console.log(data);
                    updateCategory(data);

                },
                error: function (error) {
                    console.log(error);
                }
            });

        function updateCategory(Category) {

            var getcategoryID = function (string) {
                var cateID = 0;
                for (var i = 0; i < Category.length; i++) {
                    if (string == Category[i].name) {
                        cateID = i;
                        break;
                    }
                }
                return cateID
            }

            var myChart = echarts.init(document.getElementById('main0'));
            let selectDate = document.getElementById("dateInput").value;
            // var selectDate = "2020-9";
            var categoryNum = Category.length;

            var dataExpensesValue = new Array([categoryNum]);
            for (var i = 0; i < categoryNum; i++) {
                dataExpensesValue[i] = 0.0;
            }
            var cDate = "";
            var categoryValueNum = CategoryValue.length;
            var categoryID = 0;
            for (var i = 0; i < categoryValueNum; i++) {
                cDate = CategoryValue[i].date;
                if (selectDate && selectDate !== ''
                    && parseInt(selectDate.split('-')[1]) === (new Date(cDate).getMonth() + 1)
                    && parseInt(selectDate.split('-')[0]) === new Date(cDate).getFullYear()) {
                    categoryID = getcategoryID(CategoryValue[i].categoryName);
                    dataExpensesValue[categoryID] += CategoryValue[i].value;
                }
            }

            // var dataExpensesValue = new Array([Category.length]);
            // for (var i = 0; i < Category.length; i++) {
            //     dataExpensesValue[i] = Math.ceil(Math.random() * 100);
            // }
            //var dataExpensesValue = getCategoryValue(CategoryValue);

            var dataExpensesLabel = getCategory(Category);

            var mydata = new Array([dataExpensesLabel.length]);
            for (var i = 0; i < dataExpensesLabel.length; i++) {
                mydata[i] = {
                    value: dataExpensesValue[i].toFixed(2),
                    name: dataExpensesLabel[i],
                }
            }

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: 'Expense Category',
                    left: 'center',
                    fontSize: 35,
                    padding: [20, 0, 0, 0]
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b} : {c} ({d}%)',

                },
                legend: {
                    icon: 'roundRect',
                    type: 'scroll',
                    orient: 'vertical',
                    left: '3%',
                    data: dataExpensesLabel,
                    padding: [150, 100, 0, 10],
                    fontSize: 40,
                },
                series: [{
                    name: 'Expense',
                    type: 'pie',
                    radius: '68%',
                    center: ['56%', '58%'],
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
        }
    }

    var compare = function (x, y) {//比较函数
        if (x < y) {
            return -1;
        } else if (x > y) {
            return 1;
        } else {
            return 0;
        }
    }

    function updateIncomePie(getIncome) {

        var myChart2 = echarts.init(document.getElementById('main1'));

        var names = ["Transfer", "Investment income", "Refund", "Wage"];
        //var color = ["#e4ee52", "#75e772", "#e55ca2", "#60d4e7"];
        var value = getIncomeValue(getIncome);

        var dataIncome = new Array([names.length]);
        for (var i = 0; i < names.length; i++) {
            dataIncome[i] = parseFloat(value[i])
        }

        var dataIncomeSort = JSON.parse(JSON.stringify(dataIncome));
        dataIncomeSort.sort(compare);

        var option2 = {
            title: {
                text: 'The Source of Income',
                left: 'center',
                fontSize: 35,
            },

            dataset: {
                source: [
                    ['score', 'Value', 'Account'],
                    [dataIncomeSort.indexOf(dataIncome[0]), dataIncome[0], 'Transfer'],
                    [dataIncomeSort.indexOf(dataIncome[1]), dataIncome[1], 'Investment income'],
                    [dataIncomeSort.indexOf(dataIncome[2]), dataIncome[2], 'Refund'],
                    [dataIncomeSort.indexOf(dataIncome[3]), dataIncome[3], 'Wage'],
                ]
            },
            grid: {containLabel: true},
            xAxis: {name: 'Value'},
            yAxis: {type: 'category'},
            visualMap: {
                orient: 'horizontal',
                left: 'center',
                min: 0,
                max: 3,
                text: ['High Value', 'Low Value'],
                // Map the score column to color
                dimension: 0,
                inRange: {
                    color: ['#D7DA8B', '#E15457']
                }
            },
            series: [
                {
                    type: 'bar',
                    encode: {
                        // Map the "amount" column to X axis.
                        x: 'Value',
                        // Map the "product" column to Y axis
                        y: 'Account'
                    }
                }
            ]
        };
        myChart2.setOption(option2, true);
    }

    var date = $("#dateInput").val();
    var budgetVal = 0;
    var allExpense = 0;
    var flagEx = 0;
    var flagBu = 0;
    monthTime = month;

    //var monthTime = 0;
    function updateExpensePie(budgetValue, allExpenses) {

        //monthTime = document.getElementById("dateInput").value;
        console.log(monthTime);
        var proportion = 0;
        if ((budgetValue[monthTime-1] == 0)||
            ([1,2,3,4,5,6,7,8,9,10,11,12].indexOf(monthTime) == -1)){
        }else {
            proportion = Math.ceil(allExpenses[monthTime-1] / budgetValue[monthTime-1] * 100);
            if (proportion >= 100) {
                proportion = 100;
            }
        }
        console.log(proportion);

        // var dataBudgetExpenseLabel = []
        // var dataBudgetExpenseValue = []
        // for(var i in budgetExpense){
        //     dataBudgetExpenseLabel.push(i)
        //     dataBudgetExpenseValue.push(budgetExpense[i])
        //
        // }
        //console.log(dataBudgetExpenseValue);

        var budgetExpenseChart = echarts.init(document.getElementById('main2'));

        var budgetExpenseoption = {
            title: {
                text: 'Budget Utilization',
                left: 'center',
                c: 35,
                padding: [0, 0, 0, 0],
            },

            tooltip: {
                formatter: '{a} <br/>{b} : {c}%'
            },
            toolbox: {
                feature: {
                    restore: {},
                    saveAsImage: {}
                }
            },
            series: [
                {
                    name: 'Budget',
                    type: 'gauge',
                    detail: {formatter: '{value}%'},
                    data: [{value: 50+"%", name: '', color: '#f1f111'}]
                }
            ]
        };

        budgetExpenseoption.series[0].data[0].value = (proportion).toFixed(2) - 0;
        budgetExpenseChart.setOption(budgetExpenseoption, true);

    }

    function getAllValue(getString) {
        var dEValue = [0,0,0,0,0,0,0,0,0,0,0,0];
        for (var i = 0; i < getString.length; i++) {
            var date = new Date(getString[i].date);
            var Month = date.getMonth();
            dEValue[Month] += getString[i].value;
        }
        return dEValue;
    }

    function getBudgetValue(getString) {
        var dBValue = [0,0,0,0,0,0,0,0,0,0,0,0];
        var monthMun = 0;
        for (var i = 0; i < getString.length; i++) {
            monthMun = getString[i].dateByMonth.split('-')[1];
            dBValue[monthMun-1] = getString[i].value;
        }
        return dBValue;
    }

    $.ajax(
        {
            url: "/expenses/details",
            dataType: "json",
            type: "GET",
            // data: formData,
            // processData: false,
            // async: false,
            contentType: "application/json",
            success: function (data) {
                allExpense = getAllValue(data);
                flagEx = 1;
                if (flagEx && flagBu) {
                    flagEx = 0;
                    flagBu = 0;
                    updateExpensePie(budgetVal, allExpense);
                }
            },
            error: function (error) {
                console.log(error);
            }
        });

    $.ajax(
        {
            url: "/budget/details",
            dataType: "json",
            type: "GET",
            // async: false,
            // data: formData,
            // processData: false,
            contentType: "application/json",
            success: function (data) {
                budgetVal = getBudgetValue(data);
                flagBu = 1;
                if (flagEx && flagBu) {
                    flagEx = 0;
                    flagBu = 0;
                    updateExpensePie(budgetVal, allExpense);
                }
            },
            error: function (error) {
                console.log(error);
            }
        });

    $.ajax(
        {
            url: "/income/details",
            dataType: "json",
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

    $.ajax(
        {
            url: "/expenses/details",
            dataType: "json",
            type: "GET",
            // data: formData,
            // processData: false,
            contentType: "application/json",
            success: function (data) {
                // var jsonData = $.parseJSON(data);
                // updatePieChart(jsonData);
                // updateDetail(jsonData);
                console.log(data);
                updateExpensesCategory(data);

            },
            error: function (error) {
                console.log(error);
            }
        });
}