package harryPotterApp.controllers.pet;

import harryPotterApp.dto.HPCharacterDto;
import harryPotterApp.dto.PetDto;
import harryPotterApp.services.PetService;
import harryPotterApp.services.PetServiceImpl;
import harryPotterApp.services.ValidationService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/find-pet")
public class FindPetController extends HttpServlet {
    private PetService petService = new PetServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idToFind = req.getParameter("idToFind");
        Map<String, String> errorsMap = ValidationService.searchValidate(idToFind);
        if (errorsMap.isEmpty()){
            List<PetDto> foundPets = petService.findById(idToFind);
            req.setAttribute("petsList",foundPets);
        } else {
            req.setAttribute("noId",errorsMap.get("noId"));
            req.setAttribute("invalidId",errorsMap.get("invalidId"));
            List<PetDto> allPets = petService.getAllPets();
            List<HPCharacterDto> charactersWithoutPets = petService.getAllCharactersWithoutPet();
            req.setAttribute("petsList", allPets);
            req.setAttribute("ownersAvailable", charactersWithoutPets);
            req.getRequestDispatcher("WEB-INF/view/pets.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("WEB-INF/view/pets.jsp").forward(req, resp);



    }
}
