/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.domain;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author tgtuuli
 */
public class Piece extends Circle {
    
    private Color color;
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
    
    public void setNextColor() {
        this.setFill(nextColor());
    }

    public int getColorIndex() {
        return colorIndex;
    }
    
    private Color nextColor() {
        Color[] colors = {Color.GREY, Color.BLACK, Color.WHITE, Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW};
        String[] colorsWritten = {"Grey", "Black",  "White", "Blue", "Red", "Green", "Yellow"};
        if (colorIndex < colors.length - 1) {
            colorIndex++;
        } else {
            colorIndex = 0;
        }
        this.color = colors[colorIndex];
        this.colorInText = colorsWritten[colorIndex];
        return this.color;
    }

    public String getColorInText() {
        return colorInText;
    }
    
    public void setFeebackPieceColor(Color c) {
        this.setFill(c);
    }
    
   
    
    
    
}
