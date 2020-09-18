function updateCategory(category) {
    glovar.category = {};
    for(var i in category) {
        glovar.category[category[i].name] = category[i].id;
    }
}

function updateDetailByCategory(year, month) {

    var expenseOrigin = d3.nest()
        .key(function (d){
            return d.categoryName;
        })
        .entries(glovar.expenseData);
    var expense = [];
    // 生成符合“日期”和“类别”的expense
    for(var i in glovar.category) {
        var value = [];
        for(var j=0, len1=expenseOrigin.length; j <len1; j++) {
            if(expenseOrigin[j].key === i && expenseOrigin[j].values && expenseOrigin[j].values.length > 0 ) {
                for(var k=0, len2 = expenseOrigin[j].values.length; k <len2; k++) {
                    let item = expenseOrigin[j].values[k];
                    if(
                        // selectDate && selectDate !== '' &&
                        month === (new Date(item.date).getMonth() +1)
                        && year === new Date(item.date).getFullYear()
                    ) {
                        let newItem = {}
                        for(var prop in item) {
                            newItem[prop] = item[prop];
                        }
                        value.push(newItem);
                    }
                }
                break;
            }
        }
        expense.push({
            "key": i,
            "values": value
        })
    }

    console.log(expense);

    // 清空之前的内容
    d3.select("#panel-961250")
        .selectAll("div.panel")
        .remove();

    var categaoryDiv = d3.select("#panel-961250")
        .selectAll("div.panel")
        .data(expense)
        .enter()
        .append("div")
        .attr("class", function (d){
            return "panel panel-default category_" + d.key;
        });


    var contentDiv = categaoryDiv.append("div")
        .attr("class", "panel-heading");

    contentDiv.append("a")
        .attr("class", "panel-title")
        .attr("data-toggle", "collapse")
        .attr("data-parent", "#panel-961250")
        .attr("href", function (d) {
            return "#panel-element-"+d.key;
        })
        .text(function (d) {
            var sum = d3.sum(d.values, function (d1) {
                return d1.value;
            })
            return d.key;
        });

    contentDiv.append("button")
        .text('x')
        .style("float", "right")
        .style("width", "20px")
        .on('click', function () {
            var name = d3.select(this).data()[0].key;
            deleteCategoryInDB(name);
            d3.select(this.parentElement.parentElement).remove();
        })

    contentDiv.append("button")
        .text('+')
        .style("float", "right")
        .style("width", "20px")
        // .attr("class", "layui-btn")
        .on('click', function () {
            var category = d3.select(this).data()[0].key;
            addNewExpense(category);
        })

    contentDiv.append("a")
        .attr("class", "panel-title")
        .attr("data-toggle", "collapse")
        .attr("data-parent", "#panel-961250")
        .attr("href", function (d) {
            return "#panel-element-"+d.key;
        })
        .style("float", "right")
        .style("margin-right", "8px")
        .text(function (d) {
            var sum = d3.sum(d.values, function (d1) {
                return d1.value;
            })
            return "$" + sum.toFixed(2);
        });

    var item = categaoryDiv.append("div")
        .attr("class", "panel-collapse collapse")
        .attr("id", function (d){
            return "panel-element-"+d.key;
        })
        // .style("height", "20px")
        .selectAll(".panel-body")
        .data(function (d, i){
            return d.values;
        })
        .enter()
        .append("div")
        .attr("class", "panel-body")
        .style("padding", "8px");

    item.append("a")
        .text(function (d) {
            return d.description
        });

    item.append("a")
        .text(function (d) {
            return "$" + d.value;
        })
        .style("float", "right");

    item.append("a")
        .text(function (d) {
            return d.date;
        })
        .style("color", "#adabab")
        .style("display", "block");



}

