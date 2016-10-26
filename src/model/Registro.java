package model;

public class Registro {

	String userId;
	String itemId;
	String rating;
	String timeStamp;
	
	public Registro(String userId, String itemId, String rating, String timeStamp){
		this.userId = userId;
		this.itemId = itemId;
		this.rating = rating;
		this.timeStamp = timeStamp;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

}
