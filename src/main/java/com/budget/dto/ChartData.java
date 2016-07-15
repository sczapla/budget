package com.budget.dto;

import java.io.Serializable;

public class ChartData implements Serializable {
	private static final long serialVersionUID = 1L;

	private Datasets[] datasets;

	private String[] labels;

	public Datasets[] getDatasets() {
		return datasets;
	}

	public void setDatasets(Datasets[] datasets) {
		this.datasets = datasets;
	}

	public String[] getLabels() {
		return labels;
	}

	public void setLabels(String[] labels) {
		this.labels = labels;
	}

	@Override
	public String toString() {
		return "ClassPojo [datasets = " + datasets + ", labels = " + labels + "]";
	}
}
