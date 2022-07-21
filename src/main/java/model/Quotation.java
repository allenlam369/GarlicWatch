package main.java.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "quotation")
public class Quotation implements Serializable {

	private static final long serialVersionUID = -7823625801508371311L;

//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//	@GenericGenerator(name = "native", strategy = "native")

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", nullable = false)
	private Date date;

	@Column(name = "n")
	private int n;

	@Column(name = "avg")
	private double avg;

	@Column(name = "quote")
	private String quote;

	public Quotation() {
		// needed by hibernate
	}

	public Quotation(int n, double avg, String quote) {
		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.HOUR_OF_DAY, 8);
//		cal.set(Calendar.MINUTE, 0);
//		cal.set(Calendar.SECOND, 0);
//		cal.set(Calendar.MILLISECOND, 0);
//		this.date = new Timestamp(cal.getTimeInMillis());
		this.date = cal.getTime();

//		this.date = new Date();
		System.err.println("Date " + date);

		this.n = n;
		this.avg = avg;
		this.quote = quote;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	@Override
	public String toString() {
		return "Quotation [id=" + id + ", date=" + date + ", n=" + n + ", avg=" + avg + ", quote=" + quote + "]";
	}

}
