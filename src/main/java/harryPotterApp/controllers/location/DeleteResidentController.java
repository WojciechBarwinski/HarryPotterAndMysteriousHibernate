package harryPotterApp.controllers.location;

import harryPotterApp.dto.HPLocationDto;
import harryPotterApp.entities.HPCharacter;
import harryPotterApp.services.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/delete-resident")
public class DeleteResidentController extends HttpServlet {
    private HPLocationService hpLocationService = new HPLocationServiceImpl();
    private HpCharacterService hpCharacterService = new HpCharacterServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String residentToDelete = req.getParameter("residentToDelete");
        Map<String, String> errorsMap = ValidationService.validateSelectInput(residentToDelete);

        if (errorsMap.isEmpty()){
            Long characterId = hpCharacterService.getCharacterIdByFirstAndLastName(residentToDelete);
            hpLocationService.removeResident(characterId);
        } else {
            req.setAttribute("noSelect", errorsMap.get("noSelect"));
        }
        req.getRequestDispatcher("/show-locations").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/show-locations").forward(req, resp);
    }
}
