function asyncRequest(url, params) {
	$.ajax({

		async : true,
		cache : false,
		type : "POST",
		data : params,
		url : url,

		beforeSend : function() {
			// リクエスト送信前に行う処理を記述
		},

		success : function(msg) {
			var user = JSON.parse(msg);

			var image = user.profile_image_url;
			image.replace("normal.", "bigger.");
			$("#icon").replaceWith("<img src='" + image + "'>");

			var screen_name = htmlspecialchars(user.screen_name);
			$("#screenname").replaceWith(screen_name);
			
			var name = htmlspecialchars(user.name);
			$("#name").replaceWith(name);
			
			var desc = htmlspecialchars(user.description);
			$("#desc").replaceWith(desc);
			

		},

		complete : function() {
			// 通信が成功して応答が返って来た際の処理を記述
		}

	});
}

function getProfile(userid) {
	asyncRequest("getprofile", "userId=" + userid);
}

function htmlspecialchars(ch) {
	ch = ch.replace(/&/g, "&amp;");
	ch = ch.replace(/"/g, "&quot;");
	ch = ch.replace(/'/g, "&#039;");
	ch = ch.replace(/</g, "&lt;");
	ch = ch.replace(/>/g, "&gt;");
	return ch;
}