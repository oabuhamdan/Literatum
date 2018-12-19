package backstage;

import utils.Utils;
import org.apache.ant.compress.taskdefs.Unzip;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SubmissionHandler {

    public static void handle(String fileName) {

        String zipFilePath = Utils.UPLOAD_PATH + fileName;
        String unzippingFolderPath = Utils.UN_ZIPPING_PATH + File.separator + fileName.replace(".zip", "");

        unzipFile(zipFilePath, unzippingFolderPath);
        deleteZipFile(zipFilePath);

        List<File> filesList=listFilesInSubmission(unzippingFolderPath);

        ArticleFilesHandler.handle(getArticleFiles(filesList));
        IssueFilesHandler.handle(getIssueFiles(filesList));
    }

    private static File[] getIssueFiles(List<File> list) {
        File[] issueFiles = null;
        for ( File file : list ) {
            if (file.isDirectory() && file.getAbsolutePath().contains("issue-files"))
                issueFiles = file.listFiles();
        }
        return issueFiles;
    }

    private static File[] getArticleFiles(List<File> list) {
        File[] articleFiles = null;
        for ( File file : list ) {
            if (file.isDirectory() && !file.getAbsolutePath().contains("issue-files"))
                articleFiles = file.listFiles();
        }
        return articleFiles;
    }

    private static void unzipFile(String zipFilePath, String unzippingFolderPath) {

        File unzipDir = new File(unzippingFolderPath);
        if (!unzipDir.exists()) unzipDir.mkdir();

        Unzip unZipper = new Unzip();
        unZipper.setSrc(new File(zipFilePath));
        unZipper.setDest(new File(unzippingFolderPath));
        unZipper.execute();
    }

    private static void deleteZipFile(String filePath) {
        File file = new File(filePath);
        if (file.delete()) {
            System.out.println("File " + file.getName() + " was deleted successfully");
        } else {
            System.out.println("File" + file.getName() + " wasn't deleted successfully");
        }
    }

    private static List<File> listFilesInSubmission(String unzippingFolderPath) {
        File directory = new File(unzippingFolderPath);
        List<File> resultList = new ArrayList<>();

        // get all the files from a directory
        File[] fList = directory.listFiles();

        resultList.addAll(Arrays.asList(fList));
        for ( File file : fList ) {
            if (file.isFile()) {
               // System.out.println(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                resultList.addAll(listFilesInSubmission(file.getAbsolutePath()));
            }
        }
        return resultList;
    }
}

