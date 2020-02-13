package harryPotterApp.controllers;

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
    HpCharacterService characterService = new HpCharacterServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idToView = Long.valueOf(req.getParameter("idToView"));
        HPCharacterDto characterToView = characterService.findCharacterToView(idToView);
        req.setAttribute("character", characterToView);
        req.getRequestDispatcher("/WEB-INF/view/character-view.jsp").forward(req, resp);
    }
}
