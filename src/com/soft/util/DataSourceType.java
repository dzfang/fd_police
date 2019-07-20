package com.soft.util;

public enum DataSourceType {
	MASTER {
		public String getName() {
			return "MASTER";
		}
	},
	GIS {
		public String getName() {
			return "GIS";
		}
	};
	public abstract String getName();
}
