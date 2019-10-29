<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="utf-8">
<title>ATM System: Withdrawal Funds</title>
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
				<h1 class="demo-section-title text-uppercase text-center">Withdrawal
					Funds</h1>
				<h6 class="text-center" th:text="${accountNumber}"></h6>
				<div class="row">
					<!-- 3/4 -->
					<div class="col-xs-9">
						<div class="row">
							<div class="col-xs-4">
								<div class="tile">
									<a id="qc10" th:href="@{/withdrawl?amount=10}"
										class="btn btn-lg btn-primary text-uppercase btn-padding">&#36;
										10.00</a>
								</div>
							</div>
							<div class="col-xs-4">
								<div class="tile">
									<a id="qc50" th:href="@{/withdrawl?amount=50}"
										class="btn btn-lg btn-primary text-uppercase btn-padding">&#36;
										50.00</a>
								</div>
							</div>
							<div class="col-xs-4">
								<div class="tile">
									<a id="qc100" th:href="@{/withdrawl?amount=100}"
										class="btn btn-lg btn-primary text-uppercase btn-padding">&#36;
										100.00</a>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<h6>Click a button to get that amount of cash.</h6>
								<h5>or input a multiple of $10. Must be lower than $1000</h5>
								<input type="text" id="qcCustomNumber" class="form-control"
									placeholder="50.00" />
								<!-- Number Keys -->
								<!-- TABLE -->
								<div id="pinPad">
									<table>
										<tr>
											<td colspan="5"><a id="btnConfirm" href="#"
												class="btn btn-block btn-lg btn-primary text-uppercase">Confirm</a></td>
											<td colspan="2"><a id="btnDelete" href="#"
												class="btn btn-block btn-lg btn-default text-uppercase">Clear</a></td>
										</tr>
									</table>
									<span style="color: red;" th:utext="${message}"></span>
								</div>
								<!-- // Pin Pad -->
							</div>
						</div>
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
	<script th:inline="javascript">
		/*<![CDATA[*/
		    var context = [[@{/}]];
		/*]]>*/
		videojs.options.flash.swf = "js/video-js.swf"
		$(document).ready(
				function() {
					$("#btnConfirm").click(
							function() {
								window.location.replace(context+"withdrawl?amount="
										+ $("#qcCustomNumber").val());
							});
					$("#btnDelete").click(function() {
						$("#qcCustomNumber").val('')
					});
				});
	</script>
</body>
</html>
