Perfect — if you want your microservices to fetch i18n messages (localized error messages) from your Spring Cloud Config Server backed by Oracle DB, and support Locale-based resolution, here's how to set it up step by step.


---

Goal: MessageSource that supports locale & reads from config server (via Oracle DB)

Assumptions:

Your Spring Cloud Config Server exposes messages from Oracle DB.

Each microservice is configured to use the Config Server.

You want MessageSource.getMessage(String code, Object[] args, Locale locale) to work with dynamic, centralized messages.



---

1. Spring Cloud Config Server: Sample Properties in Oracle DB

You might have entries like:


---

2. Microservice: Load Config Server Properties

In application.properties (or bootstrap.properties):

spring.config.import=optional:configserver:http://localhost:8888
spring.application.name=user-service
spring.profiles.active=dev


---

3. Custom MessageSource Implementation for Dynamic Config Properties

You’ll create a custom MessageSource bean that pulls messages from the Environment.

DatabaseBackedMessageSource.java

@Component
public class DatabaseBackedMessageSource implements MessageSource {

    @Autowired
    private Environment environment;

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        String localizedKey = code + "." + locale.getLanguage(); // e.g., error.user.notfound.en
        String message = environment.getProperty("messages." + localizedKey);
        return (message != null) ? formatMessage(message, args) : defaultMessage;
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        String message = getMessage(code, args, null, locale);
        if (message == null) {
            throw new NoSuchMessageException(code, locale);
        }
        return message;
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        for (String code : resolvable.getCodes()) {
            String message = getMessage(code, resolvable.getArguments(), null, locale);
            if (message != null) return message;
        }
        throw new NoSuchMessageException(Arrays.toString(resolvable.getCodes()), locale);
    }

    private String formatMessage(String message, Object[] args) {
        return MessageFormat.format(message, args);
    }
}


---

4. Register as Primary MessageSource Bean

In your @SpringBootApplication class or a config class:

@Bean
@Primary
public MessageSource messageSource() {
    return new DatabaseBackedMessageSource();
}


---

5. Usage Example

In a controller or service:

@Autowired
private MessageSource messageSource;

public void someMethod() {
    Locale locale = LocaleContextHolder.getLocale();
    String msg = messageSource.getMessage("error.user.notfound", null, locale);
    System.out.println(msg); // Will print localized message
}


---

Benefits:

Centralized i18n error messages.

Locale-aware.

Dynamically updatable via DB changes (after config server refresh).



---

Would you like hot reloading support for this (e.g., via Spring Cloud Bus)? Or help building a fallback to static .properties if not found in DB?

