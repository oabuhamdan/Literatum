package backstage;

import java.io.File;

public class ArticleFilesHandler {

    public static void handle(File[] files) {
        File xmlFile = null;
        for ( File file : files ) {
            if (file.getName().endsWith(".xml"))
                xmlFile = file;
        }

        System.out.println("Hi from Article files handler" + xmlFile.getAbsolutePath());

    }
}
