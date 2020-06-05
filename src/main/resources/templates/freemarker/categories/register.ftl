<#include "../base.ftl"/>

<#macro title>Add Category</#macro>

<#macro styles></#macro>

<#macro body>
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">${action} category</h1>
</div>
<form method="POST" action=<#if action == "Add">"/categories/register"<#else>"categories/edit/${id}"</#if>>
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Category information</h6>
        </div>
        <div class="card-body">
            <div class="col-sm-12 mb-3 mb-sm-3">
                <label for="name">Name</label>
                <input name="name" type="text" class="form-control form-control-user <#if (errors.name)??>is-invalid</#if>" placeholder="Category name" value="<#if (category.name)??>${category.name}</#if>" required>
                <#if (errors.name)??><div class="invalid-feedback">${errors.name}</div></#if>
            </div>
            <div class="col-sm-12 mb-3 mb-sm-3">
                <label for="description">Description</label>
                <textarea id="description" name="description" class="form-control form-control-user" rows="5" placeholder="Category description"><#if (category.description)??>${category.description}</#if></textarea>
            </div>
    </div>
    <button type="submit" class="btn btn-primary btn-user mb-4 btn-block w-50 mx-auto">
        ${action} category
    </button>
</form>
</#macro>

<#macro scripts></#macro>

<@page/>