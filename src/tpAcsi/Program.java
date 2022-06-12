package tpAcsi;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.sql.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import databaseManager.DBManager;
import models.Medicament;
import ui.*;

public class Program {
	
	public static void main(String[] args) throws Exception {
	
			
		EventQueue.invokeLater(new Runnable() {
			public void run() {

		        try  
		        {  
	        	
		        	// Create the app frame
		    		Application.AppFrame = new MainFrame();  
		    		
		    		// Go to the sign in page
		    		Application.GotoPage(new LoginPagePanel());
                    
		    		// Set frame size
		    		Application.AppFrame.setSize(1366,768);  
					
		    		// Set close operation
		    		Application.AppFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
		    		// Set visibility to true
		    		Application.AppFrame.setVisible(true);  

		        }  
		        catch(Exception e)  
		        {     
		            //handle exception   
		            JOptionPane.showMessageDialog(null, e.getMessage());  
		        }  
		    
			}
		});
	
	}

}
