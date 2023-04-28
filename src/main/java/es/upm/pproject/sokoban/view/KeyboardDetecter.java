package es.upm.pproject.sokoban.view;

import java.awt.event.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.upm.pproject.sokoban.controller.Controller;

public class KeyboardDetecter implements KeyListener {
    private static final Logger logger = LoggerFactory.getLogger(KeyboardDetecter.class);
    private Controller controller;
    
    public KeyboardDetecter(Controller controller) {
        this.controller = controller;
    }

    public void keyTyped(KeyEvent e) {
        // NOT NEEDED
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                controller.mov(1);
                break;
            case KeyEvent.VK_DOWN:
                controller.mov(2);
                break;
            case KeyEvent.VK_LEFT:
                controller.mov(3);
                break;
            case KeyEvent.VK_RIGHT:
                controller.mov(4);
                break;
            case KeyEvent.VK_Z:
                if ((e.getModifiersEx() & java.awt.event.InputEvent.CTRL_DOWN_MASK) != 0) {
                    logger.debug("Undo pressed.");
                    controller.mov(5);
                }
                break;
            default:
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        // NOT NEEDED
    }

}
