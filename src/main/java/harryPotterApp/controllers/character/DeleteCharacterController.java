package harryPotterApp.controllers.character;

import harryPotterApp.services.HpCharacterService;
import harryPotterApp.services.HpCharacterServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-character")
public class DeleteCharacterController extends HttpServlet {
    private HpCharacterService hpCharacterService = new HpCharacterServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idToDelete = req.getParameter("idToDelete");
        hpCharacterService.deleteCharacterById(Long.valueOf(idToDelete));
        resp.sendRedirect(req.getHeader("referer"));
    }
}
