package com.ats.feedback.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.feedback.model.master.Screen;

public interface ScreenRepository extends JpaRepository<Screen, Integer> {

	List<Screen> findAll();

	List<Screen> findScreenByCompanyId(int companyId);

	Screen findScreenByScreenId(int screenId);

}
