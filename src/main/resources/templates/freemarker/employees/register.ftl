<#include "../base.ftl"/>

<#macro title><@spring.message "action.${action}" /> <@spring.message "employee.singular.dn" /></#macro>

<#macro styles></#macro>

<#macro body>
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800"><@spring.message "action.${action}" /> <@spring.message "employee.singular.dn" /></h1>
</div>
<form method="POST" action=<#if action == "Add">"/employees/register"<#else>"/employees/edit/${id_number}"</#if> enctype="multipart/form-data">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary"><@spring.message "form.label.personal-info" /></h6>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-lg-3 py-3">
                    <div class="text-center">
                        <img id=previewImage src="<#if (employee.avatar)??>${employee.avatar}<#else>/images/avatar.png</#if>" alt="" class="avatar-xxlarge rounded-circle shadow">
                        <div class="input-group my-3">
                            <div class="custom-file text-left">
                                <input name="avatar" type="file" class="custom-file-input" onchange="previewImage.src = window.URL.createObjectURL(this.files[0])">
                                <label class="custom-file-label"><@spring.message "form.choose-file" /></label>
                            </div>
                        </div>
                        <small class="form-text text-muted"><#if action == 'Edit'><@spring.message "form.tip-image-optional" /><#else><@spring.message "form.tip.optional" /></#if></small>
                    </div>
                </div>
                <div class="col-lg-9 py-3">
                    <div class="form-group row">
                        <div class="col-sm-6 mb-3 mb-sm-0">
                            <label class="text-dark"><@spring.message "user.attr.name.up" /></label>
                            <input name="name" type="text" class="form-control form-control-user <#if (errors.name)??>is-invalid</#if>" placeholder='<@spring.message "user.attr.name.up" />' value="<#if (employee.name)??>${employee.name}</#if>" required>
                            <#if (errors.name)??><div class="invalid-feedback">${errors.name}</div></#if>
                        </div>
                        <div class="col-sm-6">
                            <label class="text-dark"><@spring.message "user.attr.lastName.up" /></label>
                            <input name="lastName" type="text" class="form-control form-control-user <#if (errors.lastName)??>is-invalid</#if>" placeholder='<@spring.message "user.attr.lastName.up" />' value="<#if (employee.lastName)??>${employee.lastName}</#if>" required>
                            <#if (errors.lastName)??><div class="invalid-feedback">${errors.lastName}</div></#if>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6 mb-3 mb-sm-0">
                            <label class="text-dark"><@spring.message "user.attr.idNumber.up" /></label>
                            <input name="idNumber" type="text" class="form-control form-control-user <#if (errors.idNumber)??>is-invalid</#if>" placeholder='<@spring.message "user.attr.idNumber.up" />' value="<#if (employee.idNumber)??>${employee.idNumber}</#if>" required>
                            <#if (errors.idNumber)??><div class="invalid-feedback">${errors.idNumber}</div></#if>
                        </div>
                        <div class="col-sm-6">
                            <label class="text-dark"><@spring.message "user.attr.email.up" /></label>
                            <input name="email" type="email" class="form-control form-control-user <#if (errors.email)??>is-invalid</#if>" placeholder='<@spring.message "user.attr.email.up" />' value="<#if (employee.email)??>${employee.email}</#if>" required>
                            <#if (errors.email)??><div class="invalid-feedback">${errors.email}</div></#if>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6 mb-3 mb-sm-0">
                            <label class="text-dark"><@spring.message "user.attr.phone.up" /></label>
                            <input name="phone" type="tel" class="form-control form-control-user <#if (errors.phone)??>is-invalid</#if>" placeholder='<@spring.message "user.attr.phone.up" />' value="<#if (employee.phone)??>${employee.phone}</#if>" required>
                            <#if (errors.phone)??><div class="invalid-feedback">${errors.phone}</div></#if>
                        </div>
                        <div class="col-sm-6">
                            <label class="text-dark"><@spring.message "user.attr.role.up" /></label>
                            <select name="role" class="form-control form-control-user <#if (errors.role)??>is-invalid</#if>" value="<#if (employee.roles?first.role)??>${employee.roles?first.role}</#if>">
                                <option <#if !((user.roles?first.role)??)>selected</#if>><@spring.message "role.form.select" /></option>
                                <option value="ADMIN" <#if (employee.roles?first.role)?? && (employee.roles?first.role == "ADMIN")>selected</#if>><@spring.message "role.admin" /></option>
                                <option value="EMPLOYEE" <#if (employee.roles?first.role)?? && (employee.roles?first.role == "EMPLOYEE")>selected</#if>><@spring.message "employee.singular.up" /></option>
                            </select>
                            <#if (errors.role)??><div class="invalid-feedback">${errors.role}</div></#if>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="text-dark"><@spring.message "user.attr.address.up" /></label>
                        <input name="address" type="text" class="form-control form-control-user <#if (errors.address)??>is-invalid</#if>" placeholder="Address" value="<#if (employee.address)??>${employee.address}</#if>" required>
                        <#if (errors.address)??><div class="invalid-feedback">${errors.address}</div></#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary"><@spring.message "user.attr.password.up" /></h6>
        </div>
        <div class="card-body">
            <div class="form-group row">
                <div class="col-sm-6 mb-3 mb-sm-0">
                    <label class="text-dark"><@spring.message "user.attr.password.up" /></label>
                    <input name="password" type="password" class="form-control form-control-user <#if (errors.password)??>is-invalid</#if>" placeholder='<@spring.message "user.attr.password.up" />' <#if action == 'Add'>required</#if>>
                    <#if action == 'Edit'><small class="form-text text-muted"><@spring.message "form.tip-password-optional" /></small></#if>
                    <#if (errors.password)??><div class="invalid-feedback">${errors.password}</div></#if>
                </div>
                <div class="col-sm-6">
                    <label class="text-dark"><@spring.message "form.label.confirm-password" /></label>
                    <input name="confirmPassword" type="password" class="form-control form-control-user <#if (errors.confirmPassword)??>is-invalid</#if>" placeholder='<@spring.message "form.label.confirm-password" />' <#if action == 'Add'>required</#if>>
                    <#if action == 'Edit'><small class="form-text text-muted"><@spring.message "form.tip-password-optional" /></small></#if>
                    <#if (errors.confirmPassword)??><div class="invalid-feedback">${errors.confirmPassword}</div></#if>
                </div>
            </div>
        </div>
    </div>
    <button type="submit" class="btn btn-primary btn-user mb-4 btn-block w-50 mx-auto">
        <@spring.message "action.${action}" /> <@spring.message "employee.singular.dn" />
    </button>
</form>
</#macro>

<#macro scripts></#macro>

<@page/>