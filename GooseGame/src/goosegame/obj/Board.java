package goosegame.obj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

	private List<Integer> gooses;
	private List<Bridge> bridges;
	private int bridgeID;
	private String state;
	
	public Board(boolean createStandardBoard) {
		//new bridge id
		bridgeID = 0;
				
		if(createStandardBoard) { //check if create a standard board
			//create standard Gooses and Bridges
			gooses = Arrays.asList(Constants.STANDARD_GOOSES);
			bridges = new ArrayList<Bridge>();
			for (int i = 0; i < Constants.STANDARD_BRIDGES.length; i=i+2) {
				Bridge b = new Bridge(bridgeID++, Constants.STANDARD_BRIDGES[i], Constants.STANDARD_BRIDGES[i+1]);
				bridges.add(b);
			}
			//assign state INIT
			state = Constants.STATE_BOARD_INIT;
		}else {
			//create empty Gooses and Bridges
			gooses = new ArrayList<Integer>();
			bridges = new ArrayList<Bridge>();
			//assign state EMPTY
			state = Constants.STATE_BOARD_EMPTY;
		}//end if(createStandardBoard)
		
	}
	
	//Getter
	public List<Integer> getGooses() {
		return gooses;
	}
	public List<Bridge> getBridges() {
		return bridges;
	}
	public String getState() {
		return state;
	}
	
	//Setter
	public void setState(String state) {
		this.state = state;
	}
	
	//Adder
	public void addGooses(Integer i) {
		this.gooses.add(i);
	}
	public void addBridges(int pointA, int pointB) {
		this.bridges.add(new Bridge(bridgeID++, pointA, pointB));
	}


	
	
}
