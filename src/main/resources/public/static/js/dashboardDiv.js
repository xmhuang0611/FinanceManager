var monthTime;
function drawDashboard() {
    var date = $("#dateInput").val();
    var budgetVal = 0;
    var allExpense = 0;
    var flagEx = 0;
    var flagBu = 0;
    monthTime = date.split('-')[1];
    $.ajax(
        {
            url: "/expenses/details",
            dataType:"json",
            type: "GET",
            // data: formData,
            // processData: false,
            contentType: "application/json",
            success: function (data) {
                allExpense = getAllValue(data);
                flagEx = 1;
            },
            error: function (error) {
                console.log(error);
            }
        });

    $.ajax(
        {
            url: "/budget/details",
            dataType:"json",
            type: "GET",
            // data: formData,
            // processData: false,
            contentType: "application/json",
            success: function (data) {
                budgetVal = getAllValue(data);
                flagBu = 1;
            },
            error: function (error) {
                console.log(error);
            }
        });
    if(flagEx && flagBu){
        drawDashPie(budgetValue, allExpenses);
    }
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

function getBudgetValue(dateTime, getString) {
    var budgetVal = 10000000;
    for (var i = 0; i < getString.length; i++) {
        var date = new Date(getString[i].dateByMonth);
        var getStringMonth = date.getMonth();
        if (dateTime == getStringMonth) {
            return getString[i].value;
        }
    }
    return budgetVal;
}

function drawDashPie(budgetValue, allExpenses) {
    var proportion = Math.ceil(allExpenses[monthTime] / budgetValue * 100);
    if (proportion >= 100) {
        proportion = 99;
    }

    var labels = ["60", "70", "80", "90", "0", "10", "20", "30", "40", "50"];
    var values = [10, 10, 10, 10, 10, 10, 10, 10, 10, 10];
    var colors = ["#ffc435", "#f1a12f", "#ef5738", "#f60808", "#ffffff", "#e6ffed", "#a4ecbc", "#51f456", "#e7f43e", "#eff116"];
    var contentData = new Array([labels.length]);
    for (var i = 0; i < labels.length; i++) {
        contentData[i] = {
            "label": labels[i],
            "value": values[i],
            "color": colors[i],
        }
    };

    //var proportion = 30;
    var budgetNum = (Math.floor(proportion / 10) + 4) % 10;
    contentData[budgetNum] = {
        "label": proportion,
        "value": values[budgetNum],
        "color": "#000000",
    }

    var pie = new d3pie("dashboardDiv", {
        "header": {
            "title": {
                "text": "Budget Utilization",
                "fontSize": 24,
                "font": "open sans"
            },
            "subtitle": {
                "color": "#999999",
                "fontSize": 12,
                "font": "open sans"
            },
            "location": "pie-center",
            "titleSubtitlePadding": 9
        },
        "footer": {
            "color": "#999999",
            "fontSize": 10,
            "font": "open sans",
            "location": "bottom-left"
        },
        "size": {
            "canvasWidth": 590,
            "pieInnerRadius": "86%",
            "pieOuterRadius": "77%"
        },
        "data": {
            "content": contentData
        },
        "labels": {
            "outer": {
                "format": "none",
                "pieDistance": 32
            },
            "inner": {
                "format": "label"
            },
            "mainLabel": {
                "fontSize": 13
            },
            "percentage": {
                "color": "#2b2b2e",
                "fontSize": 15,
                "decimalPlaces": 5
            },
            "value": {
                "color": "#adadad",
                "fontSize": 11
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
        }
    });
}