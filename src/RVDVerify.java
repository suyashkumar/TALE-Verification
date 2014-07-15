import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import TalVerification.TalRVDVerification;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JCheckBox;
/*
 * @author Suyash Kumar
 * http://suyashkumar.com
 * TAL RVD Verification
 * 
 * This class sets up the user interface and provides a front-end to the verification functions 
 * found in TalRVDVerification.java .
 * 
 * Released under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License
 * http://creativecommons.org/licenses/by-nc-sa/4.0/deed.en_US
 */

public class RVDVerify {

	private JFrame frmTalDnaTo;
	private JTextField textField;
	private JTextPane textPane;
	TalRVDVerification verificationModule;
	private JLabel lblcSuyashKumar;
	private JLabel lblJustPasteYour;
	private JCheckBox chckbxRc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RVDVerify window = new RVDVerify();
					window.frmTalDnaTo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RVDVerify() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// Init DNA to RVD components
		
	    verificationModule=new TalRVDVerification();
		
		
		frmTalDnaTo = new JFrame("DNA to RVD");
		frmTalDnaTo.setTitle("Tal DNA to RVD");
		frmTalDnaTo.setBounds(100, 100, 450, 300);
		frmTalDnaTo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		frmTalDnaTo.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblDnaToRvd = new JLabel("DNA To RVD");
		lblDnaToRvd.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
		GridBagConstraints gbc_lblDnaToRvd = new GridBagConstraints();
		gbc_lblDnaToRvd.insets = new Insets(0, 0, 5, 5);
		gbc_lblDnaToRvd.gridx = 7;
		gbc_lblDnaToRvd.gridy = 0;
		frmTalDnaTo.getContentPane().add(lblDnaToRvd, gbc_lblDnaToRvd);
		
		lblJustPasteYour = new JLabel("Just paste your DNA sequence below, and hit Go!");
		lblJustPasteYour.setBackground(Color.GRAY);
		lblJustPasteYour.setForeground(Color.DARK_GRAY);
		GridBagConstraints gbc_lblJustPasteYour = new GridBagConstraints();
		gbc_lblJustPasteYour.insets = new Insets(0, 0, 5, 5);
		gbc_lblJustPasteYour.gridx = 7;
		gbc_lblJustPasteYour.gridy = 1;
		gbc_lblJustPasteYour.ipady=1;
		frmTalDnaTo.getContentPane().add(lblJustPasteYour, gbc_lblJustPasteYour);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 7;
		gbc_textField.gridy = 2;
		frmTalDnaTo.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnGo = new JButton("Go!");
		GridBagConstraints gbc_btnGo = new GridBagConstraints();
		gbc_btnGo.insets = new Insets(0, 0, 5, 5);
		gbc_btnGo.gridx = 7;
		gbc_btnGo.gridy = 3;
		frmTalDnaTo.getContentPane().add(btnGo, gbc_btnGo);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.gridwidth = 4;
		gbc_textPane.insets = new Insets(0, 0, 5, 5);
		gbc_textPane.gridx = 6;
		gbc_textPane.gridy = 4;
		textPane.setSize(new Dimension(10,10));
		frmTalDnaTo.getContentPane().add(textPane, gbc_textPane);
		
		chckbxRc = new JCheckBox("Reverse Complement");
		GridBagConstraints gbc_chckbxRc = new GridBagConstraints();
		gbc_chckbxRc.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxRc.gridx = 7;
		gbc_chckbxRc.gridy = 5;
		frmTalDnaTo.getContentPane().add(chckbxRc, gbc_chckbxRc);
		
		lblcSuyashKumar = new JLabel("(c) Suyash Kumar");
		lblcSuyashKumar.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		GridBagConstraints gbc_lblcSuyashKumar = new GridBagConstraints();
		gbc_lblcSuyashKumar.insets = new Insets(0, 0, 0, 5);
		gbc_lblcSuyashKumar.gridx = 7;
		gbc_lblcSuyashKumar.gridy = 6;
		frmTalDnaTo.getContentPane().add(lblcSuyashKumar, gbc_lblcSuyashKumar);
		
	
		//Action Listener
		
		    

		//Mouse Listener
		 MouseListener mouseListener = new MouseAdapter() {
		   
		      public void mouseReleased(MouseEvent mouseEvent) {
		        if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
		          textPane.setText("");
		         
		          if(chckbxRc.isSelected()){
		           textPane.setText("RVD Sequence:\n"+verificationModule.resolveDNA(textField.getText(),true));
		          }
		          else{
		           textPane.setText("RVD Sequence:\n"+verificationModule.resolveDNA(textField.getText(),false));
		          }
		        
		        }
		      }
		    };
		    
		    btnGo.addMouseListener(mouseListener);
		    	
		    	
		    };
		    //Add Listeners: 
	public void append(String s){
		textPane.setText(textPane.getText()+s);
	}
	
	}


