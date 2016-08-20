/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author stolawoj
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScanResult {
    private String left;
    private String up;
    private String right;
    private String down;

    @Override
    public String toString() {
        return "ScanResult{" + "left=" + left + ", up=" + up + ", right=" + right + ", down=" + down + '}';
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getDown() {
        return down;
    }

    public void setDown(String down) {
        this.down = down;
    }
}
