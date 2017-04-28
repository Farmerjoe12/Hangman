package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class GameScreen extends Screen {

	private Table mainTable;
	private Table gallows;
	private Table chosen; 
	private Table input;
	private Table blanks;
	
	private SpriteBatch batch;	
	private Texture gallows_Stage;
	private Sprite stage_Sprite;
	private Texture gallows_Post;
	private Sprite post_Sprite;
	private Texture gallows_Arm;
	private Sprite arm_Sprite;
	private Texture gallows_Noose;
	private Sprite noose_Sprite;
	
	GameScreen(MyGdxGame game_) {
		super(game_);
		
		// Here are the tables that may or may not be used for organization
		mainTable = new Table();
		gallows = new Table();
		chosen = new Table();
		input = new Table();
		blanks = new Table();
		
		// Here are the textures and sprites for the gallows parts
		gallows_Stage = new Texture(Gdx.files.internal("HangmanPieces/Hangman_0001s_0003_Stage.png"));
		stage_Sprite = new Sprite(gallows_Stage);
		gallows_Post = new Texture(Gdx.files.internal("HangmanPieces/Hangman_0001s_0002_Post.png"));
		post_Sprite = new Sprite(gallows_Post);
		gallows_Arm = new Texture(Gdx.files.internal("HangmanPieces/Hangman_0001s_0001_Arm.png"));
		arm_Sprite = new Sprite(gallows_Arm);
		gallows_Noose = new Texture(Gdx.files.internal("HangmanPieces/Hangman_0001s_0000_Noose.png"));
		noose_Sprite = new Sprite(gallows_Noose);
		batch = new SpriteBatch();
		
		// adding the mainTable to the stage
		stage.addActor(mainTable);
		mainTable.setFillParent(true);
		
		// initializing the gallows and organizing on the "Stage"
		gallows.setFillParent(false);		
		mainTable.add(gallows).top().left();
	}
	
		/** drawGallows is used to position the gallows on the screen absolutely
		 * @param originX 
		 * @param originY
		 * 		originX & Y are used as the origin points to draw the gallows
		 * 			where 0,0 is the bottom left corner of the screen
		 * **/
	public void drawGallows(float originX, float originY) {
		// All logic for organizing and drawing gallows
		batch.begin();
		batch.draw(stage_Sprite, originX, originY, 192, 58);
		batch.draw(post_Sprite, originX+150, originY+58, 10, 228);
		batch.draw(arm_Sprite, (originX+150)-80, (originY+58+228)-55, 88, 55);
		batch.draw(noose_Sprite, (originX+150)-100, ((originY+58+228)-55)-30, 46, 77);
		batch.end();
	}
	
	public void render() {
		super.render();	
		drawGallows(75,150);
	}
	
	public void dispose() {
		super.dispose();
	}
	
	
}
