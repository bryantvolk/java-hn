public class Submission {

    private final String title;
    private final String url;

    public Submission(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return String.format("Title: %s\n url: %s\n", title, url);
    }
}
