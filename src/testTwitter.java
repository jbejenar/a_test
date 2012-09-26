import twitter4j.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 26/09/12
 * Time: 3:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class testTwitter {

    public void testTwitter(String queryStock) {


        Twitter twitter = new TwitterFactory().getInstance();


        try{
            Query query = new Query(queryStock);
            QueryResult result;


            do {
                result = twitter.search(query);
                List<Tweet> tweets = result.getTweets();
                for (Tweet tweet : tweets) {
                    System.out.println("@" + tweet.getFromUser() + " - " + tweet.getText());
                }
            } while ((query = result.nextQuery()) != null);


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to search tweets: " + e.getMessage());
            System.exit(-1);
        }

    }
}
