package harryPotterApp.controllers;

import harryPotterApp.dto.HPCharacterDto;
import harryPotterApp.services.HpCharacterService;
import harryPotterApp.services.HpCharacterServiceImpl;
import harryPotterApp.startingData.DataInitializer;
import harryPotterApp.startingData.SingletonEntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeController extends HttpServlet {
    //EntityManagerFactory emf = Persistence.createEntityManagerFactory("ORM");

    EntityManager entityManager = SingletonEntityManagerFactory.getEmf().createEntityManager();

    //private HpCharacterService hpCharacterService = new HpCharacterServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataInitializer.addAllData(entityManager);
        req.getRequestDispatcher("WEB-INF/view/home.jsp").forward(req, resp);
    }
}
