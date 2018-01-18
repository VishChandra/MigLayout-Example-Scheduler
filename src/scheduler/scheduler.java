package scheduler;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import net.miginfocom.swing.MigLayout;

public class scheduler extends JFrame implements ActionListener{
	 
	
	private JRadioButton restartRadioButton;
	private JLabel timeLabel;
	private JTextField timeTextField;
	private ButtonGroup userType;
	private JRadioButton shutdownRadioButton, 
            logoffRadioButton,
            lockRadioButton;
    public JButton scheduleButton,
    		cancelButton;
	
	private scheduler()
	{
		super();
		initialize();
		
		setLayout(new MigLayout("fillx, align center center"));
		
		add1();
		setVisible(true);
        setSize(250, 155);
        setTitle("Scheduler");
        setResizable(true);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
		
	}
	
	public void initialize()
	{
		timeLabel = new JLabel("Minutes:");
		timeTextField = new JTextField("0");
		timeTextField.setPreferredSize(new Dimension(35,20));
		shutdownRadioButton = new JRadioButton("Shutdown");
		restartRadioButton = new JRadioButton("Restart");
		logoffRadioButton = new JRadioButton("Log Off");
		lockRadioButton = new JRadioButton("Lock");
		logoffRadioButton.setSelected(true);
		
		userType = new ButtonGroup();
        userType.add(shutdownRadioButton);
        userType.add(restartRadioButton);
        userType.add(logoffRadioButton);
        userType.add(lockRadioButton);
        
        
        scheduleButton = new JButton("Schedule");
        scheduleButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        scheduleButton.setActionCommand("1");
        cancelButton.setActionCommand("2");
		
		
	}
	public void add1()
	{
		 /*
        wrap - go to next line after this
        grow - expand to fill available space
        split 2 - ensures 2 components (this one and next) are in the same cell
        span 2 - makes the cell span 2 cell spaces horizontally
        align center - centers the component
        gapy 2 - sets vertical gaps (before and after) of component to 2
        */
		
		add(logoffRadioButton, "split 3, span 3, gapy 2");
		add(lockRadioButton, "wrap");
        add(timeLabel);
        add(timeTextField, "wrap, ");
        add(shutdownRadioButton, "split 3, span 3,  gapy 2");
        add(restartRadioButton, "wrap,split 2");
        add(scheduleButton, "span 2, align center, gapy 1");
        add(cancelButton, "span 2, align center, gapy 1");
	}
	
	public static void main(String[] args)
    {
		scheduler t = new scheduler();
	
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		int action = Integer.parseInt(e.getActionCommand());
		int time = Integer.parseInt(timeTextField.getText());
	 	time = time * 60;
		switch(action) {
		 case 1:
			
	            if(shutdownRadioButton.isSelected())
	            {
	            	
	            	try {
	        			Process p = Runtime.getRuntime().exec("shutdown.exe -s -t " + time);
	        			
	        		} catch (IOException e1){
	        			e1.printStackTrace();
	        		}
	            	JOptionPane.showMessageDialog(null, "Success! You can close the application");
	            	
	            }
	            else if(restartRadioButton.isSelected())
	            {
	            	
	            	try {
	        			Process p = Runtime.getRuntime().exec("shutdown.exe -r -t " + time);
	        		} catch (IOException e1){
	        			e1.printStackTrace();
	        		}
	            	JOptionPane.showMessageDialog(null, "Success! You can close the application");
	            	
	            }
	            else if(logoffRadioButton.isSelected())
	            {
	            	
	            	JOptionPane.showMessageDialog(null, "Do not close the application! You cannot cancel timed Log Off");
	            	try {
	        			//Process p = Runtime.getRuntime().exec("shutdown.exe -l");
	            		try {
							TimeUnit.SECONDS.sleep(time);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	            		Runtime.
	        			   getRuntime().
	        			   exec("cmd /c start \"\" R:\\Scheduler\\src\\scheduler\\logoff.bat");
	        		} catch (IOException e1){
	        			e1.printStackTrace();
	        		}
	            	
	            }
		         else
		            {
		        	 JOptionPane.showMessageDialog(null, "Do not close the application! You cannot cancel timed Lock");
		            	try {
		        			//Process p = Runtime.getRuntime().exec("rundll32.exe user32.dll, LockWorkStation");
		            		try {
								TimeUnit.SECONDS.sleep(time);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		        			Runtime.
		        			   getRuntime().
		        			   exec("cmd /c start \"\" R:\\Scheduler\\src\\scheduler\\lock.bat");
		        		} catch (IOException e1){
		        			e1.printStackTrace();
		        		}
		            }
			         break;
		 case 2:
			 JOptionPane.showMessageDialog(null, "Your process has been cancelled!");
			 try {
     			Process p = Runtime.getRuntime().exec("shutdown.exe -a");
     		} catch (IOException e1){
     			e1.printStackTrace();
     		}
		         break;
		 }
		
		 		
	            
	         
	      
		
	}



}
