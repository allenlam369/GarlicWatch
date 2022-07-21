package main.java.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(catalog = "garlic", schema = "public", name = "quotation")
@NamedQuery(name = "Quotation.findAll", query = "SELECT g FROM Quotation g")
public class Quotation implements Serializable {

	private static final long serialVersionUID = -7823625801508371311L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "quote_generator", sequenceName = "quote_seq", allocationSize = 1)
	@Column(name = "id")
	private int id;

	@Column(name = "date")
	private Timestamp date;

	@Column(name = "n")
	private int n;

	@Column(name = "avg")
	private double avg;

	@Column(name = "quote")
	private String quote;

	public Quotation(int n, double avg, String quote) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		date = new Timestamp(cal.getTimeInMillis());
		
		this.n = n;
		this.avg = avg;
		this.quote = quote;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
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

}
