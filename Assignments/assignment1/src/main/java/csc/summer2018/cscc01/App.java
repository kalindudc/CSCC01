package csc.summer2018.cscc01;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.lang.model.util.Elements;
import java.io.IOException;

/**
 * Simple app to read through a Websites links
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // handle exeption caused by JSOUP connection
        try {
            // establish connection 
            Document doc = Jsoup.connect(args[0]).get();
            // print out the title of the webpage
            System.out.println("title : " + doc.title() + "\n");
            // acquire all the links in the page and loop through them
            org.jsoup.select.Elements elements = doc.select("a[href]");
            for (Element ele: elements) {
                // print out the link and the text
                System.out.println("link : " + ele.attr("href"));
                System.out.println("text : " + ele.text() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error occured when trying to connect to : " + args[0]);
        }

    }
}
