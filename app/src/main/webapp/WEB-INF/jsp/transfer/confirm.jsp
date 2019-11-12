<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="utf-8">
<title>ATM System: Transfer Funds</title>
<!-- Loading Bootstrap -->
<link th:href="@{/css/bootstrap.min.css}" rel="stylesheet">

<!-- Loading Flat UI -->
<link th:href="@{/css/flat-ui.css}" rel="stylesheet">
<link th:href="@{/css/demo.css}" rel="stylesheet">
<link th:href="@{/css/custom.css}" rel="stylesheet">

<link rel="shortcut icon" th:href="@{/img/favicon.ico}">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
<!--[if lt IE 9]>
      <script src="dist/js/vendor/html5shiv.js"></script>
      <script src="dist/js/vendor/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="container">
		<div class="demo-headline" style="padding: 0px;">
			<h1 class="demo-logo">
				ATM <small>24 Hour Syariah Saving</small>
			</h1>
		</div>
		<!-- /demo-headline -->
		<div class="row">
			<div class="col-xs-12" id="screenView">
				<!-- Place <h1></h1> below -->
				<h1 class="demo-section-title text-uppercase text-center">Transfer
					Funds</h1>
				<div class="row">
					<!-- 3/4 -->
					<!-- Balance -->
					<div class="col-xs-9">
						<form class="form-horizontal" method="POST"
							th:action="@{/transfer}">
							<input name="destination" type="hidden"
								th:value="${ destination }"> <input name="amount"
								type="hidden" th:value="${ amount }"> <input
								name="reference" type="hidden" th:value="${ reference }">
							<div class="col-xs-12">
								<div class="tile">
									<h4 class="text-uppercase balance">
										<span>Reference</span><span style="color: black;"
											th:text="${reference}"></span>
									</h4>
								</div>
							</div>
							<!-- Options -->
							<div class="col-xs-6">
								<div class="tile">
									<h5>From:</h5>
									<button type="button" class="btn btn-block btn-lg btn-inverse">
										<h6>
											<span style="color: black;" th:text="${accountNumber}"></span>
										</h6>
									</button>
								</div>
							</div>

							<div class="col-xs-6">
								<div class="tile">
									<h5>To:</h5>
									<button type="button" class="btn btn-block btn-lg btn-inverse">
										<h6>
											<span style="color: black;" th:text="${ destination }"></span>
										</h6>
									</button>
								</div>
							</div>
							<!-- // END OPTIONS -->
							<div class="col-xs-12">
								<div class="tile">
									<h4 class="text-uppercase balance">
										<span>Amount</span>$<span style="color: black;"
											th:text="${ formattedAmount }"></span>
									</h4>
								</div>
							</div>
							<div class="col-xs-12" style="margin-bottom: 2rem;">
								<button type="submit" class="btn btn-block btn-lg btn-success">
									<h6>
										<span class="fui-check"></span> Confirm Transfer
									</h6>
								</button>
							</div>
						</form>
					</div>
					<!-- 1/4 -->
					<div class="col-xs-3">
						<div class="tile">
							<a id="back" th:href="@{/transaction}"
								class="btn btn-lg btn-inverse text-uppercase btn-padding"><span
								class="fui-arrow-left"></span> Back</a>
						</div>
						<div class="tile">
							<a id="cancelTransactin" th:href="@{/logout}"
								class="btn btn-lg btn-danger text-uppercase btn-padding"><span
								class="fui-cross"></span> Exit</a>
						</div>
					</div>
				</div>
				<!-- // END OPTIONS-->
			</div>
			<!--  // END column-->
		</div>
		<!-- // END row-->
	</div>
	<!-- /container -->
	<script type="text/javascript" th:src="@{/js/jquery.min.js}"></script>
	<script type="text/javascript" th:src="@{/js/flat-ui.min.js}"></script>
	<script type="text/javascript" th:src="@{/js/video.js}"></script>
	<script type="text/javascript" th:src="@{/js/application.js}"></script>
	<script>
		videojs.options.flash.swf = "js/video-js.swf";
		$(document).ready(function() {

		});
	</script>
</body>
</html>
