package com.example.MemberService;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberServiceRestController {
	@Autowired
	MemberService memberService;

	@RequestMapping(value = "/member/add", method = RequestMethod.POST)
	public void addMember(@RequestBody Member member) {
		memberService.addMember(member);
	}
	
  @RequestMapping("/member/{firstname}")
  public Member searchName(@PathVariable String firstname){    
      return memberService.search(firstname);
  }

	@RequestMapping(value = "/member/update/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> memberUpdate(@PathVariable int id, @Valid @RequestBody Member member) {
		Optional<Member> user = memberService.updateMember(id, member);
		if (!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}

	@RequestMapping(value = "/member/delete/{id}", method = RequestMethod.POST)
	public void deleteMember(@PathVariable int id) {
		memberService.deleteMember(id);
	}

	// list member type json but cannot use because use Controller
	@RequestMapping(value = "/members")
	public List<Member> getmembers() {
		return memberService.getAll();
	}

}
