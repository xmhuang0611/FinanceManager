function updateMain(year, month) {
    let selectDate = document.getElementById("dateInput").value;
    if(selectDate && selectDate === '') {
        selectDate = new Date().getFullYear() + "-"
            + ((new Date().getMonth()+1) > 9 ? (new Date().getMonth()+1) : "0"+(new Date().getMonth()+1))
    }
    year = year || parseInt(selectDate.split('-')[0]);
    month = month || parseInt(selectDate.split('-')[1]);
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

                d3.select("#Expenses").html("$" + d3.sum(data, function (d1){
                    let exDate = new Date(d1.date);
                    if(exDate.getFullYear()===year && exDate.getMonth()===(month-1)){
                        return d1.value;
                    }else {
                        return 0;
                    }
                }).toFixed(2))

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
                // let selectDate = document.getElementById("dateInput").value;
                // if(selectDate && selectDate === '') {
                //     selectDate = new Date().getFullYear() + "-"
                //         + ((new Date().getMonth()+1) > 9 ? (new Date().getMonth()+1) : "0"+(new Date().getMonth()+1))
                // }
                // let year = parseInt(selectDate.split('-')[0]);
                // let month = parseInt(selectDate.split('-')[1]);
                d3.select("#Income").html("$" + d3.sum(data, function (d1){
                    let exDate = new Date(d1.date);
                    if(exDate.getFullYear()===year && exDate.getMonth()===(month-1)){
                        return d1.value;
                    }else {
                        return 0;
                    }
                }).toFixed(2))

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
                let selectDate = document.getElementById("dateInput").value;
                if(selectDate && selectDate === '') {
                    selectDate = new Date().getFullYear() + '-' + (new Date().getMonth() + 1);
                }
                for(var i in data) {
                    let item = data[i];
                    if(parseInt(selectDate.split('-')[1]) === parseInt(item["dateByMonth"].split('-')[1])
                        && parseInt(selectDate.split('-')[0]) === parseInt(item["dateByMonth"].split('-')[0])
                    ) {
                        d3.select("#Budget").html("$" + item.value);
                        d3.select("#Budget").data(item);
                        break;
                    }
                }

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

