package codelets.sensors;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryObject;

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

		try {
			CSVReader reader = new CSVReader(new FileReader("input/entities.csv"));
			List<String[]> myEntries = reader.readAll();
			myEntries.isEmpty();
		} catch (IOException | CsvException e) {
			// TODO Auto-generated catch block e.printStackTrace();
		}

	}// end proc()

	@Override
	public void calculateActivation() {

	}

}// class end
