package io.github.hizhangbo.service;

import com.alibaba.fastjson.JSON;
import io.github.hizhangbo.config.MessageConstanceTopic;
import io.github.hizhangbo.model.Book;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Bob
 * @date 2020-03-04 15:29
 */
@Log4j2
@Service
public class MessageChannelService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private BookService bookService;

    public void sendBook() {
        long count = 20000l;//bookService.count();
        int size = 10;
        for (int i = 10000; i < count; i = i + size) {
            Page<Book> books = bookService.findByPage(i, size);
            books.get().forEach(book -> kafkaTemplate.send(MessageConstanceTopic.BOOK_TOPIC, book.getId(), JSON.toJSONString(book)));
        }

    }
}
