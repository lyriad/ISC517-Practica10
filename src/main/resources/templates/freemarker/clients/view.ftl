<#include "../base.ftl"/>

<#macro title><@spring.message "client.singular.up" /></#macro>

<#macro styles></#macro>

<#macro body>
<div class="d-flex row">
    <div class="col-md-4">
        <div class="card shadow">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary"><@spring.message "form.label.personal-info" /></h6>
            </div>
            <div class="card-body text-center d-block">
                <img id=previewImage src="<#if (client.avatar)??>${client.avatar}<#else>/images/avatar.png</#if>" class="avatar-xxlarge rounded-circle shadow">
                <hr/>
                <h5 class="my-3">${client.idNumber}</h5>
                <h3 class="font-weight-bold my-2">${client.getFullName()}</h3>
                <h5 class="my-3">${client.email}</h5>
                <h5 class="my-3">${client.phone}</h5>
                <h5 class="my-2">${client.address}</h5>
                <hr/>
                <a href="/clients/edit/${client.idNumber}" class="btn btn-warning btn-block">
                    <@spring.message "action.Edit" />
                </a>
            </div>
        </div>
    </div>
    <div class="col-md-8">
        <div class="card shadow">
            <div class="card-header d-flex justify-content-between py-3">
                <h6 class="my-auto font-weight-bold text-primary"><@spring.message "label.rental-history" /></h6>
                <button type="button" class="btn btn-primary btn-icon-split" data-toggle="modal" data-target="#addRentalModal">
                    <span class="icon text-white-50">
                        <i class="fas fa-plus"></i>
                    </span>
                    <span class="text"><@spring.message "action.Add" /> <@spring.message "new.m.dn" /> <@spring.message "rental.singular.dn" /></span>
                </button>
            </div>
            <div class="card-body">
                <#list rentals>
                <div class="table-responsive">
                    <table class="table table-bordered" width="100%" cellspacing="0">
                        <thead>
                            <tr role="row">
                                <th style="flex: 1; min-width: 1px;"><@spring.message "equipment.singular.up" /></th>
                                <th style="width: 1px;"><@spring.message "rental.attr.amount.up" /></th>
                                <th style="width: 20%;"><@spring.message "rental.attr.createdAt.up" /></th>
                                <th style="width: 20%;"><@spring.message "rental.attr.promisedReturnDate.up" /></th>
                            </tr>
                        </thead>
                        <tbody>
                            <#items as rental>
                            <tr>
                                <td>${rental.equipment.name}</td>
                                <td>${rental.amount}</td>
                                <td>${rental.createdAt?date}</td>
                                <td>${rental.promisedReturnDate?date}</td>
                            </tr>
                            </#items>
                        </tbody>
                    </table>
                </div>
                <#else>
                <h2>No rentals</h2>
                </#list>
            </div>
        </div>
    </div>
