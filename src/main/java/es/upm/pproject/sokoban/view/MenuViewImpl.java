package es.upm.pproject.sokoban.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.upm.pproject.sokoban.controller.Controller;
import es.upm.pproject.sokoban.controller.IOController;

public class MenuViewImpl implements MenuView {

	private static final Logger logger = LoggerFactory.getLogger(MenuViewImpl.class);
	Controller controller;
	IOController ioController;

	JMenuBar menuBar;
	JMenu menu;
	JMenuItem newGame;
	JMenuItem restart;
	JMenuItem undo;
	JMenuItem save;
	JMenuItem load;
	JMenuItem close;

	public MenuViewImpl(Controller controller, IOController ioController) {
		this.ioController = ioController;
		this.controller = controller;
	}

	public void initMenu(JFrame window) {
		menuBar = new JMenuBar();
		menu = new JMenu("File");

		newGame = new JMenuItem("New Game");
		restart = new JMenuItem("Restart");
		undo = new JMenuItem("Undo");
		save = new JMenuItem("Save");
		load = new JMenuItem("Load");
		close = new JMenuItem("Close");

		menu.setPreferredSize(new Dimension(36, 30));

		newGame.addActionListener(ev -> controller.newGame());
		newGame.setPreferredSize(new Dimension(86, 30));

		restart.addActionListener(ev -> controller.restart());
		restart.setPreferredSize(new Dimension(63, 30));

		undo.addActionListener(ev -> controller.mov(5));
		undo.setPreferredSize(new Dimension(48, 30));

		save.addActionListener(ev -> {
			JFileChooser fileChooser = new JFileChooser();
			// we force the selected file to be xml
			FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("XML files", "xml");
			fileChooser.setFileFilter(extensionFilter);

			int opStatus = fileChooser.showOpenDialog(fileChooser);
			if (opStatus == JFileChooser.APPROVE_OPTION) {
				String file = fileChooser.getSelectedFile().toString();
				ioController.save(file);
			} else {
				logger.error("Error saving the actual Sokoban game. Only xml are permitted to be saved.");
			}
		});
		save.setPreferredSize(new Dimension(40, 30));

		load.addActionListener(ev -> {
			JFileChooser fileChooser = new JFileChooser();
			// we force the selected file to be xml
			FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("XML files", "xml");
			fileChooser.setFileFilter(extensionFilter);

			int opStatus = fileChooser.showOpenDialog(fileChooser);
			if (opStatus == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				ioController.load(file);
			} else {
				logger.error("Error loading the Sokoban file. Only xml are permitted to be loaded.");
			}
		});
		load.setPreferredSize(new Dimension(40, 30));

		close.addActionListener(ev -> System.exit(0));
		close.setPreferredSize(new Dimension(51, 30));

		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		menuBar.setLayout(layout);

		menu.add(newGame);
		menu.add(save);
		menu.add(load);
		menuBar.add(menu);
		menuBar.add(restart);
		menuBar.add(undo);
		menuBar.add(close);

		window.setJMenuBar(menuBar);

		logger.debug("Menu created.");
	}
}
