package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameScreen extends Screen {

	// originX & Y here are used to position the gallows & hangman
	private float originX = 75;
	private float originY = 175;
	
	private SpriteBatch batch;	
	private Texture gallows_Stage;
	private Sprite stage_Sprite;
	private Texture gallows_Post;
	private Sprite post_Sprite;
	private Texture gallows_Arm;
	private Sprite arm_Sprite;
	private Texture gallows_Noose;
	private Sprite noose_Sprite;
	
	private Sprite[] bodyParts;
	private Texture head;
	private Sprite head_Sprite;
	private Texture body;
	private Sprite body_Sprite;
	private Texture lArm;
	private Sprite lArm_Sprite;
	private Texture rArm;
	private Sprite rArm_Sprite;
	private Texture lLeg;
	private Sprite lLeg_Sprite;
	private Texture rLeg;
	private Sprite rLeg_Sprite;
	
	private Sprite[] wordBlanks;
	private Texture blank;
	private Sprite blank_Sprite;
	
	// used for testing wordBlanks spacing
	private String word = ""; 
	
	private HangmanEngine h;
	
	GameScreen(MyGdxGame game_) {
		super(game_);

		// Creating the hangman before other logic
		newGame();		
		
		// Create bodyParts array
		createBodyPartsArray();
		
		// Creates blanks array
		createBlanksArray();
		
		// Create gallows
		createGallows();
		
		// Create input functionality
		createInput();
		
		
	}
	
	private void newGame() {
		h = new HangmanEngine();
		word = h.getChosenWord();
		System.out.println("Chosen word is: " + h.getChosenWord());
	}
	
	private void createInput() {
		// Field Label
		Label inputLabel = new Label("Type Guess: ", skin);
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = font;
		labelStyle.fontColor = Color.BLACK;
		inputLabel.setStyle(labelStyle);
		inputLabel.setPosition(350, 210);
		stage.addActor(inputLabel);
		
		// TextField
		final TextField inputField = new TextField("", skin);
		inputField.setWidth(50);
		inputField.setPosition(485,200);
		stage.addActor(inputField);
		
		// Confirm label
		Label confLabel = new Label("Confirm Guess: ", skin);
		confLabel.setStyle(labelStyle);
		confLabel.setPosition(350, 150);
		stage.addActor(confLabel);
		
		// Confirm button
		final Button confButton = new Button();
		ButtonStyle bStyle = new ButtonStyle();
		bStyle.up = skin.getDrawable("round-gray");
		bStyle.down = skin.getDrawable("round-dark-gray");
		confButton.setStyle(bStyle);
		confButton.setPosition(525, 150);
		confButton.setSize(50, 25);
		confButton.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y) {
				h.guessLetter(inputField.getText());
				System.out.println("Current word is: " + h.getCurrentWord());
				
				// if letter is not in word: 
				//		add letter into letter array and update
				// if letter in word
				//   	add letter into letter array
				// 		draw letter above blanks
				
				// end game condition				
				if ( h.isSolved() || h.getWrongGuessTally() == 6) {
					MyGdxGame.setScreen(new GameOverScreen());
				}
			}
		});
		stage.addActor(confButton);		
	}
	
	private void createGallows() {
		gallows_Stage = new Texture(Gdx.files.internal("HangmanPieces/Hangman_0001s_0003_Stage.png"));
		stage_Sprite = new Sprite(gallows_Stage);
		gallows_Post = new Texture(Gdx.files.internal("HangmanPieces/Hangman_0001s_0002_Post.png"));
		post_Sprite = new Sprite(gallows_Post);
		gallows_Arm = new Texture(Gdx.files.internal("HangmanPieces/Hangman_0001s_0001_Arm.png"));
		arm_Sprite = new Sprite(gallows_Arm);
		gallows_Noose = new Texture(Gdx.files.internal("HangmanPieces/Hangman_0001s_0000_Noose.png"));
		noose_Sprite = new Sprite(gallows_Noose);
		batch = new SpriteBatch();
	}
	
	/**
	 * This method will load all textures and create all blanks Sprites, then 
	 * @return the @param wordBlanks which is a Sprite[]
	 */
	private Sprite[] createBlanksArray() {
		wordBlanks = new Sprite[word.length()];
		
		for(int i = 0; i < word.length(); i ++) {
			blank = new Texture(Gdx.files.internal("HangmanPieces/Hangman_0002s_0005_LetterSpace.png"));
			blank_Sprite = new Sprite(blank);
			blank_Sprite.setOrigin(75 + 49*i,  50);
			//(328 - (25 * word.length())) + (49 * i)
			wordBlanks[i] = blank_Sprite;
		}	
		
		return wordBlanks;	
	}
	
	// draws blanks for word
	private void drawBlanks() {
		for(int i = 0; i < word.length(); i ++){
			batch.draw(wordBlanks[i], wordBlanks[i].getOriginX(), wordBlanks[i].getOriginY());
		}
	}
	
	// draws the gallows at the origin points	
	private void drawGallows() {
		// All logic for organizing and drawing gallows
		batch.draw(stage_Sprite, originX, originY, 192, 58);
		batch.draw(post_Sprite, originX+150, originY+58, 10, 228);
		batch.draw(arm_Sprite, (originX+150)-80, (originY+58+228)-55, 88, 55);
		batch.draw(noose_Sprite, (originX+150)-100, ((originY+58+228)-55)-30, 43, 75);
	}
	
	/**
	 * This method will load all textures and create all Sprites, then 
	 * @return the @param bodyParts which is a Sprite[]
	 */
	private Sprite[] createBodyPartsArray() {
		// 230, 125
		bodyParts = new Sprite[6];
		head = new Texture(Gdx.files.internal("HangmanPieces/Hangman_0000s_0004_Head.png"));
		head_Sprite = new Sprite(head);
		head_Sprite.setOrigin(originX + 55, originY + 200);
		
		bodyParts[0] = head_Sprite;
		body = new Texture(Gdx.files.internal("HangmanPieces/Hangman_0000s_0005_Body.png"));
		body_Sprite = new Sprite(body);
		body_Sprite.setOrigin(originX + 70, originY + 115);
		//300, 240
		
		bodyParts[1] = body_Sprite;
		lArm = new Texture(Gdx.files.internal("HangmanPieces/Hangman_0000s_0001_LeftArm.png"));
		lArm_Sprite = new Sprite(lArm);
		lArm_Sprite.setOrigin(originX + 27, originY + 170);
		//257, 295
		
		bodyParts[2] = lArm_Sprite;
		rArm = new Texture(Gdx.files.internal("HangmanPieces/Hangman_0000s_0000_RightArm.png"));
		rArm_Sprite = new Sprite(rArm);
		rArm_Sprite.setOrigin(originX + 72, originY + 170);
		bodyParts[3] = rArm_Sprite;
		//302, 295
		
		lLeg = new Texture(Gdx.files.internal("HangmanPieces/Hangman_0000s_0003_LeftLeg.png"));
		lLeg_Sprite = new Sprite(lLeg);
		lLeg_Sprite.setOrigin(originX + 42, originY + 77);
		bodyParts[4] = lLeg_Sprite;
		//272, 202
		
		rLeg = new Texture(Gdx.files.internal("HangmanPieces/Hangman_0000s_0002_RightLeg.png"));
		rLeg_Sprite = new Sprite(rLeg);
		rLeg_Sprite.setOrigin(originX + 70, originY + 77);
		bodyParts[5] = rLeg_Sprite;		
		//300, 202
		return bodyParts;
	}
	
	private void drawBodyParts(int wrongGuessNum) {		
		// logic for drawing body parts goes here
		// Body parts have to have origin set before this method will work correctly
		for (int i = 0; i < wrongGuessNum; i++) {
			// 
			batch.draw(bodyParts[i], bodyParts[i].getOriginX(), bodyParts[i].getOriginY());
		}
	}	
	
	public void render() {
		super.render();	
		batch.begin();
		drawGallows();
		drawBodyParts(h.getWrongGuessTally());
		drawBlanks();
		batch.end();
	}
	
	public void dispose() {
		super.dispose();
	}
	
	
}
