package org.medmota.demojpaspecificationjointables.repositories;

import org.medmota.demojpaspecificationjointables.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaSpecificationExecutor<Employee>, JpaRepository<Employee, Integer> {
}
