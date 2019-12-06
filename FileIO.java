package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileIO {

	File f = new File("user.txt");
	BufferedReader bufferedReader;
	FileInputStream fileInputStream;
	ArrayList<stockItem> itemArrayList = new ArrayList<>();
	stockItem stockitem; 
	String menuname[] = {"메인메뉴1","메인메뉴2","메인메뉴3","메인메뉴4","메인메뉴5","메인메뉴6","메인메뉴7","사이드메뉴1","사이드메뉴2","사이드메뉴3","사이드메뉴4","사이드메뉴5","사이드메뉴6","사이드메뉴7","음료1","음료2","음료3","음료4","음료5","음료6"};
	public void fileRead() throws Exception {
		
		if (f.exists()) {
			if (f.canRead()) {
				bufferedReader = new BufferedReader(new FileReader(f));
				String sCurrentLine;
				String temp=" ";
				while ((sCurrentLine = bufferedReader.readLine()) != null) {
					
                    String[] storeString = sCurrentLine.split(temp);
                    for(int i=0;i<storeString.length;i++) {
                    	stockItem s = new stockItem(menuname[i],Integer.parseInt(storeString[i]));
                    	itemArrayList.add(s);
                    }
				}
		
				

			} else {
			}
		}
	}
	
	public ArrayList<stockItem> getItemList(){
		return itemArrayList;
	}
	
}
