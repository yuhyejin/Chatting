package Chatting;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import JDBC.InfoDAO;
import JDBC.InfoDTO; 

public class MyInfo extends JFrame implements ActionListener {
	InfoDTO infodto = new InfoDTO();
	InfoDAO dao = new InfoDAO();
	private String user_id;
	private String ID;

	private JTextField txtEmail;
	private JPasswordField txtPw, txtPwCheck;
	private JButton PwCheckbtn, checkbtn, cancelbtn, pupload;
	private String filename = null;
	private ImageIcon imageIcon, imageIcon1;
	private JLabel img;

	//�Էµ� id ���� ������
	public MyInfo(String user_id) {
		
		this.user_id = user_id;
		
		ID = user_id;	// �޾ƿ� �ƾƵ� ���� ID�� �ٽ� ����
		
		setTitle("�� ���� ����");
		setBounds(600, 100, 420, 620);

		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setBackground(new Color(227, 219, 235));	// ������ ����
		contentPane.setLayout(null);
		
		//info�� �ִ� name, Id, pwd, email, filename ������ ������ 
		infodto = dao.select_info(user_id);
		String name = infodto.getName();
		String pwd = infodto.getPwd();
		String email = infodto.getEmail();
		String filename1 = infodto.getPath();
		
		// gui ���� 
		JLabel label = new JLabel("�� ���� ����:)");
		label.setFont(new Font("������� ExtraBold", Font.BOLD, 20));
		label.setBounds(140, 25, 200, 20);
		contentPane.add(label);

		// �̸�
		JLabel Name = new JLabel("�� ��");
		Name.setBounds(30, 95, 47, 15);
		contentPane.add(Name);

		JLabel txtName = new JLabel(name);
		txtName.setBounds(130, 95, 130, 21);
		contentPane.add(txtName);

		// ���̵�
		JLabel Id = new JLabel("���̵�");
		Id.setBounds(30, 145, 60, 20);
		contentPane.add(Id);

		JLabel txtId = new JLabel(user_id);
		txtId.setBounds(130, 145, 130, 21);
		contentPane.add(txtId);
		
		// ��й�ȣ
		JLabel Pw = new JLabel("��й�ȣ �缳��");
		Pw.setBounds(30, 195, 100, 20);
		contentPane.add(Pw);

		// ��й�ȣ ������ �����ϰ� �н����� ������ �����ϰ� ��
		txtPw = new JPasswordField(pwd);
		txtPw.setBounds(130, 195, 167, 21);
		contentPane.add(txtPw);
		txtPw.setColumns(10);

		// ��й�ȣ Ȯ��
		JLabel PwCheck = new JLabel("��й�ȣ Ȯ��");
		PwCheck.setBounds(30, 245, 100, 20);
		contentPane.add(PwCheck);

		txtPwCheck = new JPasswordField();
		txtPwCheck.setText("");
		txtPwCheck.setBounds(130, 245, 167, 21);
		contentPane.add(txtPwCheck);
		txtPwCheck.setColumns(10);

		// ��й�ȣ Ȯ�� ��ư
		PwCheckbtn = new JButton("Ȯ��");
		PwCheckbtn.setBounds(300, 245, 60, 20);
		PwCheckbtn.setBackground(new Color(252, 254, 255));
		contentPane.add(PwCheckbtn);

		// E-mail
		JLabel Email = new JLabel("E-mail");
		Email.setBounds(30, 295, 60, 20);
		contentPane.add(Email);
		
		//�̸��� ������ �����ϰ� ��
		txtEmail = new JTextField(email);
		txtEmail.setBounds(130, 295, 150, 21);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		// ������ ����
		JLabel profile = new JLabel("<������ ����>");
		profile.setBounds(50, 345, 100, 20);
		contentPane.add(profile);
		
		//������ ���� ������ �����ϰ� ��
		imageIcon = new ImageIcon(filename1);
		Image img1 = imageIcon.getImage(); // �̹��� ������ �ҷ���
		Image img2 = img1.getScaledInstance(120,100, java.awt.Image.SCALE_SMOOTH); //ũ�⿡ �°� �̹��� ����
		imageIcon1 = new ImageIcon(img2); 
		img = new JLabel(imageIcon1);
		img.setBounds(30, 370, 120, 100);
		contentPane.add(img);
		
		pupload = new JButton("���� ���ε�");
		pupload.setBounds(170, 450, 105, 20);
		contentPane.add(pupload);
		pupload.setBackground(new Color(252, 254, 255));
		pupload.addActionListener(this);

		// �ϴ� Ȯ�� ��ư
		checkbtn = new JButton("Ȯ��");
		checkbtn.setBounds(140, 530, 60, 30);
		contentPane.add(checkbtn);
		checkbtn.setBackground(new Color(252, 254, 255));

		// �ϴ� ��� ��ư
		cancelbtn = new JButton("���");
		cancelbtn.setBounds(210, 530, 60, 30);
		contentPane.add(cancelbtn);
		cancelbtn.setBackground(new Color(252, 254, 255));

		// ��ư �׼� �̺�Ʈ 
		checkbtn.addActionListener(this);
		PwCheckbtn.addActionListener(this);
		cancelbtn.addActionListener(this);

		setResizable(false);
		setVisible(true); // ������ ���̰��ϴ°�
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// ��й�ȣ �ߺ� Ȯ�� ��ư
		if(e.getSource()==PwCheckbtn) {
			
			String pw = txtPw.getText().trim();
			String pwcheck = txtPwCheck.getText().trim();
			
			// ��й�ȣ �ߺ� Ȯ�� �޼���
			if (pw.length() == 0 || pwcheck.length() == 0) {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ּ���.", "����", JOptionPane.INFORMATION_MESSAGE);
			} else if (pw.equals(pwcheck)) {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ�մϴ�.", "����", JOptionPane.INFORMATION_MESSAGE);
			} else if (!pw.equals(pwcheck)) {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.", "����", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		// �� ���� ���� Ȯ�� ��ư 
		else if(e.getSource()==checkbtn) { //�ȿ� if�� �ؼ� ���̿ɼ� ó���ؾ���
			
			infodto.setId(ID);
			infodto.setPwd(txtPw.getText());
			infodto.setEmail(txtEmail.getText());
			infodto.setPath("C:\\" + filename);
			System.out.println(infodto.getPath());
			
			dao.updatemyinfo(infodto);
			JOptionPane.showMessageDialog(null, "���������� �Ϸ�Ǿ����ϴ�.");
			dispose();
		}
		if(e.getSource()==cancelbtn) { // ��ҹ�ư�� ������ �ش� �������� ����
			dispose();
		}
		// ������ ���� ���ε� ��ư
		else if (e.getSource() == pupload) {
			JFileChooser jfc = new JFileChooser("C://"); // ��θ� C���� JFileChooser ����
			// ������ ���� ��������
			int sv = jfc.showSaveDialog(this);
			if (sv == 0) {
				filename = jfc.getSelectedFile().getName();
				img.setIcon(imageIcon);
			}
			// ������ ���� ���ε� �޽��� 
			if (sv != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "������ �������� �ʾҽ��ϴ�.", "���", JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "������ ������ ���ε� �Ǿ����ϴ�.");
				System.out.println(filename);
			}
		}
	}
}
