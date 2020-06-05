<#include "../base.ftl"/>

<#macro title>Clients</#macro>

<#macro styles></#macro>

<#macro body>
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">Clients</h1>
    <a href="/clients/register" class="btn btn-primary btn-icon-split">
        <span class="icon text-white-50">
            <i class="fas fa-plus"></i>
        </span>
        <span class="text">Add client</span>
    </a>
</div>
<div class="card shadow mb-4">
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" width="100%" cellspacing="0">
                <thead>
                    <tr role="row">
                        <th class="w-25">Id number</th>
                        <th class="w-25">Name</th>
                        <th class="w-25">Email address</th>
                        <th class="w-25">Phone</th>
                        <th>Actions</th>
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
                                <span class="text">View</span>
                            </a>
                            <a href="/clients/edit/${client.idNumber}" class="btn btn-warning btn-icon-split">
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