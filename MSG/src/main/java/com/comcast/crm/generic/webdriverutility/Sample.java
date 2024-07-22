package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Sample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,30);
		String reqDate = sim.format(cal.getTime());
		System.out.println(reqDate);
	}

}
