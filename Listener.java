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
	}//매장관리버튼 누를때

class MyListener_1 implements ActionListener{
		public void actionPerformed(ActionEvent e){
		      JButton button = (JButton)e.getSource();
		      MyFrame2_2 g = new MyFrame2_2();
		}
	}//재고관리버튼 누를때

class MyListener_2 implements ActionListener{
		public void actionPerformed(ActionEvent e){
		      JButton button = (JButton)e.getSource();
		      MyFrame2_1 g = new MyFrame2_1();
		}
	}//매출관리버튼 누를 때