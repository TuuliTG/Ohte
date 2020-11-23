/*
 * The MIT License
 *
 * Copyright 2020 tgtuuli.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package mastermind.ui;

import mastermind.domain.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 *
 * @author tgtuuli
 */
public class MasterMindApplication2 extends Application {
   
    private Pane root;
    private Board board;
            
    public MasterMindApplication2() {
        
        root = new Pane();
        
        try {
             board = new Board(root);
        } catch (Exception e) {
            
        }
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        mouseEvents();
        Scene scene = new Scene(root);
        primaryStage.setTitle("MASTERMIND");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void mouseEvents() {
        root.setOnMouseMoved(e -> {
            Tile t = findTile(e.getX(), e.getY());
            if (t!= null) {
                t.setFillLight();
                board.setOtherOnRowBrown(t.getPlace());
            } else {
                board.setAllBrown();
            }
            
        });
        root.setOnMouseClicked(e -> {
            Tile t = findTile(e.getX(), e.getY());
            Piece p = t.getPiece();
            p.setNextColor();
        });
    }
    
     private Tile findTile(double x, double y) {
        Tile[][] tiles = board.getTiles();
        int row = board.getActiveRow();
        //System.out.println("current active row: " + row);
        if (y > row*constants.TILE_SIZE && y < row*constants.TILE_SIZE + constants.TILE_SIZE) {
            int tileNr = (int) x / constants.TILE_SIZE;
            if (tileNr < 4) {
                return tiles[tileNr][row];
            }
        }
        return null;
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
