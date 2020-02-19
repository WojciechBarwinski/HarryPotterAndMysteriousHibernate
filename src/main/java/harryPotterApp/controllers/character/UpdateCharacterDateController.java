package harryPotterApp.controllers.character;

import harryPotterApp.services.HpCharacterService;
import harryPotterApp.services.HpCharacterServiceImpl;
import harryPotterApp.services.ValidationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/update-character-date")
public class UpdateCharacterDateController extends HttpServlet {
    HpCharacterService characterService = new HpCharacterServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String characterId = req.getParameter("characterId");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String birthDate = req.getParameter("birthDate");

        Map<String, String> errorsMap = ValidationService.valideteDateToUpdate(firstName, lastName, birthDate);
        if (errorsMap.isEmpty()){
            characterService.updateCharacter(characterId, firstName, lastName, birthDate);
        } else {
            req.setAttribute("noValue", errorsMap.get("noValue"));
            req.setAttribute("wrongName", errorsMap.get("wrongName"));
            req.setAttribute("wrongLastName", errorsMap.get("wrongLastName"));
            req.setAttribute("wrongData", errorsMap.get("wrongData"));
        }
        req.getRequestDispatcher("/view-character?idToView=" + characterId).forward(req, resp);
    }
}
