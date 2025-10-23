package TaskPack;

public class Task {

	private String desc; 
	private boolean status; //is boolean for now, but could be in percentages/double later 
	
	public Task(String desc) {
		this.desc=desc;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void update(String desc) {
		this.desc=desc;
	}
	
	public String getStatus() {
		if (status==true) { //complete = true
			return "Complete";
		}
		else {return "Incomplete";} // incomplete = false
	}
	
}
