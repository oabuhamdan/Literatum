package backstage;

import org.apache.ant.compress.taskdefs.Unzip;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FilesUtil {

    public static void unzipFileAndDelete(String fileName) {

        String destinationFolderName = fileName.replaceAll(fileName.substring(fileName.lastIndexOf(".")), "");
        String destDir = "/home/ohamdan/IdeaProjects/Literatum/unzipped/" + destinationFolderName;
        String zipFilePath = "/home/ohamdan/IdeaProjects/Literatum/uploaded/" + fileName;

       long t1 = System.currentTimeMillis();

        Unzip unZipper = new Unzip();
        unZipper.setSrc(new File(zipFilePath));
        unZipper.setDest(new File(destDir));
        unZipper.execute();

        long t2 = System.currentTimeMillis();
        System.out.println("Time to extract : " + (t2 - t1));
        deleteFile(zipFilePath);
    }

    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.delete()){
            System.out.println("File "+file.getName()+" was deleted successfully");
        }
        else
        {
            System.out.println("File"+file.getName()+" wasn't deleted successfully");
        }
    }
}
