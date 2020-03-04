package io.github.hizhangbo.config;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;

import java.util.List;

/**
 * @author Bob
 * @date 2020-03-03 19:31
 */
@Log4j2
public class Listener {

    @KafkaListener(id = "book_batch_group", containerFactory = "batchFactory", topicPartitions = {@TopicPartition(topic = MessageConstanceTopic.BOOK_TOPIC, partitions = {"0", "1", "2"})})
    public void bookBatchListener(List<ConsumerRecord<?, ?>> books, Acknowledgment acknowledgment) {
        log.info(books.size());
        books.forEach(book -> System.out.println("批量提交 =>" + book.key() + " : " + book.value()));
        acknowledgment.acknowledge();
    }

    @KafkaListener(id = "book_item_group", containerFactory = "kafkaListenerContainerFactory", topics = MessageConstanceTopic.BOOK_TOPIC)
    public void bookItemListener(String book) {
        log.info("单个提交 =>" + book);
    }

}
