<!-- 获取map中的值 -->
${hello}
<#--获取POJO-->
id:${person.id?c}  <#--加？c是去除千分位-->
name:${person.name}<br/>
id:${person2.id?c}
name:${person2.name}<br/>

<#--取集合数据-->
<#list list as person>
    <#--判断-->
    <#if person_index%2 == 0>
        这是奇数行<br/>
    <#else>
        这是偶数行<br/>
    </#if>
    ${person.id}
    ${person.name}
    <#--取下标-->
    ${person_index}
</#list>
<br/>
<#--取map(第一种方式)-->
<#list map?keys as key>
    ${map[key].id}
    ${map[key].name}
</#list>
<hr/>
<#--第二种方式-->
${map.person.id}
${map.person.name}
<hr/>
<#--日期类型格式化-->
${date?date}<br/>
${date?time}<br/>
${date?datetime}<br/>
${date?string('yyyyMdd HH:mm:ss')}<br/>

<#--空值处理-->
<#--${test}  --> <#--如果代码去除调这段数据，会报错-->
${test!""}

${date?date}<br/>