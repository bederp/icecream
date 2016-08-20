package bot;/*
    Created by kinder112 on 20.08.2016.
 */

import client.Client;
import client.ClientApi;
import hello.Application;
import json.MoveResult;
import json.Point;
import json.StartCompetition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

public class Botv1 {
    private Point startPosition;
    private Point currentPosition;
    private Point endPosition;
    private ClientApi client = new Client("r1_1", false);

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public void run() {
        log.info("Starting Competition");
        startCompetition();
        while (!currentPosition.equals(endPosition)) {
            move1Step();
        }
        log.info("Finished maze");
    }

    private void move1Step() {



        log.info("Going 1 step");
        MoveResult moveResult = client.moveRight();
        log.info(String.valueOf(moveResult));
        if (moveResult.getOutcome() == "success") {
            currentPosition = moveResult.getPosition();
            return;
        } else {
            moveResult = client.moveDown();
            if (moveResult.getOutcome() == "success") {
                currentPosition = moveResult.getPosition();
                return;
            } else {
                moveResult = client.moveLeft();
                if (moveResult.getOutcome() == "success") {
                    currentPosition = moveResult.getPosition();
                    return;
                } else {
                    moveResult = client.moveUp();
                    if (moveResult.getOutcome() == "success") {
                        currentPosition = moveResult.getPosition();
                        return;
                    }
                }
            }
        }

    }

    private void startCompetition() {
        final StartCompetition startCompetition = client.startCompetition();
        log.info(String.valueOf(startCompetition));
        currentPosition = startPosition = startCompetition.getStartPoint();
        endPosition = startCompetition.getEndPoint();
    }




}
