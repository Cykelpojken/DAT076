/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Arvid
 */
public class BoardPiece {
    private int pos;
    private String value;
    
    public BoardPiece(int pos, String value)
    {
        this.pos = pos;
        this.value = value;
    }
    
    public int getPos()
    {
        return pos;
    }
    public String getValue()
    {
        return value;
    }
    public void setValue(String value)
    {
        this.value = value;
    }
}
