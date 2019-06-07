package br.ithappens.auxiliary;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import com.google.common.hash.Hashing;

public class AuxiliaryMethods {

	public String convertStringToSHA512(String texto) {
		return Hashing.sha512().hashString(texto, StandardCharsets.UTF_8).toString();
	}

	@SuppressWarnings("unused")
	public Date getDateToday() {
		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		dt = c.getTime();
		return dt;
	}

	@SuppressWarnings("unused")
	public Date getDateFirstDayLastMonth() {
		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		dt = c.getTime();
		return dt;
	}

	public Date getLastDayPreviousMonth() {

		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.MONTH, -1);
	    c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		dt = c.getTime();
		
		return dt;
	}
	
	public Date getLastDayPreviousMonthConsolidaded() {

		TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
		TimeZone.setDefault(tz);
		Date dt = new Date();
		
		Calendar c = GregorianCalendar.getInstance(tz);
		c.setTime(dt);
		c.add(Calendar.MONTH, -2);
		c.set(Calendar.DAY_OF_MONTH, 1);
		dt = c.getTime();
		
		return dt;
	}
	
	public Date getLastTwelveMonth() {

		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.MONTH, -12);
		c.set(Calendar.DAY_OF_MONTH, 1);
		dt = c.getTime();
		
		return dt;
	}
	
	public Date getLastSixMonth() {

		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.MONTH, -6);
		c.set(Calendar.DAY_OF_MONTH, 1);
		dt = c.getTime();
		
		return dt;
	}
	
	public Date getLastThreeMonth() {

		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.MONTH, -3);
		c.set(Calendar.DAY_OF_MONTH, 1);
		dt = c.getTime();
		
		return dt;
	}
	
	public Date convertStringToDateWithHoursMinutes(String day, String month, String year, String hour, String minute) {

		TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
		TimeZone.setDefault(tz);
		Calendar ca = GregorianCalendar.getInstance(tz);
		ca.set(Calendar.DATE, Integer.parseInt(day.replace("\"", "")));
		ca.set(Calendar.MONTH, Integer.parseInt(month.replace("\"", "")) - 1);
		ca.set(Calendar.YEAR, Integer.parseInt(year.replace("\"", "")));
		ca.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour.replace("\"", "")));
		ca.set(Calendar.MINUTE, Integer.parseInt(minute.replace("\"", "")));
		ca.set(Calendar.SECOND, 0);
		ca.set(Calendar.MILLISECOND, 0);

		/*int year1 = ca.get(Calendar.YEAR);
		int month1 = ca.get(Calendar.MONTH) + 1; // Note: zero based!
		int day1 = ca.get(Calendar.DAY_OF_MONTH);
		int hour1 = ca.get(Calendar.HOUR_OF_DAY);
		int minute1 = ca.get(Calendar.MINUTE);
		int second = ca.get(Calendar.SECOND);
		int millis = ca.get(Calendar.MILLISECOND);*/

		return ca.getTime();
	}

	// padrao da string: dd/mm/yyyy
	public Date convertStringToDate(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
		TimeZone.setDefault(tz);
		Calendar ca = GregorianCalendar.getInstance(tz);

		ca.setTime(date);
		return ca.getTime();
	}

	@SuppressWarnings("unused")
	private Date getLastDayOfQuarter(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) / 3 * 3 + 2);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	@SuppressWarnings("unused")
	private Date getFirstDayOfQuarter(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) / 3 * 3);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

}
