package com.yoga.atm.app.controller;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yoga.atm.app.model.Account;

@Controller
public class TransactionController {

	@Value("${app.unknown.error}")
	String somethingWrongMessage;

	@RequestMapping(value = "/transaction", method = RequestMethod.GET)
	public ModelAndView inputAccountNumber(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		ModelAndView view = new ModelAndView();
		try {
			Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			DecimalFormat formatter = new DecimalFormat("#,###.00");
			view.addObject("accountNumber", account.getAccountNumber());
			view.addObject("balance", formatter.format(account.getBalance()));
			view.setViewName("transaction/index");
		} catch (Exception e) {
			e.printStackTrace();
			SecurityContextHolder.getContext().setAuthentication(null);
			view = new ModelAndView("redirect:/");
			redirectAttributes.addFlashAttribute("message", somethingWrongMessage);
		}
		return view;
	}
}
