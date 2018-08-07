package com.ats.feedback.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.feedback.model.master.Customer;
import com.ats.feedback.model.master.Screen;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Transactional
	@Modifying
	@Query("UPDATE Customer SET delStatus=1    WHERE cust_id=:custId ")
	int deleteCustomer(@Param("custId") int custId);

	List<Customer> findAllByDelStatus(int i);

	Customer findCustomerByCustIdAndDelStatus(int custId, int i);

	List<Customer> findCustomerByCompanyIdAndDelStatus(int companyId, int i);

}
