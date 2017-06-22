<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<Html>
<head>
    <title>test</title>
</head>
<body>
<button onclick="onclickBtn()"></button>
<table>

    <#--<#list data as obj>-->
        <#--<tr>-->
            <#--<td><#if obj??>${obj.insertTime?date}<#else>when-missing</#if></td>-->
            <#--<td><#if obj??>${obj.test!""}<#else>when-missing</#if></td>-->
        <#--</tr>-->
    <#--</#list>-->

    <#list users as user>
        <tr>
            <td><#if user??>${user.insertTime?date}<#else>when-missing</#if></td>
            <td><#if user??>${user.userName!""}<#else>when-missing</#if></td>
        </tr>
    </#list>
</table>

<body>
<script type="text/javascript">
    function onclickBtn() {
        <#--alert(${data});-->
    }
</script>
</html>