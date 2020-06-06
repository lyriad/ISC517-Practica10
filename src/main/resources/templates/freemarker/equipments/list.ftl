<#include "../base.ftl"/>

<#macro title>Equipments</#macro>

<#macro styles></#macro>

<#macro body>
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">Equipments</h1>
    <a href="/equipments/register" class="btn btn-primary btn-icon-split">
        <span class="icon text-white-50">
            <i class="fas fa-plus"></i>
        </span>
        <span class="text">Add Equipment</span>
    </a>
</div>
<div class="card shadow mb-4">
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" width="100%" cellspacing="0">
                <thead>
                    <tr role="row">
                        <th>Name</th>
                        <th>Subcategory</th>
                        <th>Available</th>
                        <th>Cost per day</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <#list equipments as equipment>
                    <tr>
                        <td>${equipment.name}</td>
                        <td>${equipment.getSubCategory().getName()}</td>
                        <td>${equipment.cantAvailable}</td>
                        <td>${equipment.costPerDay}</td>
                        <td class="d-flex">
                            <a href="#" class="btn btn-info btn-icon-split">
                                <span class="icon text-white-50">
                                <i class="fas fa-eye"></i>
                                </span>
                                <span class="text">View</span>
                            </a>
                            <a href="/equipments/edit/${equipment.id}" class="btn btn-warning btn-icon-split">
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