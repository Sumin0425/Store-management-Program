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

//������â


public class StockManage extends JFrame {

	JLabel title = new JLabel("��� ����â(�ʱ������ �Է��ϰ� enter�Է��� �����ư)");

	File mfile = new File("stock.txt");
	JScrollPane sp;
	JTable table;

	// �������� �����͸� ���ε��ϱ� ���ؼ� JTable�ȿ��� �����͸� �����ϴ� ��ü
	DefaultTableModel model;

	// db���� ��ȸ�� �����͸� ���� ����
	Vector data = new Vector();
	ArrayList<stockItem> itemarray = new ArrayList<>();

	// �÷����� ���� ����
	Vector<String> colName;
	Vector<String> rowName;

	FileIO file = new FileIO();

	StockManage() throws Exception {
		// ������ setting �޼ҵ�
		file.fileRead();
		itemarray = file.getItemList();
		dataSetting();
		display();
		pack();
		setVisible(true);

	}

	// JTable�� �÷����� ���Ϳ� �߰��ϱ� ���� �޼ҵ�
	public void dataSetting() {
		colName = new Vector<String>();
		colName.add("���");
		colName.add("�ʱ� ���");
		colName.add("�Һ�");
		colName.add("������");

	}


	String[] stock2 = new String[20];

	public void display() {
		setLayout(new BorderLayout(10, 10));
		title.setFont(new Font("default", Font.BOLD, 30));// �󺧿� ��Ʈũ�⸦ �����ϴ� �޼���
		add("North", title);
		
		model = new DefaultTableModel(data, colName);
		
		table = new JTable(model);
		sp = new JScrollPane(table);
		// stock ������ �ҷ��ͼ� ȭ�鿡 �Ѹ��� ���ؼ� stock.txt�� �����ϴ� ��
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
			// stock ������ ���� �� �ʱⰪ ����
			if (stock2[i] == null) {
				stock2[i] = "0";
			}
			Integer temp = new Integer(Integer.parseInt(stock2[i]) - itemarray.get(i).get_stockcount());
			System.out.println(temp);
			rowName.add(stock2[i] + "");
			rowName.add(itemarray.get(i).get_stockcount() + "");
			//���� ����� 5�����̸� ���ڻ��� ���������� �ٲٱ�
			if(temp<=5) {
				rowName.add(makeColor(temp.toString(), "#FF0000"));
			} else {
				rowName.add(temp.toString());
			}
			model.addRow(rowName);
			rowName = null;
		}
		
		
		JButton btn = new JButton("��   ��");
		btn.setBackground(Color.LIGHT_GRAY);
		add("South", btn);
		
		//�����ư�� ������ stock.txt ���Ͽ� �ʱ���� ����
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] value = new String[20];
				FileWriter reader;
				try {
					// ���� ���� ����
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
						// ���ο� ���� �Է�
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
	
	//���� ���� �ٲٱ� ���� �޼ҵ�
	private String makeColor(String text, String color) {
		StringBuffer sb = new StringBuffer();
		sb.append("<html><font color=\""+color+"\">");
        sb.append(text);
        sb.append("</font>");
        return sb.toString();
	}

}
