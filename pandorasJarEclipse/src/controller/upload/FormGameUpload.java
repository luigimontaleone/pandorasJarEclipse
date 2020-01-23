package controller.upload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import model.Game;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import persistence.DAOFactory;

@WebServlet(value = "/formGameUpload", name = "formGameUpload")
public class FormGameUpload extends HttpServlet {
    private boolean isMultipart;
    private String filePath;
    private File file;

    @Override
    public void init() throws ServletException {
        filePath = this.getServletContext().getRealPath(File.separator) + File.separator + "gameFiles";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        if (req.getSession().getAttribute("logged") == null || !(Boolean) req.getSession().getAttribute("logged")) {
            requestDispatcher = req.getRequestDispatcher("notLogged.jsp");
            requestDispatcher.forward(req, resp);
            return;
        } else {
            requestDispatcher = req.getRequestDispatcher("header.jsp");
            requestDispatcher.include(req, resp);
            requestDispatcher = req.getRequestDispatcher("formGameUpload.html");
        }
        requestDispatcher.include(req, resp);
        requestDispatcher = req.getRequestDispatcher("footer.html");
        requestDispatcher.include(req, resp);
    }

    private void storeGameFile(FileItem item, String gameTitle) throws Exception {
        storeFile(item, gameTitle);
    }

    private void storeFile(FileItem item, String directory) throws Exception {
        String name = item.getName();
        file = new File(filePath + File.separator + directory + File.separator + name);
        //item.write(file); SCRITTURA DEL FILE JAR
        this.log("Uploaded: " + file.getName());
    }

    private Game handleInsertGame(HttpServletRequest req) throws Exception {
        String name = "";
        String description="";
        String specs="";
        double price=0;
        String tag="";
        String previewImage = null;
        ArrayList<String> images = new ArrayList<>();
        String video = null;

        FileItem jarFile = null;
        this.isMultipart = ServletFileUpload.isMultipartContent(req);
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        try {
            List<FileItem> fileItems = servletFileUpload.parseRequest(req);
            for (FileItem i : fileItems) {
                if (i.getFieldName().equals("gameFile")) {
                    jarFile = i;
                } else if (i.getFieldName().contains("link")) {
                        images.add(i.getString());
                } else if (i.getFieldName().equals("previewVideo")) {
                    video = i.getString();
                } else if (i.getFieldName().equals("previewImage")) {
                    previewImage = i.getString();
                    this.log("previewImage");
                } else if (i.getFieldName().equals("name")) {
                    name = i.getString();
                } else if (i.getFieldName().equals("description")) {
                    description = i.getString();
                } else if (i.getFieldName().equals("specs")) {
                    specs = i.getString();
                } else if (i.getFieldName().equals("price")) {
                    price = Double.parseDouble(i.getString());
                } else if (i.getFieldName().contains("tag")) {
                    tag = i.getString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Game g = DAOFactory.getInstance().makeGameDAO().getGameByName(name);
        if (g.getId() == 0) {
            DAOFactory.getInstance().makeGameDAO().insertGame(0, name, (Integer) req.getSession().getAttribute("userId"),
                    tag, (String)req.getSession().getAttribute("helpEmail"), price,
                    (String)req.getSession().getAttribute("paymentCoords"),description+"\n"+specs);
            g = DAOFactory.getInstance().makeGameDAO().getGameByName(name);
            DAOFactory.getInstance().makeGameDAO().insertPreviewImage(g.getId(), previewImage, true);
            this.storeGameFile(jarFile, name);
            for (String img : images) {
                DAOFactory.getInstance().makeGameDAO().insertPreviewImage(g.getId(), img, false);
            }
            DAOFactory.getInstance().makeGameDAO().insertPreviewVideo(g.getId(), video);
        }
        return g;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Game g=new Game();
        try {
            g = handleInsertGame(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/GameDataSheet?gameId=" + g.getId());
    }

    private static boolean isVideoFile(String path) {
        String mimeType = URLConnection.guessContentTypeFromName(path);
        return mimeType != null && mimeType.startsWith("video");
    }
}
