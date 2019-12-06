package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

//가장 처음 화면
class MyFrame extends JFrame{
   public MyFrame(){
	 try {
		BufferedWriter writer = new BufferedWriter(new FileWriter("user.txt"));
		writer.write("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0");
   	  	writer.close();
	}catch (IOException e) {
		e.printStackTrace();
	}
	  setTitle("첫 화면");
	  setSize(700,800);
	  
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JPanel panel1=new JPanel();


      panel1.setLayout(null);
      
      mButton1(panel1,"매장관리",200, 100, 250, 100);
      mButton2(panel1,"재고관리",200, 300, 250, 100);
      mButton3(panel1,"매출관리",200, 500, 250, 100);

      add(panel1);
      setVisible(true);
      
   }
   
   //버튼1 만드는 메소드
   private void mButton1(JPanel panel,String text,int x,int y,int z,int w){
      JButton button = new JButton(text);
      button.setBounds(x, y, z, w);
      button.setBackground(Color.LIGHT_GRAY);
      panel.add(button);
      button.addActionListener(new MyListener());
      
   }
   
   //버튼2 만드는 메소드
   private void mButton2(JPanel panel,String text,int x,int y,int z,int w){
      JButton button = new JButton(text);
      button.setBounds(x, y, z, w);
      button.setBackground(Color.LIGHT_GRAY);
      panel.add(button);
      button.addActionListener(new MyListener_1());
      
   }
   
   //버튼3 만드는 메소드
   private void mButton3(JPanel panel,String text,int x,int y,int z,int w){
      JButton button = new JButton(text);
      button.setBounds(x, y, z, w);
      button.setBackground(Color.LIGHT_GRAY);
      panel.add(button);
      button.addActionListener(new MyListener_2());
      
   }
}


//매장관리화면(테이블)
class MyFrame2 extends JFrame{
	
	ArrayList<stockItem> itemarray = new ArrayList<>();
	public int tablenum;
	private JPanel p_total,panel,p_result= new JPanel(),p_reset,p_input;
	public menu[] Mainmenu = new menu[7],Sidemenu = new menu[7],Drinkmenu = new menu[6];
	public int[][] TablemenuCount = new int[20][5];
	public int[] TablemenuCount1 = new int[20];
	public JTextArea t1=new JTextArea(),t2=new JTextArea(),t3=new JTextArea(),t4=new JTextArea();
	
