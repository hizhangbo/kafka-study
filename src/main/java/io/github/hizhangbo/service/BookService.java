package io.github.hizhangbo.service;

import io.github.hizhangbo.model.Book;
import io.github.hizhangbo.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Bob
 * @date 2020-03-03 15:17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public List<Book> findByPublisher(String publisher){
        return bookRepo.findByPublisher(publisher);
    }

    public Page<Book> findByPage(int page, int size){
        return bookRepo.findAll(PageRequest.of(page, size));
    }

    public long count(){
        return bookRepo.count();
    }
}
