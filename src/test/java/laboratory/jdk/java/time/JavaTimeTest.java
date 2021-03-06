package laboratory.jdk.java.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaTimeTest {
	private static final Logger logger = LoggerFactory.getLogger(JavaTimeTest.class);

	@Test
	public void test() {
		logger.debug("test: " + String.valueOf(Instant.now()));
		logger.debug("test: " + String.valueOf(LocalDate.now()));
	}

	@Test
	public void getLocalDate() {
		LocalDate a = LocalDate.now();
		logger.debug(String.valueOf(a)); // yyyy-MM-dd

		Assert.assertEquals("2017-12-31", LocalDate.of(2017, Month.DECEMBER, 31).toString());
		Assert.assertEquals("2017-04-10T23:49", LocalDateTime.of(2017, Month.APRIL, 10, 23, 49).toString());
	}

	@Test
	public void getMonthDay() {
		MonthDay a = MonthDay.now();
		logger.debug(String.valueOf(a)); // --MM-dd
	}

	@Test
	public void getYearMonth() {
		YearMonth a = YearMonth.now();
		logger.debug(String.valueOf(a)); // yyyy-MM
	}

	@Test
	public void getOffsetDateTime() {
		Assert.assertEquals("2017-12-31T23:59:59.999999999Z",
				OffsetDateTime.of(LocalDate.of(2017, Month.DECEMBER, 31), LocalTime.MAX, ZoneOffset.UTC).toString());
		Assert.assertEquals("2017-01-14T10:20:30Z", OffsetDateTime.of(2017, 1, 14, 10, 20, 30, 0, ZoneOffset.UTC).toString());
	}

	@Test
	public void getOffsetTime() {
		Assert.assertEquals("22:58+18:00", OffsetTime.of(LocalTime.of(22, 58), ZoneOffset.MAX).toString());
		Assert.assertEquals("22:58-18:00", OffsetTime.of(LocalTime.of(22, 58), ZoneOffset.MIN).toString());
		Assert.assertEquals("22:58Z", OffsetTime.of(LocalTime.of(22, 58), ZoneOffset.UTC).toString());
	}

	@Test
	public void splitDate() {
		LocalDate start = new GregorianCalendar(2016, 2, 5).getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = new GregorianCalendar(2016, 2, 11).getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		long periodDays = ChronoUnit.DAYS.between(start, end);
		List<LocalDate> list = new LinkedList<>();
		for (int i = 0; i < periodDays; i++) {
			list.add(start.plusDays(i));
		}
		Assert.assertEquals("[2016-03-05, 2016-03-06, 2016-03-07, 2016-03-08, 2016-03-09, 2016-03-10]", list.toString());
	}

	@Test
	public void parseFromJavaUtilDate() {
		Calendar input = new GregorianCalendar(2016, 2, 5);
		Date date = input.getTime();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Assert.assertEquals("2016-03-05", localDate.toString());
	}

	@Test
	public void parseToJavaUtilDate() {
		// case#1
		logger.debug("testToJavaUtilDate: " + Date.from(Instant.now()).toString());
		;

		// case#2
		logger.debug("testToJavaUtilDate: " + Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()).toString());

		// case#3
		Date in = new Date();
		LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
		Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		logger.debug("testToJavaUtilDate: " + out.toString());
	}

	@Test
	public void calculateDays() {
		LocalDate targetDay = LocalDate.of(2017, Month.DECEMBER, 31);
		LocalDate today = LocalDate.of(2017, Month.JANUARY, 24);
		Period period = Period.between(today, targetDay);
		long period2 = ChronoUnit.DAYS.between(today, targetDay);
		Assert.assertEquals("0 years, 11 months, 7 days later. (341 days total)", (period.getYears() + " years, " + period.getMonths()
				+ " months, " + period.getDays() + " days later. (" + period2 + " days total)"));
	}
}
