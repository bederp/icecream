/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import json.ScanResult;
import json.MoveResult;
import json.Greeting;
import json.HealthCheck;
import hello.Methods;
import json.StartCompetition;

/**
 *
 * @author stolawoj
 */
public interface ClientApi {

    Greeting greetTeam();

    HealthCheck healthCheck();
    
    StartCompetition startCompetition();
    
    MoveResult moveUp();
    MoveResult moveDown();
    MoveResult moveRight();
    MoveResult moveLeft();
    MoveResult move(Methods moveDir);
    
    ScanResult scan();
    ScanResult scanUp();
    ScanResult scanDown();
    ScanResult scanLeft();
    ScanResult scanRight();
    
}
