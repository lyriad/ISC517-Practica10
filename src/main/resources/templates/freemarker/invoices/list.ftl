<#include "../base.ftl"/>

<#macro title><@spring.message "invoice.plural.up" /></#macro>

<#macro styles></#macro>

<#macro body>
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800"><@spring.message "invoice.plural.up" /></h1>
    <a href="/invoices/register" class="btn btn-primary btn-icon-split">
        <span class="icon text-white-50">
            <i class="fas fa-plus"></i>
        </span>
        <span class="text"><@spring.message "action.Add" /> <@spring.message "invoice.singular.dn" /></span>
    </a>
</div>
<div class="card shadow mb-4">
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" width="100%" cellspacing="0">
                <thead>
                    <tr role="row">
                        <th style="flex: 1; min-width: 1px"><@spring.message "invoice.attr.client.up" /></th>
                        <th><@spring.message "invoice.attr.employee.up" /></th>
                        <th><@spring.message "invoice.attr.total.up" /></th>
                        <th><@spring.message "invoice.table.rental-amount" /></th>
                        <th><@spring.message "invoice.table.status" /></th>
                        <th style="width: 1px;"><@spring.message "action.plural.up" /></th>
                    </tr>
                </thead>
                <tbody>
                    <#list invoices as invoice>
                    <tr>
                        <td>${invoice.client.getFullName()}</td>
                        <td>${invoice.employee.getFullName()}</td>
                        <td>${invoice.total?string['0']}</td>
                        <td>${invoice.rentals?size}</td>
                        <td>${invoice.paid?string('<@spring.message "closed" />', '<@spring.message "open" />')}</td>
                        <td class="d-flex">
                            <a href="/invoices/${invoice.id}" class="btn btn-info btn-icon-split mr-2">
                                <span class="icon text-white-50">
                                    <i class="fas fa-eye"></i>
                                </span>
                                <span class="text"><@spring.message "action.View" /></span>
                            </a>
                            <a href="/invoices/edit/${invoice.id}" class="btn btn-warning btn-icon-split">
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