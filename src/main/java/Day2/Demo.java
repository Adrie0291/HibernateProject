package Day2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        EmployeeManager empManager = new EmployeeManager();
        Employee emp1 = new Employee("Mikolaj", "Klesecki", LocalDate.of(1900, 12, 10), "mikol@gmail.com");
        Employee emp2 = new Employee("Katie", "McKenzie", LocalDate.of(2000, 11, 25), "katie@nostern-lod.com");
        Employee emp3 = new Employee("Niko", "Parov", LocalDate.of(2003, 7, 9), "nik@wp.pl");
        Employee emp4 = new Employee("Marianna", "Lubiecka", LocalDate.of(1987, 1, 14), "mari@gmail.com");
        Employee emp5 = new Employee("John", "Klesecki", LocalDate.of(1999, 3, 1), "john@aoc.com");

        Phone phone1 = new Phone(PhoneBrand.LG, "393291929");
        Phone phone2 = new Phone(PhoneBrand.APPLE, "788291932");
        Phone phone3 = new Phone(PhoneBrand.LG, "604291293");
        Phone phone4 = new Phone(PhoneBrand.ASUS, "999333222");
        Phone phone5 = new Phone(PhoneBrand.XIAOMI, "134444333");

        empManager.addObjectToDB(phone1, phone2, phone3, phone4, phone5);
        empManager.addObjectToDB(emp1, emp2, emp3, emp4, emp5);


        empManager.updateName(1, "Wladyslaw");

        empManager.EmployeeBySurname("Klesecki");

        empManager.safePhoneByEmployee(emp3, phone5);
        empManager.safePhoneByEmployee(emp2, phone1);

        System.out.println("Employee 2:" + emp2);
        System.out.println("Employee 3:" + emp3);

        Task task1 = new Task("CREATE", "create new folder", TaskType.BLOCKER);
        Task task2 = new Task("CHANGE", "change name methods", TaskType.HIGH_PRIORITY);

        empManager.safeTaskToUser(task1, emp4);
        empManager.safeTaskToUser(task2, emp4);
        System.out.println(emp4);

        List<Employee> people = empManager.findPeople();

        // MANY TO MANY
        Project project1 = new Project("NEW WORLD 2100");
        Project project2 = new Project("HISTORY OF USA");
        Project project3 = new Project("NEW MICROWAVE");
        Project project4 = new Project("SUMMER CAMP BARCELONA");
        empManager.addObjectToDB(project1, project2, project3, project4);

        empManager.safeProjectToEmployee(List.of(project1, project2, project3), emp1);
        empManager.safeProjectToEmployee(List.of(project1, project4), emp3);
        empManager.safeProjectToEmployee(List.of(project4, project2), emp5);


    }
}
