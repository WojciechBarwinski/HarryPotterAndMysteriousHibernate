package harryPotterApp.controllers.location;

import harryPotterApp.dto.HPLocationDto;
import harryPotterApp.entities.HPCharacter;
import harryPotterApp.services.HPLocationService;
import harryPotterApp.services.HPLocationServiceImpl;
import harryPotterApp.services.HpCharacterService;
import harryPotterApp.services.HpCharacterServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/add-resident")
public class AddResidentController extends HttpServlet {
    private HPLocationService hpLocationService = new HPLocationServiceImpl();
    private HpCharacterService hpCharacterService = new HpCharacterServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String locationId = req.getParameter("locationId");
        String residentToAdd = req.getParameter("residentToAdd");
        Long characterId = hpCharacterService.getCharacterIdByFirstAndLastName(residentToAdd);
        hpLocationService.addResidentToLocation(String.valueOf(characterId),locationId);

        resp.sendRedirect(req.getHeader("referer"));

    }

}
