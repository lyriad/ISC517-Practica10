<#include "../base.ftl"/>

<#macro title>Equipment</#macro>

<#macro styles></#macro>

<#macro body>
<div class="d-flex row">
    <div class="col-md-12">
        <div class="card shadow">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Equipment information</h6>
            </div>
            <div class="card-body text-center">
                <img id=previewImage src="<#if (equipment.image)??>${equipment.image}<#else>/images/equipment.png</#if>" class="avatar-xxlarge shadow">
                <hr/>
                <h5 class="my-3">${equipment.name}</h5>
                <h3 class="font-weight-bold my-2">${equipment.getCategory().getName()}</h3>
                <h5 class="my-3">${equipment.getSubCategory().getName()}</h5>
                <h5 class="my-3"> Available quantity: ${equipment.cantAvailable}</h5>
                <h5 class="my-2">Cost Per Day: ${equipment.costPerDay}</h5>
                <hr/>
                <a href="/equipments/edit/${equipment.id}" class="btn btn-warning btn-user mb-4 btn-block w-50 mx-auto">
                    Edit
                </a>
            </div>
        </div>
    </div>
</div>
</#macro>

<#macro scripts></#macro>

<@page/>