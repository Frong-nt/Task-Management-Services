package com.example.MemberService;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends MongoRepository<Member, Integer> {

//	List<Member> findByFirstname(String name);
	Member findByFirstname(String name);
}

