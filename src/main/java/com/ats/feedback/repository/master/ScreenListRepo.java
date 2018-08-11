package com.ats.feedback.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.feedback.model.master.ScreenList;

public interface ScreenListRepo extends JpaRepository<ScreenList, Integer> {

	@Query(value = "SELECT * FROM m_screen WHERE m_screen.company_id=:companyId", nativeQuery = true)
	List<ScreenList> getScListAllCompanyId(@Param("companyId") int companyId);

}
