package com.greendeck.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greendeck.demo.entity.UserFollowers;
// FOR /getfollowers/
@Repository
public interface UserFollowerRepository extends JpaRepository<UserFollowers, Long>{
@Query (nativeQuery = true,value="select follower_Id from user_followers where user_Id=:userId")
	List<Long> getFollowers(Long userId);
}
