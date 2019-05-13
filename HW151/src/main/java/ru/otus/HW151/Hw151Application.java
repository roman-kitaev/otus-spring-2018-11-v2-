package ru.otus.HW151;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.*;
import org.springframework.messaging.support.MessageBuilder;
import ru.otus.HW151.domain.CryptedVeryImportantMessage;
import ru.otus.HW151.domain.VeryImportantMessage;

@SpringBootApplication
public class Hw151Application {

	@Bean
	public QueueChannel messageChannel() {
		return MessageChannels.queue(10).get();
	}

	@Bean
	public PublishSubscribeChannel cryptedMessageChannel_1() {
		return MessageChannels.publishSubscribe().get();
	}

	@Bean
	public PublishSubscribeChannel cryptedMessageChannel_2() {
		return MessageChannels.publishSubscribe().get();
	}

	@Bean (name = PollerMetadata.DEFAULT_POLLER )
	public PollerMetadata poller () {
		return Pollers.fixedRate(1000).get();
	}

	@Bean
	public IntegrationFlow messageFlow() {
		return IntegrationFlows.from("messageChannel")
				.handle("coder", "encode")
				.<CryptedVeryImportantMessage>filter(
        				msg -> msg.isNotIdDevidedByFour())
				.<CryptedVeryImportantMessage, Boolean>route(
						CryptedVeryImportantMessage::isIdEven,
						mapping -> mapping
								.subFlowMapping(true, sf -> sf
										.channel("cryptedMessageChannel_1")
								)
								.subFlowMapping(false, sf -> sf
										.channel("cryptedMessageChannel_2")
								))
				.get();
	}

	public static void main(String[] args) throws InterruptedException{
		ConfigurableApplicationContext ctx =
				SpringApplication.run(Hw151Application.class, args);

		ctx.getBean("cryptedMessageChannel_1", PublishSubscribeChannel.class).
				subscribe(x -> System.out.println("(Channel 1) Encrypted VIM: " + x.getPayload()));
		ctx.getBean("cryptedMessageChannel_2", PublishSubscribeChannel.class).
				subscribe(x -> System.out.println("(Channel 2) Encrypted VIM: " + x.getPayload()));

		MessageChannel messageChannel = ctx.getBean("messageChannel", MessageChannel.class);

		while(true) {
			Thread.sleep(1500);

			VeryImportantMessage veryImportantMessage = new VeryImportantMessage();
			System.out.println("New VIM: " + veryImportantMessage);
			messageChannel.send(MessageBuilder.withPayload(veryImportantMessage).build());
		}
	}
}
