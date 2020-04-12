package com.inma.itp;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.jms.annotation.EnableJms;

import lombok.extern.slf4j.Slf4j;

/**
 * Main class of api
 * 
 * @author ssatwa
 *
 */
@Slf4j
@SpringBootApplication
@EnableJms
public class Application extends SpringBootServletInitializer implements ApplicationRunner {

	@Autowired
	private MessageSource messageSource;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info(">>>ITP Application started successfuly");
		log.info("{}", messageSource.getMessage("l1", null, new Locale("ar", "DZ")));
		log.info("{}", messageSource.getMessage("l1", null, Locale.ENGLISH));

		
		log.info("Translated parameterized messages:");
		log.info("{}", messageSource.getMessage("l2", new Object[] { "الإنماء للإستثمار" }, new Locale("ar", "DZ")));
		log.info("{}", messageSource.getMessage("l2", new Object[] { "Inma Investement" }, Locale.ENGLISH));

		log.info("user.dir      :{}", System.getProperty("user.dir"));
		log.info("user.home     :{}", System.getProperty("user.home"));
		log.info("java.io.tmpdir:{}", System.getProperty("java.io.tmpdir"));
	}

}
