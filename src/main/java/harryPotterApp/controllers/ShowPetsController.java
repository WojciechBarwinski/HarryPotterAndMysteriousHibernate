package harryPotterApp.controllers;

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
        req.setAttribute("petsList", allPets);
        req.getRequestDispatcher("WEB-INF/view/pets.jsp").forward(req, resp);
    }
}
