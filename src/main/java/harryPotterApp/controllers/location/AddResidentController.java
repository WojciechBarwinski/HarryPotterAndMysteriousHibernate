package harryPotterApp.controllers.location;

import harryPotterApp.services.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/add-resident")
public class AddResidentController extends HttpServlet {
    private HPLocationService hpLocationService = new HPLocationServiceImpl();
    private HpCharacterService hpCharacterService = new HpCharacterServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String locationId = req.getParameter("locationId");
        String residentToAdd = req.getParameter("residentToAdd");
        Map<String, String> errorsMap = ValidationService.selectInputValidate(residentToAdd);

        if (errorsMap.isEmpty()){
            Long characterId = hpCharacterService.getCharacterIdByFirstAndLastName(residentToAdd);
            hpLocationService.addResidentToLocation(String.valueOf(characterId),locationId);
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
