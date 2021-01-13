package JDBC;

public class friendDTO {
	
	private String userid; // 사용자 id
	private String fid; // 친구 id
	private String fname; // 친구 이름
	
	// getter 멤버변수 userid 값 변경하기
	public String getUserid() {
		return userid;
	}
	// setter 멤버변수 userid 값 호출하기
	public void setUserid(String userid) {
		this.userid = userid;
	}
	// getter 멤버변수 fid 값 변경하기
	public String getFid() {
		return fid;
	}
	// setter 멤버변수 fid 값 호출하기
	public void setFid(String fid) {
		this.fid = fid;
	}
	// getter 멤버변수 fname 값 변경하기
	public String getFname() {
		return fname;
	}
	// setter 멤버변수 fname 값 호출하기
	public void setFname(String fname) {
		this.fname = fname;
	}
}
