package internals;


import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



/**
 * Created by ruslan on 17.10.2017.
 */

    public class FindPage {

        private static Logger logger = Logger.getLogger(FindPage.class);

        public static Element getContentOfPage(String pageURL) throws Exception {
            logger.info("Connection to url " + pageURL);

            org.jsoup.nodes.Document document = Jsoup.connect(pageURL).get();
            Elements elements = document.getElementsByTag("table");
            return elements.get(1);


        }

    }

