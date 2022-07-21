package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the quotation database table.
 * 
 */
@Entity
@NamedQuery(name="Quotation.findAll", query="SELECT q FROM Quotation q")
public class Quotation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private BigDecimal avg;

	private Timestamp date;

	private Integer n;

	private String quote;

	public Quotation() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAvg() {
		return this.avg;
	}

	public void setAvg(BigDecimal avg) {
		this.avg = avg;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Integer getN() {
		return this.n;
	}

	public void setN(Integer n) {
		this.n = n;
	}

	public String getQuote() {
		return this.quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

}