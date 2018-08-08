package com.ats.feedback.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.feedback.model.transaction.FeedHeader;

public interface FeedHeaderRepo extends JpaRepository<FeedHeader, Integer> {

	FeedHeader findByFbId(int fbId);

	@Transactional
	@Modifying
	@Query("UPDATE FeedHeader SET delStatus=1    WHERE fb_id=:fbId ")
	int deleteFeedHeader(@Param("fbId") int fbId);

	FeedHeader findByCompanyId(int companyId);

	@Query(value = "SELECT d.* FROM t_fb_header d WHERE d.company_id=:companyId AND d.del_status=0 AND d.status=:status", nativeQuery = true)
	List<FeedHeader> getFeedHeaderByCompanyId(@Param("companyId") int companyId, @Param("status") int status);

	@Query(value = "SELECT d.* FROM t_fb_header d WHERE d.del_status=0 AND d.fb_date BETWEEN :fromDate AND :toDate AND d.status=:status AND d.company_id=:companyId AND d.sa_id=:saId", nativeQuery = true)
	List<FeedHeader> getFeedbackList(@Param("companyId") int companyId, @Param("fromDate") String fromDate,
			@Param("toDate") String toDate, @Param("saId") int saId, @Param("status") int status);

	@Query(value = "SELECT d.* FROM t_fb_header d WHERE d.del_status=0 AND d.fb_date BETWEEN :fromDate AND :toDate  AND d.company_id=:companyId AND d.sa_id=:saId", nativeQuery = true)
	List<FeedHeader> getFeedbackListAllStatus(@Param("companyId") int companyId, @Param("fromDate") String fromDate,
			@Param("toDate") String toDate, @Param("saId") int saId);

	@Query(value = "SELECT d.* FROM t_fb_header d WHERE d.del_status=0 AND d.fb_date BETWEEN :fromDate AND :toDate  AND d.company_id=:companyId AND d.sa_id=:saId", nativeQuery = true)
	List<FeedHeader> getFeedbackListAllStatusAndSaId(@Param("companyId") int companyId,
			@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	@Query(value = "SELECT d.* FROM t_fb_header d WHERE d.del_status=0 AND d.fb_date BETWEEN :fromDate AND :toDate AND d.status=:status AND d.company_id=:companyId", nativeQuery = true)
	List<FeedHeader> getFeedbackListAllSaId(@Param("companyId") int companyId, @Param("fromDate") String fromDate,
			@Param("toDate") String toDate, @Param("status") int status);

}
