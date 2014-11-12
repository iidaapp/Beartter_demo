function asyncRequest(url, params){
	$.ajax({

	async: true,
	cache:false,
	type: "POST",
	data: params,
	url: url,

	beforeSend: function(){
	// リクエスト送信前に行う処理を記述
	},

	success: function(msg){
		var myData =  JSON.parse(msg);
		alert( "Data Saved: " + myData.utc_offset );
	},

	complete: function(){
	// 通信が成功して応答が返って来た際の処理を記述
	}

	});
}

function getProfile(userid){
	asyncRequest("getprofile", "userId=" + userid);
}