package com.whiteoaksecurity.copier.models;

public class ResponseRulesTableModel extends RulesTableModel {
	
	public ResponseRulesTableModel() {
		this.ruleType = "Response";
		this.locations = new String[]{
			"Response",
			"Response Status Line",
			"Response Headers",
			"Response Header",
			"Response Header Name",
			"Response Header Value",
			"Response Body"
		};
	}
}
