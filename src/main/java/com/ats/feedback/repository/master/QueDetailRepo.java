package com.ats.feedback.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.feedback.model.master.QueDetail;

public interface QueDetailRepo extends JpaRepository<QueDetail, Integer> {

	List<QueDetail> findByQueNo(int queNo);

	List<QueDetail> findByCompanyId(int companyId);

	@Transactional
	@Modifying
	@Query("UPDATE QueDetail SET delStatus=0    WHERE que_detail_id=:queDetailId ")
	int deleteQueDetail(@Param("queDetailId") int queDetailId);

}
