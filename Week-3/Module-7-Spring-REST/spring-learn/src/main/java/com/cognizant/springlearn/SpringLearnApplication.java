package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringLearnApplication {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) throws ParseException {
        SpringApplication.run(SpringLearnApplication.class, args);
        LOGGER.info("Inside main");

        displayDate();        // Hands-on 2
        displayCountry();     // Hands-on 4
        displayCountrySingleton(); // Hands-on 5
        displayCountries();   // Hands-on 6
    }

    // Hands-on 2
    public static void displayDate() throws ParseException {
        LOGGER.info("START - displayDate");
        ApplicationContext context =
            new ClassPathXmlApplicationContext("date-format.xml");
        SimpleDateFormat format =
            context.getBean("dateFormat", SimpleDateFormat.class);
        Date date = format.parse("31/12/2018");
        LOGGER.debug("Date : {}", date);
        LOGGER.info("END - displayDate");
    }

    // Hands-on 4
    public static void displayCountry() {
        LOGGER.info("START - displayCountry");
        ApplicationContext context =
            new ClassPathXmlApplicationContext("country.xml");
        Country country =
            context.getBean("country", Country.class);
        LOGGER.debug("Country : {}", country.toString());
        LOGGER.info("END - displayCountry");
    }

    // Hands-on 5
    public static void displayCountrySingleton() {
        LOGGER.info("START - displayCountrySingleton");
        ApplicationContext context =
            new ClassPathXmlApplicationContext("country.xml");
        Country country =
            context.getBean("country", Country.class);
        Country anotherCountry =
            context.getBean("country", Country.class);
        LOGGER.debug("Country 1 : {}", country);
        LOGGER.debug("Country 2 : {}", anotherCountry);
        LOGGER.debug("Same instance? : {}", (country == anotherCountry));
        LOGGER.info("END - displayCountrySingleton");
    }

    // Hands-on 6
    @SuppressWarnings("unchecked")
    public static void displayCountries() {
        LOGGER.info("START - displayCountries");
        ApplicationContext context =
            new ClassPathXmlApplicationContext("country.xml");
        List<Country> countryList =
            context.getBean("countryList", List.class);
        LOGGER.debug("Countries : {}", countryList);
        LOGGER.info("END - displayCountries");
    }
}