<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Error</title>
        <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="/css/sb-admin-2.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container-fluid my-5">
            <div class="text-center">
                <div class="error mx-auto" data-text="${Request.status}">${Request.status}</div>
                <p class="lead text-gray-800 mb-5">
                    <#if Request.status == 403>
                    You are not allowed here!
                    <#else>
                    There was an error!
                    </#if>
                </p>
                <#if Request.status != 403>
                <p class="text-gray-500 mb-0">It looks like you found a glitch in the matrix...</p>
                </#if>
            </div>
        </div>
    </body>
</html>