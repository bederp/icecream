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
    private Map<Point, Boolean> visitedPoints = new HashMap<>();
    private ClientApi client = new Client("r1_2", false);

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public void run() {
        log.info("Starting Competition");
        startCompetition();
        while (!currentPosition.equals(endPosition)) {
            log.info("Current moves stack: {}", moves);
            Methods move = pickMoveDirection();
            log.info("Prioritized moves stack: {}", moves);
            log.info("Picked move: {}", move);
            move1Step(move);
        }
        log.info("Finished maze");
    }

    private Methods pickMoveDirection() {
        moves.clear();
        prioritizeMoves(Methods.MoveRight, Methods.MoveDown, Methods.MoveLeft, Methods.MoveUp);
        return moves.pop();
    }

    private void prioritizeMoves(Methods... moveDirs) {
        for (Methods method : moveDirs) {
            prioritizeMove(method);
        }
    }

    private void prioritizeMove(Methods moveDir) {
        final Point newPosiotion = getNewPosition(moveDir);
        if (visitedPoints.get(newPosiotion) != null) {
            moves.addLast(moveDir);
        }
        moves.addFirst(moveDir);
    }

    private void move1Step(Methods move) {
        final MoveResult moveResult = client.move(move);
        currentPosition = moveResult.getPosition();

        if (moveResult.isSucces()) {
            visitedPoints.put(currentPosition, true);
            log.info("Moved:" + move);
        } else {
            Point triedPosition = new Point(currentPosition);
            triedPosition.move(move);
            visitedPoints.put(triedPosition, true);
            log.info("Visited: {}", visitedPoints);
            log.info("Blocked: " + move);
        }
    }

    private void startCompetition() {
        final StartCompetition startCompetition = client.startCompetition();
        log.info(String.valueOf(startCompetition));
        currentPosition = startPosition = startCompetition.getStartPoint();
        visitedPoints.put(startPosition, Boolean.TRUE);
        endPosition = startCompetition.getEndPoint();
    }

    private Point getNewPosition(Methods move) {
        Point newPosition = new Point(currentPosition);
        newPosition.move(move);
        return newPosition;
    }

}
