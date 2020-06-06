<#include "../base.ftl"/>

<#macro title>${action} equipment</#macro>

<#macro styles></#macro>

<#macro body>
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">${action} equipment</h1>
</div>
<form method="POST" action=<#if action == "Add">"/equipments/register"<#else>"/equipments/edit/${id}"</#if> enctype="multipart/form-data">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Equipment information</h6>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-lg-3 py-3">
                    <div class="text-center">
                        <img id=previewImage src="<#if (equipment.image)??>${equipment.image}<#else>/images/equipment.png</#if>" alt="" class="avatar-xxlarge shadow">
                        <div class="input-group my-3">
                            <div class="custom-file text-left">
                                <input name="image" type="file" class="custom-file-input" onchange="previewImage.src = window.URL.createObjectURL(this.files[0])">
                                <label class="custom-file-label">Choose file</label>
                            </div>
                        </div>
                        <small class="form-text text-muted"><#if action == 'Edit'>Only if you want to change the image<#else>Optional</#if></small>
                    </div>
                </div>
                <div class="col-lg-9 py-3">
                    <div class="form-group row">
                        <div class="col-sm-12 mb-3 mb-sm-0">
                            <label class="text-dark">Name</label>
                            <input name="name" type="text" class="form-control form-control-user <#if (errors.name)??>is-invalid</#if>" placeholder="Equipment name" value="<#if (equipment.name)??>${equipment.name}</#if>" required>
                            <#if (errors.name)??><div class="invalid-feedback">${errors.name}</div></#if>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6 mb-3 mb-sm-0">
                            <label class="text-dark">Category</label>
                            <select name="id_category" id="category-select" class="form-control form-control-user <#if (errors.id_category)??>is-invalid</#if>" required>
                                <#if action == 'Add' ><option>Choose a Category</option></#if>
                                <#list categories as category>
                                    <option value="${category.id}"> ${category.name} </option>
                                </#list>
                            </select>
                            <#if (errors.id_category)??><div class="invalid-feedback">${errors.id_category}</div></#if>
                        </div>
                        <div class="col-sm-6 mb-3 mb-sm-0">
                            <label class="text-dark">Subcategory</label>
                            <select name="id_subcategory" id="subcategory-select" class="form-control form-control-user <#if (errors.idNumber)??>is-invalid</#if>" required>
                                <option value="" selected>Choose a Subcategory</option>
                            </select>
                            <#if (errors.id_subcategory)??><div class="invalid-feedback">${errors.id_subcategory}</div></#if>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6 mb-3 mb-sm-0">
                            <label class="text-dark">Quantity Available</label>
                            <input name="cantAvailable" type="number" class="form-control form-control-user <#if (errors.cantAvailable)??>is-invalid</#if>" value="<#if (equipment.cantAvailable)??>${equipment.cantAvailable}</#if>" required>
                            <#if (errors.cantAvailable)??><div class="invalid-feedback">${errors.cantAvailable}</div></#if>
                        </div>
                        <div class="col-sm-6">
                            <label class="text-dark">Cost Per Day</label>
                            <input name="costPerDay" type="number" class="form-control form-control-user <#if (errors.costPerDay)??>is-invalid</#if>" value="<#if (equipment.costPerDay)??>${equipment.costPerDay}</#if>" required>
                            <#if (errors.costPerDay)??><div class="invalid-feedback">${errors.costPerDay}</div></#if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <button type="submit" class="btn btn-primary btn-user mb-4 btn-block w-50 mx-auto">
        ${action} equipment
    </button>
</form>
</#macro>

<#macro scripts>

<script>

	$(document).ready(function(){   
        $("#category-select").change(function() {
            const id_category = $("#category-select :selected").val();
            $.ajax({
                url: "/api/categories/"+id_category+"/subcategories",
                method:'GET', 
                    success: function (data) {
                    console.log(data);
                    $('#subcategory-select option').not(':first').remove();
                    let html = '';
                    for(var i = 0; i < data.length; i++)
                        html += '<option value="'+data[i].id+'">'+data[i].name+'</option>';

                    $('#subcategory-select option').first().after(html);
                }
            });
        });
    });

</script>

</#macro>

<@page/>