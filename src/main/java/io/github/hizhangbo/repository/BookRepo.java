package io.github.hizhangbo.repository;

import io.github.hizhangbo.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Bob
 * @date 2020-03-03 13:00
 */
@Repository
public interface BookRepo extends MongoRepository<Book, String> {

    @Query("{ 'publisher' : ?0 }")
    List<Book> findByPublisher(String publisher);
}
