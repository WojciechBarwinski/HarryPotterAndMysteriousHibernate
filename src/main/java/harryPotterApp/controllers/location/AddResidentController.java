package harryPotterApp.controllers.location;

import harryPotterApp.services.HpCharacterService;
import harryPotterApp.services.HpCharacterServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-resident")
public class AddResidentController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String residentToAdd = req.getParameter("residentToAdd");
        //potrzeba id lokacji

        //validacja danych, czy sa puste

        //jesli validacja przeszła poprawnie
        //z imienia i nazwiska, weź ID characteru
        //dodaj do lokacji ID character o ID

    }
}
