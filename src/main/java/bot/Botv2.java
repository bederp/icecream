package bot;/*
    Created by kinder112 on 20.08.2016.
 */

import json.StartCompetition;
import json.MoveResult;
import json.Point;
import client.Client;
import client.ClientApi;
import hello.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumSet;
import java.util.List;
import java.util.Stack;

public class Botv2 {
    private Point startPosition;
    private Point currentPosition;
    private Point endPosition;
    Stack<Methods> moves = new Stack<>();
    private ClientApi client = new Client("r1_1", false);

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public void run() {
        initialize();
        log.info("Starting Competition");
        startCompetition();
        while (!currentPosition.equals(endPosition)) {
            move1Step();
        }
        log.info("Finished maze");
    }

    private void initialize() {
        moves.add(Methods.MoveRight);
        moves.add(Methods.MoveDown);
        moves.add(Methods.MoveLeft);
        moves.add(Methods.MoveUp);
    }

    private void move1Step() {
            final Methods move = moves.pop();
            final MoveResult moveResult = client.move(move);
            if(moveResult.isSucces()){
                log.info("Moved:" + move);
                moves.push(move);
//                findOppositeMove(move);
                currentPosition = moveResult.getPosition();
            }else {
                moves.add(move);
                log.info("Blocked: " + move);
            }
    }


    private void startCompetition() {
        final StartCompetition startCompetition = client.startCompetition();
        log.info(String.valueOf(startCompetition));
        currentPosition = startPosition = startCompetition.getStartPoint();
        endPosition = startCompetition.getEndPoint();
    }




}
