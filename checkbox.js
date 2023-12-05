
    // 全选input勾选框
    var checkAll = document.getElementById("checkAll");
    var checkAll1 = document.getElementById("checkAll1");
    // 复选框组
    var checks = document.querySelectorAll("div.cart-body>div.container>div.cart-checkbox > input");
    var cartbody =document.querySelector("#cartbody")
    // 定义选择内容的数组
    var checkValues = [];

    // 检查列表复选框是否全部勾选
    function isCheckAll(){
        for(var i = 0; i < checks.length; i++){
            if(!checks[i].checked){
                return false;
            }  
        }
        return true;
    }

    // 根据复选框组勾选状态，获取当前已选择的值并渲染至页面
    function getValues(){
        // 数组置空
        checkValues=[];
        // 依次检查是否勾选，将勾选的内容放进数据
        for(var i = 0; i < checks.length; i++){
            if(checks[i].checked){
                checkValues.push(checks[i].value);
            }  
        }
    }

    // 列表被点击
    cartbody.onclick = function(){
        // 检查是否已勾选所有复选框，更改全选框状态
        if(isCheckAll()){
            checkAll.checked = "true";
            checkAll1.checked = "true";
        }else{
            checkAll.checked = false;
        }
        // 获取当前所选择的内容并渲染至页面
        getValues();
    }

    // 全选框被点击
    checkAll.onclick = function(){
        // 所有复选框的状态跟全选框的状态保持一致
        for(var i = 0; i < checks.length; i++){
            checks[i].checked = checkAll.checked;
        }
        // 获取当前所选择的内容并渲染至页面
        checkAll1.checked = checkAll.checked;
        getValues();
    }

        // 全选框被点击
    checkAll1.onclick = function(){
        // 所有复选框的状态跟全选框的状态保持一致
        for(var i = 0; i < checks.length; i++){
            checks[i].checked = checkAll1.checked;
        }
        // 获取当前所选择的内容并渲染至页面
        checkAll.checked = checkAll1.checked;
        getValues();
    }



