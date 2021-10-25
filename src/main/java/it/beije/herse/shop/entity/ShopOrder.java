package it.beije.herse.shop.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;


@Entity
@Table(name = "`order`")
public class ShopOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="amount")
	private Double amount;
	
	@Column(name="creation_datetime")
	private LocalDateTime dateTime;

	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)//, fetch = FetchType.LAZY
	@JoinColumn(name="order_id")
	private List<ShopOrderItem> items;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public void setAmount() {
		if(items!=null && items.size()>0) {
			Double amount = 0.0;
			for(ShopOrderItem i : items) amount += i.getSellPrice()*i.getQuantity();
			this.amount = amount;
		}
	}

	
	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
	@JsonGetter("dateTime")
	public String getDateTimeAsString() {
		return dateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))+" "
				+dateTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
	}

	@JsonSetter("dateTime")
	public void setDateTime(String dateTime) {
		this.dateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME);
	}
	

	public List<ShopOrderItem> getItems() {
		return items;
	}

	public void setItems(List<ShopOrderItem> items) {
		this.items = items;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder("{id: ").append(id)
				.append(", userId: ").append(userId)
				.append(", amount: ").append(amount)
				.append(", dateTime: ").append(dateTime)
				.append("}");
		
		return builder.toString();
	}

}
