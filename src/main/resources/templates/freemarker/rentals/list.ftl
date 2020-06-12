<#include "../base.ftl"/>

<#macro title><@spring.message "rental.singular.up" /></#macro>

<#macro styles></#macro>

<#macro body>
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800"><@spring.message "rental.plural.up" /></h1>
</div>
<div class="card shadow mb-4">
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" width="100%" cellspacing="0">
                <thead>
                    <tr role="row">
                        <th class="w-25"><@spring.message "client.singular.up" /></th>
                        <th class="w-25"><@spring.message "equipment.singular.up" /></th>
                        <th style="width: 1px;"><@spring.message "rental.attr.amount.up" /></th>
                        <th style="width: 1px;"><@spring.message "rental.attr.rentedDays.up" /></th>
                        <th style="width: 15%;"><@spring.message "rental.attr.createdAt.up" /></th>
                    </tr>
                </thead>
                <tbody>
                    <#list rentals as rental>
                    <tr>
                        <td>${rental.client.getFullName()}</td>
                        <td>${rental.equipment.name}</td>
                        <td>${rental.amount}</td>
                        <td>${rental.getRentedDays()}</td>
                        <td>${rental.createdAt?date}</td>
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