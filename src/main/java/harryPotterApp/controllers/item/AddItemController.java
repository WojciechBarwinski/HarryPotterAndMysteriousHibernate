package harryPotterApp.controllers.item;

import harryPotterApp.services.ItemService;
import harryPotterApp.services.ItemServiceImpl;
import harryPotterApp.services.ValidationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/add-item")
public class AddItemController extends HttpServlet {
    private ItemService itemService = new ItemServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemName = req.getParameter("name");
        String itemValue = req.getParameter("value");
        String itemType = req.getParameter("type");

        Map<String, String> errorsMap = ValidationService.addItemValidate(itemName, itemValue);
        if (errorsMap.isEmpty()){
            itemService.add(itemName, itemValue, itemType);
        } else {
            req.setAttribute("noValue", errorsMap.get("noValue"));
            req.setAttribute("wrongName", errorsMap.get("wrongName"));
        }
        req.getRequestDispatcher("/view-item?itemType=" + itemType).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view-item").forward(req, resp);
    }
}
