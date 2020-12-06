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
package mastermind.gui;


import java.io.FileInputStream;
import java.util.Properties;
import javafx.application.Application;
import javafx.stage.Stage;
import mastermind.dao.FileGameDao;
import mastermind.dao.FilePlayerDao;
import mastermind.domain.Player;
import mastermind.domain.PlayerService;


/**
 *
 * @author tgtuuli
 */
public class MasterMindApplication2 extends Application {
           
    private PlayerService playerService;
    
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String playerFile = properties.getProperty("playerFile");
        String gameFile = properties.getProperty("gameFile");
        FilePlayerDao playerDao = new FilePlayerDao(playerFile);
        FileGameDao fileGameDao = new FileGameDao(gameFile);
        playerService = new PlayerService(playerDao, fileGameDao);
        
        
    }
    
    @Override
    public void start(Stage primaryStage) {
        GameScene gameScene = new GameScene(primaryStage, playerService, true);
        
    }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
