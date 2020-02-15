package harryPotterApp.controllers;

import harryPotterApp.startingData.DataInitializer;
import harryPotterApp.startingData.EntityManagerFactory;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeController extends HttpServlet {
    EntityManager entityManager = EntityManagerFactory.getEmf().createEntityManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataInitializer.addAllData(entityManager);
        req.getRequestDispatcher("WEB-INF/view/home.jsp").forward(req, resp);
    }
}
