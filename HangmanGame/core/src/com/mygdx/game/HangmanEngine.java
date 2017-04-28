package com.mygdx.game;

import java.util.*;
import java.lang.Character;

public class HangmanEngine {
	private String chosenWord;
	private String currentWord;
	private HashMap<Character, Character> currentGuesses = new HashMap<Character, Character>();
	private int guessTally = 0;
	private int wrongGuessTally = 0;
	private boolean isSolved = false;
	private String[] wordList = null;

	public HangmanEngine(String newWord)
	{
		populateWordList();
		newHangman(newWord);
	}

	public HangmanEngine()
	{
		populateWordList();
		newHangman(getRandomWord());
	}
	
	// Returns 0 if the letter isn't in the word
	// Returns 1 if the letter is in the word
	// Returns 2 if the letter has already been guessed
	// Returns 3 on any other condition
	public int guessLetter(String letter)
	{
		char guessedLetter = letter.charAt(0);
		guessedLetter = Character.toUpperCase(guessedLetter);

		this.guessTally++;

		if (this.currentGuesses.containsKey(guessedLetter)) {
			if (this.currentGuesses.get(guessedLetter) == guessedLetter) {
				wrongGuessTally++;
				return 2;
			} else {
				this.currentGuesses.put(guessedLetter, guessedLetter);

				if (this.chosenWord.contains(Character.toString(guessedLetter))) {
					addGuessedLetter(guessedLetter);
					if (!this.currentWord.contains("_")) {
						this.isSolved = true;
					}
					return 1;
				} else {
					wrongGuessTally++;
					return 0;
				}
			}
		} else {
			wrongGuessTally++;
			return 3;
		}
	}

	public String getCurrentWord()
	{
		return this.currentWord;
	}

	public String getChosenWord()
	{
		return this.chosenWord;
	}

	public int getCurrentTally()
	{
		return this.guessTally;
	}
	
	public int getWrongGuessTally() 
	{
		return this.wrongGuessTally;
	}

	public boolean isSolved()
	{
		return this.isSolved;
	}

	public void newHangman()
	{
		newHangman(getRandomWord());
	}

	public void newHangman(String newWord)
	{
		for (char letter = 'A'; letter <= 'Z'; letter++) {
			this.currentGuesses.put(letter, '_');
		}

		this.guessTally = 0;
		this.isSolved = false;

		this.chosenWord = newWord.toUpperCase();
		createCurrentWord();
	}

	private void addGuessedLetter(char letter)
	{
		StringBuilder str = new StringBuilder(this.currentWord);

		for (int i = 0; i < this.chosenWord.length(); i++) {
			if (this.chosenWord.charAt(i) == letter) {
				str.setCharAt(i, letter);
			}
		}

		this.currentWord = str.toString();
	}

	private void createCurrentWord() {
		StringBuilder str = new StringBuilder();

		for (int i = 0; i < this.chosenWord.length(); i++) {
			str.append('_');
		}

		this.currentWord = str.toString();
	}

	private String getRandomWord()
	{
		int rnd = new Random().nextInt(this.wordList.length);
		return this.wordList[rnd];
	}

