package codelets.sensors;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryObject;
import java.util.List;

/**
 * Vision codelet is responsible for getting vision information from the
 * environment. It returns all objects in the visual field an its properties.
 * 
 * @author fabiogr
 */
// TODO How about implementing getvs 0 in Client?
public class Vision extends Codelet {

	private Memory visionMO;

	public Vision() {

	}

	@Override
	public void accessMemoryObjects() {
		visionMO = (MemoryObject) this.getOutput("VISION");
	}

	@Override
	public void proc() {

	}// end proc()

	@Override
	public void calculateActivation() {

	}

}// class end
