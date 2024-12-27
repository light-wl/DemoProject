package light.quant.adput;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AdputApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdputApplication.class, args);
    }

}
