<#include "../base.ftl"/>

<#macro title>Employee</#macro>

<#macro styles></#macro>

<#macro body>
<div class="d-flex row">
    <div class="col-md-4">
        <div class="card shadow">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Personal information</h6>
            </div>
            <div class="card-body text-center d-block">
                <img id=previewImage src="<#if (user.avatar)??>${user.avatar}<#else>/images/avatar.png</#if>" class="avatar-xxlarge rounded-circle shadow">
                <hr/>
                <h5 class="my-3">${user.idNumber}</h5>
                <h3 class="font-weight-bold my-2">${user.getFullName()}</h3>
                <h5 class="my-3">${user.email}</h5>
                <h5 class="my-3">${user.phone}</h5>
                <h5 class="my-2">${user.address}</h5>
                <hr/>
                <a href="/employees/edit/${user.idNumber}" class="btn btn-warning btn-block">
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