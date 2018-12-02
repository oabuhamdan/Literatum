package backstage;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FilesUtil {

    public static void unzipFileAndDelete(String fileName) {

        String destinationFolderName = fileName.replaceAll(fileName.substring(fileName.lastIndexOf(".")), "");
        String destDir = "/home/osama-abuhamdan/IdeaProjects/Literatum/unzipped/" + destinationFolderName;
        String zipFilePath = "/home/osama-abuhamdan/IdeaProjects/Literatum/uploaded/" + fileName;

        long t1 = System.currentTimeMillis();
        BufferedOutputStream fos = null;
        ZipEntry ze;

        try (ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFilePath)))) {
            while (true) {
                ze= zis.getNextEntry();
                if (ze == null) break;
                String unzippedName = ze.getName();
                File newFile = new File(destDir + File.separator + unzippedName);
                System.out.println("Unzipping to " + newFile.getAbsolutePath());
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();

                fos = new BufferedOutputStream(new FileOutputStream(newFile));

                fos.flush();

                zis.closeEntry();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
