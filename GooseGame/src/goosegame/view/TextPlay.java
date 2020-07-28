package goosegame.view;

import java.util.Iterator;
import java.util.Scanner;

import goosegame.controller.Play;
import goosegame.exception.PlayerAlreadyExistsException;
import goosegame.obj.Constants;
import goosegame.obj.Player;
import goosegame.obj.Step;

public class TextPlay {
	
	private static Scanner sc;
	private static Play game;

	/*
	 * Principal Method to run the project
	 */
	public static void main(String[] args) {
		try {
			//init the scanner
			sc = new Scanner(System.in);
			game = new Play();
			/*
			//Choose interface
			System.out.println(Constants.TEXT_SYS_IT_CHOOSE_INTERFACE);
			boolean textPlay = checkInput();
			if (!textPlay) {//Se non gioco in testuale passo il controllo all'altra interfaccia
				//TODO
			}*/
			
			//cast the game board
			System.out.println(Constants.TEXT_SYS_IT_WELCOME);
			game.InitBoard(checkInputSN(Constants.TEXT_SYS_IT_WELCOME));
			//customize gooses and bridge
			if(!game.isBoardReadytoPlay()) {
				//Cycle for bridges
				while(true) {
					System.out.println(Constants.TEXT_SYS_IT_BRIDGES_WANT);
					if(checkInputSN(Constants.TEXT_SYS_IT_BRIDGES_WANT)) {
						System.out.println(Constants.TEXT_SYS_IT_BRIDGES_INSERT);
						int[] cells = checkInputBridge(Constants.TEXT_SYS_IT_BRIDGES_INSERT);
						try{
							//insert the bridge
							game.addBridge(cells);
							//alert the result
							String res = Constants.TEXT_SYS_IT_BRIDGES_INSERT_RESULT + cells[0] + Constants.TEXT_SYS_IT_ARROW + cells[1];
							System.out.println(res);
						} catch (Exception e) {
							//element already into the set
							System.out.println(Constants.TEXT_SYS_IT_BRIDGES_INSERT_ERROR_RESULT);
						}
					} else {
						break;
					}//end if inputSN
				}//end while bridge
				//Cycle for gooses
				while(true) {
					System.out.println(Constants.TEXT_SYS_IT_GOOSES_WANT);
					if(checkInputSN(Constants.TEXT_SYS_IT_GOOSES_WANT)) {
						System.out.println(Constants.TEXT_SYS_IT_GOOSES_INSERT);
						int cell = checkInputGoose(Constants.TEXT_SYS_IT_GOOSES_INSERT);
						try{
							game.addGoose(cell);
							//alert the result
							String res = Constants.TEXT_SYS_IT_GOOSES_INSERT_RESULT + cell;
							System.out.println(res);
						} catch (Exception e) {
							//element already into the set
							System.out.println(Constants.TEXT_SYS_IT_GOOSES_INSERT_ERROR_RESULT);
						}
					} else {
						break;
					}//end if inputSN
				}//end while gooses
			}//end if game empty
			
			//Populate players
			System.out.println(Constants.TEXT_SYS_IT_ALMOST_READY);
			boolean cycleCheck = true;
			do {
				System.out.println(Constants.TEXT_SYS_IT_PLAYER_INSERT);
				inputPlayer(Constants.TEXT_SYS_IT_PLAYER_INSERT);
				//Update cycleCheck var
				cycleCheck = game.getState().equals(Constants.STATE_GAME_READY);
				if(cycleCheck) {
					String[] wantMore = Constants.TEXT_SYS_IT_PLAYER_WANT_MORE.split("\\$");
					boolean first = true;
					for (Iterator<Player> i = game.getPlayers().iterator(); i.hasNext();) {
						Player player = i.next();
						wantMore[0]  += !first? ", " + player.getName() : player.getName();
						first = false;
					} //end for
					System.out.println(wantMore[0] + " " + wantMore[1]);
					cycleCheck = !checkInputSN(Constants.TEXT_SYS_IT_PLAYER_WANT_MORE);
				}//end if
			} while(!cycleCheck);//end do-while
						
			//play
			game.setState(Constants.STATE_GAME_PLAY);
			System.out.println(Constants.TEXT_SYS_IT_GAME_BEGIN);
			int countPlay = 0;
			do {
				//play cycle
				if(countPlay%5 == 0) {//expose the rule every 5 play
					System.out.println(Constants.TEXT_SYS_IT_GAME_PLAY_FORMULA);
				}//end if
				inputPlay(Constants.TEXT_SYS_IT_GAME_PLAY_FORMULA);
				countPlay++;
			} while(game.getState().equals(Constants.STATE_GAME_PLAY));
			
			
		}catch (Throwable e) {
			System.out.println("ERROR: " + e.getMessage());
		} finally {
			sc.close();
		}
	}
	
