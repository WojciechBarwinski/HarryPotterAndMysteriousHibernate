package harryPotterApp.startingData;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SingletonEntityManagerFactory {

    private static SingletonEntityManagerFactory singletonFactory;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ORM");

    private SingletonEntityManagerFactory() {
    }

    public static EntityManagerFactory getEmf(){
        return emf;
    }

    public static SingletonEntityManagerFactory getInstance() {
        if (singletonFactory.equals(null)) {
            synchronized (SingletonEntityManagerFactory.class) {
                if (singletonFactory.equals(null)) {
                    singletonFactory = new SingletonEntityManagerFactory();
                }
            }
        }
        return singletonFactory;
    }
}
