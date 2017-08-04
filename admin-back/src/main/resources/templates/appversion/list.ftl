<!DOCTYPE html>
<html lang="zh-cn">
<head>
<#include "/base/head_meta.ftl"/>
    <title>app版本管理</title>
</head>
<body>
<form method="post" action="">
    <div class="panel admin-panel">
        <div class="panel-head"><strong class="icon-reorder"> app版本管理</strong></div>
    <#--<div class="padding border-bottom">
        <ul class="search">
            <li><a class="button border-main icon-plus-square-o" href="add.html"> 添加班级</a></li>
        </ul>
    </div>-->
        <table class="table table-hover text-center">
            <tr>
                <th width="120">ID</th>
                <th>路径</th>
                <th>版本号</th>
                <th>设备系统</th>
                <th>操作</th>
            </tr>
        <#list pageParam as item>
            <tr>
                <td>${item.id!''}</td>
                <td>${item.url!''}</td>
                <td>${item.version!''}</td>
                <td>${item.deviceType!''}</td>
                <td>
                    <div class="button-group">
                        <a class="button border-red" href="javascript:void(0)"
                           onclick="return edit(${item.id!''})"><span
                                class="icon-edit"></span> 编辑</a>
                        <a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span
                                class="icon-trash-o"></span> 删除</a>
                    </div>
                </td>
            </tr>
        </#list>
        </table>
    </div>
</form>
<script type="text/javascript">

    function edit(id) {
        window.location.href = "${rc.contextPath}/appVersion/edit?id=" + id;
    }

    function del(id) {
        if (confirm("您确定要删除吗?")) {

        }
    }

    function DelSelect() {
        var Checkbox = false;
        $("input[name='id[]']").each(function () {
            if (this.checked == true) {
                Checkbox = true;
            }
        });
        if (Checkbox) {
            var t = confirm("您确认要删除选中的内容吗？");
            if (t == false) return false;
        }
        else {
            alert("请选择您要删除的内容!");
            return false;
        }
    }

</script>
</body>
</html>