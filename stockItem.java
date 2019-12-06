package project;

public class stockItem {
	

	private String name;
	private int count;
	
	
	public stockItem(int count) {
		set_stockname(null);
		set_stockcount(count);
	}
	
	public stockItem(String name, int count) {
		set_stockname(name);
		set_stockcount(count);
	}
	
	public void set_stockname(String name) {
		this.name = name;
	}
	public void set_stockcount(int count) {
		this.count = get_stockcount()+count;
	}
	
	public String get_stockname() {
		return this.name;
	}
	public int get_stockcount() {
		return this.count;
	}

}
