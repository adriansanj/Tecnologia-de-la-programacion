package simulator.factories;

import org.json.JSONObject;

import simulator.model.DequeuingStrategy;
import simulator.model.LessContaminationStrategy;
import simulator.model.RoundRobinStrategy;

public class LessContaminationStrategyBuilder extends Builder<DequeuingStrategy>{

	public LessContaminationStrategyBuilder() {
		super("less_cont_dqs");
		
	}
	@Override
	protected DequeuingStrategy createTheInstance(JSONObject data) {
		if(data.has("limit")){
			return new LessContaminationStrategy(data.getInt("limit"));
		}else{
			//Si no hay limit ponemos limit a 1;
			return new LessContaminationStrategy(1);
		}
		
	}

}
