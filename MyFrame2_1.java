package project;

import javax.swing.JFrame;
import javax.swing.JPanel;

class MyFrame2_1 extends JFrame{
	   public MyFrame2_1(){
		   try {
				this.add(new MoneyManage());
			} catch (Exception e) {
		
			}
	   }
}//재고관리화면