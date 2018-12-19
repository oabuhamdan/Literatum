package backstage;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Issue {
    private String issueType;
    private String pubMonth;
    private String pubYear;
    private String volume;
    private String issue_number;
    private String id;

    public Issue() {
    }

    private Issue(String issueType, String pubMonth, String pubYear, String volume, String issue_number, String id) {
        this.issueType = issueType;
        this.pubMonth = pubMonth;
        this.pubYear = pubYear;
        this.volume = volume;
        this.issue_number = issue_number;
        this.id = id;
    }

    public String getIssueType() {
        return issueType;
    }

    public String getPubMonth() {
        return pubMonth;
    }

    public String getPubYear() {
        return pubYear;
    }

    public String getVolume() {
        return volume;
    }

    public String getIssue_number() {
        return issue_number;
    }

    public String getId() {
        return id;
    }

    private void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    private void setPubMonth(String pubMonth) {
        this.pubMonth = pubMonth;
    }

    private void setPubYear(String pubYear) {
        this.pubYear = pubYear;
    }

    private void setVolume(String volume) {
        this.volume = volume;
    }

    private void setIssue_number(String issue_number) {
        this.issue_number = issue_number;
    }

    private void setId(String id) {
        this.id = id.substring(id.indexOf('/')+1, id.indexOf('_'));
    }

    public void setIssueMetaData(NodeList nList) {
        for ( int temp = 0; temp < nList.getLength(); temp++ ) {
            Node nNode = nList.item(temp);

            System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                setIssueType(eElement
                        .getElementsByTagName("issue-type")
                        .item(0)
                        .getTextContent());
                setPubMonth(eElement
                        .getElementsByTagName("month")
                        .item(0)
                        .getTextContent());
                setPubYear(eElement
                        .getElementsByTagName("year")
                        .item(0)
                        .getTextContent());
                setVolume(eElement
                        .getElementsByTagName("volume")
                        .item(0)
                        .getTextContent());
                setIssue_number(eElement
                        .getElementsByTagName("issue")
                        .item(0)
                        .getTextContent());
                setId(eElement
                        .getElementsByTagName("issue-id")
                        .item(0)
                        .getTextContent());
            }
        }
        addToIssues();
    }

    @Override
    public String toString() {
        return "Issue{" +
                "issueType='" + issueType + '\'' +
                ", pubMonth='" + pubMonth + '\'' +
                ", pubYear='" + pubYear + '\'' +
                ", volume='" + volume + '\'' +
                ", issue_number='" + issue_number + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getDataCS() {
        return '\'' + issueType + '\'' +
                ',' + '\'' + pubMonth + '\'' +
                ',' + '\'' + pubYear + '\'' +
                ',' + '\'' + volume + '\'' +
                ',' + '\'' + issue_number + '\'' +
                ',' + '\'' + id + '\'';
    }

    private void addToIssues() {
        Utils.issues.add(new Issue(issueType, pubMonth, pubYear, volume, issue_number, id));
    }
}
