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
	String menuname[] = {"���θ޴�1","���θ޴�2","���θ޴�3","���θ޴�4","���θ޴�5","���θ޴�6","���θ޴�7","���̵�޴�1","���̵�޴�2","���̵�޴�3","���̵�޴�4","���̵�޴�5","���̵�޴�6","���̵�޴�7","����1","����2","����3","����4","����5","����6"};
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
