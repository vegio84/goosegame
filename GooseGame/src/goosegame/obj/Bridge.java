package goosegame.obj;

public class Bridge {
	
	private int id;
	private int pointA;
	private int pointB;
	
	public Bridge(int idBridge, int pointApar, int pointBpar) {
		id = idBridge;
		pointA = pointApar;
		pointB = pointBpar;
	}
	
	@Override
	public boolean equals(Object b) {
		if(!(b instanceof Bridge)) {
			return false;
		}
		if(this.id == ((Bridge)b).id || this.pointA == ((Bridge)b).pointA || this.pointB == ((Bridge)b).pointB) {
			return true;
		} else {
			return false;
		}
	}
	
	//Getter
	public int getPointA() {
		return this.pointA;
	}
	public int getPointB() {
		return this.pointB;
	}
	public int getId() {
		return this.id;
	}
	
	//Setter
	public void setPointA(int pointA) {
		this.pointA = pointA;
	}
	public void setPointB(int pointB) {
		this.pointB = pointB;
	}
}
