package Day2;

import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class EmployeeManager {
    private static final SessionFactory sessionFact = HibernateUtil.getSessionFactory();

    public void addObjectToDB(Object... objects) {
        EntityManager entityManager = sessionFact.createEntityManager();

        entityManager.getTransaction().begin();
        for (int i = 0; i < objects.length; i++) {
            entityManager.persist(objects[i]);
        }
        entityManager.getTransaction().commit();
        entityManager.clear();

    }

    public void updateName(int id, String name) {

        EntityManager entityManager = sessionFact.createEntityManager();
        entityManager.getTransaction().begin();
        Employee eFROMDB = entityManager.find(Employee.class, id);
        eFROMDB.setFirstName(name);
        entityManager.merge(eFROMDB);
        System.out.println("Update Name sucessful");
        ;
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    public void EmployeeBySurname(String surname) {
        EntityManager entityManager = sessionFact.createEntityManager();
        List<Employee> employeeSurname = entityManager.createQuery("from Employee where familyName = :param1")
                .setParameter("param1", "Klesecki")
                .getResultList();

        employeeSurname.forEach(System.out::println);
    }

    public void safePhoneByEmployee(Employee employee, Phone phone) {
        EntityManager entityManager = sessionFact.createEntityManager();
        entityManager.getTransaction().begin();
        employee.setPhone(phone);
        entityManager.merge(employee);
        entityManager.getTransaction().commit();


    }

    public void safeTaskToUser(Task task, Employee employee) {
        EntityManager entityManager = sessionFact.createEntityManager();
        entityManager.getTransaction().begin();
        task.setEmployee(employee);
        entityManager.merge(task);
        entityManager.getTransaction().commit();
        entityManager.clear();


    }

    public List<Employee> findPeople() {
        EntityManager entityManager = sessionFact.createEntityManager();
        List<Employee> employeeList = entityManager.createQuery("from Employee")
                .getResultList();
        employeeList.forEach(System.out::println);
        return employeeList;
    }

    public void safeProjectToEmployee(List<Project> project, Employee employee) {
        EntityManager entityManager = sessionFact.createEntityManager();
        entityManager.getTransaction().begin();
        employee.setProjects(project);
        entityManager.merge(employee);
        entityManager.getTransaction().commit();

    }

    public void safeEmployeeToProject(List<Employee> employee, Project project) {
        EntityManager entityManager = sessionFact.createEntityManager();
        entityManager.getTransaction().begin();
        project.setEmployees(employee);
        entityManager.merge(project);
        entityManager.getTransaction().commit();

    }
}



