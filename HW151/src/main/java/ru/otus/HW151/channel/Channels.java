package ru.otus.HW151.channel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.SubscribableChannel;


@Configuration
public class Channels {
    @Bean("channelDirect")
    SubscribableChannel channelDirect() {
        return MessageChannels.
                direct("channelDirect").
                get();
    }

    @Bean("channelPollable")
    PollableChannel channelPollable() {
        return MessageChannels.
                queue(3).
                get();
    }
}
