layui.use(['table', 'layer'], function () {
    var table = layui.table;
    var layer = layui.layer;
    //显示数据，已完成
    var tableIns = table.render({
        elem: '#empList',
        height: 521,
        url: 'empList', //数据接口
        page: true,//开启分页
        limit: 10,
        limits: [5, 10, 15, 20, 25, 30],
        toolbar: "#toolbarDemo",
        cols: [[ //表头
            {type: 'checkbox', width: 80, fixed: 'left'},
            {field: 'empId', title: '员工编号', width: 120, sort: true},
            {field: 'empName', title: '员工姓名', width: 120},
            {
                field: 'empGender', title: '性别', width: 80, sort: true,
                templet: function (g) {
                    var gender = g.empGender;
                    if (gender === 1) {
                        return '男';
                    } else {
                        return '女';
                    }
                }
            },
            {field: 'empPost', title: '职务', width: 120, sort: true},
            {field: 'empPhone', title: '电话', width: 120, sort: true,},
            {field: 'empSalary', title: '工资', width: 80, sort: true},
            {title: '操作', width: 120, toolbar: "#barDemo"}
        ]],
        where: {},
    });


    table.on("toolbar(employees)", function (obj) {
        // 添加员工，已完成
        if (obj.event === 'add_employee') {
            layer.open({
                title: "添加员工",
                type: 2,
                area: ['800px', '500px'],
                maxmin: true,
                content: "to_addEmployee",
            })
        }
        //批量删除，已完成
        else if (obj.event === 'del_employees_batch') {
            layer.confirm('真的删除这些数据吗?', function (index) {
                var checkStatus = table.checkStatus('empList'); //idTest 即为基础参数 id 对应的值
                var empIds = "";
                $.each(checkStatus.data, function (i, v) {
                    console.log(v.empId);
                    empIds = empIds + v.empId + ",";
                })
                empIds = empIds.substring(0, empIds.length - 1);
                console.log(empIds);
                $.ajax({
                    url: "del_Employee_Batch",
                    dataType: "json",
                    data: {empIds: empIds},
                    type: "post",
                    success: function (dataJson) {
                        if (dataJson.code === 200) {
                            window.location.reload();
                        } else {
                            layer.msg(dataJson.msg);
                        }
                    }
                })
            })
        }
    })

    //监听行工具栏，编辑和删除单个数据，已完成
    table.on('tool(employees)', function (obj) {
        var form = layui.form;//注意，layui定义方式
        //编辑员工,数据回显，不通过查询数据库，直接在前端获取数据进行显示，显示form表单的，回显前端操作数据的js代码
        if (obj.event === 'edit') {
            var data = obj.data;
            edit = layer.open({
                title: "编辑用户",
                type: 1,
                area: ['800px', '500px'],
                maxmin: true,
                content: layui.$("#employee_edit_form"),
            })
            console.log(data);
            form.val("employee_edit", {
                // // "empId":data.empId,
                "empName": data.empName,
                "empGender": data.empGender,
                "empAge": data.empAge,
                "empPost": data.empPost,
                "empPhone": data.empPhone,
                "empSalary": data.empSalary
            });

        } else if (obj.event === 'del') {//删除单个员工数据的js代码
            layer.confirm('真的删除行么', function (index) {
                $.ajax({
                    url: "del_Employee",
                    data: {empId: obj.data.empId},
                    dataType: "json",
                    type: "post",
                    success: function (dataJson) {
                        if (dataJson.code === 200) {
                            window.location.reload();
                        } else {
                            layer.msg(dataJson.msg);
                        }
                    }
                })
            });
        }
    })

    $("#searchBtn").on('click', function () {
        var val = $("#searchReload").val();
        console.log(val);
        // 重载数据
        tableIns.reload({
            where: {empName: val},
            page: {curr: 1}  //重新从第 1 页开始
        });
    })
});


//更新员工的js代码，已完成
layui.use(['form', 'layer'], function () {
    var form = layui.form;
    var layer = layui.layer;

    form.on('submit(modifyEmployee)', function (data) {
        //前端构造类用于封装数据
        function Employee() {
            this.empId = $("input[name=empId]").val();
            this.empName = $("input[name=empName]").val();
            this.empGender = $("input[name=empGender]:checked").val();
            this.empAge = $("input[name=empAge]").val();
            this.empPost = $("input[name=empPost]").val();
            this.empPhone = $("input[name=empPhone]").val();
            this.empSalary = $("input[name=empSalary]").val();
        }

        var employee = new Employee();
        $.ajax({
            url: "updateEmployee",
            data: {employee: JSON.stringify(employee)},
            type: "post",
            dataType: "json",
            success: function (dataJson) {
                if (dataJson.code === 200) {
                    layer.msg(dataJson.msg);
                    window.parent.location.reload();//刷新父页面
                    var index = parent.layer.getFrameIndex(window.name);//获取当前iframe层的索引
                    parent.layer.close(index);//执行关闭
                } else {
                    layer.msg(dataJson.msg);
                }
            }
        })
        return false;
    })
})
