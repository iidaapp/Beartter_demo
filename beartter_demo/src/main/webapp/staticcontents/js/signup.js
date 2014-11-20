$(document).ready(
		function() {
			$("#custom_form")
					.validate(
							{
								errorClass : 'has-error',
								errorElement : 'span',

								groups : {
									date : "year month day" // name属性で大丈夫っぽい。
								},

								errorPlacement : function(error, element) {
									// エラーを出す場所を決める
									if (element.attr("name") == "year"
											|| element.attr("name") == "month"
											|| element.attr("name") == "day") {
										error.insertAfter(".myError");

									} else
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
									userName : {
										required : true,
										maxlength : 50,
										userNameValidation : true
									},
									mailAddress : {
										required : true,
										maxlength : 200,
										mailAddressValidation : true
									},
									year : {
										required : true,
										maxlength : 4,
										yearValidation : true
									},
									month : {
										required : true,
										maxlength : 2,
										monthdayValidation : true
									},
									day : {
										required : true,
										maxlength : 2,
										monthdayValidation : true
									},
									password : {
										required : true,
										maxlength : 45
									},
									passwordConfirm : {
										required : true,
										equalTo : "#password",
										maxlength : 45
									}
								},
								messages : {
									userName : {
										required : "ユーザ名を入力してください",
										maxlength : "50文字以下にしてください"
									},
									mailAddress : {
										required : "メールアドレスを入力してください",
										maxlength : "200文字以下にしてください"
									},
									password : {
										required : "パスワードを入力してください",
										maxlength : "45文字以下にしてください"
									},
									passwordConfirm : {
										required : "確認用パスワードを入力してください",
										equalTo : "パスワードが一致していません",
										maxlength : "45文字以下にしてください"
									},
									year : {
										required : "例の通りに入力してください",
										maxlength : "年は4字以下にしてください"
									},
									month : {
										required : "例の通りに入力してください",
										maxlength : "月は2字以下にしてください"
									},
									day : {
										required : "例の通りに入力してください",
										maxlength : "日は2字以下にしてください"
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

// カスタムルールを追加
jQuery.validator.addMethod(

"userNameValidation",

function(value, element) {

	reg = new RegExp("^[0-9a-zA-Z]+$");

	return this.optional(element) || reg.test(value);

},

"ユーザー名は「半角英数字」で入力してください"

);

jQuery.validator.addMethod(

		"mailAddressValidation",

		function(value, element) {

			reg = new RegExp("..*[@]..*");

			return this.optional(element) || reg.test(value);

		},

		"正しいメールアドレスの形式で入力して下さい"

		);

jQuery.validator.addMethod(

		"yearValidation",

		function(value, element) {

			reg = new RegExp("\\d{4}");

			return this.optional(element) || reg.test(value);

		},

		"例の通りに入力してください"

		);

jQuery.validator.addMethod(

		"monthdayValidation",

		function(value, element) {

			reg = new RegExp("\\d{2}");

			return this.optional(element) || reg.test(value);

		},

		"例の通りに入力してください"

		);