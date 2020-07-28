package goosegame.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import goosegame.exception.PlayerAlreadyExistsException;
import goosegame.obj.Board;
import goosegame.obj.Bridge;
import goosegame.obj.Constants;
import goosegame.obj.Move;
import goosegame.obj.Player;
import goosegame.obj.Step;

public class Play {
	
	private Board board;
	private List<Player> players;
	private String state;
	private Move move;
	
	/*
	 * Constructor
	 */
	public Play() {
		this.players= new ArrayList<Player>();
		setState(Constants.STATE_GAME_INIT);
	}
	
	/*
	 * Method to initialize the board
	 * @Param:
	 *       boolean - if is a standard board
	 */
	public void InitBoard(boolean input) {		
		//initialize the game board
		if(input) {//Standard board
			board = new Board(true);
		} else {//Customized board
			board = new Board(false);
		} 
	}

	/*
	 * Method to chek if the board si ready or need initialization
	 * 
	 * @return 
	 * 		boolean - if ready
	 */
	public boolean isBoardReadytoPlay() {
		if(board==null || board.getState()==null || board.getState().equals(Constants.STATE_BOARD_EMPTY)) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * Method to add a bridge to the board
	 * 
	 * @Param:
	 * 		int[] - the anchor of the new bridge
	 */
	public void addBridge(int[] points) {
		board.addBridges(points[0], points[1]);
	}
	
	/*
	 * Method to add a goose to the board
	 * 
	 * @Param:
	 * 		int - the cell of the goose
	 */
	public void addGoose(int cell) {
		board.addGooses(new Integer(cell));;
	}

	/*
	 * Method to add a player to the board
	 * 
	 * @Param:
	 * 		String - the input inserted by the user
	 * @Return
	 * 		boolean - if the player is valid
	 * @Throws
	 * 		PlayerAlreadyExistsException if a player with the same name is already used
	 */
	public boolean insertPlayer(String input) throws PlayerAlreadyExistsException {
		if(validatePlayer(input)) {
			String name = input.substring(4);
			if(uniqueAddToList(this.players,new Player(name))) {
				if(this.players.size()>=2) {
					setState(Constants.STATE_GAME_READY);
				}
				return true;
			} else {
				throw new PlayerAlreadyExistsException(name);
			}
		}
		return false;
	}

	/*
	 * Method to check if the play is valid
	 * 
	 * @Param
	 * 		String - the play input
	 * 
	 * @Return
	 * 		boolean - if is ok
	 */
	public boolean play(String input) {
		//validate formula
		String[] splitInput = input.split(" ");
		move = new Move();
		if(validatePlay(input)) {
			//check and set dice
			if(!isInputWithDiceRolled(input)){
				//roll dice
				for (int i = 0; i < move.getDice().length; i++) {
					move.setDice(i, (int) Math.round(Math.random()*5+1));
				}
			} else {
				//extract dice from input
				if(splitInput.length==3){//dice roll only comma separated
					String[] dicesInput = splitInput[2].split(",");
					move.setDice(0, Integer.valueOf(dicesInput[0]));
					move.setDice(1, Integer.valueOf(dicesInput[1]));
				} else if(splitInput.length==4) {//dice roll comma-space separated
					move.setDice(0, Integer.valueOf(splitInput[2].substring(0, 1)));
					move.setDice(1, Integer.valueOf(splitInput[3]));
				}//end if splitInput
			}//end else inputWithRoll
			
			//move player
			executeMove(Constants.TEXT_SYS_IT_STEP_REASON_DICE, null);
			
			return true;
		}//end validatePlay
		
		return false;
	}

	/*
	 * Recursive Method to execute the play
	 * 
	 * @Param
	 * 		String - reason of the play
	 * 		Bridge - if a player jump on a bridge
	 */
	private void executeMove(String reason, Bridge bridgePar) {
		//change player position
		int newPosition = 0;
		int bounceStep = 0;
		switch (reason) {
			case Constants.TEXT_SYS_IT_STEP_REASON_BRIDGE:
				newPosition = bridgePar.getPointB();
				move.getPlayer().setPosition(newPosition);
				move.addStep(new Step(newPosition, reason));
				break;
			case Constants.TEXT_SYS_IT_STEP_REASON_OVERBOARD:
				bounceStep = move.getPlayer().getPosition() - 63;
				newPosition = 63 - bounceStep;
				move.updateLastStep(63);
				move.addStep(new Step(newPosition,reason));
				return;
			default:
				newPosition = move.getPlayer().getPosition() + move.getDice(0) + move.getDice(1);
				move.getPlayer().setPosition(newPosition);
				move.addStep(new Step(newPosition, reason));
				break;				
		}
		//check goose
		for (Iterator<Integer> i = board.getGooses().iterator(); i.hasNext();) {
			Integer goose = (Integer) i.next();
			if(newPosition==goose.intValue()) {
				executeMove(Constants.TEXT_SYS_IT_STEP_REASON_GOOSE, null);
				return;
			}
		}
		//check bridge
		for (Iterator<Bridge> i = board.getBridges().iterator(); i.hasNext();) {
			Bridge bridge = (Bridge) i.next();
			if(newPosition==bridge.getPointA()) {
				executeMove(Constants.TEXT_SYS_IT_STEP_REASON_BRIDGE, bridge);
				return;
			}
		}
		//check overboard
		if(newPosition > 63) {
			executeMove(Constants.TEXT_SYS_IT_STEP_REASON_OVERBOARD,null);
			return;
		}
		//check win
		if(newPosition == 63) {
			move.addStep(new Step(63,Constants.TEXT_SYS_IT_STEP_REASON_WIN));
			return;
		} 
	}

	/*
	 * Method to validate the input for the play
	 * 
	 * @Param
	 * 		String - input string
	 */
	private boolean validatePlay(String input) {
		//input match the formula
		if(input!=null && input.matches("(move )(\\w|è|ò|à|ù|ì)+(( )[1-6](,)( )?[1-6])?")) {
			boolean playerMatch = false;
			//check if is a insert player
			for (Iterator<Player> i = players.iterator(); i.hasNext();) {
				Player player = (Player) i.next();
				playerMatch = input.contains(player.getName());
				if(playerMatch) {
					//init move
					this.move = new Move(player);
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * Method to verify if a play has the dice result
	 */
	private boolean isInputWithDiceRolled(String input) {
		if(input!=null) {
			String temp = input.substring(5+this.move.getPlayer().getName().length());
			if(temp.matches("( )([1-6](,)( )?[1-6])")) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Method to check if a player is already inserted
	 * 
	 * @Param
	 * 		List - list of player
	 * 		Palyer - new player
	 */
	private boolean uniqueAddToList(List<Player> playersPar, Player player) {
		for (Iterator<Player> i = playersPar.iterator(); i.hasNext();) {
			Player tempPlayer = (Player) i.next();
			if(tempPlayer.equals(player)) {
				return false;
			}
		}
		playersPar.add(player);
		return true;
	}

	/*
	 * Method to check if the input for the Bridge is valid
	 * 
	 * @Param 
	 * 		String - input for the bridge
	 */
	public boolean validateBridge(String s) {
		//check in s is null or contain a split char
		if(s==null || !s.contains(",")) {
			return false;
		} 
		String[] split = s.split(",");
		// check there are 2 element
		if (split.length!=2) {
			return false;
		}
		try {
			//check if the input are number and in range, different from 0 and between them
			int[] result = {0,0};
			result[0] = Integer.parseInt(split[0]);
			result[1] = Integer.parseInt(split[1]);
			if((result[0]==0 || result[1]==0) &&
					!(1 >= result[0] || result[0] >= 62) &&
					!(1 >= result[1] || result[1] >= 62) &&
					result[0]==result[1]) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}			 
	}

	/*
	 * Method to check if the input for the Goose is valid
	 * 
	 * @Param 
	 * 		String - input for the goose
	 */
	public boolean validateGoose(String s) {
		// check if input is null
		if(s==null) {
			return false;
		} 
		
		try {
			//check if the input is a number and in range
			int result = Integer.parseInt(s);
			if(1 >= result || result >= 62) {
				return false;
			}
			return true;
		} catch (Exception e) {
			//the input is not e number
			return false;
		}			 
	}

	/*
	 * Method to check if the input for the player is valid
	 * 
	 * @Param 
	 * 		String - input for the player
	 */
	public boolean validatePlayer(String s) {
		// check if input is null
		if(s==null) {
			return false;
		} 
		//check if the formula is correct
		if(!s.matches("(add )((\\w|è|ò|à|ù|ì)+|( ))+")){
			return false;
		}
		return true;
	}

	//Getter
	public String getState() {
		return state;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public Move getMove() {
		return move;
	}

	//Setter
	public void setState(String state) {
		this.state = state;
	}

	
}
