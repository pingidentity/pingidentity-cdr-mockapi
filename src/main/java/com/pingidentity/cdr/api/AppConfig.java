package com.pingidentity.cdr.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.pingidentity.cdr.api.data.bankimpl.IBankDataAccess;
import com.pingidentity.cdr.api.data.bankimpl.MockBankDataAccess;
import com.pingidentity.cdr.api.data.energyimpl.IEnergyDataAccess;
import com.pingidentity.cdr.api.data.energyimpl.MockEnergyDataAccess;
import com.pingidentity.cdr.api.utils.ClassLoaderUtil;

@Configuration
@ComponentScan("com.pingidentity.cdr.api")
public class AppConfig {

	private final Properties configProps;

	private final Log log = LogFactory.getLog(this.getClass());
	
	public AppConfig()
	{
		InputStream configPropsIS = ClassLoaderUtil.getResourceAsStream("application.properties",
				this.getClass());
		
		configProps = new Properties();
		try {
			configProps.load(configPropsIS);
			
		} catch (IOException e) {
		}
	}
	
	@Bean
	public String baseUrl()
	{
		String config = getConfig("server.baseurl");		
		return config;
	}
	
	@Bean 
	public IBankDataAccess bankDataAccess()
	{
		return new MockBankDataAccess();
	}
	
	@Bean 
	public IEnergyDataAccess energyDataAccess()
	{
		return new MockEnergyDataAccess();
	}
	
	private String getConfig(String configName)
	{
		String envName = "MOCK_API-" + configName.replaceAll("\\.", "-");
		
		if(System.getenv(envName) != null && !System.getenv(envName).isEmpty())
		{
			log.info("Reading config from envVar: " + envName);
			return System.getenv(envName);
		}

		envName = "MOCK_API_" + configName.replaceAll("\\.", "_");

		if (System.getenv(envName) != null && !System.getenv(envName).isEmpty())
		{
			log.info("Reading config from envVar: " + envName);
			return System.getenv(envName);
		}
			
		return configProps.getProperty(configName);
	}
}
