package backstage;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UploadPageControllerServlet", urlPatterns = "/uploadFile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 100,
        maxRequestSize = 1024 * 1024 * 100)
public class UploadPageControllerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("viewFiles/jsps/fileUpload.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String UPLOAD_DIRECTORY = "/home/osama-abuhamdan/IdeaProjects/Literatum/uploaded";
        String fileName = "";
        try {
            for ( Part part : request.getParts() ) {
                fileName = part.getSubmittedFileName();
                if (fileName.equals("")) throw new Exception("Please choose a file");
                if (!fileName.endsWith(".zip")) throw new Exception("ZIP files allowed only");
                part.write(UPLOAD_DIRECTORY + File.separator + fileName);
            }

            sendMsgToJSP("success","File "+fileName+" Uploaded  Successfully",request,response);
            System.out.println("File Uploaded Successfully");

            FilesUtil.unzipFileAndDelete(fileName);

        } catch (Exception e) {
            sendMsgToJSP("error",e.getMessage(),request,response);
            System.out.println("File Uploaded Unsuccessfully");
        }
    }

    private void sendMsgToJSP(String attribute, String msg, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(attribute, msg);
        RequestDispatcher rd = request.getRequestDispatcher("viewFiles/jsps/fileUpload.jsp");
        rd.forward(request, response);
    }
}




