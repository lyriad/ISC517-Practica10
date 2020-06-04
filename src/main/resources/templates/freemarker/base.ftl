<#macro page>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title><@title/></title>
        <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="/vendor/Nunito/all.css" rel="stylesheet" type="text/css">
        <link href="/css/sb-admin-2.min.css" rel="stylesheet">
        <link href="/css/style.css" rel="stylesheet">
        <@styles/>
    </head>

    <body id="page-top">
        <div id="wrapper">
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
                <li class="nav-item active">
                    <a class="nav-link" href="/">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Dashboard</span></a>
                </li>
                <hr class="sidebar-divider"/>
                <li class="nav-item">
                    <a class="nav-link" href="#">
                    <i class="fas fa-fw fa-th-list"></i>
                    <span>Equipments</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">
                    <i class="fas fa-fw fa-truck"></i>
                    <span>Rentals</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">
                    <i class="fas fa-fw fa-file-invoice-dollar"></i>
                    <span>Invoices</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">
                    <i class="fas fa-fw fa-users"></i>
                    <span>Clients</span></a>
                </li>
                <#if Session.user.hasRole('ADMIN')>
                <li class="nav-item">
                    <a class="nav-link" href="/employees">
                    <i class="fas fa-fw fa-user-tie"></i>
                    <span>Employees</span></a>
                </li>
                </#if>
                <hr class="sidebar-divider"/>
                <li class="nav-item">
                    <a class="nav-link" href="/auth/logout">
                    <i class="fas fa-fw fa-sign-out-alt"></i>
                    <span>Logout</span></a>
                </li>
            </ul>
            <div id="content-wrapper" class="d-flex flex-column">
                <div id="content">
                    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                    <button id="sidebarToggleTop" class="btn btn-link rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">
                                    ${Session.user.getFullName()}
                                </span>
                                <img class="img-profile rounded-circle" src="<#if Session.user.avatar??>${Session.user.avatar}<#else>/images/avatar.png</#if>">
                            </a>
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Profile
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="/auth/logout">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>
                    </ul>
                </nav>

                <div class="container-fluid">
                    <@body/>
                </div>
            </div>
        </div>
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <script src="/js/jquery.min.js"></script>
        <script src="/js/bootstrap.bundle.min.js"></script>
        <script src="/js/jquery.easing.min.js"></script>
        <script src="/js/sb-admin-2.min.js"></script>
        <@scripts/>

    </body>
</html>
</#macro>