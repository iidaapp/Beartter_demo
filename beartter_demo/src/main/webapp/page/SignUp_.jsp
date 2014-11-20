<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<jsp:include page="/page/util/ImplementsJquery.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Bootstrap -->
<link href="/beartter_demo/staticcontents/css/bootstrap.css" rel="stylesheet">
<link href="/beartter_demo/staticcontents/css/bootstrap-theme.css" rel="stylesheet">
<script src="/beartter_demo/staticcontents/js/bootstrap.min.js" type="text/javascript"></script>
<!-- mine -->
<link href="/beartter_demo/staticcontents/css/animate.css" rel="stylesheet">
<script src="/beartter_demo/staticcontents/js/jquery.leanModal.min.js" type="text/javascript"></script>
<script type="text/javascript" script-name="syncopate" src="http://use.edgefonts.net/syncopate.js"></script>
<link rel="stylesheet" href="/beartter_demo/staticcontents/css/Main_.css" type="text/css" />

</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/beartter_demo/main">
				<img src="/beartter_demo/staticcontents/img/icon.png" height="20" width="20" alt="icon"></a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="#howtouse">How to</a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li>
						<div class="modal-button">
							<a data-toggle="modal" href="#tweetModal" class="btn btn-primary">Tweet</a>
						</div>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
						Config
						<span class="caret"></span>
						</a>
						<ul class="dropdown-menu" role="menu">
							<li class="dropdown-header">（ユーザー名）</li>
							<li class="divider"></li>
							<li><a href="#">Settings</a></li>
							<li><a href="#">Sign Out</a></li>
						</ul>
					</li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>



	<!-- モーダルの設定 -->
	<div class="modal fade" id="tweetModal" tabindex="-1" role="dialog" aria-labelledby="tweetModalLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">閉じる</span>
					</button>
					<h4 class="modal-title" id="tweetModalLabel">New Tweet</h4>
				</div>
				<form action="/beartter_demo/tweet" method="post">
					<div class="modal-body">
							<textarea name="tweet_text" id="tweet_text"></textarea>

					</div>
					<div class="modal-footer">
						<img src="${profileImageUrl}" />
						<button type="button" class="btn btn-default" data-dismiss="modal">back</button>
						<button type="submit" class="btn btn-primary">Tweet</button>
					</div>
				</form>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


	<div id="nav_tl">
		<table>
			<tr>
				<td>
					<div class="arrow_box">
						<p>ほげほげほげほげ</p>
						<div id="talk-span">
						<input type="text">
						</div>
					</div>
				</td>
				<td><img alt="character"
					src="/beartter_demo/staticcontents/img/default_character.png">
				</td>
		</table>
	</div>


	<div id="container">
	<div class="panel panel-default  col-xs-8 col-xs-offset-2">
		<table id="timeline" class="table table-bordered table-striped">

			<!-- RT -->
				<tr>
					<td class="left"><a rel="leanModal" href="#prof" onclick="getValue(89377434);"> <img
							src="http://pbs.twimg.com/profile_images/499845730501013504/UYjXiXHI_normal.jpeg">
					</a></td>
					<td>「オール沖縄」は、沖縄をひとつにするのではなく、多様な沖縄をつなぎあうネットワークだといいなと、そこには希望を持ちたい。
						翁長新知事が掲げる「アイデンティティ」が試される（新城和博）｜ポリタス 「沖縄県知事選2014」から考える http://t.co/kautebA6j2</td>
				</tr>
				<tr>
					<td class="left"><a rel="leanModal" href="#prof" onclick="getValue(89377434);"> 熊本 博之 </a></td>
					<td>tsudaretweeted Thu Nov 20 12:58:41 JST 2014</td>
				</tr>

				<!-- RT -->
				<tr>
					<td class="left"><a rel="leanModal" href="#prof" onclick="getValue(86910048);"> <img
							src="http://pbs.twimg.com/profile_images/514053059257704451/u6hTZmGk_normal.jpeg">
					</a></td>
					<td>総選挙もあるし、沖縄のこと続いていくのでみつめてこうと思います http://t.co/UqGTaAU0Fg</td>
				</tr>
				<tr>
					<td class="left"><a rel="leanModal" href="#prof" onclick="getValue(86910048);"> アラカキヒロコ </a></td>
					<td>tsudaretweeted Thu Nov 20 12:37:54 JST 2014</td>
				</tr>

				<!-- RT -->
				<tr>
					<td class="left"><a rel="leanModal" href="#prof" onclick="getValue(86910048);"> <img
							src="http://pbs.twimg.com/profile_images/514053059257704451/u6hTZmGk_normal.jpeg">
					</a></td>
					<td>実家で「東京では沖縄って観光のイメージでしか見てなくて基地問題とか全然みんな知らないよ」とぼやいたら、父に「北方領土のことをどれだけ知っているのか」と諭されたことがある。周縁の宿命でもあるのかもしれない。それでも、今回ポリタスの沖縄県知事選特集は本当に嬉しかった。</td>
				</tr>
				<tr>
					<td class="left"><a rel="leanModal" href="#prof" onclick="getValue(86910048);"> アラカキヒロコ </a></td>
					<td>tsudaretweeted Thu Nov 20 12:25:26 JST 2014</td>
				</tr>

				<!-- RT -->
				<tr>
					<td class="left"><a rel="leanModal" href="#prof" onclick="getValue(86910048);"> <img
							src="http://pbs.twimg.com/profile_images/514053059257704451/u6hTZmGk_normal.jpeg">
					</a></td>
					<td>沖縄がすきです。だから不当だと思ったら腹がたつし無視されると悲しい。沖縄問題が解決するとは思ってないけどなるべくましな未来があってほしい。でもそれは日本のなるべくましな未来ともつながってると思うので。</td>
				</tr>
				<tr>
					<td class="left"><a rel="leanModal" href="#prof" onclick="getValue(86910048);"> アラカキヒロコ </a></td>
					<td>tsudaretweeted Thu Nov 20 12:22:32 JST 2014</td>
				</tr>

				<!-- RT -->
				<tr>
					<td class="left"><a rel="leanModal" href="#prof" onclick="getValue(86910048);"> <img
							src="http://pbs.twimg.com/profile_images/514053059257704451/u6hTZmGk_normal.jpeg">
					</a></td>
					<td>沖縄問題を沖縄以外で語っても多くの人は興味を示さないっていう侘しさと無力感を知ってるし、人生をかけて沖縄を見つめている研究者の方々と出会ってからは、知見とデリカシーが求められる問題に素人の自分が杜撰なこと言いたくなくていつもなかなか公に言葉を発せない。</td>
				</tr>
				<tr>
					<td class="left"><a rel="leanModal" href="#prof" onclick="getValue(86910048);"> アラカキヒロコ </a></td>
					<td>tsudaretweeted Thu Nov 20 12:17:04 JST 2014</td>
				</tr>

				<!-- RT -->
				<tr>
					<td class="left"><a rel="leanModal" href="#prof" onclick="getValue(86910048);"> <img
							src="http://pbs.twimg.com/profile_images/514053059257704451/u6hTZmGk_normal.jpeg">
					</a></td>
					<td>沖縄県知事選挙のあとも問題は続いてくし進展してる。今回ポリタスを引用してツイートしまくってたのは、沖縄を知って欲しいと単純に思ったから。それと、沖縄で育った日々で自分の中に培われた負の想いは建設的な形で出したかったから。</td>
				</tr>
				<tr>
					<td class="left"><a rel="leanModal" href="#prof" onclick="getValue(86910048);"> アラカキヒロコ </a></td>
					<td>tsudaretweeted Thu Nov 20 12:16:41 JST 2014</td>
				</tr>

				<!-- 通常ツイート -->
				<tr>
					<td id="icon_tl"><a rel="leanModal" href="#prof" onclick="getValue(15913455);"><img
							src="http://pbs.twimg.com/profile_images/3195991494/6602a4b935c36ea8bd0b95e0fe73a784_normal.jpeg"></a></td>
					<td>読モの私服が買えるフリマアプリ「Bijoux de Marche」配信 http://t.co/uzTN5qGvcj http://t.co/UmuVAHCXdK</td>
				</tr>
				<tr>
					<td class="left"><a rel="leanModal" href="#prof">Fashionsnap.com</a></td>
					<td>Thu Nov 20 15:01:17 JST 2014</td>
				</tr>

				<!-- 通常ツイート -->
				<tr>
					<td id="icon_tl"><a rel="leanModal" href="#prof" onclick="getValue(169480493);"><img
							src="http://pbs.twimg.com/profile_images/1830329681/icon_twitter_128-128_normal.png"></a></td>
					<td>円、118円台後半に下げ幅拡大 ７年３カ月ぶり円安 http://t.co/etn7ZcHfXN</td>
				</tr>
				<tr>
					<td class="left"><a rel="leanModal" href="#prof">日本経済新聞 電子版</a></td>
					<td>Thu Nov 20 15:01:03 JST 2014</td>
				</tr>

				<!-- RT -->
				<tr>
					<td class="left"><a rel="leanModal" href="#prof" onclick="getValue(150144770);"> <img
							src="http://pbs.twimg.com/profile_images/450827794159648769/yGVJoUgW_normal.jpeg">
					</a></td>
					<td>決して反対する事がおかしい、とか間違いだなんて思ってるのではなく、僕はSNSがどんな考えの人も自由闊達に意見を呟ける場であって欲しいんだ。僕らはムードにコントロールされやすい。そことは切り離した所での一人一人の判断や選択が大切。ポリタスの重要性もきっとその辺にあるんだろうな。</td>
				</tr>
				<tr>
					<td class="left"><a rel="leanModal" href="#prof" onclick="getValue(150144770);"> 松岡英明☪12/21LIVE
							GATE </a></td>
					<td>tsudaretweeted Thu Nov 20 11:48:30 JST 2014</td>
				</tr>

				<!-- RT -->
				<tr>
					<td class="left"><a rel="leanModal" href="#prof" onclick="getValue(135190213);"> <img
							src="http://pbs.twimg.com/profile_images/838210303/IMGP2637_normal.JPG">
					</a></td>
					<td>基地保有市の支持が圧倒的。やっぱり可視化されているものはすごくわかりやすい。 / 【沖縄県知事選】地域別得票数データから見る第12回沖縄県知事選挙（ポリタス編集部）
						｜ポリタス 「沖縄県知事選2014」... http://t.co/A4d3HKfk7Z #NewsPicks</td>
				</tr>
				<tr>
					<td class="left"><a rel="leanModal" href="#prof" onclick="getValue(135190213);"> Fujioka Satoko </a></td>
					<td>tsudaretweeted Thu Nov 20 13:31:43 JST 2014</td>
				</tr>

				<!-- 通常ツイート -->
				<tr>
					<td id="icon_tl"><a rel="leanModal" href="#prof" onclick="getValue(50270149);"><img
							src="http://pbs.twimg.com/profile_images/471518943023083520/ahBUtbxf_normal.jpeg"></a></td>
					<td>Mr.brainwash + Hijack。グラフィティアートをリードする親子2人の日本初個展。 http://t.co/oUH63fHlEe
						http://t.co/HqQ206sOBi</td>
				</tr>
				<tr>
					<td class="left"><a rel="leanModal" href="#prof">VOGUE JAPAN</a></td>
					<td>Thu Nov 20 15:00:19 JST 2014</td>
				</tr>

				<!-- RT -->
				<tr>
					<td class="left"><a rel="leanModal" href="#prof" onclick="getValue(2511640327);"> <img
							src="http://pbs.twimg.com/profile_images/486041044111212545/fo3IFfUp_normal.jpeg">
					</a></td>
					<td>@takapon_jp 意外と深く考えないで、行動力だけでなんとかなる人って自分の周りも多いんだよな。きっとやってみることが大事なんだな。
						BARホリエモンチャンネル×しごとバー！〜ECサイトプロ編vol.3〜http://t.co/juvzoDmPef #ホリエモンチャンネル</td>
				</tr>
				<tr>
					<td class="left"><a rel="leanModal" href="#prof" onclick="getValue(2511640327);"> 愛らんど太郎 </a></td>
					<td>takapon_jpretweeted Thu Nov 20 14:58:10 JST 2014</td>
				</tr>

				<!-- 通常ツイート -->
				<tr>
					<td id="icon_tl"><a rel="leanModal" href="#prof" onclick="getValue(168816611);"><img
							src="http://pbs.twimg.com/profile_images/491483872677228544/fuq52Rp2_normal.jpeg"></a></td>
					<td>村上隆、14年ぶりの個展「村上隆の五百羅漢図展」森美術館で開催 - 全長100メートルの大作展示 http://t.co/VgPr3aFZVP
						http://t.co/cxoVtYtbwt</td>
				</tr>
				<tr>
					<td class="left"><a rel="leanModal" href="#prof">Fashion Press</a></td>
					<td>Thu Nov 20 14:53:56 JST 2014</td>
				</tr>

				<!-- 通常ツイート -->
				<tr>
					<td id="icon_tl"><a rel="leanModal" href="#prof" onclick="getValue(265205959);"><img
							src="http://pbs.twimg.com/profile_images/2082745275/saigai_128_normal.jpg"></a></td>
					<td>【エボラ出血熱】「エボラ出血熱はどのような病気？どのように感染するの？」といった疑問に専門家がお答えします。官邸「エボラ出血熱への対応」ＨＰに、政府インターネットＴＶ「“知っておきたい”
						エボラ出血熱の基本情報」へのリンクを追加。⇒http://t.co/eCNWWsbgz8</td>
				</tr>
				<tr>
					<td class="left"><a rel="leanModal" href="#prof">首相官邸（災害情報）</a></td>
					<td>Thu Nov 20 14:46:36 JST 2014</td>
				</tr>

				<!-- 通常ツイート -->
				<tr>
					<td id="icon_tl"><a rel="leanModal" href="#prof" onclick="getValue(50270149);"><img
							src="http://pbs.twimg.com/profile_images/471518943023083520/ahBUtbxf_normal.jpeg"></a></td>
					<td>コクのあるリッチなボディミルクと、濃密な泡がなめらかな肌に洗い上げる華やかな香り付きのボディソープをセットにしたキットが、ポール &amp; ジョーから登場。
						http://t.co/Chlpw2cyou http://t.co/mFixJRL8du</td>
				</tr>
				<tr>
					<td class="left"><a rel="leanModal" href="#prof">VOGUE JAPAN</a></td>
					<td>Thu Nov 20 14:45:21 JST 2014</td>
				</tr>

				<!-- 通常ツイート -->
				<tr>
					<td id="icon_tl"><a rel="leanModal" href="#prof" onclick="getValue(168816611);"><img
							src="http://pbs.twimg.com/profile_images/491483872677228544/fuq52Rp2_normal.jpeg"></a></td>
					<td>ジャーナルスタンダード×雑誌『&amp; Premium』全店舗で“スタンダード”なアイテム提案 - http://t.co/oRd8pCOPkv
						http://t.co/VFAPIRHdCR</td>
				</tr>
				<tr>
					<td class="left"><a rel="leanModal" href="#prof">Fashion Press</a></td>
					<td>Thu Nov 20 14:40:02 JST 2014</td>
				</tr>

				<!-- RT -->
				<tr>
					<td class="left"><a rel="leanModal" href="#prof" onclick="getValue(554157023);"> <img
							src="http://pbs.twimg.com/profile_images/2410751083/yqkgn33gcasuswfekbd9_normal.jpeg">
					</a></td>
					<td>【新着イベント】11/24 Mon 藤井直敬 × 高橋建滋「仮想現実の世界へようこそ〜ムック『xReality』が魅せる未来〜」『xReality
						』刊行記念開催決定！http://t.co/67Iuhv3rOO @takapon_jp</td>
				</tr>
				<tr>
					<td class="left"><a rel="leanModal" href="#prof" onclick="getValue(554157023);"> B&amp;B </a></td>
					<td>takapon_jpretweeted Thu Nov 20 14:17:18 JST 2014</td>
				</tr>

				<!-- 通常ツイート -->
				<tr>
					<td id="icon_tl"><a rel="leanModal" href="#prof" onclick="getValue(44791521);"><img
							src="http://pbs.twimg.com/profile_images/378800000764568022/d33865b33603209d9d73dd7db1543655_normal.jpeg"></a></td>
					<td>これいいね<br>装着するだけでスマホが”ポラロイドカメラ”になるケース – LINE NEWS - http://t.co/f3OTJftYvd
					</td>
				</tr>
				<tr>
					<td class="left"><a rel="leanModal" href="#prof">堀江貴文(Takafumi Horie)</a></td>
					<td>Thu Nov 20 14:34:26 JST 2014</td>
				</tr>

				<!-- 通常ツイート -->
				<tr>
					<td id="icon_tl"><a rel="leanModal" href="#prof" onclick="getValue(44791521);"><img
							src="http://pbs.twimg.com/profile_images/378800000764568022/d33865b33603209d9d73dd7db1543655_normal.jpeg"></a></td>
					<td>これはマジすごい！<br>“Oculus Rift”で操作する次世代ロボットショベルカー – LINE NEWS - http://t.co/KyUxsrzl0W
					</td>
				</tr>
				<tr>
					<td class="left"><a rel="leanModal" href="#prof">堀江貴文(Takafumi Horie)</a></td>
					<td>Thu Nov 20 14:34:23 JST 2014</td>
				</tr>

				<!-- 通常ツイート -->
				<tr>
					<td id="icon_tl"><a rel="leanModal" href="#prof" onclick="getValue(44791521);"><img
							src="http://pbs.twimg.com/profile_images/378800000764568022/d33865b33603209d9d73dd7db1543655_normal.jpeg"></a></td>
					<td>行ってみたい<br>世界一の高さからねじり落とされる恐怖のジェットコースター – LINE NEWS - http://t.co/lHgBELWwUB
					</td>
				</tr>
				<tr>
					<td class="left"><a rel="leanModal" href="#prof">堀江貴文(Takafumi Horie)</a></td>
					<td>Thu Nov 20 14:34:19 JST 2014</td>
				</tr>

		</table>
</div>

	</div>

</body>
</html>