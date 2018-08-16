package com.ats.feedback.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.feedback.model.master.User;
import com.ats.feedback.model.transaction.GetFeedHeader;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findAllByDelStatus(int i);

	@Transactional
	@Modifying
	@Query("UPDATE User SET delStatus=1    WHERE user_id=:userId ")
	int deleteUser(@Param("userId") int userId);

	User findUserByUserIdAndDelStatus(int userId, int i);

	List<User> findUserByCompanyIdAndDelStatus(int companyId, int i);

	User findByUserMobileAndPasswordAndDelStatus(String userMobile, String password, int i);

	@Query(value = "SELECT m_user.* FROM m_user WHERE m_user.user_type=:userType AND m_user.del_status=0", nativeQuery = true)
	List<User> findTokenByUserTypeIdAndDelStatus(@Param("userType") int userType);
}
