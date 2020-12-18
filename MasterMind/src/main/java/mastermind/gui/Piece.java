/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.gui;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Sets up the piece layout and functions.
 * @author tgtuuli
 */
public class Piece extends Circle {
    
    private Color color;
    private final Color[] colors = {Color.GREY, Color.BLACK, Color.WHITE, Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW};
    private final String[] colorsWritten = {"Grey", "Black",  "White", "Blue", "Red", "Green", "Yellow"};
    private int colorIndex;
    private String colorInText;
    
    public Piece(int radius, Color color, int locationX, int locationY) {
        this.setCenterX(locationX);
        this.setCenterY(locationY);
        this.setFill(color);
        this.color = color;
        this.setRadius(radius);
        this.colorIndex = 0;
    }
    
    private int getColorIndex() {
        return colorIndex;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    /**
     * Sets the piece to next color when the player clicks on it (left mouse click).
     */
    public void setNextColor() {
        
        if (colorIndex < colors.length - 1) {
            colorIndex++;
        } else {
            colorIndex = 0;
        }
        this.color = colors[colorIndex];
        this.colorInText = colorsWritten[colorIndex];
        setFill(this.color);
    }
    
    /**
     * Sets the piece to previous color when the player clicks on it (right mouse click).
     */
    public void setPreviousColor() {
        if (colorIndex == 0) {
            colorIndex = colors.length - 1;
        } else {
            colorIndex--;
        }
        this.color = colors[colorIndex];
        this.colorInText = colorsWritten[colorIndex];
        setFill(this.color);
    }
    
    /**
     * returns the color in String.
     * @return String color
     */
    public String getColorInText() {
        return colorInText;
    }
    
    public void setFeebackPieceColor(Color c) {
        this.setFill(c);
    }

    public void setColorInText(String colorInText) {
        this.colorInText = colorInText;
    }
    
    
}
