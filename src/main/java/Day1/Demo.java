package Day1;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;

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

        Employee emp1 = new Employee("Katarzyna", "Skrzynowska",  // stworzenie nowego obiektu pracownika
                LocalDate.of(1990, 12, 10), "Katarzyna.Skrzynowska@testtarossa.com");
        Employee emp2 = new Employee("Marzena", "Walicka",  // stworzenie nowego obiektu pracownika
                LocalDate.of(2003, 3, 7), "marzena@wp.pl");
        Employee emp3 = new Employee("Alicja", "Skrzynowska",  // stworzenie nowego obiektu pracownika
                LocalDate.of(1940, 8, 11), "ala@gmail.com");
        Phone phone1 = new Phone(PhoneBrand.LG, "444333222");
        Phone phone2 = new Phone(PhoneBrand.LG, "232321234");
        Phone phone3 = new Phone(PhoneBrand.LG, "999222123");
        emp1.setPhone(phone1);
        emp2.setPhone(phone2);
        emp3.setPhone(phone3);

        Task task1 = new Task("Delet", "Delete option UPDATE", TaskType.NORMAL);
        Task task2 = new Task("Update", "Update new option", TaskType.BLOCKER);
        Task task3 = new Task("Start", "Start new system", TaskType.BLOCKER);
        Task task4 = new Task("Create", "Create new folder", TaskType.HIGH_PRIORITY);
        task1.setEmployee(emp3);

        Project project = new Project("VLC 13.0");
     //   project.setEmployees(List.of(emp1,emp2));


        em.getTransaction().begin();  // rozpoczęcie transakcji
         /* odczyt oraz update obiektu z bazy danych
        Day1.Employee eFromDB = em.find(Day1.Employee.class, 1);
        System.out.println(eFromDB);
        eFromDB.setFirstName("Waleriana");
          em.merge(eFromDB);
        */

        em.persist(emp1);  // zapisanie persist obiektu do bazy
        em.persist(emp2);
        em.persist(emp3);
        em.persist(phone1);
        em.persist(phone2);
        em.persist(phone3);
        em.persist(task1);
        em.persist(task2);
        em.persist(task3);
        em.persist(task4);
       // em.persist(project);



        em.getTransaction().commit();       // commit transakcji, zapisanie
        EntityManager ent4 = HibernateUtil.getSessionFactory().createEntityManager();


        List<Employee> employeeList = ent4.createQuery("from Employee")
                //  .setParameter("param1", "Skrzynowska")
                .getResultList();
        employeeList.forEach(System.out::println);

        List<Employee> ludzieTelefon = ent4.createQuery("from Employee e WHERE e.phone.brand = :param", Employee.class)
                .setParameter("param", PhoneBrand.LG)
                .getResultList();
        ludzieTelefon.forEach(System.out::println);
        /* Usuwaunie z tabeli osób, które nie mają żadnego zadania
        DifferentMethod differentMethod = new DifferentMethod();
        differentMethod.deleteEmployeesWithNoTask();

         */
        DifferentMethod differentMethod = new DifferentMethod();
        differentMethod.updateBlockerTask();

       // differentMethod.updateAllEmail();
        differentMethod.updateAllEmail2();

        HibernateUtil.shutdown();           // zamnięcie hibernate
    }
}