   public MyFrame2(){
	   for(int i=0; i<20;i++){
   		TablemenuCount1[i]=0;
   		}
      setTitle("매장관리");
      setSize(1200,800);
      
      FileIO file = new FileIO();
	  try {
		file.fileRead();
		itemarray = file.getItemList();
	} catch (Exception e1) {
		e1.printStackTrace();
	}

	  for(int i=0; i<itemarray.size();i++){
  		TablemenuCount1[i]+=itemarray.get(i).get_stockcount();
  		}
    File mfile = new File("user.txt");
    try {
    	mfile.createNewFile();
    } catch (IOException e) {
    	e.printStackTrace();
    }
      JPanel panel1=new JPanel();
      tablenum=1;
      
      panel1.setLayout(null);
      
      JTextField f1 = new JTextField("주문받고자 하는 테이블 버튼을 먼저 누르세요");
      f1.setBounds(50, 20,550,30);
      panel1.add(f1);
      mButton1(panel1,"Table1",50, 50, 250, 50);
      mButton2(panel1,"Table2",350, 50, 250, 50);
      mButton3(panel1,"Table3",50, 400, 250, 50);
      mButton4(panel1,"Table4",350, 400, 250, 50);

      JScrollPane scrollPane1 = new JScrollPane(t1);
      mTextArea(panel1,scrollPane1,50,100,250,160);

      JScrollPane scrollPane2 = new JScrollPane(t2);
      mTextArea(panel1,scrollPane2,350,100,250,160);

      JScrollPane scrollPane3 = new JScrollPane(t3);
      mTextArea(panel1,scrollPane3,50,450,250,160);

      JScrollPane scrollPane4 = new JScrollPane(t4);
      mTextArea(panel1,scrollPane4,350,450,250,160);




      JButton b1=new JButton("계산1");
      b1.setBounds(50, 260, 250, 50);
      b1.setFont(new Font("Dialog",Font.BOLD,20));
      b1.setBackground(Color.LIGHT_GRAY);
      panel1.add(b1);
      b1.addActionListener(new ActionListener() {
                              public void actionPerformed(ActionEvent e) {
                            	  try{
                            		  for(int i=0;i<20;i++){
                            			 TablemenuCount1[i]+=TablemenuCount[i][tablenum]++;
                            		  }
                            		  
                            	  BufferedWriter writer = new BufferedWriter(new FileWriter("user.txt"));
                            	 
                            	  writer.write(OutputString());
                            	  writer.close();
                            	  System.out.println(OutputString());
                            	 
                            	  for(int i=0;i<20;i++){
                            		  TablemenuCount[i][tablenum]=0;
                            	  }
      	Buttongenerator();
      	  }catch(Exception ee){ee.printStackTrace();}
        }
      });
      
      JButton b2=new JButton("계산2");
      b2.setBounds(350, 260, 250, 50);
      b2.setFont(new Font("DialogInput",Font.BOLD,20));
      b2.setBackground(Color.LIGHT_GRAY);
      panel1.add(b2);
      b2.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  try{
        		  for(int i=0;i<20;i++){
         			 TablemenuCount1[i]+=TablemenuCount[i][tablenum]++;
         		  }
        	  BufferedWriter writer = new BufferedWriter(new FileWriter("user.txt"));
        	  
        	  writer.write(OutputString());
        	  writer.close();
        	  System.out.println(OutputString());
        	 
        	  for(int i=0;i<20;i++){
        		  TablemenuCount[i][tablenum]=0;
        	  }
      	Buttongenerator();
      	  }catch(Exception ee){ee.printStackTrace();}
        }
      });
      
      JButton b3=new JButton("계산3");
      b3.setBounds(50, 610, 250, 50);
      b3.setFont(new Font("DialogInput",Font.BOLD,20));
      b3.setBackground(Color.LIGHT_GRAY);
      panel1.add(b3);
      b3.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  try{
        		  for(int i=0;i<20;i++){
         			 TablemenuCount1[i]+=TablemenuCount[i][tablenum]++;
         		  }
        	  BufferedWriter writer = new BufferedWriter(new FileWriter("user.txt"));
        	 
        	  writer.write(OutputString());
        	  writer.close();
        	  System.out.println(OutputString());
        	  
        	  for(int i=0;i<20;i++){
        		  TablemenuCount[i][tablenum]=0;
        	  }
      	Buttongenerator();
      	  }catch(Exception ee){ee.printStackTrace();}
        }
      });
      
      JButton b4=new JButton("계산4");
      b4.setBounds(350, 610, 250, 50);
      b4.setFont(new Font("DialogInput",Font.BOLD,20));
      b4.setBackground(Color.LIGHT_GRAY);
      panel1.add(b4);
      b4.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  try{
        		  for(int i=0;i<20;i++){
         			 TablemenuCount1[i]+=TablemenuCount[i][tablenum]++;
         		  }
        	  BufferedWriter writer = new BufferedWriter(new FileWriter("user.txt"));
        	  
        	  writer.write(OutputString());
        	  writer.close();
        	  System.out.println(OutputString());
        	  
        	  for(int i=0;i<20;i++){
        		  TablemenuCount[i][tablenum]=0;
        	  }
      	Buttongenerator();
      	  }catch(Exception ee){ee.printStackTrace();}
        }
      });
      

  	
  		JPanel p_total=new JPanel();
  		p_total.setLayout(null);
  		p_total.setSize(600,600);
        JLabel result= new JLabel(" ");


               @SuppressWarnings("null")
                           
                     
             JPanel panel = new JPanel();
                            
                            panel.setLayout(new GridLayout(1,3, 10,10));
                            panel.setBackground(Color.pink);
 

                            //취소버튼 생성 및 리스너 등록
                            JButton reset = new JButton("전체취소");
                            reset.setBackground(Color.LIGHT_GRAY);
    
                            panel.add(reset);
                            
                            class MyListener5 implements ActionListener{
                     		   public void actionPerformed(ActionEvent e){
                     			 for (int i=0;i<20;i++) {
                     				 TablemenuCount[i][tablenum]=0;
                               }
                              Buttongenerator();
                     		   }
                     		}
                            reset.addActionListener(new MyListener5());
                      	

                             
                               
                            p_total.add(panel);
                            setVisible(true);
                            panel.setBounds(15,510,430,40);
