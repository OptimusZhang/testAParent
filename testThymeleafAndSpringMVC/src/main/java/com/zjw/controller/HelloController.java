package com.zjw.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sample")
public class HelloController {
	
	@RequestMapping(value="/hello", method=RequestMethod.POST)
	public ModelAndView hello(HttpServletRequest request, HttpServletResponse response) {
		
		String jName = request.getParameter("jName");
		String eName = request.getParameter("eName");
		ModelAndView mv = new ModelAndView();
		mv.addObject("spring", "spring mvc");
		mv.addObject("jName", jName);
		mv.addObject("eName", eName);
		mv.setViewName("hello");
		
		return mv;
	}
}
