package simulator.view;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;
import simulator.model.Vehicle;

public class SpeedLimitTableModel extends AbstractTableModel implements TrafficSimObserver {
	List<List<Vehicle>> listaVehiculos;
	private int t;
	private int speedLimit;
	private Controller _ctrl;
	private String[] nombreColumnas = { "Time", "Vehicles"};
	
	public SpeedLimitTableModel(Controller _ctrl, int sl) {
		this._ctrl=_ctrl;
		speedLimit = sl;
		initGUI();
	}
	private void initGUI() {
		_ctrl.addObserver(this);
	}

	@Override
	public int getRowCount() {
		
		return t;
	}

	@Override
	public int getColumnCount() {
		
		return nombreColumnas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object s = null;
		if(columnIndex == 0) {
			s=rowIndex;
		}else if(columnIndex == 1) {
			List<Vehicle> dev=new LinkedList<Vehicle>();
			for(Vehicle v: listaVehiculos.get(rowIndex-1)) {
				if (v.getSpeed()>speedLimit) {
					dev.add(v);
				}
			}
			s=dev;
		}
		return s;
	}

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		listaVehiculos.add(map.getVehicles());
		t = time;
		fireTableDataChanged();
		
		
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		/*listaVehiculos.add(map.getVehicles());
		t = time;
		fireTableDataChanged();*/
		
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
	
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		
		
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
	
		
	}

	@Override
	public void onError(String err) {
		// TODO Auto-generated method stub
		
	}

}
