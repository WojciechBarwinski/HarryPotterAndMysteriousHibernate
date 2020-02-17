package harryPotterApp.controllers.character;

import harryPotterApp.dto.HPCharacterDto;
import harryPotterApp.services.HpCharacterService;
import harryPotterApp.services.HpCharacterServiceImpl;
import harryPotterApp.services.ValidationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/find-character")
public class FindCharacterController extends HttpServlet {
    private HpCharacterService characterService = new HpCharacterServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idToFind = req.getParameter("idToFind");
        Map<String, String> errorsMap = ValidationService.searchValidate(idToFind);

        if (errorsMap.isEmpty()) {
            List<HPCharacterDto> foundedCharacter = characterService.findCharacterById(idToFind);
            req.setAttribute("charactersList", foundedCharacter);
        } else {
            req.setAttribute("noId", errorsMap.get("noId"));
            req.setAttribute("invalidId", errorsMap.get("invalidId"));
            List<HPCharacterDto> allCharacters = characterService.getAllCharacters();
            req.setAttribute("charactersList", allCharacters);
        }
        req.getRequestDispatcher("WEB-INF/view/characters.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/show-characters").forward(req, resp);
    }
}
