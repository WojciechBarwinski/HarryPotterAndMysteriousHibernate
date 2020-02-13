package harryPotterApp.controllers;

import harryPotterApp.services.HpCharacterService;
import harryPotterApp.services.HpCharacterServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/add-character")
public class AddCharacterController extends HttpServlet {

    HpCharacterService characterService = new HpCharacterServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        LocalDate birthDate = LocalDate.parse(req.getParameter("birthDate")); // data must by in "2016-08-16"
        characterService.addCharacter(firstName, lastName, birthDate);
        resp.sendRedirect(req.getHeader("referer"));
    }

    private LocalDate convertStringToLocalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, formatter);
    }
}
