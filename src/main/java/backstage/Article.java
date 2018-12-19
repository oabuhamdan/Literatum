package backstage;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Arrays;
import java.util.List;

public class Article {
    private String articleID;
    private String title;
    private List<String> authors;
    private String selfUri;
    private String abst;

    public Article() {
    }

    private Article(String articleID, String title, List<String> authors, String selfUri, String abst) {
        this.articleID = articleID;
        this.title = title;
        this.authors = authors;
        this.selfUri = selfUri;
        this.abst = abst;
    }

    public String getArticleID() {
        return articleID;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getSelfUri() {
        return selfUri;
    }

    public String getAbst() {
        return abst;
    }

    public void setSelfUri(String selfUri) {
        this.selfUri = selfUri;
    }

    public void setAbst(String abst) {
        this.abst = abst;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(List<String> authors) {

        this.authors = authors;
    }

    public void setJournalMetaData(NodeList nList) {
        for ( int temp = 0; temp < nList.getLength(); temp++ ) {
            Node nNode = nList.item(temp);

            System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                setArticleID((eElement
                        .getElementsByTagName("article-id")
                        .item(0)
                        .getTextContent()));
                setTitle((eElement
                        .getElementsByTagName("article-title")
                        .item(0)
                        .getTextContent()));
                setAuthors((Arrays.asList(eElement
                        .getElementsByTagName("authors")
                        .item(0)
                        .getTextContent().split("\n"))));
                setSelfUri(eElement
                        .getElementsByTagName("self-uri")
                        .item(0).getAttributes().item(1).getTextContent());
                setAbst((eElement
                        .getElementsByTagName("abstract")
                        .item(0)
                        .getTextContent()));

            }
        }
        addToArticles();
    }

    public String getDataCS() {
        if (title.length() > 45)
            title = title.substring(0, 45);

        return '\'' + articleID + '\'' +
                ',' + '\'' + title + '\'' +
                ',' + '\'' + Arrays.toString(authors.toArray()) + '\'' +
                ',' + '\'' + selfUri + '\'';
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleID='" + articleID + '\'' +
                ", title='" + +'\'' +
                ", authors=" + Arrays.toString(authors.toArray()) +
                ", selfUri='" + selfUri + '\'' +
                ", abst='" + abst + '\'' +
                '}';
    }

    //    public Article(String articleID, String title, List<String> authors, String selfUri, String abst) {
    private void addToArticles() {
        Utils.articles.add(new Article(articleID, title, authors, selfUri, abst));
    }
}
