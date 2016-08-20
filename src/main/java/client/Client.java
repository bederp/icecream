package client;

import hello.Greeting;
import hello.HealthCheck;
import hello.Methods;
import hello.MoveResult;
import hello.StartCompetition;
import hello.URLFactory;
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
public class Client implements ClientApi {

    private static final Logger logger = LoggerFactory.getLogger(Client.class);
    private static final String remoteUrl = "http://192.168.0.50:12345/maze-game";
//    String localUrl = "https://hackathon-mutineer.c9users.io/StartCompetition";

    @Override
    public HealthCheck healthCheck() {
        RestTemplate restTemplate = new RestTemplate();

        HealthCheck response = restTemplate.getForObject(URLFactory.getProd(Methods.HealthCheck), HealthCheck.class);

        logger.info("Got healthCheck reposne: {}", response.toString());
        return response;
    }

    @Override
    public Greeting greetTeam() {
        return new RestTemplate().postForObject(remoteUrl+"/GreetTeam", this, Greeting.class);
    }

    @Override
    public StartCompetition startCompetition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MoveResult moveUp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MoveResult moveDown() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MoveResult moveRight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MoveResult moveLeft() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
