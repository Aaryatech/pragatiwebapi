package com.ats.feedback.repository.master;

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

}
