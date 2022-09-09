package Day1;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
       /* Session session = Day1.HibernateUtil.getSessionFactory().openSession(); // otwarcie Sesji - zwraca obiekt sesji
        // Testowe polączenie wyświetla wersję
        String sql = "SELECT VERSION();";
        String checkVersion = (String) session.createNativeQuery(sql).getSingleResult();
        System.out.println(checkVersion);

        session.close();  // zamknięcie sesji

        */
        EntityManager em = HibernateUtil.getSessionFactory().createEntityManager();// stworzenie polączenia za pomocą EntityManager z Day1.HibernateUtil

        Employee em1 = new Employee("Katarzyna", "Skrzynowska",  // stworzenie nowego obiektu pracownika
                LocalDate.of(1990, 12, 10), "kasia@o2.pl");
        Employee em2 = new Employee("Marzena", "Walicka",  // stworzenie nowego obiektu pracownika
                LocalDate.of(2003, 3, 7), "marzena@wp.pl");
        Employee em3 = new Employee("Alicja", "Skrzynowska",  // stworzenie nowego obiektu pracownika
                LocalDate.of(1940, 8, 11), "ala@gmail.com");
        Phone phone = new Phone("Samsung","444333222");
         em1.setPhone(phone);
      //  Task task1 = new Task("Delet","Delete option create","IMPORTANT");
      //  task1.setEmployee(em3);

        em.getTransaction().begin();  // rozpoczęcie transakcji
         /* odczyt oraz update obiektu z bazy danych
        Day1.Employee eFromDB = em.find(Day1.Employee.class, 1);
        System.out.println(eFromDB);
        eFromDB.setFirstName("Waleriana");
          em.merge(eFromDB);
        */

        em.persist(em1);  // zapisanie persist obiektu do bazy
        em.persist(em2);
        em.persist(em3);
        em.persist(phone);
      //  em.persist(task1);

        em.getTransaction().commit();       // commit transakcji, zapisanie
        EntityManager ent4 = HibernateUtil.getSessionFactory().createEntityManager();



        List<Employee> employeeList = ent4.createQuery("from Employee")
              //  .setParameter("param1", "Skrzynowska")
                .getResultList();

        employeeList.forEach(System.out::println);


        HibernateUtil.shutdown();           // zamnięcie hibernate
    }
}