	private void populateWordList()
	{
		this.wordList = new String[] {"ability", "accept", "according", "account", "across", 
				"action", "activity", "actually", "address", "affect", "against", "agency", 
				"agreement", "almost", "already", "although", "always", "American", "amount", 
				"analysis", "animal", "another", "answer", "anyone", "anything", "appear", 
				"approach", "around", "arrive", "article", "artist", "assume", "attack", 
				"attention", "attorney", "audience", "author", "authority", "available", 
				"beautiful", "because", "become", "before", "behavior", "behind", "believe", 
				"benefit", "better", "between", "beyond", "billion", "brother", "budget", 
				"building", "business", "camera", "campaign", "cancer", "candidate", "capital", 
				"career", "center", "central", "century", "certain", "certainly", "challenge", 
				"chance", "change", "character", "charge", "choice", "choose", "church",
				"citizen", "clearly", "collection", "college", "commercial", "common",
				"community", "company", "compare", "computer", "concern", "condition",
				"conference", "Congress", "consider", "consumer", "contain", "continue",
				"control", "country", "couple", "course", "create", "cultural", "culture",
				"current", "customer", "daughter", "debate", "decade", "decide", "decision",
				"defense", "degree", "Democrat", "democratic", "describe", "design", "despite",
				"detail", "determine", "develop", "development", "difference", "different",
				"difficult", "dinner", "direction", "director", "discover", "discuss",
				"discussion", "disease", "doctor", "during", "economic", "economy", "education",
				"effect", "effort", "either", "election", "employee", "energy", "enough",
				"entire", "environment", "environmental", "especially", "establish", "evening",
				"everybody", "everyone", "everything", "evidence", "exactly", "example",
				"executive", "expect", "experience", "expert", "explain", "factor", "family",
				"father", "federal", "feeling", "figure", "finally", "financial", "finger",
				"finish", "follow", "foreign", "forget", "former", "forward", "friend",
				"future", "garden", "general", "generation", "government", "ground",
				"growth", "happen", "health", "herself", "himself", "history", "hospital",
				"however", "hundred", "husband", "identify", "imagine", "impact", "important",
				"improve", "include", "including", "increase", "indeed", "indicate", "individual",
				"industry", "information", "inside", "instead", "institution", "interest",
				"interesting", "international", "interview", "investment", "involve", "itself",
				"kitchen", "knowledge", "language", "lawyer", "leader", "letter", "likely",
				"listen", "little", "machine", "magazine", "maintain", "majority", "manage",
				"management", "manager", "market", "marriage", "material", "matter", "measure",
				"medical", "meeting", "member", "memory", "mention", "message", "method",
				"middle", "military", "million", "minute", "mission", "modern", "moment",
				"morning", "mother", "movement", "myself", "nation", "national", "natural",
				"nature", "nearly", "necessary", "network", "newspaper", "nothing",
				"notice", "number", "office", "officer", "official", "operation",
				"opportunity", "option", "organization", "others", "outside", "painting",
				"parent", "participant", "particular", "particularly", "partner", "patient",
				"pattern", "people", "perform", "performance", "perhaps", "period", "person",
				"personal", "physical", "picture", "player", "police", "policy", "political",
				"politics", "popular", "population", "position", "positive", "possible", 
				"practice", "prepare", "present", "president", "pressure", "pretty", 
				"prevent", "private", "probably", "problem", "process", "produce", "product",
				"production", "professional", "professor", "program", "project", "property",
				"protect", "provide", "public", "purpose", "quality", "question", "quickly", 
				"rather", "reality", "realize", "really", "reason", "receive", "recent", "recently",
				"recognize", "record", "reduce", "reflect", "region", "relate", "relationship",
				"religious", "remain", "remember", "remove", "report", "represent",
				"Republican", "require", "research", "resource", "respond", "response", "result",
				"return", "reveal", "school", "science", "scientist", "season", "second", "section",
				"security", "senior", "series", "serious", "service", "several", "sexual", "should",
				"shoulder", "significant", "similar", "simple", "simply", "single", "sister",
				"situation", "social", "society", "soldier", "somebody", "someone", "something",
				"sometimes", "source", "southern", "special", "specific", "speech", "spring",
				"standard", "statement", "station", "strategy", "street", "strong", "structure",
				"student", "subject", "success", "successful", "suddenly", "suffer", "suggest",
				"summer", "support", "surface", "system", "teacher", "technology", "television",
				"themselves", "theory", "though", "thought", "thousand", "threat", "through", 
				"throughout", "together", "tonight", "toward", "traditional", "training", "travel",
				"treatment", "trouble", "understand", "usually", "various", "victim", "violence",
				"weapon", "weight", "western", "whatever", "whether", "window", "within", 
				"without", "wonder", "worker", "writer", "yourself"};
	}
}
