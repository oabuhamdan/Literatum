package backstage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

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

        System.out.println("Uploading path: " + Utils.UPLOAD_PATH);

        File dir = new File(Utils.UPLOAD_PATH);
        if (!dir.exists()) dir.mkdir();

        try {

            String fileName = uploadFileAndGetItsName(request);

            sendMsgToJSP("success", "File " + fileName + " Uploaded  Successfully", request, response);

            SubmissionHandler.handle(fileName);

        } catch (Exception e) {
            sendMsgToJSP("error", e.getMessage(), request, response);
            System.out.println("File Uploaded Unsuccessfully");
        }
    }

    private void sendMsgToJSP(String attribute, String msg, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(attribute, msg);
        RequestDispatcher rd = request.getRequestDispatcher("viewFiles/jsps/fileUpload.jsp");
        rd.forward(request, response);
    }

    private String uploadFileAndGetItsName(HttpServletRequest request) throws Exception {
        String fileName = "";
        for ( Part part : request.getParts() ) {
            fileName = part.getSubmittedFileName();
            if (fileName.equals("")) throw new Exception("Please choose a file");
            if (!fileName.endsWith(".zip")) throw new Exception("ZIP files allowed only");
            part.write(Utils.UPLOAD_PATH + fileName);

        }
        return fileName;
    }
}




