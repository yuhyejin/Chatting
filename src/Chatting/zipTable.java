package Chatting;

import javax.swing.table.AbstractTableModel;

public class zipTable extends AbstractTableModel {
        //�÷��� �̸�
        String[] columNames =
        	{"�����ȣ","��.��","��.��","��","��","�ǹ���","����", "�Ϸù�ȣ"};
        //������ ����ó��
        Object[][] data = {{" ", " "," "," "," "," "," "," "}};
   
        public zipTable(){
        }

        public zipTable(Object[][] data) {
               this.data = data;
        }

        //getter ������ �ּ� ������ ����
        @Override
        public int getColumnCount() {
               return columNames.length;
        }

        @Override
        public int getRowCount() {
               return data.length;           //2�� �迭�� ����
        }

        @Override
        public Object getValueAt(int arg0, int arg1) {
               return data[arg0][arg1];
        }

        @Override
        public String getColumnName(int arg0) {
               return columNames[arg0];
        }

}

