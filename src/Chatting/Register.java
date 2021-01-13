package Chatting;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import JDBC.InfoDAO;
import JDBC.InfoDTO;

public class Register extends JFrame implements ActionListener {

	private JPanel contentPane; // ��ư�� �ٿ��� ���� �г� ����
	private Choice tel_list, e_list;
	private String filename = null;
	private ImageIcon imageIcon;
	private JLabel img;

	// �����ȣ �˻��⿡�� �˻��� �ּ� ��������
	public Register(String address) {
		//gui ����
		setTitle("ȸ������");
		setBounds(600, 100, 780, 800);

		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setBackground(new Color(227, 219, 235));	// ������ ����
		contentPane.setLayout(null);

		JLabel label = new JLabel("ȸ �� �� �� ������");
		label.setFont(new Font("������� ExtraBold", Font.BOLD, 20));
		label.setBounds(300, 25, 200, 40);
		contentPane.add(label);

		// �̸�
		JLabel Name = new JLabel("�� ��");
		Name.setBounds(30, 95, 47, 15);
		contentPane.add(Name);

		JTextField txtName = new JTextField();
		txtName.setText(null);
		txtName.setBounds(120, 95, 167, 21);
		contentPane.add(txtName);
		txtName.setColumns(10);

		// ���̵�
		JLabel Id = new JLabel("���̵�");
		Id.setBounds(30, 145, 60, 20);
		contentPane.add(Id);

		JTextField txtId = new JTextField();
		txtId.setText(null);
		txtId.setBounds(120, 145, 167, 21);
		contentPane.add(txtId);
		txtId.setColumns(10);

		// ���̵� �ߺ�üũ ��ư
		JButton IdCheckbtn = new JButton("�ߺ�Ȯ��");
		IdCheckbtn.setBounds(300, 145, 88, 20);
		contentPane.add(IdCheckbtn);
		IdCheckbtn.setBackground(new Color(252, 254, 255));

		// ���̵� �ߺ�üũ �̺�Ʈ ó��
		IdCheckbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InfoDAO dao = InfoDAO.getInstance(); // infoDAO�� �ִ� ������ ����Ͽ� id �ߺ� ��
				int reault = dao.findById(txtId.getText());
				if (reault == 1) {
					JOptionPane.showMessageDialog(null, "������� ���̵� �Դϴ�.");
				} else {
					JOptionPane.showMessageDialog(null, "��밡���� ���̵� �Դϴ�.");
				}
			}

		});

		// ��й�ȣ
		JLabel Pw = new JLabel("��й�ȣ");
		Pw.setBounds(30, 195, 100, 20);
		contentPane.add(Pw);

		//�н����� �ʵ带 ����Ͽ� ��й�ȣ ����
		JPasswordField txtPw = new JPasswordField();
		txtPw.setText(null);
		txtPw.setBounds(120, 195, 167, 21);
		contentPane.add(txtPw);
		txtPw.setColumns(10);

		// ��й�ȣ Ȯ��
		JLabel PwCheck = new JLabel("��й�ȣ Ȯ��");
		PwCheck.setBounds(30, 245, 100, 20);
		contentPane.add(PwCheck);

		JPasswordField txtPwCheck = new JPasswordField();
		txtPwCheck.setText(null);
		txtPwCheck.setBounds(120, 245, 167, 21);
		contentPane.add(txtPwCheck);
		txtPwCheck.setColumns(10);

		// ��й�ȣ Ȯ�� ��ư
		JButton PwCheckbtn = new JButton("Ȯ��");
		PwCheckbtn.setBounds(300, 245, 60, 20);
		PwCheckbtn.setBackground(new Color(252, 254, 255));
		contentPane.add(PwCheckbtn);
		
		// ��й�ȣ Ȯ�� �̺�Ʈ ó�� 
		PwCheckbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pw = txtPw.getText().trim(); //pw ���ڿ� ���� 
				String pwcheck = txtPwCheck.getText().trim(); //pwcheck ���ڿ� ����

				// �� trim ���ڿ� ���̸� ���Ͽ� �޽��� ���
				if (pw.length() == 0 || pwcheck.length() == 0) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ּ���.", "����", JOptionPane.INFORMATION_MESSAGE);
				} else if (pw.equals(pwcheck)) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ�մϴ�.", "����", JOptionPane.INFORMATION_MESSAGE);
				} else if (!pw.equals(pwcheck)) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.", "����", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		// �������
		JLabel Birth = new JLabel("�������");
		Birth.setBounds(30, 295, 60, 20);
		contentPane.add(Birth);

		// ������Ͽ��� ��
		JTextField txtYear = new JTextField();
		txtYear.setText(null);
		txtYear.setBounds(120, 295, 60, 21);
		contentPane.add(txtYear);
		txtYear.setColumns(10);

		JLabel Ylabel = new JLabel("��");
		Ylabel.setBounds(185, 295, 60, 20);
		contentPane.add(Ylabel);

		// ������Ͽ��� ��
		JTextField txtMonth = new JTextField();
		txtMonth.setText(null);
		txtMonth.setBounds(205, 295, 40, 21);
		contentPane.add(txtMonth);
		txtMonth.setColumns(10);

		JLabel Mlabel = new JLabel("��");
		Mlabel.setBounds(250, 295, 40, 20);
		contentPane.add(Mlabel);

		// ������Ͽ��� ��
		JTextField txtDate = new JTextField();
		txtDate.setText(null);
		txtDate.setBounds(280, 295, 40, 21);
		contentPane.add(txtDate);
		txtDate.setColumns(10);

		JLabel Dlabel = new JLabel("��");
		Dlabel.setBounds(325, 295, 40, 20);
		contentPane.add(Dlabel);

		// ���� ���� ��ư
		JLabel sex = new JLabel("����");
		sex.setBounds(30, 345, 60, 20);
		contentPane.add(sex);

		JRadioButton m = new JRadioButton("��", true);
		m.setBounds(120, 345, 60, 20);
		contentPane.add(m);

		JRadioButton fm = new JRadioButton("��", true);
		fm.setBounds(185, 345, 60, 20);

		ButtonGroup g = new ButtonGroup();
		g.add(m);
		g.add(fm);

		this.add(m);
		this.add(fm);

		// �����ȣ(�ּ�)
		JLabel Address = new JLabel("�����ȣ");
		Address.setBounds(30, 395, 60, 20);
		contentPane.add(Address);

		JTextField txtAddress = new JTextField();
		txtAddress.setText(address);
		txtAddress.setBounds(120, 395, 200, 21);
		contentPane.add(txtAddress);

		// �����ȣ(�ּ�) �˻� ��ư
		JButton Addressbtn = new JButton("�˻�");
		Addressbtn.setBounds(330, 395, 60, 20);
		contentPane.add(Addressbtn);
		Addressbtn.setBackground(new Color(252, 254, 255));

		//zipSearch Ŭ���� ����
		Addressbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new zipSearch();

			}
		});

		// ���ּ�
		JLabel Address3 = new JLabel("���ּ�");
		Address3.setBounds(30, 445, 60, 20);
		contentPane.add(Address3);

		JTextField txtAddress3 = new JTextField();
		txtAddress3.setText(null);
		txtAddress3.setBounds(120, 445, 200, 21);
		contentPane.add(txtAddress3);
		txtAddress.setColumns(10);

		// ����ó
		JLabel phNum = new JLabel("����ó");
		phNum.setBounds(30, 495, 60, 20);
		contentPane.add(phNum);

		tel_list = new Choice();
		tel_list.add("����");
		tel_list.add("010");
		tel_list.add("011");
		tel_list.add("012");
		tel_list.setBounds(120, 495, 50, 21);
		contentPane.add(tel_list);

		JLabel phNum2 = new JLabel("-");
		phNum2.setBounds(185, 495, 60, 20);
		contentPane.add(phNum2);

		JTextField txtphNum3 = new JTextField();
		txtphNum3.setText(null);
		txtphNum3.setBounds(205, 495, 50, 21);
		contentPane.add(txtphNum3);
		txtphNum3.setColumns(10);

		JLabel phNum3 = new JLabel("-");
		phNum3.setBounds(270, 495, 60, 20);
		contentPane.add(phNum3);

		JTextField txtphNum4 = new JTextField();
		txtphNum4.setText(null);
		txtphNum4.setBounds(290, 495, 50, 21);
		contentPane.add(txtphNum4);
		txtphNum4.setColumns(10);

		// E-mail
		JLabel Email = new JLabel("E-mail");
		Email.setBounds(30, 545, 60, 20);
		contentPane.add(Email);

		JTextField txtEmail = new JTextField();
		txtEmail.setText(null);
		txtEmail.setBounds(120, 545, 70, 21);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel Elabel = new JLabel("@");
		Elabel.setBounds(200, 545, 60, 20);
		contentPane.add(Elabel);

		e_list = new Choice();
		e_list.add("����");
		e_list.add("naver.com");
		e_list.add("daum.com");
		e_list.add("nate.com");
		e_list.add("google.com");
		e_list.setBounds(235, 545, 110, 20);
		contentPane.add(e_list);

		// �������� ����
		JLabel Agree = new JLabel("�������� ���� �� �̿뿡 ���� �ȳ�");
		Agree.setBounds(30, 595, 300, 20);
		contentPane.add(Agree);

		String s = "<����������޿� ���� �ȳ�>\r\n" + "(��)J&H�� ������ ������ ���������� �������̿롤Ȱ���ϰ��� �ϴ� ��쿡 ������������ȣ������ ���� ������ ���Ǹ� ��� �ֽ��ϴ�."
				+ "�������� �����ڰ� ������ ���� ���� �ٸ� �������� Ȱ������ ������, ������ ���������� �̿��� �ź��ϰ��� �� ������ �������� ����å���ڸ� ���� ����, ���� Ȥ�� ������ �䱸�� ���ֽ��ϴ�."
				+ "�� �������� ���� �� �̿����" + "   �� �������� ���� �׸� ��" + "   �ʼ��׸� : ����, �ּ�, �޴���ȭ��ȣ, �ּ�, �������, �̸��� �ּ�"
				+ "   �� �������� �̿���� ��" + "   - G&H ä�����α׷� ���� �̿�";
		JTextArea text = new JTextArea(s);
		text.setLineWrap(true);
		text.setBounds(30, 625, 600, 70);
		contentPane.add(text);

		// ������ ����
		JLabel profile = new JLabel("<������ ����>");
		profile.setBounds(555, 95, 100, 20);
		contentPane.add(profile);
		
		imageIcon = new ImageIcon(filename);
		img = new JLabel();
		img.setBounds(515, 135, 160, 160);
		contentPane.add(img);
		
		// ������ ��θ��� ������ ������ ���� ���ε�
		JButton pupload = new JButton("���� ����");
		pupload.addActionListener((ActionListener) this);
		pupload.setBounds(550, 325, 100, 20);
		pupload.setVisible(true);
		contentPane.add(pupload);
		pupload.setBackground(new Color(252, 254, 255));

		// �ϴ� Ȯ�� ��ư
		JButton checkbtn = new JButton("Ȯ��");
		checkbtn.setBounds(300, 715, 60, 30);
		contentPane.add(checkbtn);
		checkbtn.setBackground(new Color(252, 254, 255));

		//Ȯ�� ��ư�� ������ ������ ȸ�������� infoDTO�� �Է�
		checkbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String genders = "";
				if (m.isSelected()) {
					genders = "����";
				} else if (fm.isSelected()) {
					genders = "����";
				}
				InfoDTO infodto = new InfoDTO();

				infodto.setName(txtName.getText());
				infodto.setId(txtId.getText());
				infodto.setPwd(txtPw.getText());
				infodto.setBirth(txtYear.getText() + "��" + txtMonth.getText() + "��" + txtDate.getText() + "��");
				infodto.setGender(genders);
				infodto.setAddr(txtAddress.getText() + " " + txtAddress3.getText());
				infodto.setTel(tel_list.getSelectedItem() + "-" + txtphNum3.getText() + "-" + txtphNum4.getText());
				infodto.setEmail(txtEmail.getText() + "@" + e_list.getSelectedItem());
				infodto.setPath("C:\\" + filename);

				InfoDAO dao = InfoDAO.getInstance();
				int result = 0;
				try {
					result = dao.save(infodto);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (result == 1) {
					JOptionPane.showMessageDialog(null, "ȸ�������� �Ϸ�Ǿ����ϴ�.");
					dispose(); // �ϳ��� �����Ӹ� ����
				} else {
					JOptionPane.showMessageDialog(null, "������ �Է����ּ���.");
				}
			}
		});

		// �ϴ� ��� ��ư
		JButton cancelbtn = new JButton("���");
		cancelbtn.setBounds(390, 715, 60, 30);
		contentPane.add(cancelbtn);
		cancelbtn.setBackground(new Color(252, 254, 255));

		cancelbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose(); // �ϳ��� �����Ӹ� ����
			}
		});

		setResizable(false);
		setVisible(true); // �������� ���̰� ��

	}

	//�����ʻ��� ���ε� �̺�Ʈ ó��
	public void actionPerformed(ActionEvent e) {
		//C��ο��� ���� ��� ã�� �̹��� ���ε� 
		JFileChooser jfc = new JFileChooser("C://");
		int sv = jfc.showSaveDialog(this);
		if (sv == 0) {
			filename = jfc.getSelectedFile().getName();
			img.setIcon(imageIcon);
		}
		//�޽���
		if (sv != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "������ �������� �ʾҽ��ϴ�.", "���", JOptionPane.WARNING_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "������ ������ ���ε� �Ǿ����ϴ�.");
			System.out.println(filename);
		}
	}
}
