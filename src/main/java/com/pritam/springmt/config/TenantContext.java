package com.pritam.springmt.config;

public class TenantContext {

	private static String currentTenant = "";

	public static String getCurrentTenant() {
		return currentTenant;
	}

	public static void setCurrentTenant(String currentTenant) {
		TenantContext.currentTenant = currentTenant;
	}

	public static void clear() {
		currentTenant = "";
	}

}
