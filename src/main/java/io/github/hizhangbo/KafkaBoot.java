package io.github.hizhangbo;

import io.github.hizhangbo.service.MessageChannelService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Bob
 * @date 2020-03-03 18:19
 */
@Log4j2
@ComponentScan("io.github.hizhangbo")
public class KafkaBoot {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(KafkaBoot.class);

        MessageChannelService messageChannelService = applicationContext.getBean(MessageChannelService.class);
        messageChannelService.sendBook();

    }
}
