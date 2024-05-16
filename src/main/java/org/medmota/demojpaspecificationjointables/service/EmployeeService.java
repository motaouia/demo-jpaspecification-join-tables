package org.medmota.demojpaspecificationjointables.service;

import jakarta.transaction.Transactional;
import org.medmota.demojpaspecificationjointables.entities.Employee;
import org.medmota.demojpaspecificationjointables.repositories.EmployeeRepository;
import org.medmota.demojpaspecificationjointables.repositories.specif2.GenericSpecificationUtil;
import org.medmota.demojpaspecificationjointables.repositories.specif2.SearchQuery;
import org.medmota.demojpaspecificationjointables.repositories.specif2.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll(SearchQuery searchQuery) {

        Specification<Employee> spec = GenericSpecificationUtil.bySearchQuery(searchQuery, Employee.class);
        PageRequest pageRequest = getPageRequest(searchQuery);

        Page<Employee> page = employeeRepository.findAll(spec, pageRequest);

        return page.getContent();
    }

    private PageRequest getPageRequest(SearchQuery searchQuery) {

        int pageNumber = searchQuery.getPage();
        int pageSize = searchQuery.getSize();

        List<Sort.Order> orders = new ArrayList<>();

        SortOrder sortOder = searchQuery.getSortOrder();
        if(sortOder != null){
            List<String> ascProps = searchQuery.getSortOrder().getAscendingOrder();

            if (ascProps != null && !ascProps.isEmpty()) {
                for (String prop : ascProps) {
                    orders.add(Sort.Order.asc(prop));
                }
            }

            List<String> descProps = searchQuery.getSortOrder().getDescendingOrder();

            if (descProps != null && !descProps.isEmpty()) {
                for (String prop : descProps) {
                    orders.add(Sort.Order.desc(prop));
                }

            }

            Sort sort = Sort.by(orders);
            return PageRequest.of(pageNumber, pageSize, sort);
        }


       // return PageRequest.of(pageNumber, pageSize, sort);
        return PageRequest.of(pageNumber, pageSize);

    }

    @Transactional
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }
}
