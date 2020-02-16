package harryPotterApp.controllers.character;

import harryPotterApp.dto.HPCharacterDto;
import harryPotterApp.services.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/add-photo")
public class AddPhotoController extends HttpServlet {
    HpCharacterService characterService = new HpCharacterServiceImpl();
    PhotoService photoService = new PhotoServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imagePath = req.getParameter("imagesPath");
        Long characterId = Long.valueOf(req.getParameter("characterId"));
        Map<String, String> errorsMap = ValidationService.validateImagePath(imagePath);

        if (errorsMap.isEmpty()){
            photoService.addPhotoToCharacter(imagePath, characterId);
        } else {
            req.setAttribute("noValue", errorsMap.get("noValue"));
            req.setAttribute("wrongPath", errorsMap.get("wrongPath"));
        }
        HPCharacterDto characterToView = characterService.prepareCharacterToView(characterId);
        req.setAttribute("character", characterToView);
        req.getRequestDispatcher("/WEB-INF/view/character-view.jsp").forward(req, resp);
    }
}
