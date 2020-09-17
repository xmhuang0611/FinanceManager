var glovar;
function init() {
    // $('#addCategory').on('click', function () {
    //
    // });




    // d3.json("../static/data/category.json", function (d) {
    //     console.log(d);
    //     updateCategory(d);
    // })

    let selectDate = document.getElementById("dateInput").value;
    let year = parseInt(selectDate.split('-')[0]);
    let month = parseInt(selectDate.split('-')[1]);


    request();
    updateMain();
    drawPie(month);
    // drawDashboard();
}

function request() {
    // $.ajaxSetup({
    //     data: {csrfmiddlewaretoken: '{{ csrf_token }}'},
    // });

    $.ajax(
        {
            url: "/expenses/categories",
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
                updateCategory(data);


            },
            error: function (error) {
                console.log(error);
            }
        });

    $.ajax(
        {
            url: "/expenses/details",
            dataType:"json",
            type: "GET",
            // data: formData,
            // processData: false,
            contentType: "application/json",
            success: function (d) {
                // var jsonData = $.parseJSON(data);
                // updatePieChart(jsonData);
                // updateDetail(jsonData);
                var _obj = JSON.stringify(d);

                glovar.expenseData = JSON.parse(_obj);

                // console.log(d);
                // var nest = d3.nest()
                //     .key(function (d){
                //         return d.categoryName;
                //     })
                //     .entries(d);
                // updateDetailByCategory(nest);

                let selectDate = document.getElementById("dateInput").value;
                let year = parseInt(selectDate.split('-')[0]);
                let month = parseInt(selectDate.split('-')[1]);
                updateDetailByCategory(year, month);

                updateDetailByDate(year, month);

            },
            error: function (error) {
                console.log(error);
            }
        });

    // d3.json("../static/data/expense.json", function (d) {
    //     console.log(d);
    //     var nest = d3.nest()
    //         .key(function (d){
    //             return d.categoryId;
    //         })
    //         .entries(d);
    //     // console.log(nest);
    //     updateDetailByCategory(nest);
    //
    //     var nest2 = d3.nest()
    //         .key(function (d){
    //             return d.date;
    //         })
    //         .entries(d);
    //     updateDetailByDate(nest2);
    // })



}


$(document).ready(function () {

    glovar = globalVariable();
    laydate.render({
        elem: '#dateInput' //指定元素
        ,lang: 'en'
        ,type: 'month'
        ,theme: 'molv'
        ,value: new Date().getFullYear() + "-" + ((new Date().getMonth()+1) > 9 ? (new Date().getMonth()+1) : "0"+(new Date().getMonth()+1))
        ,done: function (value, date) {
            console.log(date);
            updateDetailByCategory(date.year, date.month);
            updateDetailByDate(date.year, date.month);
            updateMain(date.year, date.month);
            drawPie(date.month);
        }
    });
    init();
    d3.select("#Budget")
        .on("blur", function () {
            let val = this.innerText;
            refreshBudget(val);
            // console.log(this);
        })
});

function refreshBudget(val) {
    val = val.split("$")[1];
    let selectDate = document.getElementById("dateInput").value;
    if(selectDate && selectDate === '') {
        selectDate = new Date().getFullYear() + '-' + (new Date().getMonth() + 1);
    }
    var formData = {
        'id': '5f60dc4279523ee91c0c6d61',
        'dateByMonth': selectDate,
        'value': parseInt(val)
        // ,'date': '2020-9-16'
    };

    // "dateByMonth": "2021-2",
    //     "value": "666.0"

    // var formData = new FormData();
    // formData.id = "5f60dc4279523ee91c0c6d61";
    // formData.dateByMonth = selectDate;
    // formData.value = val;
    // formData.date = "2020-9-16";

    $.ajax(
        {
            url: "/budget/budget_modify",
            type: "PUT",
            data: JSON.stringify(formData),
            // data: formData,
            dataType: "application/json",
            processData: false,
            contentType: "application/json;charset=UTF-8",
            success: function (data) {
                console.log("modify budget success!");
            },
            error: function (error) {
                console.log(error);
            }
        });
}