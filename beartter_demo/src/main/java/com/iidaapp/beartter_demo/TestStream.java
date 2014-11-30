package com.iidaapp.beartter_demo;

import java.sql.SQLException;
import java.util.List;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.UserStreamAdapter;
import twitter4j.auth.AccessToken;

import com.iidaapp.beartter_demo.db.DbUtils;
import com.iidaapp.beartter_demo.entity.AccessTokenEntity;

public class TestStream {

public static void testStream(String beartterId) throws SQLException {

		List<AccessTokenEntity> entityList = DbUtils.selectAccessTokenListFromAccessToken(beartterId);

		if(entityList.size() == 0)
			return;

		AccessTokenEntity entity = entityList.get(0);
		AccessToken accessToken = new AccessToken(entity.getoAuthToken(), entity.getoAuthSecret());
		TwitterStream twitterStream = new TwitterStreamFactory().getInstance(accessToken);

		StatusListener listner = new MyStreamAdapter();
		
		twitterStream.addListener(listner);
		twitterStream.user();

	}
}

// イベントを受け取るリスナーオブジェクト
class MyStreamAdapter extends UserStreamAdapter{

		public void onException(Exception e) {
			e.printStackTrace();

		}

		public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
			System.out.println("Got track limitation notice:"
					+ numberOfLimitedStatuses);
		}

		public void onStatus(Status status) {

			System.out.println("@" + status.getUser().getScreenName()
					+ " - " + status.getText());
		}

		public void onStallWarning(StallWarning warning) {
			System.out.println("Got stall warning:" + warning);

		}

		public void onScrubGeo(long userId, long upToStatusId) {
			System.out.println("Got scrub_geo event userId:" + userId
					+ " upToStatusId:" + upToStatusId);
		}

		public void onDeletionNotice(
				StatusDeletionNotice statusDeletionNotice) {
			System.out.println("Got a status deletion notice id:"
					+ statusDeletionNotice.getStatusId());
		}
}
