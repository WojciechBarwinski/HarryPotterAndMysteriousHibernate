package harryPotterApp.controllers.pet;

import harryPotterApp.dto.PetDto;
import harryPotterApp.services.PetService;
import harryPotterApp.services.PetServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/view-pet")
public class PetViewController extends HttpServlet {
    private PetService petService = new PetServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idToView = req.getParameter("idToView");
        PetDto petForView = petService.preparePetForViewPage(Long.parseLong(idToView));
        req.setAttribute("pet",petForView);
        req.getRequestDispatcher("WEB-INF/view/pet-view.jsp").forward(req,resp);
    }
}
