Twittfuse 
===========
Twittfuse is simple twitter client. Allowing users to do basic twitter functionality.
This application is developed with intent to learn android programming by making it as close to real twitter app as possbile in time frame.

Time spent: 30 hours spent in total.

Completed user stories:

 * [x] Required: User can sign in to Twitter using OAuth login.
 * [x] Required: User can view the tweets from their home timeline.
 * [x] Required: User should be able to see the username, name, body and timestamp for each tweet
 * [x] Required: User should be displayed the relative timestamp for a tweet "8m", "7h"
 * [x] Required:User can view more tweets as they scroll with infinite pagination
 * [x] Optional: Links in tweets are clickable and will launch the web browser (see autolink)

* [x] Required: User can compose a new tweet
* [x] Required: User can click a “Compose” icon in the Action Bar on the top right
* [x] Required: User can then enter a new tweet and post this to twitter
* [x] Required: User is taken back to home timeline with new tweet visible in timeline
* [x] Optional: User can see a counter with total number of characters left for tweet        

* [x] Advanced: User can refresh tweets timeline by pulling down to refresh (i.e pull-to-refresh)
* [x] Advanced: User can open the twitter app offline and see last loaded tweets
* [x] Advanced: Tweets are persisted into sqlite and can be displayed from the local DB
* [x] Advanced: User can tap a tweet to display a "detailed" view of that tweet
* [x] Advanced: User can select "reply" from detail view to respond to a tweet
* [x] Advanced: Improve the user interface and theme the app to feel "twitter branded"

* [x] Bonus: User can see embedded image media within the tweet detail view
* [x] Bonus: Bonus: Compose activity is replaced with a modal overlay


Other Additional goodies :

*  Allow user to post Tweet when network is not available (offline) tweet.
*  App posts offline Tweet  when network is available and shows notification to User.
*  Reply to multiple handles: App parse tweet content and find all handles from tweet and making sure no duplicate handles in reply.
*  Embeded view all any kind of url: App shows first url in full webView allowing it to show any url not just images.
*  App does not make extra network call on device rotations (orientation change) instead it loads tweets from local db.
*  Text tracking counter in minus to show user no of over typed characters.
*  Shows Retweet information on top header for every tweet ( if tweet is retweeted) just like official twitter app.
*  User can retweet a particular tweet from Time line activity (list of tweets)  & from Tweet details activity.
*  User can mark favorite a particular tweet from Time line activity (list of tweets) & from Tweet details activity.
*  Shows and update retweet & favorite view counters.
*  Shows custom view in Toast when network is unavailable.
*  Share Tweet in email or social meida with friends.


Third Party Utilities/source used for building this app:

 1. CodePath Rest Template (https://github.com/thecodepath/android-rest-client-template)
 2. AsyncHttpClient
 3. UniversalImageLoader
 4. Twitter-Text 
 5. Twitter blue color : http://www.colourlovers.com/color/4099FF/Twitter_Blue
 6. How to drow spearators : http://stackoverflow.com/questions/5049852/android-drawing-separator-divider-line-in-layout

Know issue with Fling/Fast Scroll with PullToRefreshListView:
https://github.com/erikwt/PullToRefresh-ListView/issues/42 

#### Video walkthrough of all user stories:
* In Video I explicitly kill and restart App to show that when data is available locally app does not make network call.
* Please see [video](https://vimeo.com/99199309)
