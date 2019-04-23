package ru.otus.HW151;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.*;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
public class Hw151Application {

	public static void main(String[] args) throws InterruptedException{
		ConfigurableApplicationContext ctx =
				SpringApplication.run(Hw151Application.class, args);

		SubscribableChannel channelDirect = ctx.getBean("channelDirect", SubscribableChannel.class);
		PollableChannel pollableChannel = ctx.getBean("channelPollable", PollableChannel.class);
		Publisher publisher = ctx.getBean(Publisher.class);

		channelDirect.subscribe(
			new VeryImportantMessagesHandler(pollableChannel, "Subscriber 1"));

		channelDirect.subscribe(
			new VeryImportantMessagesHandler(pollableChannel, "Subscriber 2"));

		channelDirect.subscribe(
			new VeryImportantMessagesHandler(pollableChannel, "Subscriber 3"));

		new Thread(() -> {
			while(true) {
				Message<?> message = pollableChannel.receive();
				System.out.println("Pollable 1 got a message: " +
						message.getPayload() + " from " + message.getHeaders().get("from") +
						" and wrote it to the base (Probably).");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					System.out.println("Pollable receiver has an exception!");
				}
			}
		}).start();

		new Thread(() -> {
			try {
				publisher.run();
			} catch (InterruptedException e) {}
		}).start();
	}

	private static class VeryImportantMessagesHandler implements MessageHandler {

		private PollableChannel pollableChannel;
		private String name;

		public VeryImportantMessagesHandler(PollableChannel pollableChannel, String name) {
			this.pollableChannel = pollableChannel;
			this.name = name;
		}

		@Override
		public void handleMessage(Message<?> inputMessage) throws MessagingException {
			System.out.println(name + " got a message: " + inputMessage.getPayload() +
					" from " + inputMessage.getHeaders().get("from"));

			try {
				Thread.sleep(500);
			}catch (InterruptedException e) {
				System.out.println("Sleep in " + name + " is interrupted!");
			}

			Message<?> outputMessage =
					MessageBuilder.fromMessage(inputMessage).
							setHeader("from", name).
							build();

			pollableChannel.send(outputMessage);
		}
	}
}
