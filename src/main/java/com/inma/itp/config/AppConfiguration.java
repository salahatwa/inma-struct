package com.inma.itp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

import com.inma.itp.config.messaging.MessageTemplateService;
import com.inma.itp.config.messaging.OfflineMessageTemplateServiceImpl;
import com.inma.itp.config.messaging.QueueMessageTemplateServiceImpl;

import lombok.NoArgsConstructor;

/**
 * This class load specific implementation during app start up if isOffline
 * property is true OfflineMessageTemplateServiceImpl will loaded else
 * QueueMessageTemplateServiceImpl will loaded
 * 
 * @author ssatwa
 *
 */
@Configuration
public class AppConfiguration {
	

	@Bean(name = "msgTemplateService")
	@Conditional(OfflineCondition.class)
	public MessageTemplateService offlineMessageTemplateServiceImpl() {
		return new OfflineMessageTemplateServiceImpl();
	}

	@Bean(name = "msgTemplateService")
	@Conditional(QueueCondition.class)
	public MessageTemplateService queueMessageTemplateServiceImpl() {
		return new QueueMessageTemplateServiceImpl();
	}

	@NoArgsConstructor
	public static class OfflineCondition implements Condition {
		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			return context.getEnvironment().getProperty("isOffline").equals("true");
		}
	}

	@NoArgsConstructor
	public static class QueueCondition implements Condition {
		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			return context.getEnvironment().getProperty("isOffline").equals("false");
		}
	}

}
