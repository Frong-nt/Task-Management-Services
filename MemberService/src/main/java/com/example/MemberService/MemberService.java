package com.example.MemberService;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	public List<Member> getAll(){
			return memberRepository.findAll();
	}

	public void addMember(Member member){
		memberRepository.save(member);
	}

	public void deleteMember(int id) {
		memberRepository.deleteById(id);
	}
	
//    public List<Member> search(String firstname){
//        return memberRepository.findByFirstname(firstname);
//    }
    public Member search(String firstname){
        return memberRepository.findByFirstname(firstname);
    }
	
	public Optional<Member> updateMember(int id, Member member) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if(!memberOptional.isPresent()) {
            return memberOptional;
        }   
        return Optional.of(memberRepository.save(member));
    }
}
