package com.yoga.atm.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TransferSummaryController {

	@Value("${app.unknown.error}")
	String somethingWrongMessage;

	@RequestMapping(value = "/transferSummary", method = RequestMethod.GET)
	public ModelAndView transferSummary(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		ModelAndView view = new ModelAndView();
		try {
			view.setViewName("transfer/summary");
		} catch (Exception e) {
			e.printStackTrace();
			SecurityContextHolder.getContext().setAuthentication(null);
			view = new ModelAndView("redirect:/");
			redirectAttributes.addFlashAttribute("message", somethingWrongMessage);
		}
		return view;
	}

}
