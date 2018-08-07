package com.ats.feedback.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.feedback.model.master.QueDetail;

public interface QueDetailRepo extends JpaRepository<QueDetail, Integer> {

	List<QueDetail> findByQueNo(int queNo);

}
