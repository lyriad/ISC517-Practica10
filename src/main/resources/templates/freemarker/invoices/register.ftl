<#include "../base.ftl"/>

<#macro title><@spring.message "invoice.plural.up" /></#macro>

<#macro styles></#macro>

<#macro body>
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800"><@spring.message "action.Add" /> <@spring.message "invoice.singular.dn" /></h1>
</div>
<div class="d-flex row">
    <div class="col-md-4">
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary"><@spring.message "client.singular.up" /></h6>
            </div>
            <div class="card-body">
                <div class="col-sm-12 mb-3 mb-sm-3">
                    <label><@spring.message "invoice.form.label.client" /></label>
                    <div class="input-group">
                        <input id="clientSearch" type="text" class="form-control form-control-user" placeholder='<@spring.message "user.attr.email.up" />/<@spring.message "user.attr.idNumber.up" />' >
                        <div class="input-group-append">
                            <button id="clientSearchBtn" type="button" class="btn btn-primary btn-user mb-4">
                                <@spring.message "search.up" />
                            </button>
                        </div>
                    </div>
                </div>
                <div class="text-center">
                    <h2 id="clientNotFound" class="d-none"><@spring.message "status.404.label" />!</h2>
                    <div id="clientCard" class="d-none">
                        <img id="clientAvatar" src="" class="avatar-xxlarge rounded-circle shadow">
                        <hr/>
                        <h5 class="my-3" id="clientIdNumber"></h5>
                        <h3 class="font-weight-bold my-2" id="clientName"></h3>
                        <h5 class="my-3" id="clientEmail"></h5>
                        <h5 class="my-3" id="clientPhone"></h5>
                        <h5 class="my-2" id="clientAddress"></h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-8">
        <div class="card shadow">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary"><@spring.message "rental.plural.up" /></h6>
            </div>
            <div class="card-body">
                <div id="rentals-table" class="table-responsive d-none">
                    <table class="table table-bordered" width="100%" cellspacing="0">
                        <thead>
                            <tr role="row">
                                <th style="flex: 1; min-width: 1px;"><@spring.message "equipment.singular.up" /></th>
                                <th style="width: 1px;"><@spring.message "rental.attr.amount.up" /></th>
                                <th style="width: 20%;"><@spring.message "invoice.form.rental.cost" /></th>
                                <th style="width: 20%;"><@spring.message "rental.attr.promisedReturnDate.up" /></th>
                                <th style="width: 1px;"><@spring.message "action.plural.up" /></th>
                            </tr>
                        </thead>
                        <tbody id="rentalItems">
                        </tbody>
                    </table>
                </div>
                <h2 id="no-rentals-label" class="d-flex"><@spring.message "invoice.form.label.no-rentals" /></h2>
                <h2 id="invoice-cost-label" class="d-none">Total: <span id="invoice-cost" data-amount="0"></span></h2>
            </div>
            <button type="submit" class="btn btn-primary btn-user mb-4 btn-block w-25 mx-auto" form="invoice-rentals">
                <@spring.message "action.Add" /> <@spring.message "invoice.singular.dn" />
            </button>
        </div>
    </div>
</div>
<form id="invoice-rentals" class="d-none" method="POST" action="/invoices/register">
</form>
</#macro>

<#macro scripts>
<script>

	$(document).ready(() => {

        $('#clientSearchBtn').click( () => {
            const search = $("#clientSearch").val();
            if (search === '') {
                $("#clientSearch").addClass('is-invalid');
                return;
            }
            $.ajax({
                url: "/api/clients/search/" + search,
                method:'GET', 
                success: client => {
                    fetchRentals(client.idNumber);
                    $('#clientAvatar').attr('src', client.avatar);
                    $('#clientIdNumber').text(client.idNumber);
                    $('#clientName').text(client.fullName);
                    $('#clientEmail').text(client.email);
                    $('#clientPhone').text(client.phone);
                    $('#clientAddress').text(client.address);
                    $('#clientNotFound').removeClass().addClass('d-none');
                    $('#clientCard').removeClass().addClass('d-block');
                    $("#clientSearch").val('');

                    $('#client-id').remove()
                    const input = `<input id="client-id" type="number" name="id_client" value="`+client.id+`" readonly>`;
                    $('#invoice-rentals').append(input);
                }, error: () => {
                    $('#clientCard').removeClass().addClass('d-none');
                    $('#clientNotFound').removeClass().addClass('d-block');
                }
            });
            $("#clientSearch").removeClass('is-invalid');
        });
    });

    const currencyFormat = new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'USD',
    });

    const fetchRentals = idNumber => {
        $.ajax({
            url: '/api/rentals/client/'+idNumber+'/pending',
            method:'GET', 
            success: rentals => {
                rentals.forEach((rental, index) => {
                    const row = '<tr>' +
                        '<td>' + rental.equipment.name + '</td>' +
                        '<td>' + rental.amount + '</td>' +
                        '<td>' + currencyFormat.format(rental.cost) + '</td>' +
                        '<td>' + rental.promisedReturnDate.split('T')[0] + '</td>' +
                        '<td>' +
                            "<button class='btn btn-success' onclick='addToInvoice("+index+","+JSON.stringify(rental)+")' id='add-rental-"+index+"'>" +
                                '<@spring.message "action.Add" />' +
                            '</button>' +
                            "<button class='btn btn-danger d-none' onclick='removeFromInvoice("+index+","+JSON.stringify(rental)+")' id='remove-rental-"+index+"'>" +
                                '<@spring.message "action.Remove" />' +
                            '</button>' +
                        '</td>' +
                    '</tr>';
                    $('#rentalItems').append(row);
                });
                $('#rentals-table').removeClass('d-none');
                $('#invoice-cost').text(currencyFormat.format(0))
                $('#invoice-cost-label').removeClass('d-none');
                $('#no-rentals-label').removeClass().addClass('d-none');
            }, error: () => {
            }
        });
    }

    const addToInvoice = (index, rental) => {
        const total = parseFloat($('#invoice-cost').attr('data-amount')) + parseFloat(rental.cost);
        $('#invoice-cost').attr('data-amount', total);
        $('#invoice-cost').text(currencyFormat.format(total));

        $('#add-rental-'+index).addClass('d-none');
        $('#remove-rental-'+index).removeClass('d-none');

        const input = `<input id="rental-input-`+index+`" type="number" name="rentals" value="`+rental.id+`" readonly>`;
        $('#invoice-rentals').append(input);
    }

    const removeFromInvoice = (index, rental) => {
        const total = parseFloat($('#invoice-cost').attr('data-amount')) - parseFloat(rental.cost);
        $('#invoice-cost').attr('data-amount', total);
        $('#invoice-cost').text(currencyFormat.format(total));

        $('#remove-rental-'+index).addClass('d-none');
        $('#add-rental-'+index).removeClass('d-none');
        $('#rental-input-'+index).remove();
    }
</script>

</#macro>

<@page/>