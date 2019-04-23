package ru.otus.HW151;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.otus.HW151.domain.VeryImportantMessage;

@ComponentScan
@Service
public class Publisher {

    @Autowired
    @Qualifier("channelDirect")
    private SubscribableChannel subscribableChannel;

    public void run() throws InterruptedException{
        while(true) {
            subscribableChannel.send(MessageBuilder.
                    withPayload(new VeryImportantMessage()).
                    setHeader("from", "UNKNOWN").
                    build());
            Thread.sleep(500);
        }
    }
}
