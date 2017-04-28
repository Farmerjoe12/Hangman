package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public abstract class Screen{
	//D:/EclipseWorkspace/SER215/HangmanGame/core/assets/	
	
	protected float buttonWidth, buttonHeight;
	
	protected TextureAtlas atlas;
	protected Skin skin;
	protected Stage stage;
	protected SpriteBatch batch;
	protected Texture background;
	protected Sprite bgSprite;
	protected ImageTextButtonStyle style;
	protected BitmapFont font;
	protected ImageTextButton quitButton;
	protected Table mainTable;
	protected LabelStyle titleStyle;
	protected Label title;
	
	protected static MyGdxGame game;
	//protected AssetManager manager;
	
	Screen(MyGdxGame game_){
		game = game_;
		// Button Size variables
		buttonWidth = 175;
		buttonHeight = 40;	
		
		// Background creation
		batch = new SpriteBatch();
		background = new Texture(Gdx.files.internal("HangmanPieces/Hangman_0000_Background.png"));
		bgSprite = new Sprite(background);		
		
		// Stage, atlas, and skin creation
		stage = new Stage(new ScreenViewport());		
		atlas = new TextureAtlas(Gdx.files.internal("data/plain-james-ui.atlas"));
		skin = new Skin(Gdx.files.internal("data/plain-james-ui.json"));
		skin.addRegions(atlas);	

		// Table creation
		mainTable = new Table();
		mainTable.setFillParent(true);
		stage.addActor(mainTable);	

		// Button styling
		style = new ImageTextButtonStyle();
		style.up = skin.getDrawable("round-gray");
		style.down = skin.getDrawable("round-dark-gray");
		font = new BitmapFont();
		font = skin.getFont("font");
		style.font = font;
		
		// Label styling
		BitmapFont largeFont = new BitmapFont(
				Gdx.files.internal("data/font-title-export.fnt"), 
				Gdx.files.internal("data/font-title-export.png"), false);		
		titleStyle = new LabelStyle(
				largeFont, new Color(0f, 0f, 0f, 1f));
		// Create the quit button to be used on multiple screens		
		quitButton = new ImageTextButton("Quit", style);
		quitButton.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y) {
				// Here is where we'll quit the game
				game.dispose();
			}
		});
		
		// This method sets the input processor to the stage, this allows for 
		// easy and efficient input handling from within the game
		Gdx.input.setInputProcessor(stage);
		
		// This method sets the rendering of the game so that it only renders
		// when there's an update  (key press, mouse event, etc.)
		// Used to be mindful of resources
		Gdx.graphics.setContinuousRendering(false);
	}
	
	
	protected void render() {
		batch.begin();
		bgSprite.draw(batch);
		batch.end();
		stage.draw();
	}
	
	protected void dispose() {
		atlas.dispose();
		skin.dispose();
		batch.dispose();
		background.dispose();
		font.dispose();
		//manager.dispose();
		stage.dispose();
	}
}
