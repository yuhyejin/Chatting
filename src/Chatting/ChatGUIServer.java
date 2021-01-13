package Chatting;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JFrame;

public class ChatGUIServer extends JFrame {

	private ServerSocket socket; // ��������
	private Socket soc; // �������
	private int Port = 8080; // ��Ʈ��ȣ

	private static Vector vc = new Vector(); // ����� ����ڸ� ������ ����

	public ChatGUIServer() {

		server_start();

	}

	// ���� ���� �޼ҵ�
	private void server_start() {

		try {
			socket = new ServerSocket(Port); // ������ ��Ʈ ���ºκ�
			System.out.println("socket>>>" + socket);
			System.out.println("ä�� ���� ������...");
			
			if (socket != null) // socket �� ���������� ��������
			{
				Connection();
			}

		} catch (IOException e) {
			System.out.println("������ �̹� ������Դϴ�...");

		}

	}

	// Ŭ���̾�Ʈ�� ���� �޼ҵ�
	private void Connection() {

		Thread th = new Thread(new Runnable() { // ����� ������ ���� ������

			@Override
			public void run() {
				while (true) { // ����� ������ ����ؼ� �ޱ� ���� while��
					try {
						System.out.println("����� ���� �����...");
						soc = socket.accept(); // accept�� �Ͼ�� �������� ���� �����
						System.out.println("����� ����!!");

						UserInfo user = new UserInfo(soc, vc); // ����� ���� ������ �ݹ� ������Ƿ�, user Ŭ���� ���·� ��ü ����
																// �Ű������� ���� ����� ���ϰ�, ���͸� ��Ƶд�
						vc.add(user); // �ش� ���Ϳ� ����� ��ü�� �߰�

						user.start(); // ���� ��ü�� ������ ����

					} catch (IOException e) {	// ���ӿ���
						System.out.println("!!!! accept ���� �߻�... !!!!");
					}

				}

			}
		});

		th.start();

	}

	static class UserInfo extends Thread {

		private InputStream is;	// �Է½�Ʈ��
		private OutputStream os;	// ��½�Ʈ��
		private DataInputStream dis;	// �ڷ����� ����Ʈ�� �Է�
		private DataOutputStream dos;	// �ڷ����� ����Ʈ�� ���

		private Socket user_socket;
		private static Vector user_vc;	// ����ڸ� ����

		private static String Nickname = "";

		public UserInfo(Socket soc, Vector vc) // �����ڸ޼ҵ�
		{
			// �Ű������� �Ѿ�� �ڷ� ����
			this.user_socket = soc;
			this.user_vc = vc;

			User_network();

		}

		public void User_network() {
			try {
				is = user_socket.getInputStream();
				dis = new DataInputStream(is);
				os = user_socket.getOutputStream();
				dos = new DataOutputStream(os);

				Nickname = dis.readUTF(); // ������� �г��� �޴ºκ�
				System.out.println("������ ID " + Nickname + "");

			} catch (Exception e) {
				System.out.println("��Ʈ�� ���� ����");
			}

		}

		// Ŭ���̾�Ʈ�� ���� �޼��� �޴� �޼ҵ�
		public void InMessage(String str) {
			System.out.println("����ڷκ��� ���� �޼��� : " + str + "");
			// ����� �޼��� ó��
			broad_cast(str);	// broad_cast()�� �޼����� ����

		}

		// ���� �޼����� Ŭ���̾�Ʈ���� ������
		public static void broad_cast(String str) {
			for (int i = 0; i < user_vc.size(); i++) {	// Ŭ���̾�Ʈ ����ŭ for�� ����
				UserInfo imsi = (UserInfo) user_vc.elementAt(i);	
				imsi.send_Message(str);	// send_Message()�� �޼��� ����
			}

		}

		// Ŭ���̾�Ʈ ȭ�鿡 ���
		public void send_Message(String str) {
			try {
				dos.writeUTF(str);	//���
			} catch (IOException e) {
				System.out.println("�޽��� �۽� ���� �߻�");
			}
		}

		public void run() // ������ ����
		{

			UserInfo.broad_cast("[" + Nickname + "]" + "���� �����߽��ϴ�.");
			while (true) {
				try {

					// ����ڿ��� �޴� �޼���
					String msg = dis.readUTF();
					InMessage(msg);

				} catch (IOException e) {

					try {
						dos.close();
						dis.close();
						user_socket.close();
						vc.removeElement(this); // �������� ���� ��ü�� ���Ϳ��� �����
						System.out.println(vc.size() + " : ���� ���Ϳ� ����� ����� ��");
						System.out.println("����� ���� ������ �ڿ� �ݳ�");
						UserInfo.broad_cast("[" + Nickname + "]" + "���� �����߽��ϴ�.");
						break;

					} catch (Exception ee) {

					} // catch�� ��
				} // �ٱ� catch����
			}

		}// run�޼ҵ� ��

	} // ���� userinfoŬ������
}
