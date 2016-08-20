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
        initialize();
        log.info("Starting Competition");
        startCompetition();
        while (!currentPosition.equals(endPosition)) {
            log.info("Current moves stack: {}", moves);
            Methods move = pickMoveDirection();
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
        final Point dir = getNewPosition(moveDir);
        if (visitedPoints.get(dir) != null) {
            moves.addLast(moveDir);
        }
    }

    private void initialize() {
        moves.addLast(Methods.MoveRight);
        moves.addLast(Methods.MoveDown);
        moves.addLast(Methods.MoveLeft);
        moves.addLast(Methods.MoveUp);
    }

    private void move1Step(Methods move) {
        final MoveResult moveResult = client.move(move);
        currentPosition = moveResult.getPosition();
        visitedPoints.put(currentPosition, true);

        if (moveResult.isSucces()) {
            log.info("Moved:" + move);
            moves.addFirst(move);
            reorderOppositeMoveToEndOfStack(move);
        } else {
            moves.addLast(move);
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
        visitedPoints.put(startPosition, Boolean.TRUE);
        endPosition = startCompetition.getEndPoint();
    }

    private boolean wasThereBefore(Methods move) {
        final Point newPosition = getNewPosition(move);
        if (visitedPoints.get(newPosition) != null) {
            log.info("Change of move. Was before at {}", newPosition);
            return true;
        }
        return false;
    }

    private Point getNewPosition(Methods move) {
        Point newPosition = new Point(currentPosition);
        switch (move) {
            case MoveDown:
                newPosition.moveDown();
                break;
            case MoveUp:
                newPosition.moveUp();
                break;
            case MoveLeft:
                newPosition.moveLeft();
                break;
            case MoveRight:
                newPosition.moveRight();
                break;
        }
        return newPosition;
    }

}
