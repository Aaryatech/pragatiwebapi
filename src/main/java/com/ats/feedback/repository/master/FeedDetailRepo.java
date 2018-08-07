package com.ats.feedback.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.feedback.model.transaction.FeedDetail;

public interface FeedDetailRepo extends JpaRepository<FeedDetail, Integer> {

	List<FeedDetail> findByFbId(int fbId);

	@Transactional
	@Modifying
	@Query("UPDATE FeedDetail SET delStatus=1    WHERE fb_detail_id=:fbDetailId ")
	int deleteFeedDetail(@Param("fbDetailId") int fbDetailId);

}
