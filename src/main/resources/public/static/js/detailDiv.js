function updateCategory(category) {
    for(var i in category) {
        glovar.category[category[i].id] = category[i].name;
    }
}

function updateDetailByCategory(expense) {

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
            var id = d3.select(this).data()[0].key;
            deleteCategoryInDB(id);
            d3.select(this.parentElement.parentElement).remove();
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

function updateDetailByDate(expense) {

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
            var id = d3.select(this).data()[0].key;
            deleteCategoryInDB(id);
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
    layer.prompt({title: 'Please Add new category', offset: [100, document.body.clientWidth - 300]},function(val, index){
        addCategoryToDB(val);
        layer.msg(val + ' category add success!');
        layer.close(index);
    });
}

function deleteCategoryInDB(id){
    $.ajax(
        {
            url: "/expenses/category/delete/" + id,
            type: "POST",
            processData: false,
            data: {},
            contentType: false,
            success: function (data) {
                console.log("Delete success!");
            },
            error: function (error) {
                console.log(error);
            }
        });
}

function addCategoryToDB(val) {
    var formData = new FormData();
    $.ajaxSetup({
        data: {csrfmiddlewaretoken: '{{ csrf_token }}'},
    });
    formData.append("Name", val)


    // var formData = [];
    // formData.push({"Name": val});
    $.ajax(
        {
            url: "/expenses/category_add",
            type: "POST",
            data: formData,
            dataType: "text",
            processData: false,
            contentType: false,
            success: function (data) {
                // console.log(data);
                console.log("Add success!");
            },
            error: function (error) {
                console.log(error);
            }
        });
    addCategoryToView(val);
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
            // var id = d3.select(this).data()[0].key;
            // deleteCategoryInDB(id);
            d3.select(this.parentElement.parentElement).remove();
        })

    contentDiv.append("a")
        .attr("class", "panel-title")
        .attr("data-toggle", "collapse")
        .attr("data-parent", "#panel-961250")
        .attr("href", "#panel-element-" + val)
        .style("float", "right")
        .style("margin-right", "8px")
        .text("$0");
}

function addNewCategory1() {
    layer.open({
        type: 1
        ,offset: [100, document.body.clientWidth - 300]
        ,shadeClose: true //点击遮罩关闭
        ,content: '\<\div style="padding:20px;">自定义内容\<\/div>'
        ,time: 0 //不自动关闭
        ,btn: ['订单错误', '发票错误']
        ,yes: function(index){
            layer.close(index);
            layer.prompt({title: '请描述退回原因'},function(val, index){
                layer.msg('得到了订单'+val);
                layer.close(index);
            });
        }
        ,btn2: function(index){
            layer.close(index);
            layer.prompt(function(val, index){
                layer.msg('得到了发票'+val);
                layer.close(index);
            });
        }
    });
}
