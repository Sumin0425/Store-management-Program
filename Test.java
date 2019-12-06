package project;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

//�޴� Ŭ����
class menu {
	private String name;
	private int value;
	private int[] store = new int[22];
	private int count;
	
	public menu() {
		this.name = "NULL";
		this.value = 0;
	}
	
	public menu(String name,int value){
		this.name = name;
		this.value = value;
	}
	
	public void set_name (String name) {
		this.name = name;
	}
	
	public void set_value (int value) {
		this.value = value;
	}
	
	public void set_count(int count) {
		this.count = count;
	}
	
	
	public String return_name() {
		return name;
	} //�ش� �޴��� �̸� ��ȯ 
	
	public int return_value() {
		return value;
	} //�ش� �޴��� ���� ��ȯ 
	
	
	public int return_count() {
		return count;
	} //�޴��ֹ����� ��ȯ
	
	
	
	public String return_menu() {
		if (count>0) {
			return String.format("%s %d", name, count);
		}
		else {
			return String.format("");
		}
	}
}