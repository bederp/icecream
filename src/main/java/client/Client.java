package client;

import hello.Greeting;
import hello.HealthCheck;
import hello.Identifier;
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
    private final String maze_id;
//    String localUrl = "https://hackathon-mutineer.c9users.io/StartCompetition";

    public Client(String maze_id) {
        this.maze_id = maze_id;
    }

    private Identifier getId() {
        return new Identifier(this.maze_id);
    }

    @Override
    public HealthCheck healthCheck() {
        RestTemplate restTemplate = new RestTemplate();
        final String url = URLFactory.getProd(Methods.HealthCheck);
        logger.info("Connecting to ", url);

        HealthCheck response = restTemplate.getForObject(url, HealthCheck.class);

        logger.info("Got healthCheck reposne: {}", response.toString());
        return response;
    }

    @Override
    public Greeting greetTeam() {
        return new RestTemplate().postForObject(URLFactory.getProd(Methods.GreatTeam), this, Greeting.class);
    }

    @Override
    public StartCompetition startCompetition() {
        return new RestTemplate().postForObject(URLFactory.getProd(Methods.StartCompetition), getId(), StartCompetition.class);
    }

    @Override
    public MoveResult moveUp() {
        return new RestTemplate().postForObject(URLFactory.getProd(Methods.MoveUp), getId(), MoveResult.class);
    }

    @Override
    public MoveResult moveDown() {
        return new RestTemplate().postForObject(URLFactory.getProd(Methods.MoveUp), getId(), MoveResult.class);
    }

    @Override
    public MoveResult moveRight() {
        return new RestTemplate().postForObject(URLFactory.getProd(Methods.MoveUp), getId(), MoveResult.class);
    }

    @Override
    public MoveResult moveLeft() {
        return new RestTemplate().postForObject(URLFactory.getProd(Methods.MoveUp), getId(), MoveResult.class);
    }
}
