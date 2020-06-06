<#include "../base.ftl"/>

<#macro title><@spring.message "action.${action}" /> <@spring.message "category.singular.dn" /></#macro>

<#macro styles></#macro>

<#macro body>
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800"><@spring.message "action.${action}" /> <@spring.message "category.singular.dn" /></h1>
</div>
<form method="POST" action=<#if action == "Add">"/categories/register"<#else>"/categories/edit/${category.id}"</#if>>
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary"><@spring.message "category.label.info" /></h6>
        </div>
        <div class="card-body">
            <div class="col-sm-12 mb-3 mb-sm-3">
                <label><@spring.message "category.attr.name" /></label>
                <input name="name" type="text" class="form-control form-control-user <#if (errors.name)??>is-invalid</#if>" placeholder='<@spring.message "category.attr.name" />' value="<#if (category.name)??>${category.name}</#if>" >
                <#if (errors.name)??><div class="invalid-feedback">${errors.name}</div></#if>
            </div>
            <div class="col-sm-12 mb-3 mb-sm-3">
                <label ><@spring.message "category.attr.description" /></label>
                <textarea name="description" class="form-control form-control-user" rows="5" placeholder='<@spring.message "category.attr.description" />'><#if (category.description)??>${category.description}</#if></textarea>
                <small class="form-text text-muted"><@spring.message "form.tip.optional" /></small>
            </div>
        </div>
    </div>
    <button type="submit" class="btn btn-primary btn-user mb-4 btn-block w-50 mx-auto">
        <@spring.message "action.${action}" /> <@spring.message "category.singular.dn" />
    </button>
</form>
</#macro>

<#macro scripts></#macro>

<@page/>