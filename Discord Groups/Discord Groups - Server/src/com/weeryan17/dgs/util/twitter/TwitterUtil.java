package com.weeryan17.dgs.util.twitter;

import java.util.logging.Level;

import com.weeryan17.dgs.DiscordGroups;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterUtil {
	protected static Twitter twitter;
	
	DiscordGroups instance;
	
	public TwitterUtil(DiscordGroups instance){
		this.instance = instance;
		if(twitter == null){
			this.initTwitter();
		}
	}
	
	protected void initTwitter(){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey(instance.getProperties().getProperty("twitterId"))
		.setOAuthConsumerSecret(instance.getProperties().getProperty("twitterSecret"))
		.setOAuthAccessToken(instance.getProperties().getProperty("twitterTolken"))
		.setOAuthAccessTokenSecret(instance.getProperties().getProperty("twitterTolkenSecret"));
		
		TwitterFactory tf = new TwitterFactory(cb.build());
		
		twitter = tf.getInstance();
	}
	
	public void tweet(String message){
		try {
			twitter.updateStatus(message);
		} catch (TwitterException e) {
			instance.getLogger().log("Error twiting", Level.WARNING, e, false);
		}
	}
}
