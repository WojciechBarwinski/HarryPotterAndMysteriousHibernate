package harryPotterApp.controllers.pet;

import harryPotterApp.dto.HPCharacterDto;
import harryPotterApp.dto.PetDto;
import harryPotterApp.services.PetService;
import harryPotterApp.services.PetServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/show-pets")
public class ShowPetsController extends HttpServlet {
    private PetService petService = new PetServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PetDto> allPets = petService.getAllPets();
        List<HPCharacterDto> charactersWithoutPets = petService.getAllCharactersWithoutPet();
        req.setAttribute("petsList", allPets);
        req.setAttribute("ownersAvailable", charactersWithoutPets);
        req.getRequestDispatcher("WEB-INF/view/pets.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