</div>
<form method="POST" action="/rentals/register">
    <div class="modal fade" id="addRentalModal" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"><@spring.message "action.Add" /> <@spring.message "new.m.dn" /> <@spring.message "rental.singular.dn" /></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <div class="row">
                        <label><@spring.message "rental.form.label.equipment" /></label>
                        <div class="input-group">
                            <input id="equipmentSearch" type="text" class="form-control form-control-user" placeholder='<@spring.message "equipment.attr.name.up" />'>
                            <div class="input-group-append">
                                <button id="equipmentSearchBtn" type="button" class="btn btn-primary btn-user mb-4">
                                    <@spring.message "search.up" />
                                </button>
                            </div>
                        </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <h2 id="equipmentNotFound" class="d-none"><@spring.message "status.404.label" />!</h2>
                                <div id="equipmentCard" class="d-none">
                                    <div class="d-flex">
                                        <img id="equipmentImage" src="" class="avatar-xlarge shadow my-auto">
                                        <div class="text-center w-100">
                                            <h4 id="equipmentName" class="font-weight-bold my-3"></h4>
                                            <h6 class="my-2"><@spring.message "equipment.attr.category.up" />: <span id="equipmentCategory"></span></h3>
                                            <h6 class="my-2"><@spring.message "equipment.attr.subCategory.up" />: <span id="equipmentSubcategory"></span></h6>
                                            <h6 class="my-2"><@spring.message "equipment.attr.cantAvailable.up" />: <span id="equipmentUnits"></span></h6>
                                            <h6 class="my-2"><@spring.message "equipment.attr.costPerDay.up" />: <span id="equipmentCost"></span></h6>
                                        </div>
                                    </div>
                                    <hr />
                                    <div class="form-group">
                                        <label class="text-dark"><@spring.message "rental.attr.amount.up" /></label>
                                        <input id="rentalAmount" name="amount" type="number" min="1" value="1" class="form-control form-control-user" placeholder='<@spring.message "rental.attr.amount.up" />'>
                                    </div>
                                    <div class="form-group">
                                        <label class="text-dark"><@spring.message "rental.attr.createdAt.up" /></label>
                                        <input id="createdAt" name="createdAt" type="date" class="form-control form-control-user">
                                    </div>
                                    <div class="form-group">
                                        <label class="text-dark"><@spring.message "rental.attr.promisedReturnDate.up" /></label>
                                        <input id="rentalReturnDate" name="returnDate" type="date" class="form-control form-control-user">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button id="closeModal" type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button id="submitBtn" type="submit" class="btn btn-primary" disabled><@spring.message "action.Add" /></button>
                </div>
            </div>
        </div>
    </div>
    <input id="equipmentId" name="id_equipment" type="number" class="d-none" readonly>
    <input name="clientIdNumber" type="text" class="d-none" value="${client.idNumber}" readonly>
</form>
</#macro>

<#macro scripts>
<script>
	$(document).ready(() => {

        $('#createdAt').attr('min', new Date().toISOString().split("T")[0]);
        $('#createdAt').val(new Date().toISOString().split("T")[0]);
        $('#rentalReturnDate').attr('min', new Date().toISOString().split("T")[0]);

        $('#equipmentSearchBtn').click( () => {
            const search = $("#equipmentSearch").val();
            if (search === '') {
                $("#equipmentSearch").addClass('is-invalid');
                return;
            }
            $.ajax({
                url: "/api/equipments/search/" + search,
                method:'GET', 
                success: equipment => {
                    $('#equipmentId').val(equipment.id);
                    $('#equipmentImage').attr('src', equipment.image);
                    $('#equipmentName').text(equipment.name);
                    $('#equipmentCategory').text(equipment.category.name);
                    $('#equipmentSubcategory').text(equipment.subCategory === null ? '--' : equipment.subCategory.name);
                    $('#equipmentUnits').text(equipment.cantAvailable);
                    $('#equipmentCost').text(equipment.costPerDay);
                    $('#rentalAmount').attr('max', equipment.cantAvailable);
                    $('#equipmentNotFound').removeClass().addClass('d-none');
                    $('#equipmentCard').removeClass().addClass('d-block');
                    $("#equipmentSearch").val('');
                }, error: () => {
                    $('#equipmentCard').removeClass().addClass('d-none');
                    $('#equipmentNotFound').removeClass().addClass('d-block');
                    $('#submitBtn').attr('disabled', '')
                }
            });
            $("#equipmentSearch").removeClass('is-invalid');
        });

        $('#closeModal').click( () => {
            $('#equipmentCard').removeClass().addClass('d-none');
            $('#equipmentImage').attr('src', '');
            $('#equipmentName').text('');
            $('#equipmentCategory').text('');
            $('#equipmentSubcategory').text('');
            $('#equipmentUnits').text('');
            $('#equipmentCost').text('');
            $("#equipmentSearch").val('');
            $('#submitBtn').attr('disabled', '')
        });

        $('#rentalReturnDate').change( () => {
            if ($('#rentalReturnDate').val() !== '')
                $('#submitBtn').removeAttr('disabled')
        });
    });
</script>
</#macro>

<@page/>