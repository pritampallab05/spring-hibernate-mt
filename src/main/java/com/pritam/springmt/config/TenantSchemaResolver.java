package com.pritam.springmt.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class TenantSchemaResolver implements CurrentTenantIdentifierResolver {

	private String defaultTenant = "public";

	@Override
	public String resolveCurrentTenantIdentifier() {
		String currentTenant = TenantContext.getCurrentTenant();
		if (currentTenant != null) {
			return currentTenant;
		}
		return defaultTenant;
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return false;
	}

}
