package bot;/*
    Created by kinder112 on 20.08.2016.
 */

import client.Client;
import client.ClientApi;
import hello.MoveResult;
import hello.Point;
import hello.StartCompetition;

public class v1 {
    private Point startPosition;
    private Point currentPosition;
    private Point endPosition;
    private ClientApi client = new Client("rnd11");

    public void run() {
        startCompetition();
        while (startPosition.equals(endPosition)) {
            move1Step();
        }
    }

    private void move1Step() {
        MoveResult moveResult = client.moveRight();
        if (moveResult.getOutcome() == "success") {
            currentPosition = moveResult.getPosition();
        } else {
            moveResult = client.moveDown();
            if (moveResult.getOutcome() == "success") {
                currentPosition = moveResult.getPosition();

            } else {
                moveResult = client.moveLeft();
                if (moveResult.getOutcome() == "success") {
                    currentPosition = moveResult.getPosition();
                } else {
                    moveResult = client.moveUp();
                    if (moveResult.getOutcome() == "success") {
                        currentPosition = moveResult.getPosition();
                    }
                }
            }
        }

    }

    private void startCompetition() {
        final StartCompetition startCompetition = client.startCompetition();
        currentPosition = startPosition = startCompetition.getStartPoint();
        endPosition = startCompetition.getEndPoint();
    }


}
