package mp.consumer.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@EnableMongoRepositories(value = "mp.consumer.com.cfc.dao.repo.*")
public class ConsumerDbConfig extends AbstractMongoConfiguration{

    @Autowired
    Environment environment;
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Bean
    public MongoTemplate readSecondaryNodeTemplate(MongoDbFactory factory) throws Exception {
        return new MongoTemplate(mongo(), environment.getProperty("consumer.mongo.db.name"));

    }

    @Override
    protected String getDatabaseName() {
        return environment.getProperty("consumer.mongo.db.name");
    }


    public Mongo mongo() throws Exception {
        return new MongoClient();
    }

    @Override
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(),environment.getProperty("consumer.mongo.db.name"));
    }

    @Override
    protected String getMappingBasePackage() {
        return  "consumer.com.cfc.dao.repo.*";
    }

}
