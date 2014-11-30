function asyncRequest(url, params, callback) {
	$.ajax({

		async : true,
		cache : false,
		type : "POST",
		data : params,
		url : url,

		beforeSend : function() {
			// リクエスト送信前に行う処理を記述
		},

		success : callback,

		complete : function() {
			// 通信が成功して応答が返って来た際の処理を記述
		}

	});
}

function getProfile(userid) {

	var getProfileCallback = function(msg) {
		var user = JSON.parse(msg);

		var image = user.profile_image_url;
		image = image.replace("normal.", "bigger.");
		$("#icon").empty().append("<img src='" + image + "'>");

		var screen_name = htmlspecialchars(user.screen_name);
		$("#screenname").empty().append("@" + screen_name);

		var name = htmlspecialchars(user.name);
		$("#name").empty().append(name);

		var desc = htmlspecialchars(user.description);
		$("#desc").empty().append(desc);

	};

	asyncRequest("getprofile", "userId=" + userid, getProfileCallback);

}

function getFriendship(userid) {

	var getFriendship = function(msg) {

		var json = JSON.parse(msg);
		var elm = document.getElementById('friendshipbutton');

		if (json.isSourceFollowingTarget) {

			elm.className = "remove-css btn btn-danger";
			elm.value = "Remove";
			elm.onclick = new Function("sendRemove(" + userid + ");");

		} else if (!json.isSourceFollowingTarget && !json.isFollowRequestSent) {

			elm.className = "follow-css btn btn-primary";
			elm.value = "Follow";
			elm.onclick = new Function("sendFollow(" + userid + ");");

		} else {
			elm.className = "followrequest-css";
			elm.value = "FollowRequest Sent";
			elm.onclick = new Function("");
		}

	};

	asyncRequest("getfriendship", "userId=" + userid, getFriendship);
}

function htmlspecialchars(ch) {
	ch = ch.replace(/&/g, "&amp;");
	ch = ch.replace(/"/g, "&quot;");
	ch = ch.replace(/'/g, "&#039;");
	ch = ch.replace(/</g, "&lt;");
	ch = ch.replace(/>/g, "&gt;");
	return ch;
}

window.twttr = (function(d, s, id) {
	var t, js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id))
		return;
	js = d.createElement(s);
	js.id = id;
	js.src = "https://platform.twitter.com/widgets.js";
	fjs.parentNode.insertBefore(js, fjs);
	return window.twttr || (t = {
		_e : [],
		ready : function(f) {
			t._e.push(f)
		}
	});
}(document, "script", "twitter-wjs"));

function sendRemove(userid) {

	var sendRemove = function(msg) {

		getFriendship(userid);
	};

	asyncRequest("sendremove", "userId=" + userid, sendRemove);

}

function sendFollow(userid) {

	var sendFollow = function(msg) {

		getFriendship(userid);
	};

	asyncRequest("sendfollow", "userId=" + userid, sendFollow);
	getFriendship(userid);
}

function getValue(id){
	getProfile(id);
	getFriendship(id);
}
