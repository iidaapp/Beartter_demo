$(document).ready(
		function() {
			$("#custom_form")
					.validate(
							{
								errorClass : 'has-error',
								errorElement : 'span',

								errorPlacement : function(error, element) {
									// エラーを出す場所を決める
										error.insertAfter(element);
								},

								highlight : function(element, errorClass,
										validClass) {
									$(element).parents(
											"div[class='form-group']")
											.addClass(errorClass);
								},

								unhighlight : function(element, errorClass,
										validClass) {
									$(element).parents(".has-error")
											.removeClass(errorClass);
								},

								rules : {
									password : {
										required : true,
										maxlength : 45
									}
								},
								messages : {
									password : {
										required : "パスワードを入力してください",
										maxlength : "45文字以下にしてください"
									}
								}
							});

			// Validation成功時の遷移先
			$("input.validate").click(function() {
				if ($("#custom_form").valid() == true) {
					$(form).submit();
				}
				return false;
			});
		});