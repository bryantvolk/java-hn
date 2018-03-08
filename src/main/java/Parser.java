import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Parser {
    private final String index = "https://news.ycombinator.com/";
    private Map<String, List<Submission>> store;
    private List<Submission> home;
    private List<Submission> jobs;

    public Parser() {
        store = new HashMap<String, List<Submission>>();
        home = new LinkedList<Submission>();
        jobs = new LinkedList<Submission>();
    }

    private Document getDoc(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    /**
     * add jobs from front page to jobs map
     * @return HashMap of title: url
     * @throws IOException e
     */
    private List<Submission> addPage(String query) throws IOException {
        Document doc = getDoc(index + query);
        Elements links = doc.getElementsByClass("storylink");
        List<Submission> submissions = new LinkedList<Submission>();
        for (Element e: links) {
           submissions.add(new Submission(e.text(), e.attr("abs:href")));
        }
        return submissions;
    }

    /**
     * add lists to global store
     * @throws IOException e
     */
    public void aggregate() throws IOException {
        store.put("home", addPage(""));
        store.put("jobs", addPage("jobs"));
    }

    public static void main(String[] args) throws IOException{
        Parser p = new Parser();
        p.aggregate();
        System.out.println(p.store);
    }
}
