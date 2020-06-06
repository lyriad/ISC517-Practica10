<#include "../base.ftl"/>

<#macro title><@spring.message "client.plural.up" /></#macro>

<#macro styles></#macro>

<#macro body>
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800"><@spring.message "client.plural.up" /></h1>
    <a href="/clients/register" class="btn btn-primary btn-icon-split">
        <span class="icon text-white-50">
            <i class="fas fa-plus"></i>
        </span>
        <span class="text"><@spring.message "action.Add" /> <@spring.message "client.singular.dn" /></span>
    </a>
</div>
<div class="card shadow mb-4">
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" width="100%" cellspacing="0">
                <thead>
                    <tr role="row">
                        <th class="w-25"><@spring.message "user.attr.idNumber.up" /></th>
                        <th class="w-25"><@spring.message "user.attr.fullName.up" /></th>
                        <th class="w-25"><@spring.message "user.attr.email.up" /></th>
                        <th class="w-25"><@spring.message "user.attr.phone.up" /></th>
                        <th><@spring.message "action.plural.up" /></th>
                    </tr>
                </thead>
                <tbody>
                    <#list clients as client>
                    <tr>
                        <td>${client.idNumber}</td>
                        <td>${client.getFullName()}</td>
                        <td>${client.email}</td>
                        <td>${client.phone}</td>
                        <td class="d-flex justify-content-between">
                            <a href="/clients/${client.idNumber}" class="btn btn-info btn-icon-split">
                                <span class="icon text-white-50">
                                <i class="fas fa-eye"></i>
                                </span>
                                <span class="text"><@spring.message "action.View" /></span>
                            </a>
                            <a href="/clients/edit/${client.idNumber}" class="btn btn-warning btn-icon-split">
                                <span class="icon text-white-50">
                                <i class="fas fa-edit"></i>
                                </span>
                                <span class="text"><@spring.message "action.Edit" /></span>
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