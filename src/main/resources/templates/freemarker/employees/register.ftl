<#include "../base.ftl"/>

<#macro title>${action} employee</#macro>

<#macro styles></#macro>

<#macro body>
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">${action} employee</h1>
</div>
<form method="POST" action=<#if action == "Add">"/employees/register"<#else>"/employees/edit/${id_number}"</#if> enctype="multipart/form-data">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Personal information</h6>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-lg-3 py-3">
                    <div class="text-center">
                        <img id=previewImage src="<#if (employee.avatar)??>${employee.avatar}<#else>/images/avatar.png</#if>" alt="" class="avatar-xxlarge rounded-circle shadow">
                        <div class="input-group my-3">
                            <div class="custom-file text-left">
                                <input name="avatar" type="file" class="custom-file-input" onchange="previewImage.src = window.URL.createObjectURL(this.files[0])">
                                <label class="custom-file-label">Choose file</label>
                            </div>
                        </div>
                        <small class="form-text text-muted"><#if action == 'Edit'>Only if you want to change the image<#else>Optional</#if></small>
                    </div>
                </div>
                <div class="col-lg-9 py-3">
                    <div class="form-group row">
                        <div class="col-sm-6 mb-3 mb-sm-0">
                            <label class="text-dark">First name</label>
                            <input name="name" type="text" class="form-control form-control-user <#if (errors.name)??>is-invalid</#if>" placeholder="First name" value="<#if (employee.name)??>${employee.name}</#if>" required>
                            <#if (errors.name)??><div class="invalid-feedback">${errors.name}</div></#if>
                        </div>
                        <div class="col-sm-6">
                            <label class="text-dark">Last name</label>
                            <input name="lastName" type="text" class="form-control form-control-user <#if (errors.lastName)??>is-invalid</#if>" placeholder="Last name" value="<#if (employee.lastName)??>${employee.lastName}</#if>" required>
                            <#if (errors.lastName)??><div class="invalid-feedback">${errors.lastName}</div></#if>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6 mb-3 mb-sm-0">
                            <label class="text-dark">Id Number</label>
                            <input name="idNumber" type="text" class="form-control form-control-user <#if (errors.idNumber)??>is-invalid</#if>" placeholder="Id Number" value="<#if (employee.idNumber)??>${employee.idNumber}</#if>" required>
                            <#if (errors.idNumber)??><div class="invalid-feedback">${errors.idNumber}</div></#if>
                        </div>
                        <div class="col-sm-6">
                            <label class="text-dark">Email address</label>
                            <input name="email" type="email" class="form-control form-control-user <#if (errors.email)??>is-invalid</#if>" placeholder="Email address" value="<#if (employee.email)??>${employee.email}</#if>" required>
                            <#if (errors.email)??><div class="invalid-feedback">${errors.email}</div></#if>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6 mb-3 mb-sm-0">
                            <label class="text-dark">Phone</label>
                            <input name="phone" type="tel" class="form-control form-control-user <#if (errors.phone)??>is-invalid</#if>" placeholder="Phone" value="<#if (employee.phone)??>${employee.phone}</#if>" required>
                            <#if (errors.phone)??><div class="invalid-feedback">${errors.phone}</div></#if>
                        </div>
                        <div class="col-sm-6">
                            <label class="text-dark">Role</label>
                            <select name="role" class="form-control form-control-user <#if (errors.role)??>is-invalid</#if>">
                                <option <#if !((user.roles?first.role)??)>selected</#if>>&lt;Select Role&gt;</option>
                                <option value="ADMIN" <#if (employee.roles?first.role)?? && user.roles?first.role == "ADMIN">selected</#if>>Admin</option>
                                <option value="EMPLOYEE" <#if (employee.roles?first.role)?? && user.roles?first.role == "EMPLOYEE">selected</#if>>Employee</option>
                            </select>
                            <#if (errors.role)??><div class="invalid-feedback">${errors.role}</div></#if>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="text-dark">Address</label>
                        <input name="address" type="text" class="form-control form-control-user <#if (errors.address)??>is-invalid</#if>" placeholder="Address" value="<#if (employee.address)??>${employee.address}</#if>" required>
                        <#if (errors.address)??><div class="invalid-feedback">${errors.address}</div></#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Password</h6>
        </div>
        <div class="card-body">
            <div class="form-group row">
                <div class="col-sm-6 mb-3 mb-sm-0">
                    <label class="text-dark">Password</label>
                    <input name="password" type="password" class="form-control form-control-user <#if (errors.password)??>is-invalid</#if>" placeholder="Password" <#if action == 'Add'>required</#if>>
                    <#if action == 'Edit'><small class="form-text text-muted">Only if you want to change the password</small></#if>
                    <#if (errors.password)??><div class="invalid-feedback">${errors.password}</div></#if>
                </div>
                <div class="col-sm-6">
                    <label class="text-dark">Confirm password</label>
                    <input name="confirmPassword" type="password" class="form-control form-control-user <#if (errors.confirmPassword)??>is-invalid</#if>" placeholder="Confirm password" <#if action == 'Add'>required</#if>>
                    <#if action == 'Edit'><small class="form-text text-muted">Only if you want to change the password</small></#if>
                    <#if (errors.confirmPassword)??><div class="invalid-feedback">${errors.confirmPassword}</div></#if>
                </div>
            </div>
        </div>
    </div>
    <button type="submit" class="btn btn-primary btn-user mb-4 btn-block w-50 mx-auto">
        ${action} employee
    </button>
</form>
</#macro>

<#macro scripts></#macro>

<@page/>