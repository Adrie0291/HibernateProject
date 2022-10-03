package Day3;


import javax.persistence.EntityManager;

public class Demo {
    public static void main(String[] args) {
        EntityManager emc = HibernateUtil.getSessionFactory().createEntityManager();
        System.out.println("Siema");
        HibernateUtil.shutdown();           // zamnięcie hibernate
    }
}
