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

//매출관리창

public class MoneyManage extends JFrame{
	    JLabel title = new JLabel("매출관리창");
	    int[] itemnumber = new int[20];
	    JScrollPane sp;
	    JTable table;
	     
	    //동적으로 데이터를 바인딩하기 위해서 JTable안에서 데이터를 관리하는 객체
	    DefaultTableModel model;
	     
	    //db에서 조회된 데이터를 담을 벡터
	    Vector data = new Vector();
	    ArrayList<stockItem> itemarray = new ArrayList<>(); 
	     
	    //컬럼명을 담을 벡터
	    Vector<String> colName; 
	    Vector<String> rowName;
	    
	    FileIO file = new FileIO();
	    //가격 배열로 설정
	    int [] MenuValue = {10000,12000,10000,11000,12000,10000,10000,5000,5500,4000,4000,3000,2000,3000,1500,1500,1500,2000,2000,2000,3000};
	    MoneyManage() throws Exception{
	        //데이터 setting 메소드
	        file.fileRead();
	        itemarray = file.getItemList();
	        dataSetting();
	        display();
	        pack();
	        setVisible(true);
	    }
	    
	    

	    //JTable의 컬럼명을 벡터에 추가하기 위한 메소드
	    public void dataSetting() {
	        colName = new Vector<String>();
	        colName.add("풍명");
	        colName.add("수량");
	        colName.add("단가");
	        colName.add("수입");
	    }
	    
	  
	    //화면에 나타내는 메소드
	    public void display(){
	        setLayout(new BorderLayout(10,10));
	        title.setFont(new Font("default",Font.BOLD,30)); 
	        add("North",title);
	         
	        model = new DefaultTableModel(data,colName);
	        table = new JTable(model);
	        sp = new JScrollPane(table);
	        int total_value=0;
	        
	        //총 매출 및 메뉴별 매출 계산
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
	        
	        JTextField f1 = new JTextField("총합:"+String.valueOf(total_value)+"");
	        add("South",f1);
	        f1.setFont(new Font("default",Font.BOLD,30)); 
	    }
	    
	    
}

