package backstage;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UploadPageControllerServlet" ,urlPatterns = "/uploadFile")
public class UploadPageControllerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("viewFiles/jsps/fileUpload.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);

                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        item.write( new File("uploaded" + File.separator + name));
                    }
                }

                //File uploaded successfully
                System.out.println( "File Uploaded Successfully");
            } catch (Exception ex) {
                System.out.println("File Upload Failed due to " + ex);
            }

        }else{
            System.out.println("Sorry this Servlet only handles file upload request");
        }

        request.getRequestDispatcher("/result.jsp").forward(request, response);

    }

}




