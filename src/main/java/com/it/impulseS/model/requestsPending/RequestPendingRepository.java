package com.it.impulseS.model.requestsPending;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestPendingRepository extends JpaRepository<RequestsPending, Long> {
	
	@Query(value = "SELECT r FROM RequestsPending r WHERE r.telephoneNumber =:telephone AND r.activactionCode =:code")
	Optional<RequestsPending> findByTelephoneAndCode(@Param("telephone")String telephone, @Param("code")int code);
	
	@Query(value = "SELECT r FROM RequestsPending r WHERE r.telephoneNumber =:telephone")
	Optional<RequestsPending> findExsistTelephoneNumber(@Param("telephone") String telephone);
	
	Optional<RequestsPending> findByTelephoneNumber(String tn);
	

}
