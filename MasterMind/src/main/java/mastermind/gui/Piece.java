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
 *
 * @author tgtuuli
 */
public class Piece extends Circle {
    
    private Color color;
    private Color[] colors = {Color.GREY, Color.BLACK, Color.WHITE, Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW};
    private String[] colorsWritten = {"Grey", "Black",  "White", "Blue", "Red", "Green", "Yellow"};
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
    
    public Color getColor() {
        return this.color;
    }
    

    public int getColorIndex() {
        return colorIndex;
    }
    
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
