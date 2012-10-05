import twitter4j.*;

import java.util.*;
/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 26/09/12
 * Time: 3:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class getTwitterArticles {

    public static List getNews(String company) {

        try{

                Twitter twitter = new TwitterFactory().getInstance();
                Query query = new Query(company);
                QueryResult result = twitter.search(query);

                List<Tweet> tweets = result.getTweets();


                //for (Tweet tweet : tweets){
                //    System.out.println("inside getTwitterArcitles: " + tweet.getText());
                //}

                return tweets;

        } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed to search tweets: " + e.getMessage());
                System.exit(-1);
        }

        return  null;
    }
}
