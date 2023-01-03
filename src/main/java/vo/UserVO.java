package vo;

public class UserVO {
	
	private int userNum;
	private int userAdmin;
	private String userId;
	private String userPw;
	private String userEmail;
	private String userPhoneNum;
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public int getUserAdmin() {
		return userAdmin;
	}
	public void setUserAdmin(int userAdmin) {
		this.userAdmin = userAdmin;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhoneNum() {
		return userPhoneNum;
	}
	public void setUserPhoneNum(String userPhoneNum) {
		this.userPhoneNum = userPhoneNum;
	}
	
	@Override
	public String toString() {
		return "UserVO [userNum=" + userNum + ", userAdmin=" + userAdmin + ", userId=" + userId + ", userPw=" + userPw
				+ ", userEmail=" + userEmail + ", userPhoneNum=" + userPhoneNum + "]";
	}
	
	
	
	
}
