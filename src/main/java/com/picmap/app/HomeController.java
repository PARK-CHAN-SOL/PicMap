package com.picmap.app;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.picmap.app.travel.TravelController;
import com.picmap.app.travel.TravelDTO;
import com.picmap.app.travel.TravelService;
import com.picmap.app.util.Scroller;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private TravelService travelService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, Scroller scroller) throws Exception{
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
		

		List<TravelDTO> bestList = travelService.bestList();
		model.addAttribute("bestList", bestList);
		
		List<String> li = Arrays.asList(new String[]{"서울", "제주", "부산", "경주", "강릉", "전주", "여수", "속초"});
		int i = 1;
		for(String e : li) {
			scroller.setSearch(e);
			Long postCount = travelService.getPostCount(scroller);
			model.addAttribute("tourList"+i, postCount);
			i++;
		}
		

		return "index";
	}
	
}
