package client;

import hello.Greeting;
import hello.HealthCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author stolawoj
 */
public class Client {

    private static final Logger logger = LoggerFactory.getLogger(Client.class);
    private static final String remoteUrl = "http://192.168.0.50:12345/maze-game";
//    String localUrl = "https://hackathon-mutineer.c9users.io/StartCompetition";

    public HealthCheck healthCheck() {
        RestTemplate restTemplate = new RestTemplate();

        HealthCheck response = restTemplate.getForObject(remoteUrl + "/HealthCheck", HealthCheck.class);

        logger.info("Got healthCheck reposne: {}", response.toString());
        return response;
    }

    public Greeting greetTeam() {
        return new RestTemplate().postForObject(remoteUrl+"/GreetTeam", this, Greeting.class);
    }
}
