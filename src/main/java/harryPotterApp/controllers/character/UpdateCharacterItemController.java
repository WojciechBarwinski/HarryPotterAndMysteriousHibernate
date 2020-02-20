package harryPotterApp.controllers.character;

import harryPotterApp.entities.Item;
import harryPotterApp.repositories.CharacterRepository;
import harryPotterApp.repositories.ItemRepository;
import harryPotterApp.services.HpCharacterService;
import harryPotterApp.services.HpCharacterServiceImpl;
import harryPotterApp.services.ItemService;
import harryPotterApp.services.ItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/update-character-items")
public class UpdateCharacterItemController extends HttpServlet {
    private HpCharacterService hpCharacterService = new HpCharacterServiceImpl();
    private ItemService itemService = new ItemServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String itemToAdd = req.getParameter("itemToAdd");
        String itemToDelete = req.getParameter("itemToDelete");
        HttpSession session = req.getSession(false);
        String id = (String)session.getAttribute("sessionId");

        if ( itemToAdd != null){
            Item wantedItemToAdd = itemService.findByName(itemToAdd);
            Long itemIdToAdd = wantedItemToAdd.getId();
            hpCharacterService.addItemToCharacter(id, itemIdToAdd);
        } else if (itemToDelete != null){
            Item wantedItemToDelete = itemService.findByName(itemToDelete);
            Long itemToRemoveId = wantedItemToDelete.getId();
            hpCharacterService.removeItemToCharacter(id, itemToRemoveId);
        }

        //req.setAttribute("idToView", id);
        req.getRequestDispatcher("/view-character?idToView=" + id).forward(req,resp);

//        session.removeAttribute("sessionId");
    }
}
