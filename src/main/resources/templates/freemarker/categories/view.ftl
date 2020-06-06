<#include "../base.ftl"/>

<#macro title><@spring.message "category.singular.up" /></#macro>

<#macro styles></#macro>

<#macro body>
<div class="d-flex row">
    <div class="col-md-4 mb-4">
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary"><@spring.message "category.label.info" /></h6>
            </div>
            <div class="card-body text-center d-block">
                <h2 class="my-3">${category.name}</h2>
                <hr />
                <p class="my-2">${category.description}</p>
                <hr/>
                <a href="/categories/edit/${category.id}" class="btn btn-warning btn-block">
                    <@spring.message "action.Edit" />
                </a>
            </div>
        </div>
        <div class="card shadow">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary"><@spring.message "subcategory.plural.up" /></h6>
            </div>
            <div class="card-body">
                <form method="POST" action=<#if action == "Add">"/categories/${category.id}/subcategories/register"<#else>"/categories/${category.id}/subcategories/${subcategory.id}"</#if>>
                    <div class="col-sm-12 mb-3 mb-sm-3">
                        <label><@spring.message "subcategory.attr.name" /></label>
                        <input name="name" type="text" class="form-control form-control-user <#if (errors.name)??>is-invalid</#if>" placeholder='<@spring.message "subcategory.attr.name" />' value="<#if (subcategory.name)??>${subcategory.name}</#if>" >
                        <#if (errors.name)??><div class="invalid-feedback">${errors.name}</div></#if>
                    </div>
                    <input type="hidden" id="categoryId" value="${category.id}">
                    <button type="submit" class="btn btn-primary btn-user mb-4 btn-block w-50 mx-auto">
                        <@spring.message "action.${action}" /> <@spring.message "subcategory.singular.dn" />
                    </button>
                </form>
                <#list subcategories>
                <hr />
                <div class="table-responsive">
                    <table class="table table-bordered" width="100%" cellspacing="0">
                        <thead>
                        <tr role="row">
                            <th class="w-100"><@spring.message "subcategory.attr.name" /></th>
                            <th><@spring.message "action.plural.up" /></th>
                        </tr>
                        </thead>
                        <tbody>
                        <#items as subcategory>
                            <tr>
                                <td>${subcategory.name}</td>
                                <td class="d-flex align-items-center">
                                    <a href="/categories/${category.id}/subcategories/${subcategory.id}" class="btn btn-warning btn-icon-split">
                                    <span class="icon text-white-50">
                                    <i class="fas fa-edit"></i>
                                    </span>
                                        <span class="text"><@spring.message "action.Edit" /></span>
                                    </a>
                                </td>
                            </tr>
                        </#items>
                        </tbody>
                    </table>
                </div>
                </#list>
            </div>
        </div>
    </div>
    <div class="col-md-8">
        <div class="card shadow">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary"><@spring.message "label.performance" /></h6>
            </div>
            <div class="card-body">
                
            </div>
        </div>
    </div>
</div>

</#macro>

<#macro scripts></#macro>

<@page/>