package backstage;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Journal {
    private String id;
    private String title;
    private String issn_ppub;
    private String issn_epub;
    private String publisherName;

    public Journal() {
    }

    private Journal(String id, String title, String issn_ppub, String issn_epub, String publisherName) {
        this.id = id;
        this.title = title;
        this.issn_ppub = issn_ppub;
        this.issn_epub = issn_epub;
        this.publisherName = publisherName;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIssn_ppub() {
        return issn_ppub;
    }

    public String getIssn_epub() {
        return issn_epub;
    }

    public String getPublisherName() {
        return publisherName;
    }

    private void setId(String id) {
        this.id = id;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setIssn_ppub(String issn_ppub) {
        this.issn_ppub = issn_ppub;
    }

    private void setIssn_epub(String issn_epub) {
        this.issn_epub = issn_epub;
    }

    private void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public void setJournalMetaData(NodeList nList) {
        for ( int temp = 0; temp < nList.getLength(); temp++ ) {
            Node nNode = nList.item(temp);

            System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                setId(eElement
                        .getElementsByTagName("journal-id")
                        .item(0)
                        .getTextContent());
                setTitle(eElement
                        .getElementsByTagName("journal-title")
                        .item(0)
                        .getTextContent());
                setIssn_ppub(eElement
                        .getElementsByTagName("issn")
                        .item(0)
                        .getTextContent());
                setIssn_epub(eElement
                        .getElementsByTagName("issn")
                        .item(1)
                        .getTextContent());
                setPublisherName(eElement
                        .getElementsByTagName("publisher-name")
                        .item(0)
                        .getTextContent());
            }
        }
        addToJournals();
    }

    @Override
    public String toString() {
        return "Journal{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", issn_ppub='" + issn_ppub + '\'' +
                ", issn_epub='" + issn_epub + '\'' +
                ", publisherName='" + publisherName + '\'' +
                '}';
    }

    public String getDataCS() {
        return '\'' + id + '\'' +
                ',' + '\'' + title + '\'' +
                ',' + '\'' + issn_ppub + '\'' +
                ',' + '\'' + issn_epub + '\'' +
                ',' + '\'' + publisherName + '\'';
    }

    private void addToJournals() {
        Utils.journals.add(new Journal(id, title, issn_ppub, issn_epub, publisherName));
    }

}