// p_input패널생성
                            JPanel p_input = new JPanel();
                           

                            p_input.setBorder(new TitledBorder("메뉴"));

                            p_input.setLayout(new GridLayout(8,4));

                            
                         
                            String[] mainMenu_Name = {"메인메뉴1","메인메뉴2","메인메뉴3","메인메뉴4","메인메뉴5","메인메뉴6","메인메뉴7"};
                            int [] mainMenu_Value = {10000,12000,10000,11000,12000,10000,10000};
                            String[] sideMenu_Name = {"사이드메뉴1","사이드메뉴2","사이드메뉴3","사이드메뉴4","사이드메뉴5","사이드메뉴6","사이드메뉴7"};
                            int [] sideMenu_Value = {5000,5500,4000,4000,3000,2000,3000};
                            String[] Drink_Name = {"음료1","음료2","음료3","음료4","음료5","음료6"};
                            int [] Drink_Value = {1500,1500,1500,2000,2000,2000,3000};
                            int [] mainMenu_Stock = {100,100,100,100,100,100,100};
                            int [] DrinkMenu_Stock = {100,100,100,100,100,100,100};
                            
//메뉴버튼 생성후 p_input패널에 등록                            
                            JButton[] mainMenu = new JButton[7], sideMenu = new JButton[7], Drink = new JButton[6];
                            for (int i=0;i<7;i++) {
                          	mainMenu[i] = new JButton(mainMenu_Name[i]);
                          	mainMenu[i].setBackground(Color.LIGHT_GRAY);
                          
                            }
                            for (int i=0;i<7;i++) {
                          	  sideMenu[i] = new JButton(sideMenu_Name[i]);
                          	 sideMenu[i].setBackground(Color.LIGHT_GRAY);
                            }
                            for (int i=0;i<6;i++) {
                          	  Drink[i] = new JButton(Drink_Name[i]);
                          	Drink[i].setBackground(Color.LIGHT_GRAY);
                            }
                            
                            for(int i=0;i<6;i++) {
                          	  p_input.add(mainMenu[i]);
                          	  p_input.add(sideMenu[i]);
                          	  p_input.add(Drink[i]);
                            }
                            p_input.add(mainMenu[6]);
                            p_input.add(sideMenu[6]);

                            p_total.add(p_input);
                            p_input.setBounds(5, 5,450, 500);

                          
                          //메뉴버튼을 눌렀을 때의 리스너등록                               
                            int i=0;
             
                            mainMenu[0].addActionListener(new ActionListener() {
                          	  public void actionPerformed(ActionEvent e) {
                          		  Mainmenu[0].set_count(Mainmenu[0].return_count()+1);
                          		  TablemenuCount[0][tablenum]++;                          		 
                          		  Buttongenerator();
                            	 
                          		  
                          	  }
                            });
                            
                            mainMenu[1].addActionListener(new ActionListener() {
                          	  public void actionPerformed(ActionEvent e) {
                          		  Mainmenu[1].set_count(Mainmenu[1].return_count()+1);
                          		TablemenuCount[1][tablenum]++;                          		
                          		  Buttongenerator();
                          	  }
                            });
                            mainMenu[2].addActionListener(new ActionListener() {
                          	  public void actionPerformed(ActionEvent e) {
                          		  Mainmenu[2].set_count(Mainmenu[2].return_count()+1);
                          		TablemenuCount[2][tablenum]++;         
                          		  Buttongenerator();
                          	  }
                            });
                            mainMenu[3].addActionListener(new ActionListener() {
                          	  public void actionPerformed(ActionEvent e) {
                          		  Mainmenu[3].set_count(Mainmenu[3].return_count()+1);
                          		TablemenuCount[3][tablenum]++;
                          		  Buttongenerator();
                          	  }
                            });
                            mainMenu[4].addActionListener(new ActionListener() {
                          	  public void actionPerformed(ActionEvent e) {
                          		  Mainmenu[4].set_count(Mainmenu[4].return_count()+1);
                          		TablemenuCount[4][tablenum]++;
                          		  Buttongenerator();
                          	  }
                            });
                            mainMenu[5].addActionListener(new ActionListener() {
                          	  public void actionPerformed(ActionEvent e) {
                          		  Mainmenu[5].set_count(Mainmenu[5].return_count()+1);
                          		TablemenuCount[5][tablenum]++;
                          		  Buttongenerator();
                          	  }
                            });
                            mainMenu[6].addActionListener(new ActionListener() {
                          	  public void actionPerformed(ActionEvent e) {
                          		  Mainmenu[6].set_count(Mainmenu[6].return_count()+1);
                          		TablemenuCount[6][tablenum]++;
                          		  Buttongenerator();
                          	  }
                            });
                            
                            sideMenu[0].addActionListener(new ActionListener() {
                      		  public void actionPerformed(ActionEvent e) {
                      			  Sidemenu[0].set_count(Sidemenu[0].return_count()+1);
                      			TablemenuCount[7][tablenum]++;
                      			  Buttongenerator();
                      		  }
                      	  });
                            
                            sideMenu[1].addActionListener(new ActionListener() {
                      		  public void actionPerformed(ActionEvent e) {
                      			  Sidemenu[1].set_count(Sidemenu[1].return_count()+1);
                      			TablemenuCount[8][tablenum]++;
                      			  Buttongenerator();
                      		  }
                      	  });
                            sideMenu[2].addActionListener(new ActionListener() {
                      		  public void actionPerformed(ActionEvent e) {
                      			  Sidemenu[2].set_count(Sidemenu[2].return_count()+1);
                      			TablemenuCount[9][tablenum]++;
                      			  Buttongenerator();
                      		  }
                      	  });
                         
                            sideMenu[3].addActionListener(new ActionListener() {
                      		  public void actionPerformed(ActionEvent e) {
                      			  Sidemenu[3].set_count(Sidemenu[3].return_count()+1);
                      			TablemenuCount[10][tablenum]++;
                      			  Buttongenerator();
                      		  }
                      	  });
                            sideMenu[4].addActionListener(new ActionListener() {
                      		  public void actionPerformed(ActionEvent e) {
                      			  Sidemenu[4].set_count(Sidemenu[4].return_count()+1);
                      			TablemenuCount[11][tablenum]++;
                      			  Buttongenerator();
                      		  }
                      	  });
                            sideMenu[5].addActionListener(new ActionListener() {
                      		  public void actionPerformed(ActionEvent e) {
                      			  Sidemenu[5].set_count(Sidemenu[5].return_count()+1);
                      			TablemenuCount[12][tablenum]++;
                      			  Buttongenerator();
                      		  }
                      	  });
                            sideMenu[6].addActionListener(new ActionListener() {
                      		  public void actionPerformed(ActionEvent e) {
                      			  Sidemenu[6].set_count(Sidemenu[6].return_count()+1);
                      			TablemenuCount[13][tablenum]++;
                      			  Buttongenerator();
                      		  }
                      	  });
                            
                            Drink[0].addActionListener(new ActionListener() {
                      		  public void actionPerformed(ActionEvent e) {
                      			  Drinkmenu[0].set_count(Drinkmenu[0].return_count()+1);
                      			TablemenuCount[14][tablenum]++;
                      			  Buttongenerator();
                      		  }
                      	  });
                    
                            Drink[1].addActionListener(new ActionListener() {
                      		  public void actionPerformed(ActionEvent e) {
                      			  Drinkmenu[1].set_count(Drinkmenu[1].return_count()+1);
                      			TablemenuCount[15][tablenum]++;
                      			  Buttongenerator();
                      		  }
                      	  });
                            Drink[2].addActionListener(new ActionListener() {
                      		  public void actionPerformed(ActionEvent e) {
                      			  Drinkmenu[2].set_count(Drinkmenu[2].return_count()+1);
                      			TablemenuCount[16][tablenum]++;
                      			  Buttongenerator();
                      		  }
                      	  });
                            Drink[3].addActionListener(new ActionListener() {
                      		  public void actionPerformed(ActionEvent e) {
                      			  Drinkmenu[3].set_count(Drinkmenu[3].return_count()+1);
                      			TablemenuCount[17][tablenum]++;
                      			  Buttongenerator();
                      		  }
                      	  });
                            Drink[4].addActionListener(new ActionListener() {
                      		  public void actionPerformed(ActionEvent e) {
                      			  Drinkmenu[4].set_count(Drinkmenu[4].return_count()+1);
                      			TablemenuCount[18][tablenum]++;
                      			  Buttongenerator();
                      		  }
                      	  });
                            Drink[5].addActionListener(new ActionListener() {
                      		  public void actionPerformed(ActionEvent e) {
                      			  Drinkmenu[5].set_count(Drinkmenu[5].return_count()+1);
                      			TablemenuCount[19][tablenum]++;
                      			  Buttongenerator();
                      		  }
                      	  });
                            
                           
                            for (i=0;i<7;i++) {
                          	  Mainmenu[i] = new menu();
                            }
                            
                            for (i=0;i<7;i++) {
                          	  Sidemenu[i] = new menu();
                            }
                            
                            for (i=0;i<6;i++) {
                          	  Drinkmenu[i] = new menu();
                            }
                            
                        
 //메뉴 등록
                            for (i=0;i<7;i++) {
                          	  Mainmenu[i].set_name(String.format("%s", mainMenu_Name[i]));
                          	  Mainmenu[i].set_value(mainMenu_Value[i]);
                          	 
                            }
                            

                            for (i=0;i<7;i++) {
                          	  Sidemenu[i].set_name(String.format("%s", sideMenu_Name[i]));
                          	  Sidemenu[i].set_value(sideMenu_Value[i]);
                            }
                            for (i=0;i<6;i++) {
                          	  Drinkmenu[i].set_name(String.format("%s", Drink_Name[i]));
                          	  Drinkmenu[i].set_value(Drink_Value[i]);

                            }
                            mCalcul(panel1, p_total, 650,50,800,750);
                            add(panel1);
                            setVisible(true);                   
                            
   }
                            
              //메뉴버튼을 누를 때 테이블에 메뉴, 주문수량, 가격 등을 표시하도록 하는 메소드
   				public void Buttongenerator() {
   					p_result.removeAll();
   					int Value = 0;
   					if (tablenum==1)
   						t1.setText("");
   					if (tablenum==2)
   						t2.setText("");
   					if (tablenum==3)
   						t3.setText("");
   					if (tablenum==4)
   						t4.setText("");
   					for (int i=0;i<7;i++) {
   						if (TablemenuCount[i][tablenum]>0) {
   							JButton Button = new JButton(String.format("%s : %d개", Mainmenu[i].return_name(),TablemenuCount[i][tablenum]));
   							p_result.add(Button);
   							Value += TablemenuCount[i][tablenum] * Mainmenu[i].return_value();
   							if (tablenum==1) {
   								t1.setText(t1.getText()+Mainmenu[i].return_name()+" "+TablemenuCount[i][tablenum]+"\n");
   							}
   							if (tablenum==2) {
   								t2.setText(t2.getText()+Mainmenu[i].return_name()+" "+TablemenuCount[i][tablenum]+"\n");
   							}
   							if (tablenum==3) {
   								t3.setText(t3.getText()+Mainmenu[i].return_name()+" "+TablemenuCount[i][tablenum]+"\n");
   							}
   							if (tablenum==4) {
   								t4.setText(t4.getText()+Mainmenu[i].return_name()+" "+TablemenuCount[i][tablenum]+"\n");
   							}
   						}
   					}
   					for (int i=0;i<7;i++) {
   						if (TablemenuCount[i+7][tablenum]>0) {
   							JButton Button = new JButton(String.format("%s : %d개", Sidemenu[i].return_name(),TablemenuCount[i+7][tablenum]));
   							p_result.add(Button);
   							Value += TablemenuCount[i+7][tablenum] * Sidemenu[i].return_value();
   							if (tablenum==1) {
   								t1.setText(t1.getText()+Sidemenu[i].return_name()+" "+TablemenuCount[i+7][tablenum]+"\n");
   							}
   							if (tablenum==2) {
   								t2.setText(t2.getText()+Sidemenu[i].return_name()+" "+TablemenuCount[i+7][tablenum]+"\n");
   							}
   							if (tablenum==3) {
   								t3.setText(t3.getText()+Sidemenu[i].return_name()+" "+TablemenuCount[i+7][tablenum]+"\n");
   							}
   							if (tablenum==4) {
   								t4.setText(t4.getText()+Sidemenu[i].return_name()+" "+TablemenuCount[i+7][tablenum]+"\n");
   							}
   						}
   					}
   					for (int i=0;i<6;i++) {
   						if (TablemenuCount[i+14][tablenum]>0) {
   							JButton Button = new JButton(String.format("%s : %d개", Drinkmenu[i].return_name(),TablemenuCount[i+14][tablenum]));
   							p_result.add(Button);
   							Value += TablemenuCount[i][tablenum] * Drinkmenu[i].return_value();
   							if (tablenum==1) {
   								t1.setText(t1.getText()+Drinkmenu[i].return_name()+" "+TablemenuCount[i+14][tablenum]+"\n");
   							}
   							if (tablenum==2) {
   								t2.setText(t2.getText()+Drinkmenu[i].return_name()+" "+TablemenuCount[i+14][tablenum]+"\n");
   							}
   							if (tablenum==3) {
   								t3.setText(t3.getText()+Drinkmenu[i].return_name()+" "+TablemenuCount[i+14][tablenum]+"\n");
   							}
   							if (tablenum==4) {
   								t4.setText(t4.getText()+Drinkmenu[i].return_name()+" "+TablemenuCount[i+14][tablenum]+"\n");
   							}
   						}
   					}
   					JButton Button = new JButton(String.format("합계 : %d원", Value));
   					if (tablenum==1) {
							t1.setText(t1.getText()+"합계 : "+Value);
						}
						if (tablenum==2) {
							t2.setText(t2.getText()+"합계 : "+Value);
							}
						if (tablenum==3) {
							t3.setText(t3.getText()+"합계 : "+Value);
							}
						if (tablenum==4) {
							t4.setText(t4.getText()+"합계 : "+Value);
							}
   					p_result.add(Button);
   					p_result.validate();
   					t1.validate();
   					t2.validate();
   					t3.validate();
   					t4.validate();
   				}
   				
   				
   				//각 메뉴마다 총 주문수량을 파일에 출력하기 위해 문자열로 반환하는 메소드
   				public String OutputString() {
   					String m = String.format("");
   					m = String.format("%d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d",
   							TablemenuCount1[0],
   							TablemenuCount1[1],
   							TablemenuCount1[2],
   							TablemenuCount1[3],
   							TablemenuCount1[4],
   							TablemenuCount1[5],
   							TablemenuCount1[6],
   							TablemenuCount1[7],
   							TablemenuCount1[8],
   							TablemenuCount1[9],
   							TablemenuCount1[10],
   							TablemenuCount1[11],
   							TablemenuCount1[12],
   							TablemenuCount1[13],
   							TablemenuCount1[14],
   							TablemenuCount1[15],
   							TablemenuCount1[16],
   							TablemenuCount1[17],
   							TablemenuCount1[18],
   							TablemenuCount1[19]);
   									
   							return m;
   				}
  
   //테이블버튼 아래의 textArea 만드는 메소드
   private void mTextArea(JPanel panel,JScrollPane a,int x,int y,int z,int w){
      a.setBounds(x,y,z,w);
      panel.add(a);
      a.setEnabled(false);

   }
   
   //테이블1 버튼 생성하는 메소드
   private void mButton1(JPanel panel,String text,int x,int y,int z,int w){
      JButton button =new JButton(text);
      button.setBounds(x, y, z, w);
      button.setBackground(Color.LIGHT_GRAY);
      button.setFont(new Font("DialogInput",Font.BOLD,20));
      panel.add(button);
      button.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  tablenum=1;
			  Buttongenerator();
		  }
	  });
   }
 //테이블2 버튼 생성하는 메소드
   private void mButton2(JPanel panel,String text,int x,int y,int z,int w){
	      JButton button =new JButton(text);
	      button.setBounds(x, y, z, w);
	      button.setBackground(Color.LIGHT_GRAY);
	      button.setFont(new Font("DialogInput",Font.BOLD,20));
	      panel.add(button);
	      button.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  tablenum=2;
				  Buttongenerator();
			  }
		  });
	   }
   
 //테이블3 버튼 생성하는 메소드
   private void mButton3(JPanel panel,String text,int x,int y,int z,int w){
	      JButton button =new JButton(text);
	      button.setBackground(Color.LIGHT_GRAY);
	      button.setFont(new Font("DialogInput",Font.BOLD,20));
	      button.setBounds(x, y, z, w);
	      panel.add(button);
	      button.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  tablenum=3;
				  Buttongenerator();
			  }
		  });
	   }
   
 //테이블4 버튼 생성하는 메소드
   private void mButton4(JPanel panel,String text,int x,int y,int z,int w){
	      JButton button =new JButton(text);
	      button.setBounds(x, y, z, w);
	      button.setFont(new Font("DialogInput",Font.BOLD,20));
	      button.setBackground(Color.LIGHT_GRAY);
	      panel.add(button);
	      button.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  tablenum=4;
				  Buttongenerator();
			  }
		  });
	   }
   
   
   //패널을 패널에 등록하는 메소드
   private void mCalcul(JPanel panel, JPanel addP, int x, int y, int z, int w) {
	   addP.setBounds(x, y, z, w);
	   panel.add(addP);
   }
}


//메인함수
public class First{
	
   public static void main(String[] args){
	   MyFrame f= new MyFrame();     
   }
}