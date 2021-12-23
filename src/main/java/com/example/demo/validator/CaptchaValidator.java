package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.CaptchaResponse;

@Component
public class CaptchaValidator {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${google.recaptcha.key.secret}")
	private String secret;

	private static final String URL = "https://www.google.com/recaptcha/api/siteverify";

	public boolean isValidCaptcha(String captcha, String ip) {

//		String url = URL + "?secret=" + secret + "&response=" + captcha + "&remoteip=" + ip;
//		CaptchaResponse response = restTemplate.postForObject(url, null, CaptchaResponse.class);

		MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<String, Object>();
		requestBody.add("secret", secret);
		requestBody.add("response", captcha);
		requestBody.add("remoteip", ip);

		CaptchaResponse response = restTemplate.postForObject(URL, requestBody, CaptchaResponse.class);
		System.out.println(response.toString());

		return response.isSuccess();
	}

}
