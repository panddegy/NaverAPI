package com.biz.naver.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.naver.service.NaverService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NaverController {

	@Autowired
	NaverService nService;
	
	private Log logger=LogFactory.getLog(this.getClass());
	
	@RequestMapping(value="naver", method=RequestMethod.POST)
	public String naver(@RequestParam String search, @RequestParam String cate, Model model) {
		
		JSONArray ja=nService.getObject(nService.getString(cate, search));
		model.addAttribute("NAVER", ja);
		
		return "home";
	}
	
	@ResponseBody
	@RequestMapping(value="naver.json", method=RequestMethod.POST, produces="application/json; charset=utf8")
	public String naver_json(@RequestParam String search, @RequestParam String cate, Model model) {
		
		String res=nService.getString(cate, search);
		
		return res;
	}
}











