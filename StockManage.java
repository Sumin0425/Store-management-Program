package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//재고관리창


public class StockManage extends JFrame {

	JLabel title = new JLabel("재고 관리창(초기재고량을 입력하고 enter입력후 저장버튼)");

	File mfile = new File("stock.txt");
	JScrollPane sp;
	JTable table;

	// 동적으로 데이터를 바인딩하기 위해서 JTable안에서 데이터를 관리하는 객체
	DefaultTableModel model;

	// db에서 조회된 데이터를 담을 벡터
	Vector data = new Vector();
	ArrayList<stockItem> itemarray = new ArrayList<>();

	// 컬럼명을 담을 벡터
	Vector<String> colName;
	Vector<String> rowName;

	FileIO file = new FileIO();

	StockManage() throws Exception {
		// 데이터 setting 메소드
		file.fileRead();
		itemarray = file.getItemList();
		dataSetting();
		display();
		pack();
		setVisible(true);

	}

	// JTable의 컬럼명을 벡터에 추가하기 위한 메소드
	public void dataSetting() {
		colName = new Vector<String>();
		colName.add("재고");
		colName.add("초기 재고량");
		colName.add("소비량");
		colName.add("남은량");

	}


	String[] stock2 = new String[20];

	public void display() {
		setLayout(new BorderLayout(10, 10));
		title.setFont(new Font("default", Font.BOLD, 30));// 라벨에 폰트크기를 셋팅하는 메서드
		add("North", title);
		
		model = new DefaultTableModel(data, colName);
		
		table = new JTable(model);
		sp = new JScrollPane(table);
		// stock 파일을 불러와서 화면에 뿌리기 위해서 stock.txt에 저장하는 것
		try {
			BufferedReader in = new BufferedReader(new FileReader(mfile));
			String s;
			int i = 0;
			while ((s = in.readLine()) != null || i < 20) {
				stock2[i] = s;
				i++;
			}
			in.close();
		} catch (Exception e) {
			
			System.out.println("!");
		}
		add("Center", sp);
		for (int i = 0; i < itemarray.size(); i++) {
			rowName = new Vector<>();
			
			rowName.add(itemarray.get(i).get_stockname());
			// stock 파일이 없을 때 초기값 설정
			if (stock2[i] == null) {
				stock2[i] = "0";
			}
			Integer temp = new Integer(Integer.parseInt(stock2[i]) - itemarray.get(i).get_stockcount());
			System.out.println(temp);
			rowName.add(stock2[i] + "");
			rowName.add(itemarray.get(i).get_stockcount() + "");
			//남은 재고량이 5이하이면 글자색을 빨간색으로 바꾸기
			if(temp<=5) {
				rowName.add(makeColor(temp.toString(), "#FF0000"));
			} else {
				rowName.add(temp.toString());
			}
			model.addRow(rowName);
			rowName = null;
		}
		
		
		JButton btn = new JButton("저   장");
		btn.setBackground(Color.LIGHT_GRAY);
		add("South", btn);
		
		//저장버튼을 누르면 stock.txt 파일에 초기재고량 저장
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] value = new String[20];
				FileWriter reader;
				try {
					// 기존 파일 삭제
					reader = new FileWriter("stock.txt", false);
					reader.write("");
					reader.close();
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
				for (int i = 0; i < 20; i++) {
					value[i] = (String) table.getValueAt(i, 1);
					Integer a = new Integer(value[i]);
					value[i] = a.toString();
					try {
						// 새로운 파일 입력
						reader = new FileWriter("stock.txt", true);
						reader.write(value[i] + "\n");
						reader.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});

	}
	
	//글자 색을 바꾸기 위한 메소드
	private String makeColor(String text, String color) {
		StringBuffer sb = new StringBuffer();
		sb.append("<html><font color=\""+color+"\">");
        sb.append(text);
        sb.append("</font>");
        return sb.toString();
	}

}
