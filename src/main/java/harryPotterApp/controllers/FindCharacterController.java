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
import java.util.List;

@WebServlet("/find-character")
public class FindCharacterController extends HttpServlet {
    HpCharacterService characterService = new HpCharacterServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idToFind = req.getParameter("idToFind");
        List<HPCharacterDto> allCharacters = characterService.findCharacterById(idToFind);
        req.setAttribute("charactersList", allCharacters);
        req.getRequestDispatcher("WEB-INF/view/characters.jsp").forward(req,resp);
    }
}
