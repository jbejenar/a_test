import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 11/10/12
 * Time: 11:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class getGoogleNews {

    public static void getNews(String company) {

        try {

            //URL with dates http://www.google.com/finance/company_news?q=NASDAQ%3AAAPL&startdate=2012-06-01&enddate=2012-11-01&output=rss
            //URL url = new URL("http://feeds.theage.com.au/rssheadlines/top.xml");
            URL url = new URL("http://www.google.com/finance/company_news?q=NASDAQ:AAPL&ei=tIpiUPiHGsabkAX3Cg&output=rss");

            //InputStream in = url.openStream();
            URLConnection urlConn = url.openConnection();
            urlConn.connect();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(urlConn.getInputStream());
            doc.getDocumentElement().normalize();



            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("item");
            System.out.println("-----------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println("Title       : " + getTagValue("title", eElement));
                    System.out.println("Description : " + getTagValue("description", eElement));
                    System.out.println("Link        : " + getTagValue("link", eElement));
                    System.out.println("\n");

                    /*URL urlNews = new URL(getTagValue("link", eElement));
                    URLConnection urlConnNews = urlNews.openConnection();

                    BufferedReader in = new BufferedReader(new InputStreamReader(
                            urlConnNews.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null)
                        System.out.println(inputLine);
                    in.close();
                    */
                    //trying to parse the HTML in a useful form?
                    // difficult to do by the looks of things.
                    /*DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    dbf.setValidating(false);
                    dbf.setNamespaceAware(true);
                    dbf.setIgnoringComments(false);
                    dbf.setIgnoringElementContentWhitespace(false);
                    dbf.setExpandEntityReferences(false);
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    return db.parse(new InputSource(new StringReader(source)));
                    */
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to search tweets: " + e.getMessage());
            System.exit(-1);
        }

        //return  null;
    }


    private static String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

        Node nValue = (Node) nlList.item(0);

        return nValue.getNodeValue();
    }
}


