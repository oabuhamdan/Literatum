package backstage;

import data.Article;
import database.DBProcessing;
import utils.Utils;
import utils.XMLUtils;

import java.io.File;

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

        handleArticleData(xmlFile,pdfFile);
    }

    private static void handleArticleData(File xmlFile,File pdfFile) {
        File journalMetaXML = XMLUtils.XMLConverter(xmlFile, new File(Utils.PROJECT_PATH + "xsl/extractArticleMetaData.xsl"));
        Article a = new Article();
        a.setJournalMetaData(XMLUtils.DOMParser(journalMetaXML));
        //a.setSelfUri(pdfFile.getAbsolutePath());
        DBProcessing.executeStatement("insert into  article_info values (" + a.getDataCS() + ')');
        System.out.println(a.getDataCS());
    }

}