	/*
	 * Method to check if the input is S or N 
	 */
	private static boolean checkInputSN(String sendedMessage) {
		int count = 0;
		//Cycle on input
		while(true) {
			String s = sc.nextLine();
			//check if S/s
			if(s!=null && (s.equals(Constants.TEXT_SYS_IT_S) || s.equals(Constants.TEXT_SYS_IT_S_MINOR))) {
				return true;
			} else if(s!=null && (s.equals(Constants.TEXT_SYS_IT_N) || s.equals(Constants.TEXT_SYS_IT_N_MINOR))) {//check if N/n
				return false;
			} else {//value wrong, stay in the circle
				System.out.println(Constants.TEXT_SYS_IT_ERROR_INPUT);
				count++;
				//every 5 error show the original question
				if(count==5) {
					System.out.println(sendedMessage);
					count = 0;
				}// end if(count)
			}//end if 
		} //end while
	}
	
	/*
	 * Method to check and validate the input of a bridge
	 * @Param:
	 * 		String - the original message of the request
	 * @return: 
	 * 		int[] - the 2 point of the bridge
	 */
	private static int[] checkInputBridge(String sendedMessage) {
		int count = 0;
		boolean isOK = true;
		while(true) {
			//wrong value
			if(!isOK){
				System.out.println(Constants.TEXT_SYS_IT_ERROR_INPUT);
				count++;
				//every 5 error show the original question
				if(count==5) {
					System.out.println(sendedMessage);
					count = 0;
				}
			}//end if 
			//read new input
			String s = sc.nextLine();
			//validate the input
			isOK = game.validateBridge(s);
			if(isOK) {
				int[] result = {0,0};
				String[] split = s.split(",");
				result[0] = Integer.parseInt(split[0]);
				result[1] = Integer.parseInt(split[1]);
				return result;
			}//end if
		} //end while
	}
	
	/*
	 * Method to check and validate the input of a goose
	 * @Param:
	 * 		String - the original message of the request
	 * @return: 
	 * 		int - the cell of the goose
	 */
	private static int checkInputGoose(String sendedMessage) {
		int count = 0;
		boolean isOK = true;
		while(true) {
			//wrong value
			if(!isOK){
				System.out.println(Constants.TEXT_SYS_IT_ERROR_INPUT);
				count++;
				//every 5 error show the original question
				if(count==5) {
					System.out.println(sendedMessage);
					count = 0;
				}
			}//end if 
			//read new input
			String s = sc.nextLine();
			//validate the input
			isOK = game.validateGoose(s);
			if(isOK) {//if goose is valide
				return Integer.parseInt(s);
			}//end if(isOK)
		} //end while
	}
	
	/*
	 * Method to check and insert a player
	 * @Param:
	 * 		String - the original message of the request
	 * @return: 
	 * 		boolean - is the player valid
	 */
	private static boolean inputPlayer(String sendedMessage) {
		int count = 0;
		boolean isOK = true;
		while(true) {
			//wrong value
			if(!isOK){
				System.out.println(Constants.TEXT_SYS_IT_ERROR_INPUT);
				count++;
				//every 5 error show the original question
				if(count==5) {
					System.out.println(sendedMessage);
					count = 0;
				}
			}//end if 
			//read new input
			String s = sc.nextLine();
			//validate the input
			try {
				if(game.insertPlayer(s)){
					String result = Constants.TEXT_SYS_IT_PLAYER_RESULT;
					boolean first = true;
					//list the player inserted
					for (Iterator<Player> i = game.getPlayers().iterator(); i.hasNext();) {
						Player player = i.next();
						result += !first? ", " + player.getName() : player.getName();
						first = false;
					}//end for on player
					System.out.println(result);
					return true;
				} else {//if player is not valid
					isOK = false;
				}//end else
			} catch (PlayerAlreadyExistsException e) {
				System.out.println(s + Constants.TEXT_SYS_IT_PLAYER_ALREADY_EXIST);
			}
		} //end while
	}
	
