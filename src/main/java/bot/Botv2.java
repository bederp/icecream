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

import java.util.*;

public class Botv2 {
    private Point startPosition;
    private Point currentPosition;
    private Point endPosition;
    private Deque<Methods> moves = new ArrayDeque<>();
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
        moves.addFirst(Methods.MoveUp);
        moves.addFirst(Methods.MoveLeft);
        moves.addFirst(Methods.MoveDown);
        moves.addFirst(Methods.MoveRight);
    }

    private void move1Step() {
            final Methods move = moves.pop();
            final MoveResult moveResult = client.move(move);
            if(moveResult.isSucces()){
                log.info("Moved:" + move);
                moves.addFirst(move);
                reorderOppositeMoveToEndOfStack(move);
                currentPosition = moveResult.getPosition();
            }else {
                moves.add(move);
                log.info("Blocked: " + move);
            }
    }

    private void reorderOppositeMoveToEndOfStack(Methods move) {
        final Methods oppositeMove = Methods.opposites.get(move);
        moves.remove(oppositeMove);
        moves.addLast(oppositeMove);
    }


    private void startCompetition() {
        final StartCompetition startCompetition = client.startCompetition();
        log.info(String.valueOf(startCompetition));
        currentPosition = startPosition = startCompetition.getStartPoint();
        endPosition = startCompetition.getEndPoint();
    }




}
