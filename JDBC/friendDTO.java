package JDBC;

public class friendDTO {
	
	private String userid; // ����� id
	private String fid; // ģ�� id
	private String fname; // ģ�� �̸�
	
	// getter ������� userid �� �����ϱ�
	public String getUserid() {
		return userid;
	}
	// setter ������� userid �� ȣ���ϱ�
	public void setUserid(String userid) {
		this.userid = userid;
	}
	// getter ������� fid �� �����ϱ�
	public String getFid() {
		return fid;
	}
	// setter ������� fid �� ȣ���ϱ�
	public void setFid(String fid) {
		this.fid = fid;
	}
	// getter ������� fname �� �����ϱ�
	public String getFname() {
		return fname;
	}
	// setter ������� fname �� ȣ���ϱ�
	public void setFname(String fname) {
		this.fname = fname;
	}
}
