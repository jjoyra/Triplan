package com.triplan.member.controller;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.triplan.member.model.MemberDto;
import com.triplan.member.model.service.MemberService;

@Controller
@RequestMapping("/member")
public class LoginController {

	private MemberService memberService;

	public LoginController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}
	
//	@GetMapping("/")
//	public String index() {
//		return "index";
//	}
//	
//	@GetMapping("/login")
//	public String login() {
//		return "member/login";
//	}

	// 로그인
	@PostMapping("/login")
	public String login(@RequestParam Map<String, String> map,
			@RequestParam(name = "saveid", required = false) String saveid, Model model, HttpSession session,
			HttpServletResponse response) {
		try {
			MemberDto memberDto = memberService.loginMember(map);
			if (memberDto != null) {
				session.setAttribute("userinfo", memberDto);

				Cookie cookie = new Cookie("ssafy_id", map.get("memberId"));
				cookie.setPath("/");
				if ("ok".equals(saveid)) {
					cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
				} else {
					cookie.setMaxAge(0);
				}
				response.addCookie(cookie);
				return "redirect:/";
			} else {
				model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
				return "index";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "index";
		}
	}

	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) { 
		session.invalidate();
		return "redirect:/";
	}


}
