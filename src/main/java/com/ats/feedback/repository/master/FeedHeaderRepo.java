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

	@Transactional
	@Modifying
	@Query("UPDATE FeedHeader SET gm_remark=:gmRemark,status=:status    WHERE fb_id=:fbId ")
	int updateFeedHeader(@Param("fbId") int fbId, @Param("gmRemark") String gmRemark, @Param("status") int status);

	@Transactional
	@Modifying
	@Query("UPDATE FeedHeader SET rel_man_id=:relManId, pro_found=:proFound,action_taken=:actionTaken,status=:status   WHERE fb_id=:fbId ")
	int updateFeedHeader1(@Param("fbId") int fbId, @Param("relManId") int relManId, @Param("proFound") String proFound,
			@Param("actionTaken") String actionTaken, @Param("status") int status);

}
