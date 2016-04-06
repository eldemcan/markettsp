package mp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan({"mp.consumer", "mp.producer"})
@EnableMongoRepositories
public class CfmtpApplication {

	public static void main(String[] args) {
		SpringApplication.run(CfmtpApplication.class, args);
	}
}
