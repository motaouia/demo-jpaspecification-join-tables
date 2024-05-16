package org.medmota.demojpaspecificationjointables;

import org.medmota.demojpaspecificationjointables.entities.Employee;
import org.medmota.demojpaspecificationjointables.entities.Hobby;
import org.medmota.demojpaspecificationjointables.repositories.EmployeeRepository;
import org.medmota.demojpaspecificationjointables.repositories.specif2.*;
import org.medmota.demojpaspecificationjointables.service.EmployeeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



@SpringBootApplication
public class DemoJpaspecificationJoinTablesApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoJpaspecificationJoinTablesApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(EmployeeRepository employeeRepository, EmployeeService empService, EmployeeService employeeService) {
        return (args) -> {

            Hobby hobby1 = new Hobby("Cricket");
            Hobby hobby2 = new Hobby("Blogging");
            Hobby hobby3 = new Hobby("Football");
            Hobby hobby4 = new Hobby("Tennis");
            Hobby hobby5 = new Hobby("Blogging");
            Hobby hobby6 = new Hobby("Cricket");
            Hobby hobby7 = new Hobby("Blogging");
            Hobby hobby8 = new Hobby("Cricket");


            Set<Hobby> hobbies1 = new HashSet<>();
            hobbies1.add(hobby1);
            hobbies1.add(hobby2);

            Set<Hobby> hobbies2 = new HashSet<>();
            hobbies2.add(hobby3);
            hobbies2.add(hobby4);

            Set<Hobby> hobbies3 = new HashSet<>();
            hobbies3.add(hobby5);
            hobbies3.add(hobby6);

            Set<Hobby> hobbies4 = new HashSet<>();
            hobbies4.add(hobby7);
            hobbies4.add(hobby8);

            Employee emp1 = new Employee("Ram", "Gurram", 32, hobbies1);
            Employee emp2 = new Employee("Gopi", "Battu", 30, hobbies2);
            Employee emp3 = new Employee("Surendra", "Sami", 32, hobbies3);
            Employee emp4 = new Employee("Sai", "Praneet", 30, hobbies4);
            Employee emp5 = new Employee("Sailu", "PTR", 31, null);

            empService.save(emp1);
            empService.save(emp2);
            empService.save(emp3);
            empService.save(emp4);
            empService.save(emp5);

            for (Employee emp : employeeRepository.findAll()) {
                System.out.println(emp);
            }

            SortOrder sortOrder = new SortOrder();
            sortOrder.setAscendingOrder(List.of("firstName"));
            sortOrder.setDescendingOrder(List.of("lastName"));

            JoinColumnProps joinColumnProps = new JoinColumnProps();
            joinColumnProps.setJoinColumnName("hobbies");
            joinColumnProps.setSearchCriteria(new SearchCriteria("hobby", hobby3.getHobby(), SearchOperation.EQUAL));

            SearchQuery searchQuery = new SearchQuery();
            searchQuery.setSortOrder(sortOrder);
            searchQuery.setJoinColumnProps(List.of(joinColumnProps));
            searchQuery.setPage(0);
            searchQuery.setSize(5);
            searchQuery.setSearchCriteria(List.of(new SearchCriteria("firstName", emp2.getFirstName().toUpperCase(), SearchOperation.LIKE)));
            List<Employee> findEmployeesByHobby = employeeService.findAll(searchQuery);

            if(findEmployeesByHobby != null && !findEmployeesByHobby.isEmpty()){
                findEmployeesByHobby.forEach(emp -> {
                    System.out.println(emp);
                });
            }
            System.out.println("***2***");

        };
    }

}
