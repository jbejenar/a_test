import twitter4j.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 26/09/12
 * Time: 3:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class testTwitter {

    public static void main(String args[]) {

        System.setProperty("http.proxyHost", "proxy");
        System.setProperty("http.proxyPort", "80");

        try{

            Twitter twitter = new TwitterFactory().getInstance();
            Query query = new Query("apple");
            QueryResult result = twitter.search(query);

            List<Tweet> tweets = result.getTweets();

            for (Tweet tweet : tweets){

                System.out.println("tweet: " + tweet.getText());

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to search tweets: " + e.getMessage());
            System.exit(-1);
        }

    }
}
