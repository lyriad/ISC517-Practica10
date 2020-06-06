<#include "../base.ftl"/>

<#macro title><@spring.message "employee.singular.up" /></#macro>

<#macro styles></#macro>

<#macro body>
<div class="d-flex row">
    <div class="col-md-4">
        <div class="card shadow">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary"><@spring.message "form.label.personal-info" /></h6>
            </div>
            <div class="card-body text-center d-block">
                <img id=previewImage src="<#if (employee.avatar)??>${employee.avatar}<#else>/images/avatar.png</#if>" class="avatar-xxlarge rounded-circle shadow">
                <hr/>
                <h5 class="my-3">${employee.idNumber}</h5>
                <h3 class="font-weight-bold my-2">${employee.getFullName()}</h3>
                <h5 class="my-3">${employee.email}</h5>
                <h5 class="my-3">${employee.phone}</h5>
                <h5 class="my-2">${employee.address}</h5>
                <hr/>
                <a href="/employees/edit/${employee.idNumber}" class="btn btn-warning btn-block">
                    <@spring.message "action.Edit" />
                </a>
            </div>
        </div>
    </div>
    <div class="col-md-8">
        <div class="card shadow">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary"><@spring.message "label.performance" /></h6>
            </div>
        </div>
    </div>
</div>
</#macro>

<#macro scripts></#macro>

<@page/>