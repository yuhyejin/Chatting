package Chatting;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import JDBC.InfoDAO;

public class Login extends JFrame implements ActionListener {
	/*
	 * �α��� â Ŭ����
	 * �α��� -> ȸ������
	 * �α��� -> ģ�����
	 */

	static String user_id;	// ����� ���̵� ���� ���� ����
	private JPanel contentPane; // ��ư�� �ٿ��� ���� �г� ����
	private JLabel id, pw;	// id, pw �� ����
	private JTextField txtId;	// id �Է�â field ����
	private JPasswordField txtpw;	// pw �Է�â ������ ���� ����
	private JButton loginbtn, register;	// �α��ι�ư, ȸ������ ��ư ����
	private ImageIcon icon;	// ���ȭ�� �̹��� ������ ���� ����

	public Login() {

		setTitle("JH ä�� ���α׷�");	// ������ Ÿ��Ʋ
		setBounds(600, 100, 420, 620);	// ������ ũ�� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���μ������� ����� ����

		icon = new ImageIcon("C:\\Users\\nim45\\Downloads\\�ΰ�7.jpg");	// ���ȭ�� �̹��� ����
		contentPane = new JPanel() {
			public void paintComponent(Graphics g) {	// ������Ʈ�� �ڽ��� ����� �׸�
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ID��
		id = new JLabel("I D");
		id.setBounds(60, 360, 47, 15);
		contentPane.add(id);
		
		// ID�Է�â
		txtId = new JTextField(10);
		txtId.setText("");
		txtId.setBounds(100, 360, 167, 21);
		contentPane.add(txtId);
		txtId.setColumns(10);	// 10�ڱ��� �Է�����

		// �н�����
		pw = new JLabel("PW");
		pw.setBounds(60, 400, 47, 15);
		contentPane.add(pw);
		
		// �н����� �Է�â
		txtpw = new JPasswordField(10);
		txtpw.setText("");
		txtpw.setBounds(100, 400, 167, 21);
		contentPane.add(txtpw);
		txtpw.setColumns(10);	// 10�ڱ��� �Է�����

		// �α��� ��ư
		loginbtn = new JButton("�α���");
		loginbtn.setBounds(280, 360, 80, 60);
		contentPane.add(loginbtn);
		loginbtn.setBackground(new Color(255, 255, 255));	// ��ư �� ����

		// �α���->ȸ������ ��ư
		setContentPane(contentPane);
		register = new JButton("ȸ������");
		register.setBounds(140, 439, 90, 15);
		register.setBorderPainted(false);	//JButton�� Border�� ������
		register.setContentAreaFilled(false);	//JButton�� ���뿵�� ä��� ����
		register.setFocusPainted(false);	//JButton�� ���õǾ��� �� ����� �׵θ� ������
		contentPane.add(register);

		// �׼��̺�Ʈ ó��
		loginbtn.addActionListener(this);
		register.addActionListener(this);

		setResizable(false);	// ������ ũ�� ���� ���ϰ� ��
		setVisible(true);
	}

	// �׼��̺�Ʈ �޼ҵ�
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginbtn) {	// �α��� ��ư �׼� �̺�Ʈ
			String id = txtId.getText();	// �Է¹��� id�� id ������ ����
			String pwd = txtpw.getText();	// �Է¹��� pw�� pwd ������ ����

			InfoDAO dao = new InfoDAO();	// InfoDAOŬ���� ��üȭ
			int result = dao.findByIdAndPwd(id, pwd);	// id�� pwd�� InfoDAO findByIdAndPwd()�� ����
			if (result == 1) {	// result�� 1, �� �ȿ� ������ ������ ����

				JOptionPane.showMessageDialog(null, "�α��� ����");	// �˾�â

				user_id = txtId.getText(); // �α����� ���̵� user_id�� ����
				new F_list(user_id);	// ģ����� â���� �Ѿ
				dispose();
			} else {	// ������ ������ ����
				JOptionPane.showMessageDialog(null, "�α��� ����");	// �˾�â
			}
		}

		else if (e.getSource() == register) {	// ȸ������ ��ư �׼� �̺�Ʈ
			new Register(null);	// ȸ������ â���� �Ѿ
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login();
		new ChatGUIServer();	// �α��� â ����� ������ ���� ����

	}
	
	public static String getUser_id() {
		return user_id;
	}
}