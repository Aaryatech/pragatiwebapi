package com.ats.feedback.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.feedback.model.master.Company;

public interface CompanyRepo extends JpaRepository<Company, Integer> {

	@Transactional
	@Modifying
	@Query("UPDATE Company SET delStatus=0    WHERE company_id=:companyId ")
	int deleteCompany(@Param("companyId") int companyId);

	Company findCompanyByCompanyIdAndDelStatus(int companyId, int i);

	List<Company> findAllByDelStatus(int i);

}
