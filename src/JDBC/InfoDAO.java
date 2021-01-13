package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

// DB ������ ��� Ŭ����
public class InfoDAO {

	static final String DRIVER = "com.mysql.jdbc.Driver";	// JDBC ����̹� �ּ�
	static final String URL = "jdbc:mysql://localhost:3306/chatting?useSSL=false&autoReconnection=true"; //DB ���� �ּ�
	static final String USER = "root";	// DB ���̵�
	static final String PASS = "soft602";	// DB ��й�ȣ

	private Connection conn; // DB ���� ��ü
	private PreparedStatement pstmt; // Query �ۼ� ��ü
	private ResultSet rs; // Query ��� Ŀ��
	
	private static InfoDAO instance = new InfoDAO();

	public static InfoDAO getInstance() {
		return instance;

	}
	
	// DB���� �޼ҵ�
	public Connection getConn() {

		try {
			Class.forName(DRIVER); // 1. ����̹� �ε�
			conn = DriverManager.getConnection(URL, USER, PASS); // 2. ����̹� ����

		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;

	}

	// �����ͺ��̽� ���� ����
	public void disconnection() {
		try {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
		}
	}

	// ȸ������ ���̵� �ߺ�üũ
	public int findById(String id) {

		conn = getConn();	//DB����

		try {
			pstmt = conn.prepareStatement("select id from info where id = ?");	//DB������
			pstmt.setString(1, id);	// �������� �� ����
			rs = pstmt.executeQuery();	//rs ��ü�� ������� ����

			if (rs.next()) {	// ���� ������ ����

				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	// �α���
	public int findByIdAndPwd(String id, String pwd) {

		conn = getConn();	// DB����
		try {
			pstmt = conn.prepareStatement("select * from info where id = ? and pwd = ?"); //DB������

			// id�� pwd ����
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();	//rs ��ü�� ������� ����

			if (rs.next()) {	// ���� ������ ����

				return 1; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	// ȸ������ ���� �Է�
	public int save(InfoDTO infodto) throws SQLException {

		try {
			conn = getConn();	//DB����
			String sql = "insert into info(" + "name,id,pwd,birth,gender,addr," + "tel,email,path) "
					+ "values(?,?,?,?,?,?,?,?,?)";	//DB������
			pstmt = conn.prepareStatement(sql); // �������� ����
			// DB �������� ���� ����
			pstmt.setString(1, infodto.getName());
			pstmt.setString(2, infodto.getId());
			pstmt.setString(3, infodto.getPwd());
			pstmt.setString(4, infodto.getBirth());
			pstmt.setString(5, infodto.getGender());
			pstmt.setString(6, infodto.getAddr());
			pstmt.setString(7, infodto.getTel());
			pstmt.setString(8, infodto.getEmail());
			pstmt.setString(9, infodto.getPath());
			pstmt.executeUpdate(); // �ݿ��� ���ڵ��� �Ǽ��� ��ȯ
			pstmt.close();

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	// ģ���˻�
	public ArrayList<InfoDTO> friendSearch(String Id) throws SQLException {

		ArrayList<InfoDTO> list = new ArrayList<InfoDTO>();	// list ����

		try {
			conn = getConn();	//DB����
			String sql = " select name, id from info where id like ? ";	//DB������
			pstmt = conn.prepareStatement(sql);	// �������� ����
			pstmt.setString(1, Id + "%");	// �������� �� ����
			rs = pstmt.executeQuery();	//rs ��ü�� ������� ����

			while (rs.next()) {

				InfoDTO infodto = new InfoDTO();	//InfoDTOŬ���� ��üȭ

				// ��� ���� infodto�� ����
				infodto.setId(rs.getString(1));
				infodto.setName(rs.getString(2));
				list.add(infodto);

			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	// ģ����� -> friend ���̺� �ֱ�
	public void inputdb(friendDTO frienddto) {
		try {
			conn = getConn();	// DB����
			String sql = "insert into friend(userid, friendid, fname)" + "values(?,?,?)";	//DB������
			pstmt = conn.prepareStatement(sql);	// �������� ����
			
			// DB �������� ���� ����
			pstmt.setString(1, frienddto.getUserid());
			pstmt.setString(2, frienddto.getFid());
			pstmt.setString(3, frienddto.getFname());

			pstmt.executeUpdate();	// �ݿ��� ���ڵ��� �Ǽ��� ��ȯ
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ģ������Ʈ
	public Vector<friendDTO> listfriend(String userid) {
		Vector<friendDTO> v = new Vector<friendDTO>();

		try {
			conn = getConn();	// DB����
			String sql = "select * from friend where userid = ?";	//DB������
			pstmt = conn.prepareStatement(sql);	// �������� ����
			pstmt.setString(1, userid);	// �������� �� ����
			rs = pstmt.executeQuery();	//rs ��ü�� ������� ����

			while (rs.next()) {
				// ��� ���� infodto�� ����
				friendDTO frienddto = new friendDTO();
				frienddto.setUserid(rs.getString(1));
				frienddto.setFid(rs.getString(2));
				frienddto.setFname(rs.getString(3));
				v.add(frienddto);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
	}

	// ģ������Ʈ -> ģ�� ����
	public void friendDel(String id, String fid) {
		try {
			conn = getConn();	// DB����
			String sql = "delete from friend where userid = ? and friendid = ?";	//DB������
			pstmt = conn.prepareStatement(sql);	// �������� ����
			pstmt.setString(1, id);	// �������� �� ����
			pstmt.setString(2, fid);
			pstmt.executeUpdate();	// �ݿ��� ���ڵ��� �Ǽ��� ��ȯ

			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ����������
	public InfoDTO select_info(String user_id) {
		InfoDTO infodto = new InfoDTO();
		try {
			conn = getConn();	// DB����
			String sql = " select name, id, pwd, email, path from info where id = ?";	//DB������
			pstmt = conn.prepareStatement(sql);	// �������� ����	
			pstmt.setString(1, user_id);	// �������� �� ����
			rs = pstmt.executeQuery();	//rs ��ü�� ������� ����

			while (rs.next()) {
				// ��� ���� infodto�� ����
				infodto.setName(rs.getString(1));
				infodto.setId(rs.getString(2));
				infodto.setPwd(rs.getString(3));
				infodto.setEmail(rs.getString(4));
				infodto.setPath(rs.getString(5));
				System.out.println("��");

			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return infodto;

	}

	// ����������
	public void updatemyinfo(InfoDTO infodto) {

		try {

			conn = getConn();	// DB����
			String sql = "update info set pwd = ?, email = ?, path = ?" + "where id=?";	//DB������
			pstmt = conn.prepareStatement(sql);	// �������� ����

			// ��� ���� infodto�� ����
			pstmt.setString(1, infodto.getPwd());
			pstmt.setString(2, infodto.getEmail());
			pstmt.setString(3, infodto.getPath());
			pstmt.setString(4, infodto.getId());

			pstmt.executeUpdate();	// �ݿ��� ���ڵ��� �Ǽ��� ��ȯ

			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �õ�������
	public ArrayList<zipDTO> searchSido() {
		ArrayList<zipDTO> sidoList = new ArrayList<>();

		try {
			conn = getConn();	// DB����
			String sql = "select distinct(sido) from zipcode";	//DB������
			pstmt = conn.prepareStatement(sql);	// �������� ����
			rs = pstmt.executeQuery();	//rs ��ü�� ������� ����
			while (rs.next()) {
				// ��� ���� zipcode�� ����
				zipDTO zipcode = new zipDTO();
				zipcode.setSido(rs.getString("SIDO"));
				sidoList.add(zipcode);
			}
		} catch (SQLException e) {
		}

		return sidoList;
	}

	// ����������
	public ArrayList<zipDTO> searchGugun(String sido) {
		ArrayList<zipDTO> gugunList = new ArrayList<>();

		try {
			conn = getConn();	// DB����
			String sql = "select distinct(gugun) from zipcode where sido = \'" + sido + "\' ";	//DB������
			pstmt = conn.prepareStatement(sql);	// �������� ����
			rs = pstmt.executeQuery();	//rs ��ü�� ������� ����
			while (rs.next()) {
				// ��� ���� zipcode�� ����
				zipDTO zipcode = new zipDTO();
				zipcode.setGugun(rs.getString("GUGUN"));
				gugunList.add(zipcode);
			}

		} catch (SQLException e) {

		}

		return gugunList;
	}

	// ��������
	public ArrayList<zipDTO> searchDong(String sido, String gugun) {
		ArrayList<zipDTO> dongList = new ArrayList<>();

		try {
			conn = getConn();	// DB����
			String sql = "select distinct(dong) from zipcode where sido = \'" + sido + "\'  and gugun = \'" + gugun
					+ "\'";	//DB������
			pstmt = conn.prepareStatement(sql);	// �������� ����
			rs = pstmt.executeQuery();	//rs ��ü�� ������� ����
			while (rs.next()) {	// ��� ���� zipcode�� ����
				zipDTO zipcode = new zipDTO();
				zipcode.setDong(rs.getString("DONG"));
				dongList.add(zipcode);
			}
		} catch (SQLException e) {

		}
		return dongList;
	}

	// �����ּ� ������
	public ArrayList<zipDTO> searchAddress(String sido, String gugun, String dong) {
		ArrayList<zipDTO> addressList = new ArrayList<>();
		try {
			conn = getConn();	// DB����
			String sql = "select * from zipcode where sido = \'" + sido + "\'  and gugun = \'" + gugun
					+ "\' and dong = \'" + dong + "\'";	//DB������
			pstmt = conn.prepareStatement(sql);	// �������� ����
			rs = pstmt.executeQuery();	//rs ��ü�� ������� ����
			while (rs.next()) {	// ��� ���� zipcode�� ����
				zipDTO zipcode = new zipDTO();
				zipcode.setZipcode(rs.getString("zipcode"));
				zipcode.setSido(rs.getString("sido"));
				zipcode.setGugun(rs.getString("gugun"));
				zipcode.setDong(rs.getString("dong"));
				zipcode.setRi(rs.getString("ri"));
				zipcode.setBldg(rs.getString("bldg"));
				zipcode.setBunji(rs.getString("bunji"));
				zipcode.setSeq(rs.getString("seq"));
				addressList.add(zipcode);
			}
		} catch (SQLException e) {

		}
		return addressList;
	}

}