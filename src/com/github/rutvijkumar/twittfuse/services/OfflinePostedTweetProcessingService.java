/***

The MIT License (MIT)
Copyright � 2014 Rutvijkumar Shah
 
Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the �Software�), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
 
The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.
 
THE SOFTWARE IS PROVIDED �AS IS�, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

 ***/

package com.github.rutvijkumar.twittfuse.services;

import java.util.List;
import java.util.Random;

import org.json.JSONObject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.github.rutvijkumar.twittfuse.R;
import com.github.rutvijkumar.twittfuse.TwitterApplication;
import com.github.rutvijkumar.twittfuse.Util;
import com.github.rutvijkumar.twittfuse.activities.TimeLineActivity;
import com.github.rutvijkumar.twittfuse.api.TwitterClient;
import com.github.rutvijkumar.twittfuse.models.Tweet;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

public class OfflinePostedTweetProcessingService extends Service {

	TwitterClient client;

	public int onStartCommand(Intent intent, int flags, int startId) {
		processOfflineTweets();
		return Service.START_NOT_STICKY;
	}



	protected void processOfflineTweets() {
		
		client = TwitterApplication.getRestClient();

		/***
		 * It is important to check network connectivity before hand
		 * 
		 */
		if (!Util.isNetworkAvailable(this)) {
			return;
		}

		List<Tweet> offlineTweets = Tweet.getAllOfflineTweets(1);
		if(offlineTweets.size() >0) {
		
			final Tweet tw = offlineTweets.get(0);
			AsyncHttpResponseHandler handler = new SyncJsonHttpResponseHandler(tw);
			client.postTweet(tw.getBody(), tw.getOfflineReplyToTweetId(),handler);
		}
	}

	class SyncJsonHttpResponseHandler extends JsonHttpResponseHandler {

		private Tweet offlineTweet;

	
		public SyncJsonHttpResponseHandler(Tweet offlineTweet) {
			this.offlineTweet = offlineTweet;
		}
		
		@Override
		public void onSuccess(JSONObject jsonObj) {
			Tweet newTweet = Tweet.fromJSON(jsonObj);
			onSuccessfulPost(newTweet, offlineTweet);
		}
		
		@Override
		public void onFailure(Throwable e, JSONObject error) {
			Log.d("DEBUG", "POST Tweet Error :" + error.toString());
			Log.e("ERROR", "Exception while posting tweet", e);	
		}
		
	
	};

	private void onSuccessfulPost(Tweet newTweet, Tweet offLineTweet) {
		// remove offline tweet from db
		try {
			newTweet.save();
			sendNotification(newTweet);
			// send notification for successful tweet
			offLineTweet.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void sendNotification(Tweet newTweet) {
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		Intent intent = new Intent(this, TimeLineActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

		int random_int = new Random().nextInt();
		// build notification
		// the addAction re-use the same intent to keep the example short
		Notification n = new Notification.Builder(this)
				.setContentTitle("Tweet posted successfully")
				.setContentText(newTweet.getBody())
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentIntent(pIntent)
				.setAutoCancel(true)
				.setStyle(
						new Notification.BigTextStyle().bigText(newTweet
								.getBody())).build();

		notificationManager.notify(random_int, n);
	}
	

	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
