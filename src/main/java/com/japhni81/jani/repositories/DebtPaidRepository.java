package com.japhni81.jani.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.japhni81.jani.models.DebtPaid;

public interface DebtPaidRepository extends JpaRepository<DebtPaid, Long>{
	
	@Query(" SELECT dp FROM DebtPaid dp "
			+ "WHERE (((dp.paidDate >= :startDate) AND (dp.paidDate <= :endDate)) "
			+ "AND (dp.refunder_id= :id))")
    List<DebtPaid> findRefunderDebtHistoryByDates(Long id, Date startDate, Date endDate);
	
	
	@Query("SELECT dp FROM DebtPaid dp WHERE dp.refunder_id= :id")
    List<DebtPaid> findDebtHistoryByRefunderId(Long id);

}
