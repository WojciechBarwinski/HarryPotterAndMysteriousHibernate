package harryPotterApp.controllers.pet;

import harryPotterApp.dto.PetDto;
import harryPotterApp.entities.EntityType;
import harryPotterApp.services.PetService;
import harryPotterApp.services.PetServiceImpl;
import harryPotterApp.services.PhotoService;
import harryPotterApp.services.ValidationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/add-photo-to-pet")
public class AddPhotoToPetController extends HttpServlet {
    private PetService petService = new PetServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imagePath = req.getParameter("imagesPath");
        Long petId = Long.valueOf(req.getParameter("petId"));
        Map<String, String> errorsMap = ValidationService.imagePathValidate(imagePath);

        if (errorsMap.isEmpty()) {
            PhotoService.addPhoto(imagePath, petId, EntityType.PET);
        } else {
            req.setAttribute("noValue", errorsMap.get("noValue"));
            req.setAttribute("wrongPath", errorsMap.get("wrongPath"));
        }
        PetDto petDto = petService.preparePetForViewPage(petId);
        req.setAttribute("pet", petDto);
        req.getRequestDispatcher("/WEB-INF/view/pet-view.jsp").forward(req, resp);
    }
}
