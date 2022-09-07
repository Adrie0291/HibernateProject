import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.time.LocalDate;

public class Demo {
    public static void main(String[] args) {
       /* Session session = HibernateUtil.getSessionFactory().openSession(); // otwarcie Sesji - zwraca obiekt sesji
        // Testowe polączenie wyświetla wersję
        String sql = "SELECT VERSION();";
        String checkVersion = (String) session.createNativeQuery(sql).getSingleResult();
        System.out.println(checkVersion);

        session.close();


        */
        EntityManager em = HibernateUtil.getSessionFactory().createEntityManager();
        Employee em1 = new Employee("Katarzyna","Skrzynowska", LocalDate.of(1990,12,10),"kasia@o2.pl");
        em.getTransaction().begin();
        em.persist(em1);

        em.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}
