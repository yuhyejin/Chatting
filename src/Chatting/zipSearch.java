package Chatting;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import JDBC.InfoDAO;
import JDBC.zipDTO;

public class zipSearch extends JFrame implements ActionListener {

        private JPanel contentPane, panel, p;
        private JTable table;
        private JComboBox comboBox;
        private JComboBox comboBox_1;
        private JComboBox comboBox_2;
        private JButton okbtn,cancelbtn;        
        private JScrollPane scrollPane;
        String address1, address2, address3, address4, address5, address6, address7, address8, address;
        InfoDAO infodao = new InfoDAO();
 
        public zipSearch() {
        	// GUI����
        	setTitle("�����ȣ �˻�");
        	dispose();
            setBounds(450, 250, 628, 590);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setBackground(new Color(255, 255, 255));	// ������ ����
            contentPane.setLayout(null);
            setResizable(false); // ������ ũ�� ����(������ �� ���� ��)
            setVisible(true); // �������� ȭ�鿡 ��Ÿ�� ������ ���� 

            panel = new JPanel();
            panel.setBorder(new TitledBorder(null, "�����ȣ �˻�", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            panel.setBounds(6, 22, 594, 70);
            panel.setBackground(new Color(255, 255, 255));

            contentPane.add(panel);
            panel.setLayout(null);

            okbtn = new JButton("Ȯ��");
            okbtn.setBackground(new Color(255, 255, 255));
            okbtn.setBounds(220, 490, 65, 25);
            contentPane.add(okbtn);
            
            cancelbtn = new JButton("���");
            cancelbtn.setBackground(new Color(255, 255, 255));
            cancelbtn.setBounds(320, 490, 65, 25);
            contentPane.add(cancelbtn);
            
            scrollPane = new JScrollPane();
            scrollPane.setBounds(12, 111, 588, 356);
            contentPane.add(scrollPane);
            
            // �ּҸ� ��Ÿ�� ���̺� ����
            table = new JTable();
            table.setModel(new DefaultTableModel(
                    
         		   new Object[][] {
         			   {" ", " ", " ", " ", " ", " ", " ", " "},
                    },
                    new String[] { // �Ϸù�ȣ(�ø��� ��ȣ)
                           "\uC6B0\uD3B8\uBC88\uD638", "\uC2DC.\uB3C4", "\uAD6C.\uAD70", "\uB3D9", "\uB9AC", "\uC2DC\uC791\uBC88\uC9C0", "\uB9C8\uC9C0\uB9C9\uBC88\uC9C0", "\uC77C\uB828\uBC88\uD638"
                    })
            { // �÷��� ������ �Ұ��� �ϰ� ����
         	   boolean[] columnEditables = new boolean[] {
                        false, false, false, false, false, false, false, false
                 };
         	   
         	   // �˻��� �ּ� ���� ���̺� ��Ŀ� �°� ����ֱ�
                 public boolean isCellEditable(int row, int column) {
                        return columnEditables[column];
                 }
            });
            scrollPane.setViewportView(table);

            okbtn.addActionListener(this); //Ȯ�� ��ư �׼Ǹ�����
            cancelbtn.addActionListener(this); //��� ��ư �׼Ǹ�����
            
            // ù��° combobox ����
            comboBox = new JComboBox();  
            comboBox.setBounds(146, 40, 100, 20);
            panel.add(comboBox);
            comboBox.addItem("��.�� ����");
       
            displaySido();
            
          // ��.�� �޺��ڽ�
            comboBox.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange()==ItemEvent.SELECTED)
                    selectSido(comboBox.getSelectedItem().toString());                             
                    }
            });

            comboBox.setToolTipText(""); // ó�� �����Ҷ� �޺��ڽ� �������� ����               
           
            JLabel label = new JLabel("��.��");
            label.setBounds(146, 14, 100, 20);
            panel.add(label);
            label.setHorizontalAlignment(SwingConstants.CENTER);
          
            // ��.�� �޺��ڽ� 
            comboBox_1 = new JComboBox();
            comboBox_1.setBounds(258, 40, 100, 20);
            panel.add(comboBox_1);
            comboBox_1.addItem("��.�� ����");

            JLabel label_1 = new JLabel("��.��");
            label_1.setBounds(258, 14, 100, 20);
            panel.add(label_1);
            label_1.setHorizontalAlignment(SwingConstants.CENTER);
           
            //�̺�Ʈ ������ : ����ڰ� ������ �ּҷ� �ٲ�
            comboBox_1.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                           if(e.getStateChange()==ItemEvent.SELECTED)
                        	   		// table�� ����ֱ� ����
                                   selectGugun(comboBox.getSelectedItem().toString() ,comboBox_1.getSelectedItem().toString());
                    }
            });
            

            // �� �޺��ڽ�
            comboBox_2 = new JComboBox();
            comboBox_2.setBounds(370, 40, 100, 20);

            panel.add(comboBox_2);
            comboBox_2.addItem("�� ����");             

            JLabel label_2 = new JLabel("��");
            label_2.setBounds(370, 14, 100, 20);
            panel.add(label_2);
            label_2.setHorizontalAlignment(SwingConstants.CENTER);
            
          //�̺�Ʈ ������ : ����ڰ� ������ �ּҷ� �ٲ�
            comboBox_2.addItemListener(new ItemListener() {
            	public void itemStateChanged(ItemEvent e) {
            		if(e.getStateChange()==ItemEvent.SELECTED) {
            			// table�� ����ֱ� ����
                        selectDong(comboBox.getSelectedItem().toString(), comboBox_1.getSelectedItem().toString(), 
                              comboBox_2.getSelectedItem().toString());
            		}
            	}
            });
     }

     // ���α׷� ���۽� ��.�� �����ֱ�
     public void displaySido() {
    	 ArrayList<zipDTO> sidoList = infodao.searchSido();

    	 //�õ�����Ʈ���� for���� ���� sido�� �˻�
         for(int i = 0 ; i < sidoList.size() ; i++){
         	   zipDTO zipcode = sidoList.get(i);
                    comboBox.addItem(zipcode.getSido());
         }    
         // DB���� ����
         infodao.disconnection();
     }

     // sido ����(��.�� ���)
     public void selectSido(String sido){

            System.out.println(sido);
            ArrayList<zipDTO> gugunList = infodao.searchGugun(sido);
            comboBox_1.removeAllItems();
            comboBox_2.removeAllItems();
            comboBox_1.addItem("��.�� ����");

       	 	//��������Ʈ���� for���� ���� gugun�� �˻�
            for(int i = 0 ; i < gugunList.size() ; i++){
         	   zipDTO zipcode = gugunList.get(i);
                comboBox_1.insertItemAt(zipcode.getGugun(), i);
            }

            table.setModel(new zipTable());
            // DB���� ����
            infodao.disconnection();
     }      

     // gugun ����(�� ���)
     public void selectGugun(String sido, String gugun){

            System.out.println(gugun);
            ArrayList<zipDTO> dongList = infodao.searchDong(sido, gugun);
            comboBox_2.removeAllItems();
            comboBox_2.addItem("�� ����");
            
            //������Ʈ���� for���� ���� dong�� �˻�
            for(int i = 0 ; i < dongList.size() ; i++){
         	   zipDTO zipcode = dongList.get(i);
                comboBox_2.insertItemAt(zipcode.getDong(),i);
            }
            table.setModel(new zipTable());
            //DB���� ����
            infodao.disconnection();                 
     }

     // Dong ����(dong�� ���������� �����ϸ� ���̺� ��ü �ּ� ���)
     public void selectDong(String sido, String gugun, String dong){
            ArrayList<zipDTO> addressList = infodao.searchAddress(sido, gugun, dong);

            Object[][] arrAdd = new Object[addressList.size()][8];
       	 	//������Ʈ���� for���� ���� dong�� �˻�
            for(int i = 0 ; i < addressList.size() ; i++){
         	   zipDTO zipcode = addressList.get(i);
         	   //���̺� �ּ� ���!
                System.out.println(zipcode.getZipcode()+ " " +zipcode.getSido()+ " " +zipcode.getGugun()+ " " +zipcode.getDong() +
                      " " + zipcode.getRi() + " " + zipcode.getBldg() + " " + zipcode.getBunji() + " " + zipcode.getSeq());                      

                //���̺� �ֱ�
                arrAdd[i][0] = zipcode.getZipcode();
                arrAdd[i][1] = zipcode.getSido();
                arrAdd[i][2] = zipcode.getGugun();
                arrAdd[i][3] = zipcode.getDong();
                arrAdd[i][4] = zipcode.getRi();
                arrAdd[i][5] = zipcode.getBldg();
                arrAdd[i][6] = zipcode.getBunji();
                arrAdd[i][7] = zipcode.getSeq();                       
                table.setModel(new zipTable(arrAdd));
            }

            //DB���� ����
            infodao.disconnection();
     }
     
     // �̺�Ʈ ������
     public void actionPerformed(ActionEvent e) {
    	 // Ȯ�� ��ư�� ������ ȸ�����Կ��ִ� �����ȣ �ؽ�Ʈ �ʵ忡 ������ �ּҸ� ��Ÿ���� ��
    	 if(e.getSource()==okbtn) {
 			int row = table.getSelectedRow();
 			TableModel model = table.getModel(); 
	 		   	address1 = (String)model.getValueAt(row, 0);
	 		   	address2 = (String)model.getValueAt(row, 1);
	 		   	address3 = (String)model.getValueAt(row, 2);
	 		   	address4 = (String)model.getValueAt(row, 3);
	 		   	address5 = (String)model.getValueAt(row, 4);
	 		   	address6 = (String)model.getValueAt(row, 5);
	 		   	address7 = (String)model.getValueAt(row, 6);
	 		   	address8 = (String)model.getValueAt(row, 7);
			   
	 		   	address = address1 + " " + address2 + " " + address3 + " " + address4 + " " + address5
	 		   			+ " " + address6 + " " + address7 + " " + address8;

	 		   	Register regi = new Register(address);
	 		   	dispose();
 		}
 		//��� ��ư ������ �ش� �������� ����
 		else if (e.getSource()==cancelbtn) {
 			dispose();
 			
 		}
 		
     }            
}
        


