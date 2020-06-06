<#include "../base.ftl"/>

<#macro title>Categories - subcategories</#macro>

<#macro styles></#macro>

<#macro body>
    <div class="d-sm-flex">
        <h1 class="h3 mb-0 text-gray-800">${category.name}</h1>
    </div>
    <div class="d-sm-flex">
        <p class="mb-3">${category.description}</p>
    </div>
    <div class="d-sm-flex">
        <h3 class="h3 mb-2 text-gray-800">SubCategories</h3>
    </div>
    <form method="POST" action=<#if action == "Add">"/categories/${category.id}/subcategories/register"<#else>"/categories/${category.id}/subcategories/${subcategory.id}"</#if>>
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Add Subcategory</h6>
            </div>
            <div class="card-body">
                <div class="col-sm-12 mb-3 mb-sm-3">
                    <label for="subcategory_name">Name</label>
                    <input id="name" name="name" type="text" class="form-control form-control-user <#if (errors.name)??>is-invalid</#if>" placeholder="Subcategory name" value="<#if (subcategory.name)??>${subcategory.name}</#if>" >
                    <#if (errors.name)??><div class="invalid-feedback">${errors.name}</div></#if>
                </div>
                <input type="hidden" id="categoryId" value="${category.id}">
                <button type="submit" class="btn btn-primary btn-user mb-4 btn-block w-50 mx-auto">
                    ${action} Subcategory
                </button>
            </div>
        </div>
    </form>

    <div class="card shadow mb-4">
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" width="100%" cellspacing="0">
                    <thead>
                    <tr role="row">
                        <th class="w-50">Name</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list SubCategories as SubCategory>
                        <tr>
                            <td>${SubCategory.name}</td>
                            <td class="d-flex align-items-center">
                                <a href="/categories/${category.id}/subcategories/${SubCategory.id}" class="btn btn-warning btn-icon-split">
                                <span class="icon text-white-50">
                                <i class="fas fa-edit"></i>
                                </span>
                                    <span class="text">Edit</span>
                                </a>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</#macro>

<#macro scripts></#macro>

<@page/>