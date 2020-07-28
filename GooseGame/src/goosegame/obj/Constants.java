package goosegame.obj;

public final class Constants {
	//cast constants
	public static final String CAST_STANDARD_GAME = "STANDARD_GAME";

	//possible BOARD STATE
	public static final String STATE_BOARD_INIT = "INIT";
	public static final String STATE_BOARD_EMPTY = "EMPTY";
	public static final String STATE_BOARD_START = "START";
	
	//possible GAME STATE
	public static final String STATE_GAME_INIT = "INIT";
	public static final String STATE_GAME_READY = "READY";
	public static final String STATE_GAME_PLAY = "PLAY";
	public static final String STATE_GAME_END = "END";
	
	//standard parameter	
	public static final Integer[] STANDARD_GOOSES = {5,9,14,18,23,27};
	public static final Integer[] STANDARD_BRIDGES = {6,12};
	
	//system.out GENERAL TEXT IT
	public static final String TEXT_SYS_IT_S = "S";
	public static final String TEXT_SYS_IT_N = "N";
	public static final String TEXT_SYS_IT_S_MINOR = "s";
	public static final String TEXT_SYS_IT_N_MINOR = "n";
	public static final String TEXT_SYS_IT_ARROW = " --> ";
	public static final String TEXT_SYS_IT_ERROR_INPUT = "Risposta non corretta, si prega di rispondere quanto atteso.";
	public static final String TEXT_SYS_IT_CHOOSE_INTERFACE = "Buongiorno, il gioco presenta l'interfaccia testuale e quella html, vuoi continuare con quella testuale? S/N";
		
	//system.out TEXT IT CREATE GAME
	public static final String TEXT_SYS_IT_WELCOME = "Buongiorno, vuoi inizializzare un gioco standard con oche e ponti già preconfigurati? S/N";
	public static final String TEXT_SYS_IT_BRIDGES_WANT = "Si vuole inserire nel gioco un ponte in posizione personalizzata? S/N";
	public static final String TEXT_SYS_IT_BRIDGES_INSERT = "inserisci il numero delle casella (1-62) da cui parte il ponte e il numero della casella di destinazione del ponte (2-63) separati da virgola. Il ponte deve essere unico per ogni casella indicata.";
	public static final String TEXT_SYS_IT_BRIDGES_INSERT_RESULT = "Inserito il ponte tra le caselle ";
	public static final String TEXT_SYS_IT_BRIDGES_INSERT_ERROR_RESULT = "Inserito ponte in una casella già occupata.";
	public static final String TEXT_SYS_IT_GOOSES_WANT = "Si vuole inserire nel gioco una casella oca in posizione personalizzata? S/N";
	public static final String TEXT_SYS_IT_GOOSES_INSERT = "inserisci il numero della casella (1-62) in cui vuoi far comparire un'oca.";
	public static final String TEXT_SYS_IT_GOOSES_INSERT_RESULT = "Inserita oca alle casella ";
	public static final String TEXT_SYS_IT_GOOSES_INSERT_ERROR_RESULT = "Inserita oca in una casella già occupata.";

	//system.out TEXT IT ADD PLAYER
	public static final String TEXT_SYS_IT_ALMOST_READY = "Siamo quasi pronti a giocare. Devi inserire i giocatori (almeno 2).";
	public static final String TEXT_SYS_IT_PLAYER_INSERT = "Inserisci il nome del giocatore con il comando: add \"nome_giocatore\"";
	public static final String TEXT_SYS_IT_PLAYER_WANT_MORE = "Hai inserito come giocatori $, ne vuoi aggiungere un'altro? S/N";
	public static final String TEXT_SYS_IT_PLAYER_ALREADY_EXIST = ": il nome è già stato utilizzato per un altro giocatore. Inserisci un altro nome.";
	public static final String TEXT_SYS_IT_PLAYER_RESULT = "Giocatori: ";
	
	//system.out TEXT IT GAME
	public static final String TEXT_SYS_IT_GAME_BEGIN = "Gioco pronto per esser giocato. Buon divertimento.";
	public static final String TEXT_SYS_IT_GAME_PLAY_FORMULA = "Per giocare utilizzare la formula: \"move <nome_giocatore> X, Y\" con X e Y i valori dei dadi tirati dal giocatore. \nSe non si utilizzano dadi fisici il sistema tirerà il dado per il giocatore.";
	public static final String TEXT_SYS_IT_GAME_PLAY_RESULT_DICE = "playName tira rollDice. playName si sposta da positionFrom a positionTo";
	public static final String TEXT_SYS_IT_GAME_PLAY_RESULT_GOOSE = ", l'Oca. playName si muove ancora e va a positionTo";
	public static final String TEXT_SYS_IT_GAME_PLAY_RESULT_BRIDGE = "al Ponte. playName salta a positionTo";
	public static final String TEXT_SYS_IT_GAME_PLAY_RESULT_OVERBOARD = ". playName rimbalza! playName ritorna in positionTo";
	public static final String TEXT_SYS_IT_GAME_PLAY_RESULT_WIN = ". playName Wins!!";
	
	//system.out TEXT IT STEP REASON
	public static final String TEXT_SYS_IT_STEP_REASON_DICE = "DICE";
	public static final String TEXT_SYS_IT_STEP_REASON_GOOSE = "GOOSE";
	public static final String TEXT_SYS_IT_STEP_REASON_BRIDGE = "BRIDGE";
	public static final String TEXT_SYS_IT_STEP_REASON_OVERBOARD = "OVERBOARD";
	public static final String TEXT_SYS_IT_STEP_REASON_WIN = "WIN";
}
