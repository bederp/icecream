/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import hello.ScanResult;
import hello.MoveResult;
import hello.Greeting;
import hello.HealthCheck;
import hello.Methods;
import hello.StartCompetition;

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
