package com.budget.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "expenses")
public class Expenses {
	@Id
	@GeneratedValue
	private Integer id;
	@Column
	private String name;
	@Column
	private String description;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_name")
	private User user;
	@Column
	private BigDecimal amount;
	@Temporal(TemporalType.DATE)
	@Column
	private Date date;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "expense_type_id")
	private ExpenseType type;
}
