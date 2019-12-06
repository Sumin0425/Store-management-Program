package project;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//�������â

public class MoneyManage extends JFrame{
	    JLabel title = new JLabel("�������â");
	    int[] itemnumber = new int[20];
	    JScrollPane sp;
	    JTable table;
	     
	    //�������� �����͸� ���ε��ϱ� ���ؼ� JTable�ȿ��� �����͸� �����ϴ� ��ü
	    DefaultTableModel model;
	     
	    //db���� ��ȸ�� �����͸� ���� ����
	    Vector data = new Vector();
	    ArrayList<stockItem> itemarray = new ArrayList<>(); 
	     
	    //�÷����� ���� ����
	    Vector<String> colName; 
	    Vector<String> rowName;
	    
	    FileIO file = new FileIO();
	    //���� �迭�� ����
	    int [] MenuValue = {10000,12000,10000,11000,12000,10000,10000,5000,5500,4000,4000,3000,2000,3000,1500,1500,1500,2000,2000,2000,3000};
	    MoneyManage() throws Exception{
	        //������ setting �޼ҵ�
	        file.fileRead();
	        itemarray = file.getItemList();
	        dataSetting();
	        display();
	        pack();
	        setVisible(true);
	    }
	    
	    

	    //JTable�� �÷����� ���Ϳ� �߰��ϱ� ���� �޼ҵ�
	    public void dataSetting() {
	        colName = new Vector<String>();
	        colName.add("ǳ��");
	        colName.add("����");
	        colName.add("�ܰ�");
	        colName.add("����");
	    }
	    
	  
	    //ȭ�鿡 ��Ÿ���� �޼ҵ�
	    public void display(){
	        setLayout(new BorderLayout(10,10));
	        title.setFont(new Font("default",Font.BOLD,30)); 
	        add("North",title);
	         
	        model = new DefaultTableModel(data,colName);
	        table = new JTable(model);
	        sp = new JScrollPane(table);
	        int total_value=0;
	        
	        //�� ���� �� �޴��� ���� ���
	        for(int i=0;i<20;i++){
	        	itemnumber[i]+=itemarray.get(i).get_stockcount()*MenuValue[i];
	        	total_value = total_value +itemnumber[i];
	        }
	        
	        add("Center",sp);
	        for(int i=0; i<itemarray.size();i++){
		        rowName = new Vector<>();

		        rowName.add(itemarray.get(i).get_stockname());
	        	rowName.add(itemarray.get(i).get_stockcount()+"");
	        	rowName.add(MenuValue[i]+"");
	        	rowName.add(itemnumber[i]+"");
	        	
	        	model.addRow(rowName);
	        	rowName =null;
	        }
	        
	        JTextField f1 = new JTextField("����:"+String.valueOf(total_value)+"");
	        add("South",f1);
	        f1.setFont(new Font("default",Font.BOLD,30)); 
	    }
	    
	    
}

