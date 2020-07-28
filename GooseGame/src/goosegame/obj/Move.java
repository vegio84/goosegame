package goosegame.obj;

import java.util.ArrayList;
import java.util.List;

public class Move {
	
	private Player player;
	private List<Step> steps;
	private int oldPosition;
	private int[] dice;
	
	public Move(Player playerParam) {
		setPlayer(playerParam);
		setOldPosition(playerParam.getPosition());
		dice = new int[2];
		steps = new ArrayList<Step>();
	}
	
	public Move() {
		player = null;
		dice = new int[2];
		steps = new ArrayList<Step>();
	}

	//Getter
	public Player getPlayer() {
		return player;
	}
	public int getOldPosition() {
		return oldPosition;
	}
	public int[] getDice() {
		return dice;
	}
	public int getDice(int i) {
		return dice[i];
	}
	public List<Step> getSteps() {
		return steps;
	}
	
	//Setter
	public void setPlayer(Player player) {
		this.player = player;
	}
	public void setOldPosition(int oldPosition) {
		this.oldPosition = oldPosition;
	}
	public void setDice(int id, int value) {
		this.dice[id] = value;
	}
	
	//Adder
	public void addStep(Step step) {
		this.steps.add(step);
	}
	public void updateLastStep(int positionNewPar) {
		this.steps.get(this.steps.size()).setNewPosition(positionNewPar);
	}
	
}
