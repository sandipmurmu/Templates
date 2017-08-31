package com.api.rest.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	private DateUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static String addDays(Long days, String date) {
		LocalDate localDate = LocalDate.parse(date);
		LocalDate newDate = localDate.plusDays(days);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String newDateStr = newDate.format(formatter);
		return newDateStr;
	}
}
