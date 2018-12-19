package utils;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;

public class XMLUtils {

    private static int counter = 0;

    private XMLUtils() {
    }


    // validate using journalMetaDOMParser (DTD as defined in the XML)
    public static boolean validateWithDTDUsingDOM(File xmlFile) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(true);

            DocumentBuilder builder = factory.newDocumentBuilder();

            builder.setErrorHandler(
                    new ErrorHandler() {
                        public void warning(SAXParseException e) throws SAXException {
                            System.out.println("WARNING : " + e.getMessage()); // do nothing
                        }

                        public void error(SAXParseException e) throws SAXException {
                            System.out.println("ERROR : " + e.getMessage());
                            throw e;
                        }

                        public void fatalError(SAXParseException e) throws SAXException {
                            System.out.println("FATAL : " + e.getMessage());
                            throw e;
                        }
                    }
            );
            builder.parse(xmlFile);
            return true;
        } catch (ParserConfigurationException | IOException pce) {
            try {
                throw pce;
            } catch (ParserConfigurationException | IOException e) {
                e.printStackTrace();
            }
        } catch (SAXException se) {
            return false;
        }
        return false;
    }

    public static File XMLConverter(File xmlFile, File xslFile) {

        File outputFile = null;
        try {

            if (!new File(Utils.PROJECT_PATH + "processed").exists())
                new File(Utils.PROJECT_PATH + "processed").mkdir();

            TransformerFactory factory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(xslFile);
            Transformer transformer = factory.newTransformer(xslt);

            Source text = new StreamSource(xmlFile);

            outputFile = new File(Utils.PROJECT_PATH + "processed/newFile" + counter++ + ".xml");

            transformer.transform(text, new StreamResult(outputFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputFile;
    }

    public static NodeList DOMParser(File xmlFile) {
        NodeList nList = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            nList = doc.getElementsByTagName(doc.getDocumentElement().getNodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nList;
    }
}


