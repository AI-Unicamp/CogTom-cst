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
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;

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
			Table entities = Table.read().csv("input/entities.csv");

			// Loop through each one of the rows of the table.
			for (int i = 0; i < entities.rowCount(); i++) {
				Row r = entities.row(i);
				String name = r.getString("Entity");
				Boolean isAgent = r.getBoolean("Is_Agent");
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}// end proc()

	@Override
	public void calculateActivation() {

	}

}// class end
