package Chatting;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import JDBC.InfoDAO;
import JDBC.InfoDTO;
import JDBC.friendDTO;

public class F_list extends JFrame implements ActionListener {

	private DefaultTableModel model;	// ���̺��� ������ ���� ����
	private JTable table;	// ���̺� ����
	private JPanel contentPane;	// Panel ����
	private JLabel txtlist, txtmy, txtfr, txtuser, txtname;	// �� ���� ����
	private ImageIcon normalIcon;	// �̹��� ����� ���� ���� ����
	private JButton bt_add, bt_myinfo, bt_del, bt_logout, bt_re;	// ��ư ���� ����

	private String name;	// InfoDTO���� ������ �� ���� ���� ����
	private static String user_id;
	//private static String ID = Login.getUser_id();	// ����� ���̵� �����ϴ� ���� ����

	// ģ�� ���
	public F_list(String user_id) {
		
		this.user_id = user_id;
		
		setTitle("JH ä�� ���α׷�");	// ������ Ÿ��Ʋ
		setBounds(600, 100, 470, 720);	// ������ ũ��

		// ��
		contentPane = new JPanel() {
			public void paint(Graphics g) {
				super.paint(g);
				g.drawLine(10, 80, 440, 80);
				g.drawLine(10, 102, 440, 102);
				g.drawLine(10, 160, 440, 160);
				g.drawLine(10, 182, 440, 182);
			}
		};
		contentPane.setBackground(new Color(227, 219, 235));	// ������ ����
		contentPane.setLayout(null);	// ��ġ�����ڸ� ���Ƿ� ����

		InfoDAO dao = new InfoDAO();	//InfoDAOŬ���� ��üȭ
		InfoDTO infodto = new InfoDTO(); //InfoDTOŬ���� ��üȭ
		infodto = dao.select_info(user_id);	//select_info()�� user_id ����
		name = infodto.getName();	//getName() ���� name�� ����
		//id = infodto.getId();

		// ģ������Ʈ ���̺�
		String[] col = { "ID", "NAME" };
		String[][] row = new String[0][2];
		model = new DefaultTableModel(row, col) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		}; // �� ���� �Ұ���

