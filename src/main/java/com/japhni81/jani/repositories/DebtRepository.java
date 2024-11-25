package com.japhni81.jani.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.japhni81.jani.models.Debt;

public interface DebtRepository extends JpaRepository<Debt, Long>{
	
	@Query(" SELECT dt FROM Debt dt "
			+ "WHERE ( ((dt.debtDate >= :startDate) AND (dt.debtDate <= :endDate)) "
			+ "AND (dt.requester_id= :id) )")
    List<Debt> findRequesterDebtHistoryByDates(Long id, Date startDate, Date endDate);
	
	@Query("SELECT d FROM Debt d WHERE d.requester_id= :id")
    List<Debt> findDebtHistoryByRequesterId(Long id);

}
