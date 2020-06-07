<#include "../base.ftl"/>

<#macro title>Equipment</#macro>

<#macro styles></#macro>

<#macro body>
<div class="d-flex row">
    <div class="col-md-4">
        <div class="card shadow">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Equipment information</h6>
            </div>
            <div class="card-body text-center d-block">
                <img id=previewImage src="<#if (equipment.image)??>${equipment.image}<#else>/images/equipment.png</#if>" class="avatar-xxlarge shadow">
                <hr/>
                <h3 class="font-weight-bold my-3">${equipment.name}</h3>
                <h5 class="my-2">Category: ${equipment.category.name}</h3>
                <h5 class="my-2">Subcategory: <#if (equipment.subCategory.name)??>${equipment.subCategory.name}<#else>None</#if></h5>
                <h5 class="my-2">Available: ${equipment.cantAvailable}</h5>
                <h5 class="my-2">Cost/day: ${equipment.costPerDay}</h5>
                <hr/>
                <a href="/equipments/edit/${equipment.id}" class="btn btn-warning btn-block">
                    Edit
                </a>
            </div>
        </div>
    </div>
    <div class="col-md-8">
        <div class="card shadow">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Performance</h6>
            </div>
        </div>
    </div>
</div>
</#macro>

<#macro scripts></#macro>

<@page/>