		// ���̺� ����
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);	//���̺� ��ũ�� ����
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
		scrollPane.setBounds(10, 190, 430, 425);
		contentPane.add(scrollPane);

		//ģ����� ���� �� friendDB�� ����� ���� ���̺� ����
		for (int i = model.getRowCount() - 1; i >= 0; i--) {	
			model.removeRow(i);
		}	
		Vector<friendDTO> v = dao.listfriend(user_id);

		for (friendDTO frienddtos : v) {	// friendSearch()�� ID�� ������ �࿡ ���
			String[] data = { frienddtos.getFid(), frienddtos.getFname() };
			model.addRow(data);
		}

		table.setRowHeight(30); // ���̺� �� ũ��

		// ģ�����
		txtlist = new JLabel("ģ�����");
		txtlist.setFont(new Font("���� ���", Font.BOLD, 30));
		txtlist.setBounds(15, 25, 140, 30);
		contentPane.add(txtlist);

		// ������
		txtmy = new JLabel("������");
		txtmy.setFont(new Font("���� ���", Font.PLAIN, 15));
		txtmy.setBounds(15, 76, 140, 30);
		contentPane.add(txtmy);

		// ģ��
		txtfr = new JLabel("ģ��");
		txtfr.setFont(new Font("���� ���", Font.PLAIN, 15));
		txtfr.setBounds(15, 156, 140, 30);
		contentPane.add(txtfr);

		// ���̵�
		txtuser = new JLabel(user_id);
		txtuser.setFont(new Font("���� ���", Font.PLAIN, 15));
		txtuser.setBounds(15, 120, 90, 25);
		contentPane.add(txtuser);

		// �̸�
		txtname = new JLabel(name);
		txtname.setFont(new Font("���� ���", Font.PLAIN, 15));
		txtname.setBounds(110, 120, 90, 25);
		contentPane.add(txtname);

		// ģ�����
		normalIcon = new ImageIcon("D:\\����\\3�г�2�б�\\�ڹ�2\\add.png");
		bt_add = new JButton(normalIcon);
		bt_add.setBackground(new Color(255, 255, 255));
		bt_add.setBounds(390, 18, 47, 48);
		contentPane.add(bt_add);

		// ���ΰ�ħ
		bt_re = new JButton("���ΰ�ħ");
		bt_re.setBackground(new Color(255, 255, 255));
		bt_re.setBounds(352, 163, 88, 17);
		contentPane.add(bt_re);

		// ����������
		bt_myinfo = new JButton("����������");
		bt_myinfo.setBackground(new Color(255, 255, 255));
		bt_myinfo.setBounds(340, 120, 100, 25);
		contentPane.add(bt_myinfo);

		// ����
		bt_del = new JButton("����");
		bt_del.setBackground(new Color(255, 255, 255));
		bt_del.setBounds(270, 635, 60, 25);
		contentPane.add(bt_del);

		// �α׾ƿ�
		bt_logout = new JButton("�α׾ƿ�");
		bt_logout.setBackground(new Color(255, 255, 255));
		bt_logout.setBounds(340, 635, 90, 25);
		contentPane.add(bt_logout);

		add(contentPane);

		// �̺�Ʈ ó��
		bt_add.addActionListener(this);
		bt_myinfo.addActionListener(this);
		bt_logout.addActionListener(this);
		bt_re.addActionListener(this);
		bt_del.addActionListener(this);
		table.addMouseListener(new MyMouseAdapter());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �����ư
		setResizable(false); // ũ�� ���� �Ұ��ϵ��� ����
		setVisible(true);
		System.out.println(user_id);
	}

	// �׼��̺�Ʈ �޼ҵ�
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt_add) {	// ģ����� ��ư Ŭ���� 
			new newAdd(user_id);	// ģ����� Ŭ���� �̵�
		} else if (e.getSource() == bt_myinfo) {	// ���������� ��ư Ŭ����
			new MyInfo(user_id);	// ������Ŭ���� �̵�

		} else if (e.getSource() == bt_logout) {	// �α׾ƿ� ��ư Ŭ�� �� �α���ȭ������ �̵�
			JOptionPane.showMessageDialog(null, "�α׾ƿ� �Ǿ����ϴ�.");
			dispose();
			new Login();
		}

		else if (e.getSource() == bt_re) {	// ���ΰ�ħ ��ư Ŭ�� �� ģ�� �߰�
			InfoDAO dao = new InfoDAO();
			Vector<friendDTO> v = dao.listfriend(user_id);	// listfriend()�� ���̵� �ѱ�� ���� v�� ����
			
			// ���� ������ �����ϰ� ����
			for (int i = model.getRowCount() - 1; i >= 0; i--) {
				model.removeRow(i);
			}

			for (friendDTO frienddtos : v) {	// friendSearch()�� ID�� ������ �࿡ ���
				String[] data = { frienddtos.getFid(), frienddtos.getFname() };
				model.addRow(data);
			}
		} else if (e.getSource() == bt_del) {	// ��� ��ư ������ ����
			InfoDAO dao = new InfoDAO();	// InfoDAOŬ���� ��üȭ
			int row = table.getSelectedRow();	// ���õ� �� row�� ����
			TableModel model1 = table.getModel();	// ���̺��� ���� �������� �޼ҵ�
			if (row < 0)
				return; // ���� ���õ��� ������(-1) ����
			String fid = (String) model1.getValueAt(row, 0);	// ���� ù��° ���� id�� ����
			dao.friendDel(user_id, fid);	// friendDB id ����
			model.removeRow(row);	// �� ����
		}

	}

	// �� ���� Ŭ�� �޼ҵ�
	class MyMouseAdapter extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) { // ����Ŭ��

				new ChatGUIClient(name);	// ä�ù� ����
			}

		}
	}
}
