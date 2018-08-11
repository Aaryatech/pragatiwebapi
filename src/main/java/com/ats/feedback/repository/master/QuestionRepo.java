package com.ats.feedback.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.feedback.model.master.Question;

public interface QuestionRepo extends JpaRepository<Question, Integer> {

	Question findByQueNo(int queNo);

	@Transactional
	@Modifying
	@Query("UPDATE Question SET delStatus=0    WHERE que_no=:queNo ")
	int deleteQue(@Param("queNo") int queNo);

	Question findByCompanyId(int companyId);

	List<Question> findAllByCompanyId(int companyId);

	List<Question> findByCompanyIdAndDelStatus(int companyId, int i);

	@Query(value = "SELECT * FROM m_question WHERE m_question.que_no IN(:queNo) AND m_question.del_status=0", nativeQuery = true)
	List<Question> getQuestionScreenList(@Param("queNo") List<String> dataList);

}
