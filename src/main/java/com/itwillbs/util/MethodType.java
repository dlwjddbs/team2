package com.itwillbs.util;

public enum MethodType {
	
	SELECT("select"),
	INSERT("insert"),
	MODIFY("modify"),
	DELETE("delete");

	private final String value;

	MethodType(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
	
}
