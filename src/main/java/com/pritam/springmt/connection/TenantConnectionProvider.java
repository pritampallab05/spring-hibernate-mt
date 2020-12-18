package com.pritam.springmt.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component
public class TenantConnectionProvider implements MultiTenantConnectionProvider {

	private static Logger logger = LoggerFactory.getLogger(TenantConnectionProvider.class);
	private DataSource datasource;
	private String DEFAULT_TENANT = "public";

	public TenantConnectionProvider(DataSource dataSource) {
		this.datasource = dataSource;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean isUnwrappableAs(Class unwrapType) {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> unwrapType) {
		return null;
	}

	@Override
	public Connection getAnyConnection() throws SQLException {
		return datasource.getConnection();
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		connection.close();

	}

	@Override
	public Connection getConnection(String tenantIdentifier) throws SQLException {
		logger.info("Get connection for the tenant {}", tenantIdentifier);
		final Connection connection = getAnyConnection();
		connection.setSchema(tenantIdentifier);
		return connection;
	}

	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
		logger.info("Release connection for the tenant {}", tenantIdentifier);
		connection.setSchema(DEFAULT_TENANT);
		releaseAnyConnection(connection);
	}

	@Override
	public boolean supportsAggressiveRelease() {
		return false;
	}

}
