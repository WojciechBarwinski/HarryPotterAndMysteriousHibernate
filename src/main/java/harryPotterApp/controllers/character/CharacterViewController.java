package harryPotterApp.controllers.character;

import harryPotterApp.dto.HPCharacterDto;
import harryPotterApp.entities.Item;
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
import java.util.List;
import java.util.Set;

@WebServlet("/view-character")
public class CharacterViewController extends HttpServlet {
    private HpCharacterService characterService = new HpCharacterServiceImpl();
    private ItemService itemService = new ItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idToView = Long.valueOf(req.getParameter("idToView"));
        HttpSession session = req.getSession();

        session.setAttribute("sessionId",String.valueOf(idToView));
        String sessionId = (String)session.getAttribute("sessionId");

        HPCharacterDto characterToView = characterService.prepareCharacterToView(idToView);
        List<Item> itemsAvailable = itemService.getAllItemsWhichAreNotOwnedByCharacter(String.valueOf(idToView));
        req.setAttribute("character", characterToView);
        req.setAttribute("itemsAvailable", itemsAvailable);
        req.getRequestDispatcher("/WEB-INF/view/character-view.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
