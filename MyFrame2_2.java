package project;

import javax.swing.JFrame;
import javax.swing.JPanel;

class MyFrame2_2 extends JFrame{
	   public MyFrame2_2(){
	      try {
			add(new StockManage());
		} catch (Exception e) {
	   }
	   }
}
//매출관리화면