package com.ats.feedback.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.feedback.model.transaction.GetFeedHeader;

public interface GetFeedHeaderRepo extends JpaRepository<GetFeedHeader, Integer> {

	@Query(value = "SELECT d.*,s.sa_name,u.user_name FROM t_fb_header d,m_sa s,m_user u WHERE d.del_status=0 AND s.del_status=0 AND d.sa_id=s.sa_id AND d.rel_man_id=u.user_id AND d.company_id=:companyId AND d.status=:status", nativeQuery = true)
	List<GetFeedHeader> getAllFeedHeaderByCompanyId(@Param("companyId") int companyId, @Param("status") int status);

}
