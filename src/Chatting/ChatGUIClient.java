package Chatting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Chatting.join;
import Chatting.ChatGUIClient.Myaction;

public class ChatGUIClient extends JFrame {

	JTextField txt; // ������ �ؽ�Ʈ �Է�â
	JTextArea chatting; // ���۹��� �ؽ�Ʈ ���
	JButton exit, go, join; // ��ư ���� ����
	JScrollPane js; // ��ũ�ѹ� ����
	JPanel contentPane;

	private static String id; // ����� ���̵� �����ϴ� ���� ����
	private static String ip = "192.168.35.193"; // ip �����ϴ� ����
	private static int port = 8080; // ������ȣ ���� ����

	private Socket socket; // �������� ����� ����
	private InputStream is; // �Է½�Ʈ��
	private OutputStream os; // ��½�Ʈ��
	private DataInputStream dis; // �ڷ����� ����Ʈ�� �Է�
	private DataOutputStream dos; // �ڷ����� ����Ʈ�� ���

	public ChatGUIClient(String id)// ������
	{
		this.id = id;

		init();
		start();

		System.out.println("�Ű� ������ �Ѿ�� �� : " + id + " " + ip + " " + port + "\n");

		network();

	}

	public void network() {
		// ������ ����
		try {
			socket = new Socket(ip, port);
			if (socket != null) // socket�� null���� �ƴҶ� ��! ����Ǿ�����
			{
				Connection(); // ���� �޼ҵ带 ȣ��
			}
		} catch (UnknownHostException e) {

		} catch (IOException e) {
			chatting.append("���� ���� ����!!\n");
		}

	}

	public void Connection() { // ���� ���� �޼ҵ� ����κ�

		try { // ��Ʈ�� ����
			is = socket.getInputStream();
			dis = new DataInputStream(is);

			os = socket.getOutputStream();
			dos = new DataOutputStream(os);

		} catch (IOException e) {
			chatting.append("��Ʈ�� ���� ����!!\n");
		}

		send_Message(id); // ���������� ����Ǹ� ���� id�� ����

		Thread th = new Thread(new Runnable() { // �����带 ������ �����κ��� �޼����� ����

			@Override
			public void run() {
				while (true) {

					try {
						String msg = dis.readUTF(); // �޼����� �����Ѵ�
						chatting.append(msg + "\n");
					} catch (IOException e) {
						chatting.append("�޼��� ���� ����!!\n");
						// ������ ���� ��ſ� ������ ������ ��� ������ �ݴ´�
						try {
							os.close();
							is.close();
							dos.close();
							dis.close();
							socket.close();
							break; // ���� �߻��ϸ� while�� ����
						} catch (IOException e1) {

						}

					}
				} // while�� ��

			}// run�޼ҵ� ��
		});

		th.start();

	}

	public void send_Message(String str) { // ������ �޼����� ������ �޼ҵ�
		try {
			dos.writeUTF(str);
		} catch (IOException e) {
			chatting.append("�޼��� �۽� ����!!\n");
		}
	}

	public void init() { // ȭ�鱸�� �޼ҵ�

		// â, ������ ������Ʈ ���� �� ����
		txt = new JTextField();
		chatting = new JTextArea();
		JPanel contentPane;

		// �ؽ�Ʈ ���â�� ��ũ�� �� ����
		js = new JScrollPane(chatting);
		js.setBounds(10, 50, 430, 540);

		// BorderLayout ��ġ������, JTextArea�� ���߾ӿ� ����
		add(js, "Center");

		// �ؽ�Ʈ �ʵ带 �ϴܿ� ����
		add(txt, BorderLayout.SOUTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// â ũ�� ����
		setBounds(600, 100, 470, 720);

		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setBackground(new Color(227, 219, 235)); // ������ ����
		contentPane.setLayout(null); // ��ġ�����ڸ� ���Ƿ� ����

		contentPane.add(js);

		chatting.setCaretPosition(chatting.getDocument().getLength()); // JTextArea�� ������ �߰��� ������ ��ũ�� ������ �ʰ� �ٷ� ����
		js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // As needed �� �ʿ信���ؼ� ������ �������� ��ũ�� �ٰ�
																					// �����
		// ä�� �Է�
		txt = new JTextField();
		txt.setBounds(10, 600, 350, 60);
		contentPane.add(txt);
		txt.setColumns(10);

		// �ʴ��ϱ� ��ư
		join = new JButton("�ʴ��ϱ�");
		join.setBounds(265, 25, 87, 20);
		join.setBackground(new Color(237, 237, 246));
		contentPane.add(join);

		// ��ư �׼��̺�Ʈ
		join.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new join(id);
			}

		});

		// ������ ��ư
		exit = new JButton("������");
		exit.setBounds(358, 25, 80, 20);
		exit.setBackground(new Color(237, 237, 246));
		contentPane.add(exit);

		// ��ư �׼��̺�Ʈ
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					os.close();
					is.close();
					dos.close();
					dis.close();
					socket.close();
					dispose();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		getContentPane().add(exit);

		// ���� ��ư
		go = new JButton("����");
		go.setBounds(370, 600, 70, 60);
		go.setBackground(new Color(237, 237, 246));
		contentPane.add(go);

		chatting.setLineWrap(true); // �ۻ��� �Ѿ�� �� �ٲ�
		chatting.setEditable(false); // �Է�����

		// â�� ���̵��� ����
		setVisible(true);

		// �ؽ�Ʈ �ʵ忡 Ŀ�� �Է�
		txt.requestFocus();

		// X��ư Ŭ���� ���� ����ǵ��� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void start() { // �׼��̺�Ʈ ���� �޼ҵ�

		go.addActionListener(new Myaction()); // ����Ŭ������ �׼� �����ʸ� ��ӹ��� Ŭ������
		txt.addActionListener(new Myaction()); // ���� // ����

	}

	class Myaction implements ActionListener // ����Ŭ������ �׼� �̺�Ʈ ó�� Ŭ����
	{

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == go) // �׼� �̺�Ʈ�� sendBtn�϶�
			{

				send_Message(id + " : " + txt.getText());
				txt.setText(""); // �޼��� �� ������ ���� �޼��� ����â�� ����.
				txt.requestFocus(); // �޼����� ������ Ŀ���� �ٽ� �ؽ�Ʈ �ʵ�� ��ġ��Ų��

			} else if (e.getSource() == txt) {
				send_Message(txt.getText());
				txt.setText(id + " : " + txt.getText()); // �޼��� �� ������ ���� �޼��� ����â�� ����.
				txt.requestFocus(); // �޼����� ������ Ŀ���� �ٽ� �ؽ�Ʈ �ʵ�� ��ġ��Ų��
			}
		}

	}
}
