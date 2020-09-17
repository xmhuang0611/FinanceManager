var glovar;
function init() {
    glovar = globalVariable();
    laydate.render({
        elem: '#dateInput' //指定元素
        ,lang: 'en'
        ,type: 'month'
        ,theme: 'molv'
        ,value: new Date().getFullYear() + "-" + new Date().getMonth()
        ,done: function (value, date) {
            console.log(date);
        }
    });
    // $('#addCategory').on('click', function () {
    //
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

    // d3.json("../static/data/category.json", function (d) {
    //     console.log(d);
    //     updateCategory(d);
    // })
    request();
    updateMain();
    drawPie();
    drawDashboard();
}

function request() {
    // $.ajaxSetup({
    //     data: {csrfmiddlewaretoken: '{{ csrf_token }}'},
    // });

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
                console.log(d);
                var nest = d3.nest()
                    .key(function (d){
                        return d.categoryName;
                    })
                    .entries(d);
                // console.log(nest);
                updateDetailByCategory(nest);

                var nest2 = d3.nest()
                    .key(function (d){
                        return d.date;
                    })
                    .entries(d);
                updateDetailByDate(nest2);

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

$(document).ready(init);