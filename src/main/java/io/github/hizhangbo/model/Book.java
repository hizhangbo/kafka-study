package io.github.hizhangbo.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * @author Bob
 * @date 2020-03-03 11:13
 */
@Data
@Builder
@ToString
@Document(collection="books")
public class Book {
    @MongoId
    private String id;
    private String name;
    private String ISBN;
    private String author;
    private String jd_id;
    private String publisher;
    private String publish_date;
    private String commit;
    private String high_opinion;
    private String middle_opinion;
    private String low_opinion;
    private String page_count;
    private String paper_size;
    private String paper_type;
    private String pack_type;
    private String fixed_price;
    private String discounted_price;
    private String classify;
    private String tags;
    private String edition;
}
