package com.ats.feedback.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.feedback.model.transaction.FeedDetail;

public interface FeedDetailRepo extends JpaRepository<FeedDetail, Integer> {

	List<FeedDetail> findByFbId(int fbId);

}
