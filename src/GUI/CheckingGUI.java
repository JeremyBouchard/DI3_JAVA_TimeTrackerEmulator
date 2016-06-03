package GUI;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("serial")
public class CheckingGUI extends JFrame {
	public CheckingGUI() {
	}

	private JPanel panel = new JPanel(new GridBagLayout());
	List<JLabel> list = new ArrayList<JLabel>();
	/*list(0) -> RealTimeLabel
	 * list(1) ->RoundedTimeLabel
	 * list(2) ->DateLabel
	 */
	Container contenu = getContentPane() ;
	

	public boolean make(){
		
		setWindows();
		
		dateInfoLabel();
		realTimeInfoLabel();
		dateLabel();
		realTimeLabel();
		checkingTimeInfoLabel();
		checkingTimeLabel();
		synchButton();
		employeeListComboBox();
		submitButton();

		ClockTimer();
		return true;
	}

	private void ClockTimer(){

		ActionListener clockUpdate = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				timeUpdatorAction();
			}
		};

		Timer timer = new Timer(100, clockUpdate);
		timer.setInitialDelay(0);
		timer.start();

	}
	

	private void timeUpdatorAction(){
		SimpleDateFormat realTimeFormat=new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat roundedTimeFormat=new SimpleDateFormat("HH:mm");
		SimpleDateFormat dateFormat=new SimpleDateFormat("EEE, d MMM yyyy", Locale.ENGLISH);
		Date realTime=new Date();
		Date roundedTime = new Date(900000 * ((realTime.getTime() + 450000) / 900000));

		list.get(1).setText(realTimeFormat.format(realTime));
		list.get(2).setText(roundedTimeFormat.format(roundedTime));
		list.get(0).setText(dateFormat.format(realTime));
	}

	private void setWindows(){

		setVisible(true);
		setContentPane(panel);
		setTitle("Time Tracker Emulator 1.0");
		setSize(300 ,200);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//TODO : Serialisation
		setLocationRelativeTo(null);

	}
	
	private void dateInfoLabel(){
		JLabel date= new JLabel("Today we are the :");
		GridBagConstraints constrains = new GridBagConstraints();

		constrains.gridx = 0;
		constrains.gridy = 0;
		constrains.anchor=GridBagConstraints.WEST;
		panel.add(date, constrains);

	}
	
	private void dateLabel(){
		JLabel date= new JLabel();
		GridBagConstraints constrains = new GridBagConstraints();

		constrains.gridx = 0;
		constrains.gridy = 1;
		constrains.anchor=GridBagConstraints.CENTER;
		panel.add(date, constrains);

		list.add(date);//list[0]
		
	}

	private void realTimeInfoLabel(){
		JLabel realTime= new JLabel("It's currently :");
		GridBagConstraints constrains = new GridBagConstraints();

		constrains.gridx = 1;
		constrains.gridy = 0;
		constrains.anchor=GridBagConstraints.EAST;
		panel.add(realTime, constrains);

	}
	
	private void realTimeLabel(){
		JLabel realTime= new JLabel();
		GridBagConstraints constrains = new GridBagConstraints();

		constrains.gridx = 1;
		constrains.gridy = 1;
		constrains.anchor=GridBagConstraints.EAST;
		panel.add(realTime, constrains);

		list.add(realTime);//list[1]

	}

	
	private void checkingTimeInfoLabel(){
		JLabel checkingTime= new JLabel("Rounded time for checking");
		GridBagConstraints constrains = new GridBagConstraints();

		constrains.gridx = 0;
		constrains.gridy = 2;
		constrains.anchor=GridBagConstraints.WEST;
		panel.add(checkingTime, constrains);

	}
	
	private void checkingTimeLabel(){
		JLabel checkingTime= new JLabel();
		GridBagConstraints constrains = new GridBagConstraints();

		constrains.gridx = 0;
		constrains.gridy = 3;
		constrains.gridwidth=2;
		constrains.anchor=GridBagConstraints.CENTER;
		panel.add(checkingTime, constrains);

		checkingTime.setForeground(Color.red);

		list.add(checkingTime);//list[2]

	}

	private void synchButton(){

		JButton synchButton = new JButton("Synchronize Database");
		GridBagConstraints constrains = new GridBagConstraints();

		synchButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				synchButtonAction();
			}
		});

		constrains.gridx = 0;
		constrains.gridy = 4;
		constrains.anchor=GridBagConstraints.WEST;
		//constrains.insets=new Insets(0,0,0,150);
		panel.add(synchButton, constrains);

	}
	
	private void employeeListComboBox(){

		JComboBox<String> employeeList = new JComboBox<String>();
		GridBagConstraints constrains = new GridBagConstraints();

		employeeList.addItem(null);
		employeeList.addItem("BETA TESTEUR");//TODO : Manage Importation from main apps
		employeeList.addItem("BETA TESTEUR 2");

		constrains.gridx = 0;
		constrains.gridy = 5;
		constrains.anchor=GridBagConstraints.WEST;
		constrains.fill =GridBagConstraints.BOTH;
		
		panel.add(employeeList, constrains);

		employeeList.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent event){
				employeeListComboBoxAction(event);
			}
		});

	}
	
	private void submitButton(){

		JButton submitButton = new JButton("Submit");
		GridBagConstraints constrains = new GridBagConstraints();

		submitButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				submitButtonAction();
			}
		});

		// constrains.fill = GridBagConstraints.HORIZONTAL;
		constrains.gridx = 1;
		constrains.gridy = 5;
		constrains.anchor=GridBagConstraints.EAST;
		
		panel.add(submitButton, constrains);

	}

	private void submitButtonAction(){
		System.out.println("Submited !");
	}

	private void synchButtonAction(){
		System.out.println("Synchronized !");
	}

	private void employeeListComboBoxAction(ItemEvent event){
		if (event.getStateChange() == ItemEvent.SELECTED){
			System.out.println("Selection de" + event.getItem());
		}
	}

}