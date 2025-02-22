package com.itwillbs.util;

public enum BeanType {
	
	SERVICE("Service"),
	MAPPER("Mapper");

	private final String type;

	BeanType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
	
}
