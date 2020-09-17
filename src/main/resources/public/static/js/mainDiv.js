function updateMain() {

    $.ajax(
        {
            url: "/expenses/details",
            dataType:"json",
            type: "GET",
            // data: formData,
            // processData: false,
            contentType: "application/json",
            success: function (data) {
                // console.log(data);
                d3.select("#Expenses").html("$" + d3.sum(d, function (d1){
                    return d1.value;
                }))

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
                // console.log(data);
                d3.select("#Income").html("$" + d3.sum(d, function (d1){
                    return d1.value;
                }))

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
                // console.log(data);
                d3.select("#Budget").html("$" + d3.sum(d, function (d1){
                    return d1.value;
                }))
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
                // console.log(data);
                d3.select("#Budget").html("$" + d3.sum(d, function (d1){
                    return d1.value;
                }))
            },
            error: function (error) {
                console.log(error);
            }
        });

    d3.json("../static/data/account.json", function (d) {
        d3.select("#Accounts").html("$" + d3.sum(d, function (d1){
            return d1.value;
        }))
    })
}