package com.budget.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "income_type")
public class IncomeType {
	@Id
	@GeneratedValue
	private int id;
	@Column
	private String name;
	@Column
	private String description;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_name")
	private User user;

}
