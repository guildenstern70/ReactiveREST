/*
 * Project ReactREST
 * Copyright (c) Alessio Saltarin 2022
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.reactiverest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ReactRestApplication implements CommandLineRunner
{
	public static final String VERSION = "0.0.4";

	private final Logger logger = LoggerFactory.getLogger(ReactRestApplication.class);

	private final Environment environment;

	@Autowired
	public ReactRestApplication(Environment environment)
	{
		this.environment = environment;
	}

	private void hello()
	{
		String runningUrl = "http://localhost:" + this.environment.getProperty("local.server.port");
		logger.info("**************************************************");
		logger.info("  ReactREST v." + VERSION);
		logger.info("  Running on " + runningUrl + " (JVM " + System.getProperty("java.version") + ")");
		logger.info("**************************************************");
	}

	@Override
	public void run(String... args)
	{
		this.hello();
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactRestApplication.class, args);
	}

}
