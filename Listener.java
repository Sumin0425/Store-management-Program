package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


class MyListener implements ActionListener{
	   public void actionPerformed(ActionEvent e){
	      JButton button = (JButton)e.getSource();
	      MyFrame2 g = new MyFrame2();
	   }
	}//���������ư ������

class MyListener_1 implements ActionListener{
		public void actionPerformed(ActionEvent e){
		      JButton button = (JButton)e.getSource();
		      MyFrame2_2 g = new MyFrame2_2();
		}
	}//��������ư ������

class MyListener_2 implements ActionListener{
		public void actionPerformed(ActionEvent e){
		      JButton button = (JButton)e.getSource();
		      MyFrame2_1 g = new MyFrame2_1();
		}
	}//���������ư ���� ��