	/*
	 * Method to check and insert a play
	 * @Param:
	 * 		String - the original message of the request
	 * @return: 
	 * 		boolean - true if OK
	 */
	private static boolean inputPlay(String sendedMessage) {
		int count = 0;
		boolean isOK = true;
		while(true) {
			//check wrong value
			if(!isOK){
				System.out.println(Constants.TEXT_SYS_IT_ERROR_INPUT);
				count++;
				//every 5 error show the original question
				if(count==5) {
					System.out.println(sendedMessage);
					count = 0;
				}
			}//end if 
			//read new input
			String s = sc.nextLine();
			//validate the input
			if(game.play(s)){
				String result = "";
				//build the result of the play
				for (Iterator<Step> i = game.getMove().getSteps().iterator(); i.hasNext();) {
					Step step = (Step) i.next();
					String temp = "";
					switch (step.getStepReason()) {
						case Constants.TEXT_SYS_IT_STEP_REASON_DICE:
							//playName tira rollDice. playName si sposta da positionFrom a positionTo
							temp = Constants.TEXT_SYS_IT_GAME_PLAY_RESULT_DICE;
							temp = temp.replaceAll("playName", game.getMove().getPlayer().getName());
							String dice = game.getMove().getDice()[0] + ", " + game.getMove().getDice()[1];
							temp = temp.replace("rollDice", dice);
							temp = temp.replace("positionFrom", String.valueOf(game.getMove().getOldPosition()));
							temp = temp.replace("positionTo", String.valueOf(step.getNewPosition()));
							result += temp;
							break;
						case Constants.TEXT_SYS_IT_STEP_REASON_BRIDGE:
							// al Ponte. playName salta a positionTo
							temp = Constants.TEXT_SYS_IT_GAME_PLAY_RESULT_BRIDGE;
							temp = temp.replaceAll("playName", game.getMove().getPlayer().getName());
							temp = temp.replace("positionTo", String.valueOf(step.getNewPosition()));
							result = result.substring(0, result.lastIndexOf(" a "));
							result += temp;
							break;
						case Constants.TEXT_SYS_IT_STEP_REASON_GOOSE:
							//, l'Oca. playName si muove ancora e va a positionTo
							temp = Constants.TEXT_SYS_IT_GAME_PLAY_RESULT_GOOSE;
							temp = temp.replaceAll("playName", game.getMove().getPlayer().getName());
							temp = temp.replace("positionTo", String.valueOf(step.getNewPosition()));
							result += temp;
							break;
						case Constants.TEXT_SYS_IT_STEP_REASON_OVERBOARD:
							//. playName rimbalza! playName ritorna in positionTo
							temp = Constants.TEXT_SYS_IT_GAME_PLAY_RESULT_OVERBOARD;
							temp = temp.replaceAll("playName", game.getMove().getPlayer().getName());
							temp = temp.replace("positionTo", String.valueOf(step.getNewPosition()));
							result += temp;
							break;
						case Constants.TEXT_SYS_IT_STEP_REASON_WIN:
							//. playName Wins!!
							temp = Constants.TEXT_SYS_IT_GAME_PLAY_RESULT_WIN;
							temp = temp.replaceAll("playName", game.getMove().getPlayer().getName());
							result += temp;
							game.setState(Constants.STATE_GAME_END);
					} //end switch
				}	//end for
				System.out.println(result);
				return true;
			} else {//else if game.play
				isOK = false;
			}
		} //end while
	}
}
