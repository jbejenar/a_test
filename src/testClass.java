

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import twitter4j.Tweet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import twitter4j.*;


/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 25/09/12
 * Time: 2:45 PM
 * To change this template use File | Settings | File Templates.
 */
class testClass {
    public static void main(String args[]) {

        System.out.println("hello wold");

        System.setProperty("http.proxyHost", "proxy");
        System.setProperty("http.proxyPort", "80");

        try {

            String company = "aapl";
            List<Tweet> tweetResults = getTwitterArticles.getNews(company);
            System.out.println("Twitter results for: " + company + "\n");
            for (Tweet tweet : tweetResults){
                System.out.println("TwitterResults:     ") ;
                System.out.println("    Date Created:   " + tweet.getCreatedAt());
                System.out.println("    GeoLocation:    " + tweet.getGeoLocation());
                System.out.println("    UserName:       " + tweet.getFromUserName());
                System.out.println("    Text:           " + tweet.getText());
                System.out.println("    Language:       " + tweet.getIsoLanguageCode());
                System.out.println("\n");

            }



        } catch (Exception e) {
            System.out.println("Dang we got an exception: " + e.toString());
        }


    }
}


