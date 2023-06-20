package com.frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;

public class MainFrame {

	private JFrame frmStopWatchApp;
	private JTextField alertTxt;
	private Timer timer;
	private Timer alert; 
	private int alertMinute = -1;
	long startTime = 0;
	long elapsedTime = 0;
	long elapsedSeconds = 0;
	long secondsDisplay = 0;
	long elapsedMinutes = 0;
	long elapsedHours = 0;
	private JTextField messageTxt;

	DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmStopWatchApp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStopWatchApp = new JFrame();
		frmStopWatchApp.setTitle("Stop Watch App");
		frmStopWatchApp.setBounds(100, 100, 457, 374);
		frmStopWatchApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStopWatchApp.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.GRAY, null, null, null));
		panel.setBounds(10, 0, 421, 115);
		frmStopWatchApp.getContentPane().add(panel);
		panel.setLayout(null);

		
		JLabel timerLbl = new JLabel("00:00:00");
		timerLbl.setBounds(10, 11, 437, 92);
		panel.add(timerLbl);
		timerLbl.setForeground(Color.RED);
		timerLbl.setFont(new Font("Tahoma", Font.PLAIN, 99));
		
		JButton startBtn = new JButton("START");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (timer != null) timer.stop();
				startTime = System.currentTimeMillis();
				timer = new Timer(1000, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						elapsedTime = System.currentTimeMillis() - startTime;
						elapsedSeconds = elapsedTime / 1000;
						secondsDisplay = elapsedSeconds % 60;
						elapsedMinutes = elapsedSeconds / 60;
						elapsedHours = elapsedMinutes / 60;
						LocalTime lT = LocalTime.of((int)elapsedHours, (int)elapsedMinutes, (int)secondsDisplay);
						timerLbl.setText(lT.format(timeFormatter)); 
					}
				});
				timer.start();
			}
		});


		startBtn.setBounds(10, 126, 130, 47);
		frmStopWatchApp.getContentPane().add(startBtn);

		JButton stopBtn = new JButton("STOP");
		stopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (alert != null) alert.stop();
				if (timer != null) timer.stop();
			}
		});
		stopBtn.setBounds(155, 126, 130, 47);
		frmStopWatchApp.getContentPane().add(stopBtn);

		JLabel alertLbl = new JLabel("ALERT INTERVAL");
		alertLbl.setBounds(20, 197, 120, 30);
		frmStopWatchApp.getContentPane().add(alertLbl);

		alertTxt = new JTextField();
		alertTxt.setBounds(123, 199, 72, 27);
		frmStopWatchApp.getContentPane().add(alertTxt);
		alertTxt.setColumns(10);

		JButton addAlertMinuteBtn = new JButton("ADD");
		addAlertMinuteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (alertTxt.getText() != null) {
					alertMinute = Integer.parseInt(alertTxt.getText());
				}
				
				String message = messageTxt.getText();
		
				alert = new Timer(1000, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (elapsedSeconds != 0 && alertMinute != 0 && elapsedSeconds % (alertMinute * 60) == 0) {
							if (message != null) JOptionPane.showMessageDialog(null, message);
							else JOptionPane.showMessageDialog(null, "Alert !!!");
							
						}
					}
				});
				alert.start();
			}
		});
		addAlertMinuteBtn.setBounds(123, 287, 73, 27);
		frmStopWatchApp.getContentPane().add(addAlertMinuteBtn);

		JButton btnResume = new JButton("RESUME");
		btnResume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (timer != null) timer.stop();
				startTime = System.currentTimeMillis() - elapsedSeconds*1000;
				timer = new Timer(1000, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						elapsedTime = System.currentTimeMillis() - startTime;
						elapsedSeconds = elapsedTime / 1000;
						secondsDisplay = elapsedSeconds % 60;
						elapsedMinutes = elapsedSeconds / 60;
						elapsedHours = elapsedMinutes / 60;
						LocalTime lT = LocalTime.of((int)elapsedHours, (int)elapsedMinutes, (int)secondsDisplay);
						timerLbl.setText(lT.toString()); 
					}
				});
				timer.start();
			}
		});
		btnResume.setBounds(301, 126, 130, 47);
		frmStopWatchApp.getContentPane().add(btnResume);
		
		JLabel lblNewLabel = new JLabel("MINUTE(S)");
		lblNewLabel.setBounds(205, 197, 69, 30);
		frmStopWatchApp.getContentPane().add(lblNewLabel);
		
		JLabel alertMessageLbl = new JLabel("MESSAGE");
		alertMessageLbl.setBounds(20, 236, 72, 30);
		frmStopWatchApp.getContentPane().add(alertMessageLbl);
		
		messageTxt = new JTextField();
		messageTxt.setBounds(123, 235, 286, 33);
		frmStopWatchApp.getContentPane().add(messageTxt);
		messageTxt.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 184, 421, 148);
		frmStopWatchApp.getContentPane().add(panel_1);
		
		
	}
}