function updateDetailByDate(year, month) {

    var expenseOrigin = [];

    for(let i=0, len=glovar.expenseData.length; i<len; i++) {
        let itemDate = new Date(glovar.expenseData[i].date);
        if(itemDate.getFullYear() === year && itemDate.getMonth() === (month-1)){
            var _obj = JSON.stringify(glovar.expenseData[i]);
            expenseOrigin.push(JSON.parse(_obj));
        }
    }

    var expense = d3.nest()
        .key(function (d){
            return d.date;
        })
        .entries(expenseOrigin);


    d3.select("#panel-961251")
        .selectAll("div.panel")
        .remove();

    var categaoryDiv = d3.select("#panel-961251")
        .selectAll("div.panel")
        .data(expense)
        .enter()
        .append("div")
        .attr("class", function (d){
            return "panel panel-default category_" + d.key;
        });


    var contentDiv = categaoryDiv.append("div")
        .attr("class", "panel-heading")

    contentDiv.append("a")
        .attr("class", "panel-title")
        .attr("data-toggle", "collapse")
        .attr("data-parent", "#panel-961251")
        .attr("href", function (d) {
            return "#panel-element-"+d.key;
        })
        .text(function (d) {
            var sum = d3.sum(d.values, function (d1) {
                return d1.value;
            })
            return d.key;
        });


    contentDiv.append("button")
        .text('x')
        .style("float", "right")
        .style("width", "20px")
        .on('click', function () {
            var name = d3.select(this).data()[0].key;
            deleteCategoryInDB(name);
            d3.select(this.parentElement.parentElement).remove();
        })

    contentDiv.append("a")
        .attr("class", "panel-title")
        .attr("data-toggle", "collapse")
        .attr("data-parent", "#panel-961251")
        .attr("href", function (d) {
            return "#panel-element-"+d.key;
        })
        .style("float", "right")
        .style("margin-right", "8px")
        .text(function (d) {
            var sum = d3.sum(d.values, function (d1) {
                return d1.value;
            })
            return "$" + sum.toFixed(2);
        });

    var item = categaoryDiv.append("div")
        .attr("class", "panel-collapse collapse")
        .attr("id", function (d){
            return "panel-element-"+d.key;
        })
        // .style("height", "20px")
        .selectAll(".panel-body")
        .data(function (d, i){
            return d.values;
        })
        .enter()
        .append("div")
        .attr("class", "panel-body")
        .style("padding", "8px");

    item.append("a")
        .text(function (d) {
            return d.description
        });

    item.append("a")
        .text(function (d) {
            return "$" + d.value;
        })
        .style("float", "right");

    item.append("a")
        .text(function (d) {
            return d.date;
        })
        .style("color", "#adabab")
        .style("display", "block");

}

function addNewCategory() {
    layer.prompt({title: 'Please Add new category',
        offset: [100, document.body.clientWidth - 300],
        btn: ["add", "cancel"]},
        function(val, index){
            addCategoryToDB(val);
            layer.msg(val + ' category add success!');
            // layer.lang("en");
            layer.close(index);
        });
}

function deleteCategoryInDB(name){
    var id = glovar.category[name];
    $.ajax(
        {
            url: "/expenses/category/delete/" + id,
            type: "DELETE",
            processData: false,
            // data: {},
            contentType: false,
            success: function (data) {
                console.log("Delete success!");
                request();
                let month = parseInt($("#dateInput").val().split('-')[1]);
                drawPie(month);
            },
            error: function (error) {
                console.log(error);
            }
        });
}

function addCategoryToDB(val) {
    $.ajaxSetup({
        data: {csrfmiddlewaretoken: '{{ csrf_token }}'},
    });

    // var formData = new FormData();
    // formData.append('Name', val);


    var formData = {"name": val};

    $.ajax(
        {
            url: "/expenses/category_add",
            type: "PUT",
            data: JSON.stringify(formData),
            // data: formData,
            dataType: "application/json",
            processData: false,
            contentType: "application/json;charset=UTF-8",
            success: function (d) {
                // console.log(data);
                console.log("Add success!");
                // request();
                // let month = parseInt($("#dateInput").val().split('-')[1]);
                // drawPie(month);
            },
            error: function (error) {
                console.log(error);
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
                            // updateCategory(data);
                            request();
                            let month = parseInt($("#dateInput").val().split('-')[1]);
                            drawPie(month);
                        },
                        error: function (error) {
                            console.log(error);
                        }
                    });
            }
        });
    // addCategoryToView(val);
}

