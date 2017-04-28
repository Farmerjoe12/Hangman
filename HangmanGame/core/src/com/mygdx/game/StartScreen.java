package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class StartScreen extends Screen{
	
	private ImageTextButton startButton;
	
	StartScreen() {
		super(game);		
		
		// Title creation
		title = new Label("Welcome to Hangman!", skin);
		title.setStyle(titleStyle);		
		
		// Button creation
		startButton = new ImageTextButton("Play Hangman", style);
		startButton.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y) {
				// Change the screen to a new GameScreen
				MyGdxGame.setScreen(new GameScreen(game));
			}
		});
		
		// Adding elements to the screen		
		mainTable.add(title).center().spaceBottom(100);
		mainTable.row();
		mainTable.add(startButton).center().spaceBottom(20).size(buttonWidth, buttonHeight);
		mainTable.row();
		mainTable.add(quitButton).center().spaceBottom(20).size(buttonWidth, buttonHeight);		
	}

	public void render () {
		super.render();
	}
	
	public void dispose () {
		super.dispose();
	}
	
}
