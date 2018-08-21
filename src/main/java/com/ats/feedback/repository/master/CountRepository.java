package com.ats.feedback.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.feedback.model.master.Count;

public interface CountRepository extends JpaRepository<Count, Integer> {

	@Query(value = "select COALESCE((select count(*) from t_fb_header h where h.status=9 AND h.company_id=:companyId AND h.del_status=0),0) as status1, COALESCE((select count(*) from t_fb_header h where h.status=8  AND h.company_id=:companyId AND h.del_status=0),0) as status2,COALESCE((select count(*) from t_fb_header h where h.status=7 AND h.company_id=:companyId AND h.del_status=0),0) as status3,COALESCE((select count(*) from t_fb_header h where h.status=0  AND h.company_id=:companyId AND h.del_status=0),0) as status4", nativeQuery = true)
	Count getStatusCount(@Param("companyId") int companyId);

}
