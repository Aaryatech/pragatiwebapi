package com.ats.feedback.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.feedback.model.master.Notification;

public interface NotiRepo extends JpaRepository<Notification, Integer> {

}
