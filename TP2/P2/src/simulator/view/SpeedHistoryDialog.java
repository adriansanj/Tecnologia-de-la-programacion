package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;
import simulator.model.RoadMap;
import simulator.model.Vehicle;

public class SpeedHistoryDialog extends JDialog {
	 Controller _ctrl;

	
	private int speedLimit = 1;
	
	public SpeedHistoryDialog(Frame controlPanel, Controller c) {
		super(controlPanel, true);	
		_ctrl = c;
		initGUI();
	}
	private void initGUI() {
		
		setTitle("Vehicles Speed History");
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		
		JLabel mensajeAyuda1 = new JLabel("Select a speed limit and press UPDATE to show the vehicles that exceeded this speed in each tick.");
		mensajeAyuda1.setAlignmentX(CENTER_ALIGNMENT);
		panelPrincipal.add(mensajeAyuda1);
		
		panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
		
		//Panel spinner
		JPanel panelSpinner= new JPanel();
		panelSpinner.setAlignmentX(CENTER_ALIGNMENT);
		panelPrincipal.add(panelSpinner);
		//texto spinner
		panelSpinner.add(new JLabel("Speed Limit:  "));
		panelPrincipal.add(Box.createRigidArea(new Dimension(5, 20)));
		//spinner
		SpinnerModel sm = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
		JSpinner speedSpinner = new JSpinner(sm);
		speedSpinner.setPreferredSize(new Dimension(40, 40));
		panelSpinner.add(speedSpinner);
		panelSpinner.add(Box.createRigidArea(new Dimension(0, 20)));
		
		panelPrincipal.add(panelSpinner);
		
		// PANEL DE BOTONES
		JPanel panelBotones = new JPanel();
		panelBotones.setAlignmentX(CENTER_ALIGNMENT);
		panelPrincipal.add(panelBotones);
		
		//BOTON CLOSE
		JButton close = new JButton("Close");
		close.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
				SpeedHistoryDialog.this.setVisible(false);
			}
		});
		panelBotones.add(close);
		
	
		
		
		
		//BOTON UPDATE
		JButton update = new JButton("Update");
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
						
				if (speedSpinner.getValue() != null) {
					speedLimit = (int) speedSpinner.getValue();
					
					JPanel vehiclesSpeed =createViewPanel(new JTable(new SpeedLimitTableModel(_ctrl,speedLimit)), "Vehicles Speed Hitory");
					vehiclesSpeed.setPreferredSize(new Dimension(500, 200));
					panelPrincipal.add(vehiclesSpeed);
					panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
	
					vehiclesSpeed.setVisible(true);
					
					
				}
			}
		});
		panelBotones.add(update);
		
		
		
		this.add(panelPrincipal);
		setPreferredSize(new Dimension(500, 200));
		pack();
		setResizable(false);
		setVisible(false);
		
	}
	private JPanel createViewPanel(JComponent c, String title) {
		Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 1);
		JPanel p = new JPanel( new BorderLayout() );
		p.setBorder(BorderFactory.createTitledBorder(_defaultBorder,
				title, TitledBorder.LEFT, TitledBorder.TOP));
		p.add(new JScrollPane(c));
		return p;
		
	}
	public void open(RoadMap map) {
		
		setLocation(getParent().getLocation().x + 250, getParent().getLocation().y + 250);
		setVisible(true); 				
	}
}
