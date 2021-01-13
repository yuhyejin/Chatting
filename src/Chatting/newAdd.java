package Chatting;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import JDBC.InfoDAO;
import JDBC.InfoDTO;
import JDBC.friendDTO;

public class newAdd extends JFrame implements ActionListener {

	private static String user_id;	// ����� ���̵� �����ϴ� ���� ����
	private JTextField tf_add;	// ���̵� �˻� �Է� field ���� ����
	private DefaultTableModel model;	// ���̺��� ������ ���� ����
	private JButton bt_newadd, bt_ok, bt_cancel;	// �˻�, ���, ��� ��ư ���� ����
	private JTable table;	// ���̺� ���� ����

	public newAdd(String user_id) {
		
		this.user_id = user_id;

		setTitle("ģ�����");	// ������ Ÿ��Ʋ

		JPanel newp = new JPanel() {
			public void paint(Graphics g) {	// ��
				super.paint(g);
				g.drawLine(10, 70, 370, 70);
			}
		};

		newp.setBackground(new Color(227, 219, 235));	// ������ ����
		newp.setLayout(null);	// ��ġ�����ڸ� ���Ƿ� ����

		//ID �˻���
		JLabel la_add = new JLabel("ID");
		la_add.setBounds(35, 10, 60, 60);
		newp.add(la_add);
		
		// ID �Է� Field
		tf_add = new JTextField(10);
		tf_add.setBounds(60, 27, 205, 25);
		newp.add(tf_add);
		
		bt_newadd = new JButton("�˻�");
		bt_newadd.setBackground(new Color(237, 237, 246));
		bt_newadd.setBounds(280, 27, 60, 25);
		newp.add(bt_newadd);

		// ��� ��ư
		bt_ok = new JButton("���");
		bt_ok.setBackground(new Color(237, 237, 246));
		bt_ok.setBounds(120, 210, 60, 25);
		newp.add(bt_ok);
		
		// ��� ��ư
		bt_cancel = new JButton("���");
		bt_cancel.setBackground(new Color(237, 237, 246));
		bt_cancel.setBounds(195, 210, 60, 25);
		newp.add(bt_cancel);

		// �˻� ���̺�
		String[] col = { "NAME", "ID" };	// column ��
		String[][] row = new String[0][2];

		model = new DefaultTableModel(row, col) {	// ���̺� �� ����, ������ �ϱ� ���� ����
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		}; // �� ���� �Ұ���

		table = new JTable(model);	// ���̺� ����

		// ���̺� ��ũ�ӹ� ����
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(40, 90, 300, 100);
		newp.add(scrollPane);

		scrollPane.setViewportView(table);

		add(newp);

		setBounds(600, 100, 400, 300);	// ������ ũ��
		setResizable(false);	// ũ�� ���� �Ұ���
		setVisible(true);

		// �׼� �̺�Ʈ
		tf_add.addActionListener(this);
		bt_cancel.addActionListener(this);
		bt_ok.addActionListener(this);
		bt_newadd.addActionListener(this);
	}

	// �׼��̺�Ʈ �޼ҵ�
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == tf_add) {	// ���̵� �Է� �� ���͸� ������ ����
			String Id = tf_add.getText();	// field�� ���� ���̵� ������ ����
			if (Id.length() == 0) { // �Է��� �ȵ� ���
				JOptionPane.showMessageDialog(this, "ID�� �Է��ϼ���.");
				return;
			}
			
			// ���̺� ���� ������ �� �� ��ŭ for���� ���� ����
			for (int i = model.getRowCount() - 1; i >= 0; i--) {	
				model.removeRow(i);
			}
			InfoDAO dao = new InfoDAO();	// InfoDAOŬ���� ��üȭ
			ArrayList<InfoDTO> list = null;
			try {	// ����ó��
				list = dao.friendSearch(Id);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			for (InfoDTO infodto : list) {	// friendSearch()�� ID�� ������ �࿡ ���
				String[] data = { infodto.getId(), infodto.getName() };
				model.addRow(data);
			}
		}
		
		else if (e.getSource() == bt_newadd) {	// �˻���ư ������ ����
			String Id = tf_add.getText();	// field�� ���� ���̵� ������ ����
			if (Id.length() == 0) { // �Է��� �ȵ� ���
				JOptionPane.showMessageDialog(this, "ID�� �Է��ϼ���.");
				return;
			}
			for (int i = model.getRowCount() - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			InfoDAO dao = new InfoDAO();	// InfoDAOŬ���� ��üȭ
			ArrayList<InfoDTO> list = null;
			try {
				list = dao.friendSearch(Id);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			for (InfoDTO infodto : list) {	// friendSearch()�� ID�� ������ �࿡ ���
				String[] data = { infodto.getId(), infodto.getName() };
				model.addRow(data);
			}
		}

		else if (e.getSource() == bt_ok) {	// ��Ϲ�ư ������ ����
			friendDTO frienddto = new friendDTO();	// friendDTOŬ���� ��üȭ
			InfoDAO dao = new InfoDAO();	// InfoDAOŬ���� ��üȭ

			int row = table.getSelectedRow();	// ���õ� ���� row�� ����
			TableModel model = table.getModel();	// ���̺��� ���� �������� �޼ҵ�
			String name = (String) model.getValueAt(row, 0);	// ���� ù��° �� ���� name�� ����
			String id = (String) model.getValueAt(row, 1);	// ���� �ι�° �� ���� id�� ����

			frienddto.setUserid(user_id);	// friendDTOŬ������ �� ����
			frienddto.setFid(id);	// friendDTOŬ������ �� ����
			frienddto.setFname(name);	// friendDTOŬ������ �� ����

			dao.inputdb(frienddto);	// inputdb()�� ����� �� ����
			JOptionPane.showMessageDialog(null, "ģ������� �Ϸ�Ǿ����ϴ�.");
			dispose();

		}

		else if (e.getSource() == bt_cancel) {	// �ּҹ�ư ������ ����
			dispose(); // �ϳ��� �����Ӹ� ����
		}
	}
}
