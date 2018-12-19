package backstage;

import java.io.File;

public class IssueFilesHandler {

    public static void handle(File[] files) {

        File xmlFile = null;

        for ( File file : files ) {
            if (file.getName().endsWith(".xml"))
                xmlFile = file;
        }

        System.out.println("Hi from Issue files handler : " + xmlFile.getAbsolutePath());

        handleJournalData(xmlFile);
        handleIssueData(xmlFile);
        //deleteGeneratedFiles();

    }

    private static void deleteGeneratedFiles() {
        for ( File file : new File(Utils.PROJECT_PATH + "processed").listFiles() ) {
            file.delete();
        }
    }

    private static void handleJournalData(File xmlFile) {
        File journalMetaXML = XMLUtils.XMLConverter(xmlFile, new File(Utils.PROJECT_PATH + "xsl/extractJournalMetaData.xsl"));
        Journal j = new Journal();
        j.setJournalMetaData(XMLUtils.DOMParser(journalMetaXML));
        DBProcessing.executeStatement("insert into  journal_info values (" + j.getDataCS() + ')');
        System.out.println(j.getDataCS());
        System.out.println(j);
    }

    private static void handleIssueData(File xmlFile) {
        File journalMetaXML = XMLUtils.XMLConverter(xmlFile, new File(Utils.PROJECT_PATH + "xsl/extractIssueMetaData.xsl"));
        Issue i = new Issue();
        i.setIssueMetaData(XMLUtils.DOMParser(journalMetaXML));
        DBProcessing.executeStatement("insert into  issue_info values (" + i.getDataCS() + ')');
        System.out.println(i);
    }
}
