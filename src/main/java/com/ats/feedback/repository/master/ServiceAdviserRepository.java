package com.ats.feedback.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.feedback.model.master.ServiceAdviser;

public interface ServiceAdviserRepository extends JpaRepository<ServiceAdviser, Integer> {

	@Transactional
	@Modifying
	@Query("UPDATE ServiceAdviser SET delStatus=0    WHERE sa_id=:saId ")
	int deleteServiceAdviser(@Param("saId") int saId);

	ServiceAdviser findServiceAdviserBySaIdAndDelStatus(int saId, int i);

	List<ServiceAdviser> findAllByDelStatus(int i);

	List<ServiceAdviser> findServiceAdviserByCompanyIdAndDelStatus(int companyId, int i);

}
