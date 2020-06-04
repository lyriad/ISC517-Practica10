<#include "../base.ftl"/>

<#macro title>Add employee</#macro>

<#macro styles></#macro>

<#macro body>
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">${action} employee</h1>
</div>
<form method="POST" action=<#if action == "Add">"/employees/register"<#else>"/edit/${id_number}"</#if> enctype="multipart/form-data">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Personal information</h6>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-lg-4">
                    <div class="text-center">
                        <img id=previewImage src="<#if (user.avatar)??>${user.avatar}<#else>/images/avatar.png</#if>" alt="" class="avatar-xxlarge rounded-circle shadow">
                        <div class="input-group my-3">
                            <div class="custom-file text-left">
                                <input name="avatar" type="file" class="custom-file-input <#if (errors.avatar)??>is-invalid</#if>" onchange="previewImage.src = window.URL.createObjectURL(this.files[0])">
                                <label class="custom-file-label">Choose file</label>
                            </div>
                            <#if (errors.avatar)??><div class="d-block invalid-feedback">${errors.avatar}</div></#if>
                        </div>
                    </div>
                </div>
                <div class="col-lg-8 py-3">
                    <div class="form-group row">
                        <div class="col-sm-6 mb-3 mb-sm-0">
                            <input name="name" type="text" class="form-control form-control-user <#if (errors.name)??>is-invalid</#if>" placeholder="First name" value="<#if (user.name)??>${user.name}</#if>" required>
                            <#if (errors.name)??><div class="invalid-feedback">${errors.name}</div></#if>
                        </div>
                        <div class="col-sm-6">
                            <input name="lastName" type="text" class="form-control form-control-user <#if (errors.lastName)??>is-invalid</#if>" placeholder="Last name" value="<#if (user.lastName)??>${user.lastName}</#if>" required>
                            <#if (errors.lastName)??><div class="invalid-feedback">${errors.lastName}</div></#if>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6 mb-3 mb-sm-0">
                            <input name="idNumber" type="text" class="form-control form-control-user <#if (errors.idNumber)??>is-invalid</#if>" placeholder="Id Number" value="<#if (user.idNumber)??>${user.idNumber}</#if>" required>
                            <#if (errors.idNumber)??><div class="invalid-feedback">${errors.idNumber}</div></#if>
                        </div>
                        <div class="col-sm-6">
                            <input name="email" type="email" class="form-control form-control-user <#if (errors.email)??>is-invalid</#if>" placeholder="Email address" value="<#if (user.email)??>${user.email}</#if>" required>
                            <#if (errors.email)??><div class="invalid-feedback">${errors.email}</div></#if>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6 mb-3 mb-sm-0">
                            <input name="phone" type="tel" class="form-control form-control-user <#if (errors.phone)??>is-invalid</#if>" placeholder="Phone" value="<#if (user.phone)??>${user.phone}</#if>" required>
                            <#if (errors.phone)??><div class="invalid-feedback">${errors.phone}</div></#if>
                        </div>
                        <div class="col-sm-6">
                            <select name="role" class="form-control form-control-user <#if (errors.role)??>is-invalid</#if>">
                                <option <#if !((user.roles?first.role)??)>selected</#if>>&lt;Select Role&gt;</option>
                                <option value="ADMIN" <#if (user.roles?first.role)?? && user.roles?first.role == "ADMIN">selected</#if>>Admin</option>
                                <option value="EMPLOYEE" <#if (user.roles?first.role)?? && user.roles?first.role == "EMPLOYEE">selected</#if>>Employee</option>
                            </select>
                            <#if (errors.role)??><div class="invalid-feedback">${errors.role}</div></#if>
                        </div>
                    </div>
                    <div class="form-group">
                        <input name="address" type="text" class="form-control form-control-user <#if (errors.address)??>is-invalid</#if>" placeholder="Home address" value="<#if (user.address)??>${user.address}</#if>" required>
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
                    <input name="password" type="password" class="form-control form-control-user <#if (errors.password)??>is-invalid</#if>" placeholder="Password" required>
                    <#if (errors.password)??><div class="invalid-feedback">${errors.password}</div></#if>
                </div>
                <div class="col-sm-6">
                    <input name="confirmPassword" type="password" class="form-control form-control-user <#if (errors.confirmPassword)??>is-invalid</#if>" placeholder="Confirm password" required>
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