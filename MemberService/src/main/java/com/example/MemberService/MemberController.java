package com.example.MemberService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RestController
public class MemberController {

	@Autowired
	MemberService memberService;

//	@RequestMapping(value = "/member/add", method = RequestMethod.POST)
//	public void addMember(@RequestBody Member member) {
//		memberService.addMember(member);
//	}
//
//	@RequestMapping(value = "/member/update/{id}", method = RequestMethod.POST)
//	public ResponseEntity<?> memberUpdate(@PathVariable int id, @Valid @RequestBody Member member) {
//		Optional<Member> user = memberService.updateMember(id, member);
//		if (!user.isPresent()) {
//			return ResponseEntity.notFound().build();
//		}
//		return ResponseEntity.ok().build();
//	}
//
//	@RequestMapping(value = "/member/delete/{id}", method = RequestMethod.POST)
//	public void deleteMember(@PathVariable int id) {
//		memberService.deleteMember(id);
//	}
//
//	// list member type json but cannot use because use Controller
//	@RequestMapping(value = "/members")
//	public List<Member> getmembers() {
//		return memberService.getAll();
//	}

	// list member form templete
	@GetMapping("/")
	public String Main(Model model) {
		List<Member> lst = memberService.getAll();
		model.addAttribute("Members", lst);
		return "index";
	}


//	@RequestMapping("/member/search")
//	public String search() {
//		try {
//			String name = "Kaya";
//			return memberService.search(name).getFirstname();
//		} catch (Exception e) {
//			return "THIS IS MEMBER IS NOT FOUND";
//		}
//		
//
//	}

	// add member form template
	@RequestMapping("/new")
	public String newMember(Model model) {
		Member member = new Member();
		model.addAttribute("member", member);
		return "new_member";
	}

	@RequestMapping(value = "/index")
	public String FirstPage(Model model) {
//		เป็นโครงไว้เชื่อมกับตัว login ของเพื่อน โดยการดึง ข้อมูลมาแล้วดูว่า คนนี้เคยใช้ service ไหม ถ้าไม่เคยจะทำการสร้าง Member ใหม่ขึ้นมา
		String name = "pimlapat";
		try {
			if (memberService.search(name) == null) {
				Member member = new Member();
				model.addAttribute("member", member);
				return "new_member";
			} else {
				String lst = memberService.search(name).getFirstname();
				model.addAttribute("Members", lst);
				return "index";
			}
		} catch (Exception e) {
			return "ERROR";
		}

//		String role = "pim";// check role in login user
//		if (role == "kazuya") {
//			List<Member> lst = memberService.getAll();
//			model.addAttribute("Members", lst);
//			return "index";
//		} else {
//			Member member = new Member();
//			model.addAttribute("member", member);
//			return "new_member";
//		}
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveMember(@ModelAttribute("member") Member member) {
		memberService.addMember(member);
		return "redirect:/";
	}
}
