package Avero_Test2;

import java.io.File;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;

//adding checkpoint 1 
//adding checkpoint 2
public class Avero_Class2 {
	  public static void main(String[] args) {
		  Schema schema = null;
		    System.out.println("Start Class");		  
		try {
			schema = new Schema.Parser().parse(new File("c:/temp/user.avsc"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  GenericRecord user1 = new GenericData.Record(schema);
		  user1.put("name", "Alyssa");
		  user1.put("favorite_number", 256);
		  // Leave favorite color null

		  GenericRecord user2 = new GenericData.Record(schema);
		  user2.put("name", "Ben");
		  user2.put("favorite_number", 7);
		  user2.put("favorite_color", "red");  
		  
		  File file = new File("c:/temp/users.avro");
		  DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
		  DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
		  try {
			dataFileWriter.create(schema, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			dataFileWriter.append(user1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			dataFileWriter.append(user2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			dataFileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		  
		  
		  DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
		  DataFileReader<GenericRecord> dataFileReader = null;
		try {
			dataFileReader = new DataFileReader<GenericRecord>(file, datumReader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  GenericRecord user = null;
		  while (dataFileReader.hasNext()) {
		  // Reuse user object by passing it to next(). This saves us from
		  // allocating and garbage collecting many objects for files with
		  // many items.
		  try {
			user = dataFileReader.next(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  System.out.println(user);
	    System.out.println("End Class");
		  }
	  }
}
