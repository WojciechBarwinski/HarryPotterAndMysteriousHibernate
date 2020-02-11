import startingData.DataInitializer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ORM");

        EntityManager entityManager = emf.createEntityManager();

        DataInitializer.addAllData(entityManager);
        /*final EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();


        transaction.commit();*/
    }
}
