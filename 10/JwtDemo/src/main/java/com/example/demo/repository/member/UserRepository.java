package com.example.demo.repository.member;


import com.example.demo.entity.member.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */
public interface UserRepository extends JpaRepository<User,Long> {
	User findByName(String name);
	User findByMobile(String mobile);
	User findByEmail(String email);

	/**
	 * 根据id集合查询用户，分页查询
	 *
	 * @param ids
	 * @return
	 */
	Page<User> findByIdIn(List<Integer> ids, Pageable pageable);
	/**
	 * 根据id集合查询用户，不分页
	 *
	 * @param ids
	 * @return
	 */
	List<User> findByIdIn(List<Integer> ids);
	@Modifying(clearAutomatically=true)
	@Transactional
	@Query("update User set outDate=:outDate, validataCode=:validataCode where email=:email")
	int setOutDateAndValidataCode(@Param("outDate") String outDate, @Param("validataCode") String validataCode, @Param("email") String email);

	@Modifying(clearAutomatically=true)
	@Transactional
	@Query("update User set active=:active where email=:email")
	int setActive(@Param("active") Integer active, @Param("email") String email);


}
