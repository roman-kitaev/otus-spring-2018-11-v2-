package ru.otus.HW101.repostory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.HW101.domain.Author;

import java.util.List;

public class AuthorRepositoryCustomImpl implements AuthorRepositoryCustom {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Author> findAuthorsByNames(List<String> names) {
        Criteria stubCriteria = Criteria.where("name").exists(true);
        Query query = new Query();
        Criteria[] criterias = new Criteria[names.size()];
        for(int i = 0; i < names.size(); i++) {
            criterias[i] = Criteria.where("name").is(names.get(i));
        }

        query.addCriteria(stubCriteria.orOperator(criterias));
        return mongoTemplate.find(query, Author.class);
    }
}
