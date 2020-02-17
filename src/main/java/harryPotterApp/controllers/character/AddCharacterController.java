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

@WebServlet("/add-character")
public class AddCharacterController extends HttpServlet {
    private HpCharacterService characterService = new HpCharacterServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String birthDate = req.getParameter("birthDate");

        Map<String, String> errorsMap = ValidationService.addValidate(firstName, lastName, birthDate);
        if (errorsMap.isEmpty()){
            characterService.addCharacter(firstName, lastName, birthDate);
            List<HPCharacterDto> allCharacters = characterService.getAllCharacters();
            req.setAttribute("charactersList", allCharacters);
        } else {
            req.setAttribute("noValue", errorsMap.get("noValue"));
            req.setAttribute("wrongName", errorsMap.get("wrongName"));
            req.setAttribute("wrongLastName", errorsMap.get("wrongLastName"));
            req.setAttribute("wrongData", errorsMap.get("wrongData"));
            req.setAttribute("characterExists", errorsMap.get("characterExists"));
        }
        req.getRequestDispatcher("WEB-INF/view/characters.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/show-characters").forward(req, resp);
    }
}
