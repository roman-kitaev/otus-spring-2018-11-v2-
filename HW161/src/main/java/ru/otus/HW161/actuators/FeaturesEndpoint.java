package ru.otus.HW161.actuators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;
import ru.otus.HW161.repostory.BookRepository;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "features")
public class FeaturesEndpoint {

    @Autowired
    BookRepository bookRepository;

    private Map<String, Object> features = new HashMap<>();

    @ReadOperation
    public Map<String, Object> features() {
        features.put("nbooks", bookRepository.count());
        return features;
    }

    @ReadOperation
    public Object feature(@Selector String name) {
        return features.get(name);
    }

    @WriteOperation
    public void configureFeature(@Selector String name, Object feature) {
        features.put(name, feature);
    }

    @DeleteOperation
    public void deleteFeature(@Selector String name) {
        features.remove(name);
    }
}