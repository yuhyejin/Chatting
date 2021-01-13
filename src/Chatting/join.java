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

public class join extends JFrame implements ActionListener {
   private JTextField tf_add;
   private DefaultTableModel model;
   private JButton bt_search, bt_join, bt_cancel;
   private JTable table;
   private ChatGUIClient cgc; // Ŭ���̾�Ʈ ��ü ����
   private static String user_id;

   //user_id ���
   public join(String user_id) {
	   
	   this.user_id = user_id;
      
      setTitle("ä�ù� �ʴ�");

      //gui ����
      JPanel newp = new JPanel() {
         public void paint(Graphics g) {
            super.paint(g);
            g.drawLine(10, 70, 370, 70);
         }
      };

      newp.setBackground(new Color(227, 219, 235));	// ������ ����
      newp.setLayout(null);

      // ID��
      JLabel la_add = new JLabel("ID");
      la_add.setBounds(35, 10, 60, 60);
      newp.add(la_add);

      // �ʴ��ư
      bt_join = new JButton("�ʴ�");
      bt_join.setBackground(new Color(237, 237, 246));
      bt_join.setBounds(120, 210, 60, 25);
      newp.add(bt_join);

      // ��ҹ�ư
      bt_cancel = new JButton("���");
      bt_cancel.setBackground(new Color(237, 237, 246));
      bt_cancel.setBounds(195, 210, 60, 25);
      newp.add(bt_cancel);

      // �˻���ư
      bt_search = new JButton("�˻�");
      bt_search.setBackground(new Color(237, 237, 246));
      bt_search.setBounds(280, 27, 60, 25);
      newp.add(bt_search);

      // ���̺�
      String[] col = { "NAME", "ID" };
      String[][] row = new String[0][2];

      //�� ���� �Ұ���
      model = new DefaultTableModel(row, col) {
         public boolean isCellEditable(int row, int column) {
            return false;
         }
      }; 

      table = new JTable(model);
      JScrollPane scrollPane = new JScrollPane(table); 
      scrollPane.setBounds(40, 90, 300, 100);
      newp.add(scrollPane);

      scrollPane.setViewportView(table);//���̺� ��ũ������ �߰�

      // �˻��ʵ�
      tf_add = new JTextField(10);
      tf_add.setBounds(60, 27, 205, 25);
      newp.add(tf_add);

      add(newp);

      setBounds(600, 100, 400, 300);
      setResizable(false);
      setVisible(true);

      // �̺�Ʈ ó��
      tf_add.addActionListener(this);
      bt_cancel.addActionListener(this);
      bt_join.addActionListener(this);
   }

   //�̺�Ʈ ó��
   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub

	  //�˻� ��ư 
      if (e.getSource() == tf_add) {
         String Id = tf_add.getText();//id�� �Է��Ͽ� �ʴ��� ģ�� ã��
         if (Id.length() < 1) { //�Է��� �ȵ� ���
            JOptionPane.showMessageDialog(this, "ID�� �Է��ϼ���.");
            return;
         }
         //model�� �� ����
         for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
         }
         InfoDAO dao = new InfoDAO(); // infoDAO ��� ���� ���� 
         ArrayList<InfoDTO> list = null;
         try {
            list = dao.friendSearch(Id); // id�� list�ȿ� �ִ� ���� ã��
         } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }

         //ã�� ����(id, name)�� model ���̺� �߰� 
         for (InfoDTO infodto : list) {
            String[] data = { infodto.getId(), infodto.getName() };
            model.addRow(data);
         }
      }

      //�˻���ư
      else if (e.getSource() == bt_search) {
         String Id = tf_add.getText();
         if (Id.length() < 1) { // �Է��� �ȵ� ���
            JOptionPane.showMessageDialog(this, "ID�� �Է��ϼ���.");
            return;
         }
         //model�� �� ����
         for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
         }
         InfoDAO dao = new InfoDAO(); // infoDAO ��� ���� ���� 
         ArrayList<InfoDTO> list = null;
         //Id�� ����Ͽ� InfoDAO���� DB�� �����ϴ� ģ�� ã��
         try {
            list = dao.friendSearch(Id);
         } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }

         // InfoDTO�� �ִ� Id�� Name ������ model ���̺� �߰�
         for (InfoDTO infodto : list) {
            String[] data = { infodto.getId(), infodto.getName() };
            model.addRow(data);
         }
      }

      //�ʴ��ư
      if (e.getSource() == bt_join) {

         JOptionPane.showMessageDialog(null, "�ʴ밡 �Ϸ�Ǿ����ϴ�.");
         dispose();
      }

      //��� ��ư
      if (e.getSource() == bt_cancel) {
         dispose(); // �ϳ��� �����Ӹ� ����
      }

   }
}