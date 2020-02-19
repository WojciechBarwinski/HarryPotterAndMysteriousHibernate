package harryPotterApp.controllers.character;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update-character-items")
public class UpdateCharacterItemController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemToAdd = req.getParameter("itemIdToAdd");
        String itemToDelete = req.getParameter("itemIdToDelete");
    }
}
