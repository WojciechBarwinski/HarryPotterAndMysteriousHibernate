package harryPotterApp.controllers;

import harryPotterApp.services.PetService;
import harryPotterApp.services.PetServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-pet")
public class DeletePetController extends HttpServlet {
    private PetService petService = new PetServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idToDelete = req.getParameter("idToDelete");
        petService.deleteById(Long.parseLong(idToDelete));
        resp.sendRedirect(req.getHeader("referer"));
    }
}
