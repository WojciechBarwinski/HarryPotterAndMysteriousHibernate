package harryPotterApp.controllers.character;

import harryPotterApp.dto.HPCharacterDto;
import harryPotterApp.services.HpCharacterService;
import harryPotterApp.services.HpCharacterServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/view-character")
public class CharacterViewController extends HttpServlet {
    private HpCharacterService characterService = new HpCharacterServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idToView = Long.valueOf(req.getParameter("idToView"));
        HPCharacterDto characterToView = characterService.prepareCharacterToView(idToView);
        req.setAttribute("character", characterToView);
        req.getRequestDispatcher("/WEB-INF/view/character-view.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
