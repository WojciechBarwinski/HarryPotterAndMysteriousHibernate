package harryPotterApp.controllers.location;

import harryPotterApp.dto.HPLocationDto;
import harryPotterApp.entities.HPCharacter;
import harryPotterApp.entities.HPLocation;
import harryPotterApp.services.HPLocationService;
import harryPotterApp.services.HPLocationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/show-locations")
public class ShowLocationsController extends HttpServlet {

    private HPLocationService hpLocationService = new HPLocationServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<HPLocationDto> locationsList = hpLocationService.getAllLocations();
        List<HPCharacter> charactersWithoutLocation = hpLocationService.getAllCharactersWithoutLocation();
        req.setAttribute("locationsList", locationsList);
        req.setAttribute("residentsAvailable", charactersWithoutLocation);
        req.getRequestDispatcher("WEB-INF/view/locations.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
