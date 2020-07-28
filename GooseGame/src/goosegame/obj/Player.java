package goosegame.obj;

public class Player {
	private String name;
	private int position;
	
	public Player(String newName) {
		this.name = newName;
		this.position = 0;
	}
	
	@Override
	public boolean equals(Object o) {
		return this.getName().equals(((Player) o).getName());
	}
	
	//Getter
	public int getPosition() {
		return position;
	}
	public String getName() {
		return name;
	}
	
	//Setter
	public void setPosition(int place) {
		this.position = place;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
