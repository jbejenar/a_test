

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.net.URLConnection;


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

        System.setProperty("http.proxyHost", "proxyhost2");
        System.setProperty("http.proxyPort", "80");

        try {

            URL url = new URL("http://feeds.theage.com.au/rssheadlines/top.xml");
            //InputStream in = url.openStream();
            URLConnection urlConn = url.openConnection();
            urlConn.connect();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = null;
            doc = db.parse(urlConn.getInputStream());
            System.out.println("document: " + doc.toString());


        } catch (Exception e) {
            System.out.println("Dang we got an exception: " + e.toString());
        }


    }
}