function addCategoryToView(val) {
    var categaoryDiv = d3.select("#panel-961250")
        // .selectAll("div.panel")
        // .data(expense)
        // .enter()
        .append("div")
        .attr("class", "panel panel-default category_" + val
        //     function (d){
        //     return "panel panel-default category_" + d.key;
        // }
        );

    var contentDiv = categaoryDiv.append("div")
        .attr("class", "panel-heading");

    contentDiv.append("a")
        .attr("class", "panel-title")
        .attr("data-toggle", "collapse")
        .attr("data-parent", "#panel-961250")
        .attr("href", "#panel-element-" + val)
        .text(val);

    contentDiv.append("button")
        .text('x')
        .style("float", "right")
        .style("width", "20px")
        .on('click', function () {
            var name = this.parentElement.firstElementChild.innerText;
            deleteCategoryInDB(name);
            d3.select(this.parentElement.parentElement).remove();
        });

    contentDiv.append("a")
        .attr("class", "panel-title")
        .attr("data-toggle", "collapse")
        .attr("data-parent", "#panel-961250")
        .attr("href", "#panel-element-" + val)
        .style("float", "right")
        .style("margin-right", "8px")
        .text("$0");
}

function addNewExpense(categaory) {
    layer.prompt({
            title: 'Please Add new expense item',
            formType: 0,
            placeholder: 'Please enter expense description',
            offset: [100, document.body.clientWidth - 300],
            btn: ["add", "cancel"]},
        function(val, index, elem){
            let exVal = $("#exVal").val();
            let exDate = $("#exDate").val();

            let sDate = new Date(exDate);
            exDate = sDate.getFullYear() + '-' + (sDate.getMonth() + 1) + '-' + sDate.getDate();
            // description, value, date, category
            addExpenseToDB(val, exVal, exDate, categaory);
            layer.msg(val + ' expense add success!');
            // layer.lang("en");
            layer.close(index);
        });
    $(".layui-layer-content").append("<br/>" +
        "<input type=\"text\" id= \"exVal\" class=\"layui-input\" placeholder=\"Please enter expense value\"/><br/>" +
        "<input type=\"date\" lang=\"en\" id= \"exDate\" value='2020-9-18' class=\"layui-input\" placeholder=\"Please enter expense date\"/>")
}

function addExpenseToDB(val, exVal, exDate, categaory) {
    $.ajaxSetup({
        data: {csrfmiddlewaretoken: '{{ csrf_token }}'},
    });

    // var formData = new FormData();
    // formData.append('Name', val);


    var formData = {
        "categoryName": categaory,
        "value": parseFloat(exVal),
        "date": exDate,
        "description": val
    };

    $.ajax(
        {
            url: "/expenses/expenses_add",
            type: "PUT",
            data: JSON.stringify(formData),
            // data: formData,
            dataType: "application/json",
            processData: false,
            contentType: "application/json;charset=UTF-8",
            success: function (d) {
                // console.log(data);
                console.log("Add success!");
                // request();
                // let month = parseInt($("#dateInput").val().split('-')[1]);
                // drawPie(month);
            },
            error: function (error) {
                console.log(error);
                request();
                let month = parseInt($("#dateInput").val().split('-')[1]);
                drawPie(month);
                updateMain();
            }
        });
    // addCategoryToView(val);
}

function deleteExpenseInDB(name){
    var id = glovar.category[name];
    $.ajax(
        {
            url: "/expenses/expenses_delete/" + id,
            type: "DELETE",
            processData: false,
            // data: {},
            contentType: false,
            success: function (data) {
                console.log("Delete success!");
                request();
                let month = parseInt($("#dateInput").val().split('-')[1]);
                drawPie(month);
            },
            error: function (error) {
                console.log(error);
            }
        });
}