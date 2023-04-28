package es.upm.pproject.sokoban.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.upm.pproject.sokoban.controller.Controller;
import es.upm.pproject.sokoban.controller.IOController;
import es.upm.pproject.sokoban.model.Pair;
import es.upm.pproject.sokoban.model.MapModel.MapElement;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.*;

public class ViewImpl implements View {

	private static final Logger logger = LoggerFactory.getLogger(ViewImpl.class);

	// Attributes & getters
	Controller controller;
	IOController ioController;

	// Main window 
	JFrame window;

	// Auxiliar classes Menu
	MenuView menu;
	KeyboardDetecter kbd;

	// Images on movement and their type for update optimization
	JLabel[][] labels;
	MapElement[][] labelsType;

	// Score
	JLabel[] scoreLabels;
	// LevelName
	JLabel levelNameLabel;

	// parts of the view
	JPanel game;
	JPanel gamePanel;
	JPanel scorePanel;
	JPanel levelNamePanel;

	// dimensions
	int xDimMap = 0;
	int yDimMap = 0;

	// Constructor
	public ViewImpl(Controller controller, IOController ioController) {
		this.controller = controller;
		this.ioController = ioController;

		// initialization of the auxiliar classes
		menu = new MenuViewImpl(controller, ioController);
		kbd = new KeyboardDetecter(controller);

		initComponents();
	}

	// Methods
	public void initComponents() {
		window = new JFrame();

		// frame configuration
		window.setIconImage(new ImageIcon(Paths.get("mapImages", "logo.png").toString()).getImage());
		window.setTitle("Sokoban");
		window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		window.setResizable(false);

		// menu initialization
		menu.initMenu(window);

		// stack panel for game and score
		game = new JPanel();
		game.setLayout(new BoxLayout(game, BoxLayout.Y_AXIS));

		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.CENTER);

		// gameBoard
		gamePanel = new JPanel(null);

		// scorePanel
		scorePanel = createScorePanel(layout);

		// levelName panel
		levelNamePanel = createLevelNamePanel(layout);

		// adding to the window display
		game.add(levelNamePanel);
		game.add(gamePanel);
		game.add(scorePanel);

		window.add(game);
	}

	public void show() {
		logger.debug("Window displayed.");

		window.setVisible(true);

		logger.debug("Keylistener added.");

		// until GUI is on screen, game should not start
		window.addKeyListener(kbd);
	}

	public void blockMov() {
		logger.debug("Keylistener removed.");
		window.removeKeyListener(kbd);
	}

	public void loadMap(MapElement[][] map, Pair dim, int gameScore, String levelName) {
		xDimMap = dim.getElement0();
		yDimMap = dim.getElement1();
		labels = new JLabel[xDimMap][yDimMap];
		labelsType = new MapElement[xDimMap][yDimMap];

		// updates grid size
		gamePanel.removeAll();
		gamePanel.setLayout(new GridLayout(xDimMap, yDimMap));

		// fills up game panel
		updateMap(map);
		updateScore(0, gameScore);
		updateNameLevel(levelName);

		// set window on the middle of the screen
		window.setLocationRelativeTo(null);

		// adjust size of the window
		window.pack();
	}

	public void updateMap(MapElement[][] map) {
		for (int i = 0; i < xDimMap; i++) {
			for (int j = 0; j < yDimMap; j++) {

				// only empty or different cells from previous ones are updated
				if (labelsType[i][j] == null
						|| (labelsType[i][j] != null && !map[i][j].equals(labelsType[i][j]))) {

					// selector of image
					String path = "mapImages";
					Path imagePath = Paths.get(path, map[i][j] + ".png");
					ImageIcon image = new ImageIcon(imagePath.toString());
					labels[i][j] = new JLabel(image);

					if (labelsType[i][j] != null) {
						int positionInMatrix = i * yDimMap + j;

						gamePanel.remove(positionInMatrix);
						gamePanel.add(labels[i][j], positionInMatrix);
					} else {
						gamePanel.add(labels[i][j]);
					}
					labelsType[i][j] = map[i][j];
				}
			}
		}
		window.pack();
	}

	public void updateScore(int actualLevelScore, int gameScore) {
		scorePanel.updateUI();
		scoreLabels[0].setText("Level score: " + actualLevelScore);
		scoreLabels[1].setText("Total score: " + gameScore);

		scorePanel.updateUI();
	}

	public void updateNameLevel(String levelName) {
		levelNameLabel.setText(levelName);
		levelNamePanel.updateUI();
	}

	public void errorLoadingMap() {
		JOptionPane.showMessageDialog(window, "Error loading new level.", "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	public void end(int score) {
		JOptionPane.showMessageDialog(window, "CONGRATS. YOU HAVE PASSED THE GAME.\nYOUR SCORE IS: " + score,
				"※\\" + "(^o^)/※", JOptionPane.DEFAULT_OPTION);
	}

	private JPanel createScorePanel(FlowLayout layout) {
		JPanel newScorePanel = new JPanel(layout);
		newScorePanel.setBackground(Color.decode("#ffffe6"));
		// sets new map score
		scoreLabels = new JLabel[2];
		JLabel lblActualScore = new JLabel("", SwingConstants.CENTER);
		JLabel lblTotalScore = new JLabel("", SwingConstants.LEFT);

		// label padding
		lblActualScore.setBorder(new EmptyBorder(3, 0, 3, 0));
		lblTotalScore.setBorder(new EmptyBorder(3, 0, 3, 0));

		scoreLabels[0] = lblActualScore;
		scoreLabels[1] = lblTotalScore;
		newScorePanel.add(scoreLabels[0]);
		newScorePanel.add(scoreLabels[1]);

		return newScorePanel;
	}

	private JPanel createLevelNamePanel(FlowLayout layout) {
		JPanel newLvlNamePanel = new JPanel(layout);
		levelNameLabel = new JLabel("", SwingConstants.CENTER);

		// label padding
		levelNameLabel.setBorder(new EmptyBorder(2, 0, 2, 0));
		newLvlNamePanel.add(levelNameLabel);

		return newLvlNamePanel;
	}
}
