package harryPotterApp.controllers;

import harryPotterApp.dto.HPCharacterDto;
import harryPotterApp.services.HpCharacterService;
import harryPotterApp.services.HpCharacterServiceImpl;
import harryPotterApp.startingData.DataInitializer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeController extends HttpServlet {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ORM");

    EntityManager entityManager = emf.createEntityManager();

    private HpCharacterService hpCharacterService = new HpCharacterServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataInitializer.addAllData(entityManager);
        List<HPCharacterDto> allCharacters = hpCharacterService.getAllCharacters();
        req.setAttribute("charactersList", allCharacters);
        req.getRequestDispatcher("WEB-INF/view/home.jsp").forward(req,resp);
    }
}