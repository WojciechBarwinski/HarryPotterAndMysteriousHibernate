package harryPotterApp.controllers;

import harryPotterApp.dto.HPCharacterDto;
import harryPotterApp.dto.PetDto;
import harryPotterApp.services.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/add-pet")
public class AddPetController extends HttpServlet {
    private PetService petService = new PetServiceImpl();
    private HpCharacterService hpCharacterService = new HpCharacterServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String species = req.getParameter("species");
        String[] ownerData = req.getParameter("owner").split(" ");
        String firstName = ownerData[0];
        String lastName = ownerData[1];

        Map<String, String> errorsMap = ValidationService.validateAddingPet(name, species, lastName);
        if (errorsMap.isEmpty()) {
            List<HPCharacterDto> allCharacters = hpCharacterService.getAllCharacters();

            Long ownerId = allCharacters.stream()
                    .filter(hpCharacterDto -> hpCharacterDto.getFirstName().equals(firstName))
                    .filter(hpCharacterDto -> hpCharacterDto.getLastName().equals(lastName))
                    .map(HPCharacterDto::getId)
                    .findFirst().get();

            petService.add(name, species, ownerId);
            req.setAttribute("name", name);
            req.setAttribute("species", species);

            List<PetDto> allPets = petService.getAllPets();
            List<HPCharacterDto> charactersWithoutPets = petService.getAllCharactersWithoutPet();
            req.setAttribute("petsList", allPets);
            req.setAttribute("ownersAvailable", charactersWithoutPets);

        } else {
            List<HPCharacterDto> charactersWithoutPets = petService.getAllCharactersWithoutPet();
            req.setAttribute("ownersAvailable", charactersWithoutPets);
            req.setAttribute("noValue", errorsMap.get("noValue"));
            req.setAttribute("wrongName", errorsMap.get("wrongName"));
            req.setAttribute("wrongSpecies", errorsMap.get("wrongSpecies"));
        }
        req.getRequestDispatcher("WEB-INF/view/pets.jsp").forward(req, resp);
    }

}
