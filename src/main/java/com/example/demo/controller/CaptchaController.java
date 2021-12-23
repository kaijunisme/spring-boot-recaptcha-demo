package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.validator.CaptchaValidator;

@Controller
public class CaptchaController {

	@Autowired
	private CaptchaValidator validator;

	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
	public String captcha() {

		return "captcha";
	}

	@RequestMapping(value = "/captcha", method = RequestMethod.POST)
	public String doCaptcha(
			@RequestParam(value = "email") String email, 
			@RequestParam(value = "password") String password,
			@RequestParam(value = "g-recaptcha-response") String captcha, 
			HttpServletRequest request) {

		String ip = request.getRemoteAddr();
		System.out.println(validator.isValidCaptcha(captcha, ip));

		return "redirect:captcha";
	}

}
