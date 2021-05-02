package codelets.sensors;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryObject;
import tech.tablesaw.*;
import tech.tablesaw.api.Table;

import java.io.IOException;
import java.util.List;

/**
 * Vision codelet is responsible for getting vision information from the
 * environment. It returns all objects in the visual field an its properties.
 * 
 * @author fabiogr
 */
public class Vision extends Codelet {

	private Memory entitiesMO;

	public Vision() {

	}

	@Override
	public void accessMemoryObjects() {
		entitiesMO = (MemoryObject) this.getOutput("ENTITIES");
	}

	@Override
	public void proc() {

		// Read in the table from file entities.txt using TableSaw.
		try {
			Table t = Table.read().file("input\\entities");
			t.copy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// end proc()

	@Override
	public void calculateActivation() {

	}

}// class end
