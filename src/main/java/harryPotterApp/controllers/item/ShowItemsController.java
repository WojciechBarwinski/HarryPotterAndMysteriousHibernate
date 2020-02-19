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
import java.util.List;

@WebServlet("/view-item")
public class ShowItemsController extends HttpServlet {
    private ItemService itemService = new ItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemType = req.getParameter("itemType");

        List<Item> items = itemService.getAllItemsByType(itemType);
        req.setAttribute("itemList", items);
        req.setAttribute("type", itemType);

        req.getRequestDispatcher("WEB-INF/view/items.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
