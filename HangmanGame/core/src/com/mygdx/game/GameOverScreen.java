package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameOverScreen extends Screen{

	private ImageTextButton playAgain;
	
	GameOverScreen() {
		super(game);
		
		// Title creation
		title = new Label("Game Over!", skin);
		title.setStyle(titleStyle);
		
		// Button creation
		playAgain = new ImageTextButton("Play Again!",style);
		playAgain.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y) {
				// change the screen to a new GameScreen
				MyGdxGame.setScreen(new GameScreen(game));
			}
		});
		
		// Adding elements to the screen
		mainTable.add(title).center().spaceBottom(100);
		mainTable.row();
		mainTable.add(playAgain).center().spaceBottom(20).size(buttonWidth,buttonHeight);
		mainTable.row();
		mainTable.add(quitButton).center().spaceBottom(20).size(buttonWidth,buttonHeight);
	}
	
	public void render () {
		super.render();
	}
	
	public void dispose () {
		super.dispose();
	}
}
