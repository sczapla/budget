package com.budget.dto;

import java.io.Serializable;

public class Datasets implements Serializable {
	private static final long serialVersionUID = 1L;

	private String[] backgroundColor;

	private double[] data;

	public String[] getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String[] backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public double[] getData() {
		return data;
	}

	public void setData(double[] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ClassPojo [backgroundColor = " + backgroundColor + ", data = " + data + "]";
	}
}
