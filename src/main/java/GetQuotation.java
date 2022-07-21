package main.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.common.PropertyLoader;
import main.java.model.Quotation;

public class GetQuotation {
	// Rs 90 / kg
	static String regex1 = "Rs (\\d+)\\s*/\\s*[Kk]g";
	static String regex2 = "Rs (\\d+)\\s*/\\s*[Kk]ilogram";
	static Pattern pattern1 = Pattern.compile(regex1);
	static Pattern pattern2 = Pattern.compile(regex2);
	static Logger log = LoggerFactory.getLogger(GetQuotation.class);

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		double sum = 0.0;
		int n = 0;
		for (int i = 1; i <= 10; i++) {
			String urlName = "url" + i;
			String urlStr = PropertyLoader.getInstance().getProperty(urlName);

			String priceStr = process(urlStr);
			try {
				Integer price = Integer.parseInt(priceStr);
				if (price != null) {

					sb.append(price).append(' ');

					sum += price;
					n++;
				}
			} catch (NumberFormatException e) {
				log.error("not a number err: " + priceStr);
			}
		}
		double avg = sum / n;
		String quoteStr = sb.toString().trim();

		Session session = HibernateUtil.getSessionFactory().withOptions().jdbcTimeZone(TimeZone.getTimeZone("HKT"))
				.openSession();
		session.beginTransaction();

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 8);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date curTime = cal.getTime();

		String hql = "FROM main.java.model.Quotation q WHERE q.date = ?1";
		Query q = session.createQuery(hql).setParameter(1, curTime);

		@SuppressWarnings("unchecked")
		List<Quotation> rs = q.getResultList();
		if (rs != null && !rs.isEmpty()) {
			Quotation quo = rs.get(0);
			quo.setN(n);
			quo.setAvg(avg);
			quo.setQuote(quoteStr);
			session.saveOrUpdate(quo);
			log.info("updated " + quo.toString());
		}

		else {
			Quotation quote = new Quotation(n, avg, sb.toString().trim());
			session.persist(quote);
			log.info("inserted " + quote);
		}

		session.getTransaction().commit();
	}

	private static String process(String urlStr) {
		String content = getUrlContent(urlStr);
		String title = StringUtils.substringBetween(content, "<title>", "</title>");
		log.info(title);

		// Rs 100 /kilogram
		Matcher m = pattern1.matcher(title);
		if (m.find()) {
			String priceStr = m.group(1);
			log.info("price = " + priceStr);
			return priceStr;
		}

		else {
			Matcher m2 = pattern2.matcher(title);
			if (m2.find()) {
				String priceStr = m2.group(1);
				log.info("price = " + priceStr);
				return priceStr;
			}
		}

		return null;
	}

	private static String getUrlContent(String urlStr) {
		StringBuilder content = new StringBuilder();
		// Use try and catch to avoid the exceptions
		try {
			URL url = new URL(urlStr); // creating a url object
			URLConnection urlConnection = url.openConnection(); // creating a urlconnection object

			// wrapping the urlconnection in a bufferedreader
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line;
			// reading from the urlconnection using the bufferedreader
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line + "\n");
			}
			bufferedReader.close();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return content.toString();
	}

}
