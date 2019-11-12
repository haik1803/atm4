package com.yoga.atm.app.controller;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yoga.atm.app.model.Account;
import com.yoga.atm.app.model.Transaction;
import com.yoga.atm.app.response.DatatableResponse;
import com.yoga.atm.app.service.TransactionService;

@Controller
public class ViewTransactionController {

	@Autowired
	private TransactionService transactionService;

	@Value("${app.unknown.error}")
	String somethingWrongMessage;

	@RequestMapping(value = "/viewTransaction", method = RequestMethod.GET)
	public ModelAndView inputAccountNumber(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		ModelAndView view = new ModelAndView();
		try {
			view.setViewName("view/transaction");
		} catch (Exception e) {
			e.printStackTrace();
			SecurityContextHolder.getContext().setAuthentication(null);
			view = new ModelAndView("redirect:/");
			redirectAttributes.addFlashAttribute("message", somethingWrongMessage);
		}
		return view;
	}

	@RequestMapping(value = "/getDataTransaction", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public DatatableResponse getDataCommunity(HttpServletRequest request,
			@RequestParam(value = "draw", required = true) long draw,
			@RequestParam(value = "length", required = true) int length,
			@RequestParam(value = "start", required = true) int start) {
		DatatableResponse response = new DatatableResponse();
		try {
			Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			response.setDraw(draw);
			Long countTransaction = transactionService.countByAccountNumber(account.getAccountNumber());
			response.setRecordsFiltered(countTransaction);
			response.setRecordsTotal(countTransaction);
			Pageable paging = PageRequest.of(start / length, length, Sort.by("date").descending());
			List<Transaction> listTransaction = transactionService.findAllByAccountNumber(account.getAccountNumber(),
					paging);
			List<List<String>> rows = new ArrayList<List<String>>();
			List<String> row = null;
			DecimalFormat formatter = new DecimalFormat("#,###.00");
			for (Transaction transaction : listTransaction) {
				row = new ArrayList<String>();
				row.add(String.valueOf(transaction.getId()));
				row.add(transaction.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a")));
				row.add(transaction.getType().toString());
				row.add(formatter.format(transaction.getAmount()));
				if (transaction.getDestinationAccount() != null)
					row.add(transaction.getDestinationAccount().getAccountNumber());
				else
					row.add(null);
				row.add(transaction.getReference());
				rows.add(row);
			}
			response.setData(rows);
		} catch (Exception e) {
			e.printStackTrace();
			response.setDraw(draw);
			response.setRecordsFiltered(0L);
			response.setRecordsTotal(0L);
			response.setData(new ArrayList<List<String>>());
		}
		return response;
	}
}
