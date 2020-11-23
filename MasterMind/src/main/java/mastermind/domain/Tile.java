/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.domain;

import java.util.ArrayList;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 *
 * @author tgtuuli
 */
public class Tile extends Region {
   
    private Piece piece;
    private int xMin;
    private int xMax;
    private int yMin;
    private int yMax;
    private boolean isFeedbackTile;
    private int place;
    private Piece[] feedbackPieces = new Piece[4];
    
    
    public boolean hasPiece() {
        return piece != null;
    }
    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    
    public Tile(int x, int y) {
        setWidth(Constants.TILE_SIZE);
        setHeight(Constants.TILE_SIZE);

        relocate(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE);
        this.xMin = x * Constants.TILE_SIZE;
        this.xMax = xMin + Constants.TILE_SIZE - 1;
        this.yMin = y * Constants.TILE_SIZE;
        this.yMax = yMin + Constants.TILE_SIZE - 1;

        setStyle("-fx-background-color: BURLYWOOD; -fx-border-style:solid; -fx-border-width: 2; -fx-border-color:black; "
                + "-fx-min-height:" + Constants.TILE_SIZE + ";-fx-min-width:" + Constants.TILE_SIZE + ";"
                + "-fx-max-height: " + Constants.TILE_SIZE + ";"
                        + "-fx-max-widh:" + Constants.TILE_SIZE + ";");
    }

    public int getxMin() {
        return xMin;
    }

    public int getxMax() {
        return xMax;
    }

    public int getyMin() {
        return yMin;
    }

    public int getyMax() {
        return yMax;
    }

    public void setIsFeedbackTile(boolean isFeedbackTile) {
        this.isFeedbackTile = isFeedbackTile;
    }
    
    public void setFillLight() {
        setStyle("-fx-background-color: BLANCHEDALMOND; -fx-border-style:solid; -fx-border-width: 2; -fx-border-color:black; "
                + "-fx-min-height:" + Constants.TILE_SIZE + ";-fx-min-width:" + Constants.TILE_SIZE + ";"
                + "-fx-max-height: " + Constants.TILE_SIZE + ";"
                        + "-fx-max-widh:" + Constants.TILE_SIZE + ";");
    }
    
    public void setFillDark() {
        setStyle("-fx-background-color: BURLYWOOD; -fx-border-style:solid; -fx-border-width: 2; -fx-border-color:black; "
                + "-fx-min-height:" + Constants.TILE_SIZE + ";-fx-min-width:" + Constants.TILE_SIZE + ";"
                + "-fx-max-height: " + Constants.TILE_SIZE + ";"
                        + "-fx-max-widh:" + Constants.TILE_SIZE + ";");
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getPlace() {
        return place;
    }
    
    public void setFeedbackPieces(ArrayList<Piece> pieces) {
        for (int i = 0; i < 4; i++) {
            this.feedbackPieces[i] = pieces.get(i);
        }
    }

    public Piece[] getFeedbackPieces() {
        return feedbackPieces;
    }
    
    
    
    
    
    
    
    
    
}
