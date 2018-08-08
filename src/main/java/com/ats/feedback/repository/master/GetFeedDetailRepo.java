package com.ats.feedback.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.feedback.model.transaction.GetFeedDetail;

public interface GetFeedDetailRepo extends JpaRepository<GetFeedDetail, Integer> {

	@Query(value = "SELECT d.*,q.que_text FROM t_fb_detail d,m_question q WHERE d.del_status=0 AND d.que_no=q.que_no AND d.fb_id=:fbId", nativeQuery = true)
	List<GetFeedDetail> getDetailByQueNo(@Param("fbId") int fbId);

}
