package com.ats.feedback.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.feedback.model.transaction.GetFeedHeader;

public interface GetFeedHeaderRepo extends JpaRepository<GetFeedHeader, Integer> {

	@Query(value = "SELECT d.*,s.sa_name,u.user_name ,rel.user_name AS manager_name FROM t_fb_header d,m_sa s,m_user u ,m_user rel WHERE d.del_status=0 AND s.del_status=0 AND d.sa_id=s.sa_id AND d.rel_man_id=u.user_id AND d.company_id=:companyId AND d.status=:status AND rel.user_id=d.rel_man_id", nativeQuery = true)
	List<GetFeedHeader> getAllFeedHeaderByCompanyId(@Param("companyId") int companyId, @Param("status") int status);

	@Query(value = "SELECT d.*,s.sa_name,u.user_name ,rel.user_name AS manager_name  FROM t_fb_header d ,m_sa s,m_user u,m_user rel WHERE d.del_status=0 AND d.fb_date BETWEEN :fromDate AND :toDate AND d.status=:status AND d.company_id=:companyId AND d.sa_id=:saId  AND d.sa_id=s.sa_id AND d.user_id=u.user_id AND rel.user_id=d.rel_man_id ", nativeQuery = true)
	List<GetFeedHeader> getFeedbackList(@Param("companyId") int companyId, @Param("fromDate") String fromDate,
			@Param("toDate") String toDate, @Param("saId") int saId, @Param("status") int status);

	@Query(value = "SELECT d.*,s.sa_name,u.user_name ,rel.user_name AS manager_name FROM t_fb_header d,m_sa s,m_user u,m_user rel WHERE d.del_status=0 AND d.fb_date BETWEEN :fromDate AND :toDate  AND d.company_id=:companyId AND d.sa_id=:saId  AND d.sa_id=s.sa_id AND d.rel_man_id=u.user_id AND rel.user_id=d.rel_man_id", nativeQuery = true)
	List<GetFeedHeader> getFeedbackListAllStatus(@Param("companyId") int companyId, @Param("fromDate") String fromDate,
			@Param("toDate") String toDate, @Param("saId") int saId);

	@Query(value = "SELECT d.*,s.sa_name,u.user_name ,rel.user_name AS manager_name FROM t_fb_header d,m_sa s,m_user u,m_user rel  WHERE d.del_status=0 AND d.fb_date BETWEEN :fromDate AND :toDate  AND d.company_id=:companyId AND d.sa_id=s.sa_id AND d.rel_man_id=u.user_id AND rel.user_id=d.rel_man_id", nativeQuery = true)
	List<GetFeedHeader> getFeedbackListAllStatusAndSaId(@Param("companyId") int companyId,
			@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	@Query(value = "SELECT d.*,s.sa_name,u.user_name ,rel.user_name AS manager_name FROM t_fb_header d ,m_sa s,m_user u ,m_user rel WHERE d.del_status=0 AND d.fb_date BETWEEN :fromDate AND :toDate AND d.status=:status AND d.company_id=:companyId AND d.sa_id=s.sa_id AND d.rel_man_id=u.user_id AND rel.user_id=d.rel_man_id", nativeQuery = true)
	List<GetFeedHeader> getFeedbackListAllSaId(@Param("companyId") int companyId, @Param("fromDate") String fromDate,
			@Param("toDate") String toDate, @Param("status") int status);

}
