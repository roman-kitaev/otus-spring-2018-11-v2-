package ru.otus.HW101.repostory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.HW101.domain.Genre;

import java.util.List;

public class GenreRepositoryCustomImpl implements GenreRepositoryCustom {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Genre> findGenresByNames(List<String> names) {
        Criteria stubCriteria = Criteria.where("genre").exists(true);
        Query query = new Query();
        Criteria[] criterias = new Criteria[names.size()];
        for(int i = 0; i < names.size(); i++) {
            criterias[i] = Criteria.where("genre").is(names.get(i));
        }

        query.addCriteria(stubCriteria.orOperator(criterias));
        return mongoTemplate.find(query, Genre.class);
    }
}
