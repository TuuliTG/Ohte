/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.gui;

import java.util.ArrayList;
import javafx.scene.layout.Region;
import mastermind.util.Constants;


/**
 *
 * @author tgtuuli
 */
public class Tile extends Region {
   
    private Piece piece;
    
    private int place;
    private Piece[] feedbackPieces = new Piece[4];
    
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

        setStyle("-fx-background-color: BURLYWOOD; -fx-border-style:solid; -fx-border-width: 2; -fx-border-color:black; "
                + "-fx-min-height:" + Constants.TILE_SIZE + ";-fx-min-width:" + Constants.TILE_SIZE + ";"
                + "-fx-max-height: " + Constants.TILE_SIZE + ";"
                        + "-fx-max-widh:" + Constants.TILE_SIZE + ";");
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
