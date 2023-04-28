package es.upm.pproject.sokoban;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.upm.pproject.sokoban.controller.*;

public class Sokoban {
	private static final Logger logger =
			LoggerFactory.getLogger(Sokoban.class);
	
    public static void main(String[] args) throws IOException {
        logger.info("Sokoban initation.");
    	
    	Controller controller = new ControllerImpl();
        controller.start();
    }
}
