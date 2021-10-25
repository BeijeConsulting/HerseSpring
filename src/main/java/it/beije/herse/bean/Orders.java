package it.beije.herse.bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
@Table(name = "`order`")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "amount")
	private Integer amount;

	@Column(name = "creation_datetime")
	private LocalDateTime dateTime;

	@Column(name = "user_id")
	private Integer userId;

	// Getter & Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@JsonGetter("dateTime")
	public String getDateTimeAsString() {
		if(this.dateTime != null) {
			return this.dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
		}else {
			return null;
		}
		
	}

	@JsonSetter("dateTime")
	public void setDateTime(String dateTime) {
		this.dateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME);
	}

	// ToString using Fields
	@Override
	public String toString() {
		return "Orders [id=" + id + ", amount=" + amount + ", dateTime=" + dateTime + ", userId=" + userId + "]";
	}

}