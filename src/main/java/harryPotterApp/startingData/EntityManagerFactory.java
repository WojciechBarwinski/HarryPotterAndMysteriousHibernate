package harryPotterApp.startingData;

import javax.persistence.Persistence;

public class EntityManagerFactory {
    
    private static javax.persistence.EntityManagerFactory emf = Persistence.createEntityManagerFactory("ORM");

    private EntityManagerFactory() {
    }

    public static javax.persistence.EntityManagerFactory getEmf(){
        return emf;
    }

}
