<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<title>Login</title>
		<link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
		<link href="/vendor/Nunito/all.css" rel="stylesheet" type="text/css">
		<link href="/css/sb-admin-2.min.css" rel="stylesheet">
	</head>

	<body class="bg-gradient-primary">
		<div class="container" style="margin-top: 10%;">
			<div class="row justify-content-center">
				<div class="col-xl-10 col-lg-12 col-md-9">
					<div class="card o-hidden border-0 shadow-lg my-5">
						<div class="card-body p-0">
							<div class="row">
							<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
								<div class="col-lg-6">
									<div class="p-5">
										<div class="text-center">
											<h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
										</div>
										<form method="POST" action="/auth/login">
											<div class="form-group">
												<input type="email" class="form-control form-control-user" name="email" id="loginEmail" placeholder="Email">
											</div>
											<div class="form-group">
												<input type="password" class="form-control form-control-user" name="password" id="loginPassword" placeholder="Password">
											</div>
											<div class="form-group">
												<div class="custom-control custom-checkbox small">
													<input type="checkbox" class="custom-control-input" id="customCheck">
													<label class="custom-control-label" for="customCheck">Remember Me</label>
												</div>
											</div>
											<button type="submit" class="btn btn-primary btn-user btn-block">
												Login
											</button>
										</form>
										<#if RequestParameters.error?? >
										<hr>
										<div class="alert alert-danger alert-dismissible fade show" role="alert">
											Wrong email or password!
											<button type="button" class="close" data-dismiss="alert" aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										</#if>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script src="/js/jquery.min.js"></script>
		<script src="/js/bootstrap.bundle.min.js"></script>
		<script src="/js/jquery.easing.min.js"></script>
		<script src="/js/sb-admin-2.min.js"></script>

	</body>
</html>
