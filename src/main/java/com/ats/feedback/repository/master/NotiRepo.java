package com.ats.feedback.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.feedback.model.master.Notification;

public interface NotiRepo extends JpaRepository<Notification, Integer> {

	Notification findByNotiIdAndDelStatus(int notiId, int i);

	@Transactional
	@Modifying
	@Query("UPDATE Notification SET delStatus=1    WHERE noti_id=:notiId ")
	int deleteNotification(@Param("notiId") int notiId);

}
