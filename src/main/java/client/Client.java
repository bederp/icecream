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
    private boolean isProduction = true;
//    String localUrl = "https://hackathon-mutineer.c9users.io/StartCompetition";

    public Client(String maze_id) {
        this(maze_id, true);
    }

    public Client(String maze_id, boolean isProducion) {
        this.maze_id = maze_id;
        this.isProduction = isProducion;
    }

    private Identifier getId() {
        return new Identifier(this.maze_id);
    }

    @Override
    public HealthCheck healthCheck() {
        RestTemplate restTemplate = new RestTemplate();

        HealthCheck response = restTemplate.getForObject(getUrl(Methods.HealthCheck), HealthCheck.class);

        logger.info("Got healthCheck reposne: {}", response.toString());
        return response;
    }

    private String getUrl(Methods method) {
        return this.isProduction ? URLFactory.getProd(method) : URLFactory.getTest(method);
    }

    @Override
    public Greeting greetTeam() {
        return new RestTemplate().postForObject(getUrl(Methods.GreatTeam), this, Greeting.class);
    }

    @Override
    public StartCompetition startCompetition() {
        return new RestTemplate().postForObject(getUrl(Methods.StartCompetition), getId(), StartCompetition.class);
    }

    @Override
    public MoveResult moveUp() {
        return new RestTemplate().postForObject(getUrl(Methods.MoveUp), getId(), MoveResult.class);
    }

    @Override
    public MoveResult moveDown() {
        return new RestTemplate().postForObject(getUrl(Methods.MoveDown), getId(), MoveResult.class);
    }

    @Override
    public MoveResult moveRight() {
        return new RestTemplate().postForObject(getUrl(Methods.MoveRight), getId(), MoveResult.class);
    }

    @Override
    public MoveResult moveLeft() {
        return new RestTemplate().postForObject(getUrl(Methods.MoveLeft), getId(), MoveResult.class);
    }
}
