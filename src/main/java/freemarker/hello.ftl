字符串输出:
${"Hello ${name} !"} / ${"Hello " + name + " !"}


定义变量
<#assign cname=r"特殊字符完成输出(http:\www.baidu.com)">
${cname}

<#assign cname2='<img class="k">hi.asd//http:\\www.baidu.com<img>'>
${cname2}


索引访问： ${name[2]}
截取字符串：${name[0..2]}

算数运算：
<#assign number1 = 10>
<#assign number2 = 5>
"+" : ${number1 + number2}
"－" : ${number1 - number2}
"*" : ${number1 * number2}
"/" : ${number1 / number2}
"%" : ${number1 % number2}

比较运算符：
<#if number1 + number2 gte 12 || number1 - number2 lt 6>
    "*" : ${number1 * number2}
<#else>
    "/" : ${number1 / number2}
</#if>

内建函数：
<#assign data = "abcd1234">
第一个字母大写：${data?cap_first}
所有字母小写：${data?lower_case}
所有字母大写：${data?upper_case}
<#assign floatData = 12.34>
数值取整数：${floatData?int}
获取集合的长度：${users?size}
时间格式化：${dateTime?string("yyyy-MM-dd")}

空判断和对象集合：
<#if users??>
    <#list users as user >
        ${user.id} - ${user.name}
    </#list>
<#else>
    ${user!"变量为空则给一个默认值"}
</#if>

Map集合：
<#assign mapData={"name":"程序员", "salary":15000}>
name：${mapData["name"]}

通过Key遍历Map：
<#list mapData?keys as key>
    Key: ${key} - Value: ${mapData[key]}
</#list>

通过Value遍历Map：
<#list mapData?values as value>
    Value: ${value}
</#list>

List集合：
<#assign listData=["ITDragon", "blog", "is", "cool"]>
<#list listData as value>
    ${value}
</#list>

include指令：
引入其他文件：<#include "otherFreeMarker.ftl" />

macro宏指令：
<#macro mo>
    定义无参数的宏macro--${name}
</#macro>
使用宏macro: <@mo />
<#macro moArgs a b c>
    定义带参数的宏macro-- ${a+b+c}
</#macro>

使用带参数的宏macro: <@moArgs a=1 b=2 c=3 />

命名空间：
<#import "otherFreeMarker.ftl" as otherFtl>
${otherFtl.otherName}
<@otherFtl.addMethod a=10 b=20 />
<#assign otherName="修改otherFreeMarker.ftl中的otherName变量值"/>
${otherFtl.otherName}
<#assign otherName="修改otherFreeMarker.ftl中的otherName变量值" in otherFtl />
${otherFtl.otherName}


<#assign x = ["red", 16, "blue", "cyan"]>
数组是否包含blue，包含返回yes，否则no
"blue": ${x?seq_contains("blue")?string("yes", "no")}

<#if x?seq_contains("blue")>
    has blueblueblue
</#if>

判断map是否为空
<#if kindsMap3?size gt 0>
    map size 大于0
<#else>
    map size 不大于0
</#if>

<#list kindsMap?keys as mKey>
    ${mKey}//取出来key
    <#assign item = kindsMap[mKey]>
    <#list item as itemValue>
        ${itemValue}//取出来值
    </#list>
</#list>


