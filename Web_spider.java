import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;

public class Web_spider {
    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://www.ptt.cc/bbs/").get();
            Elements hotClasses = doc.select("#main-container .b-net a");
            System.out.println(doc.title());
            Elements newsHeadlines = doc.select("div.b-ent a");
            for (Element hotClass : hotClasses) {
                String pttTitle = hotClasses.select(".board-name").text();
                String articleSortDoc = hotClass.absUrl("href").toString();
                Document hotBoardDoc = Jsoup.connect(articleSortDoc).get();
                Elements articleSortClasses = hotBoardDoc.select("#main-container .r-ent.title a");
                for (Element articleSortClass:articleSortClasses){
                    String articleTitle = articleSortClass.text();
                    String articleDoc = articleSortClass.absUrl("href").toString();
                    Document articleClasses = Jsoup.connect(articleSortDoc).get();
                    if (!articleClasses.select(".push").isEmpty()){
                        Element firstPush = articleClasses.select("#main-container #main-content .push").get(0);
                        String commitName = firstPush.select("span").get(1).text();
                        String firstCommit = firstPush.select("span").get(2).text();
                        System.out.println("Board: "+ pttTitle);
                        System.out.println("Article Title:"+ articleTitle);
                        System.out.println("Commit"+ commitName + firstCommit);
                    }
                    else {
                        System.out.println("Board: "+ pttTitle);
                        System.out.println("Article Title:"+ articleTitle);
                        System.out.println("Commit"+ null);
                    }
                }

            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
