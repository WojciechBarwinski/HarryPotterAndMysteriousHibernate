package harryPotterApp.controllers.item;

import harryPotterApp.entities.Item;
import harryPotterApp.entities.ItemType;
import harryPotterApp.services.ItemService;
import harryPotterApp.services.ItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-item")
public class DeleteItemController extends HttpServlet {
    private ItemService itemService = new ItemServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemIdToDelete = req.getParameter("itemIdToDelete");
        Item itemToDelete = itemService.findById(itemIdToDelete);
        ItemType type = itemToDelete.getType();
        itemService.deleteById(itemIdToDelete);
        req.getRequestDispatcher("/view-item?itemType=" + type).forward(req, resp);
    }
}
