package com.ats.feedback.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.feedback.model.transaction.FeedHeader;

public interface FeedHeaderRepo extends JpaRepository<FeedHeader, Integer> {

	FeedHeader findByFbId(int fbId);

}
