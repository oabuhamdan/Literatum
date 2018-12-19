package backstage;


import org.apache.commons.io.FileUtils;

import javax.rmi.CORBA.Util;
import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;

public class ArticleFilesHandler {

    public static void handle(File[] files) {
        File pdfFile = null, xmlFile = null;
        for ( File file : files ) {
            if (file.getName().endsWith(".xml"))
                xmlFile = file;

            if (file.getName().endsWith(".pdf"))
                pdfFile = file;
        }


        System.out.println("Hi from Article files handler" + xmlFile.getAbsolutePath());

        System.out.println("Is Article Valid ? " + XMLUtils.validateWithDTDUsingDOM(xmlFile));

        handleArticleData(xmlFile);
    }

    private static void handleArticleData(File xmlFile) {
        File journalMetaXML = XMLUtils.XMLConverter(xmlFile, new File(Utils.PROJECT_PATH + "xsl/extractArticleMetaData.xsl"));
        Article a = new Article();

        a.setJournalMetaData(XMLUtils.DOMParser(journalMetaXML));
        DBProcessing.executeStatement("insert into  article_info values (" + a.getDataCS() + ')');
        System.out.println(a.getDataCS());
    }

    private static void deleteGeneratedFiles() {
        for ( File file : new File(Utils.PROJECT_PATH + "processed").listFiles() ) {
            file.delete();
        }
    }


}
