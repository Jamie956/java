<#--<#list kindsMap?keys as mKey>-->
<#--    ${mKey}//取出来key-->
<#--    <#assign item = kindsMap[mKey]>-->
<#--    <#list item as itemValue>-->
<#--        ${itemValue}//取出来值-->
<#--    </#list>-->
<#--</#list>-->


<#--<#list kindsMap2?keys as mKey>-->
<#--    ${mKey}-->
<#--    <#assign item = kindsMap2[mKey]>-->
<#--    <#list item as itemValue>-->
<#--        ${itemValue.id} - ${itemValue.name}-->
<#--    </#list>-->
<#--</#list>-->


声明一个序列，包含若干个元素
<#assign x = ["red", 16, "blue", "cyan"]>
<#--使用seq_contains判断序列中的元素是否存在-->
"blue": ${x?seq_contains("blue")?string("yes", "no")}
"yellow": ${x?seq_contains("yellow")?string("yes", "no")}
16: ${x?seq_contains(16)?string("yes", "no")}
"16": ${x?seq_contains("16")?string("yes", "no")}

<#if x?seq_contains("blue")>
    blueblueblue
</#if>

<#if x?seq_contains("yellow")>
    yellowyellowyellow
</#if>


<#list kindsMap3?keys as mKey>
    ${mKey}
<#--    <#assign item = kindsMap2[mKey]>-->
<#--    <#list item as itemValue>-->
<#--        ${itemValue.id} - ${itemValue.name}-->
<#--    </#list>-->
</#list>


判断map是否为空
<#if kindsMap3?size gt 0>
    map大于0
</#if>