/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author stolawoj
 */
@SpringBootApplication
public class Application implements CommandLineRunner {
    
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(String... strings) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        final StartCompetition start = restTemplate.postForObject(URLFactory.getTest(Methods.StartCompetition), new Identifier("id_1"), StartCompetition.class);
        log.info(String.valueOf(start));
//        final HealthCheck healthCheck = restTemplate.getForObject("http://192.168.0.50:12345/maze-game/HealthCheck", HealthCheck.class);
//        log.info(String.valueOf(healthCheck));
    }

}
