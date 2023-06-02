package simulator.model;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class LessContaminationStrategy implements DequeuingStrategy{
	private int limit;
	public LessContaminationStrategy(int l) {
		limit = l;
	}	
	@Override
	public List<Vehicle> dequeuing(List<Vehicle> l) {
		List<Vehicle> aux=new LinkedList<Vehicle>();
		List<Vehicle> dev=new LinkedList<Vehicle>();
		for(Vehicle v : l) {
			aux.add(v);
		}
		aux.sort(new Comparator<Vehicle>() {
		@Override
			public int compare(Vehicle v1, Vehicle v2) {
				int d;
				if( v1.getContClass()<v2.getContClass()) {
					d= -1;
				}else if (v1.getContClass()==v2.getContClass()){
					d=0;
				}else{
					d = 1;
				}
				return d;
		}
		});
		if(aux.size()>this.limit) {
			for(int i = 0; i<this.limit;i++) {
				dev.add(aux.get(i));
			}
		}else {
			for(int i = 0; i<aux.size();i++) {
				dev.add(aux.get(i));
			}

		}
		return dev;
	}
	
	
	
}

