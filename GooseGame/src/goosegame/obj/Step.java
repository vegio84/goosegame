package goosegame.obj;

public class Step {

	private int newPosition;
	private String stepReason;
	
	public Step(int pos, String reason) {
		setNewPosition(pos);
		setStepReason(reason);
	}

	
	//Getter
	public int getNewPosition() {
		return newPosition;
	}
	public String getStepReason() {
		return stepReason;
	}
	
	//Setter
	public void setNewPosition(int newPosition) {
		this.newPosition = newPosition;
	}
	public void setStepReason(String stepReason) {
		this.stepReason = stepReason;
	}
}
