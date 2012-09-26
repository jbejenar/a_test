

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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

        System.setProperty("http.proxyHost", "proxy");
        System.setProperty("http.proxyPort", "80");

        try {

            URL url = new URL("http://feeds.theage.com.au/rssheadlines/top.xml");
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
                    System.out.println("Link        : " + getTagValue("guid", eElement));
                    System.out.println("\n");

                    URL urlNews = new URL(getTagValue("guid", eElement));
                    URLConnection urlConnNews = urlNews.openConnection();

                    BufferedReader in = new BufferedReader(new InputStreamReader(
                            urlConnNews.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null)
                        System.out.println(inputLine);
                    in.close();

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


        } catch (Exception e) {
            System.out.println("Dang we got an exception: " + e.toString());
        }


    }

    private static String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

        Node nValue = (Node) nlList.item(0);

        return nValue.getNodeValue();
    }

}


