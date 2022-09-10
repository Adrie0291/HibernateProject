package Day1;

import javax.persistence.EntityManager;
import java.util.List;

public class DifferentMethod {

    private static EntityManager emtman = HibernateUtil.getSessionFactory().createEntityManager();


    public void deleteEmployeesWithNoTask() {
        emtman.getTransaction().begin();
        emtman.createQuery("delete from Employee e where e.task is empty ")
                .executeUpdate();
        emtman.getTransaction().commit();

    }

    public void updateBlockerTask() {
        emtman.getTransaction().begin();
        List<Task> blockerTasks = emtman.createQuery("from Task t where t.taskType = :typeParam", Task.class)
                .setParameter("typeParam", TaskType.BLOCKER)
                .getResultList();

        for (Task t : blockerTasks) {
            System.out.println("Przed  zmianą:" + t);
            t.setTitle("BLOCKER!" + t.getTitle());
            System.out.println("Po zmianie: " + t);
            emtman.merge(t);
        }
        emtman.getTransaction().commit();
    }

    public List<Task> lodTaskPage(int pageNr) {
        List<Task> resultList = emtman.createQuery("from Task;", Task.class)
                .setFirstResult(pageNr * 3) // stronnicowanie
                .setMaxResults(3) // stronnicowanie - ile max moze się zaczytać zadań
                .getResultList();

        return resultList;
    }

    /*
    public void updateAllEmail() {


        List<Employee> employesWithout = emtman.createQuery("from Employee e where e.email not like lower " +
                        "(concat(e.firstName, '.', e.familyName, '@my-domain.com'))", Employee.class)
                .getResultList();

        emtman.getTransaction().begin();
        for (Employee g : employesWithout) {
            g.setEmail(g.getFirstName() + "." + g.getFamilyName() + "@my-domain.com");
            emtman.merge(g);
        }
        emtman.getTransaction().commit();
    }

     */
    public void updateAllEmail2() {

        emtman.getTransaction().begin();

        emtman.createQuery("update Employee e " +
                        "set e.email = lower (concat(e.firstName, '.', e.familyName, '@gol-log.com')) " +
                        "where e.email not like lower (concat(e.firstName, '.', e.familyName, '@testtarossa.com'))")
                .executeUpdate();
        emtman.getTransaction().commit();
    }
}


