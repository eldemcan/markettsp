package mp.producer.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(" mp.producer.com.cfp.dao.repo.*")
public class ProducerDbConfig extends AbstractMongoConfiguration {

    @Autowired
    Environment environment;

    @Override
    protected String getDatabaseName() {
        return environment.getProperty("producter.mongo.db.name");
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient();
    }

    @Override
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), environment.getProperty("producter.mongo.db.name"));
    }

    @Override
    protected String getMappingBasePackage() {
        return  "producer.com.cfc.dao.repo.*";
    }


}
