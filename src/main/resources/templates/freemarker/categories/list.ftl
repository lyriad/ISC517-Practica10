<#include "../base.ftl"/>

<#macro title>Categories</#macro>

<#macro styles></#macro>

<#macro body>
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Categories</h1>
        <a href="/categories/register" class="btn btn-primary btn-icon-split">
        <span class="icon text-white-50">
            <i class="fas fa-plus"></i>
        </span>
            <span class="text">Add category</span>
        </a>
    </div>
    <div class="card shadow mb-4">
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" width="100%" cellspacing="0">
                    <thead>
                    <tr role="row">
                        <th class="w-25">Name</th>
                        <th class="w-50">Description</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list categories as category>
                        <tr>
                            <td>${category.name}</td>
                            <td>${category.description}</td>
                            <td class="d-flex">
                                <a href="/categories/${category.id}/subcategories" class="btn btn-info btn-icon-split">
                                <span class="icon text-white-50">
                                <i class="fas fa-eye"></i>
                                </span>
                                    <span class="text">View</span>
                                </a>
                                <a href="/categories/edit/${category.id}" class="btn btn-warning btn-icon-split">
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