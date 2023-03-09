package E_commerceTest.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader 
{
	public List<HashMap<String, String>> jsonDataToMap() throws IOException
	{
		// read json to String
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+
				"\\src\\test\\java\\E_commerceTest\\data\\PurchaseOrder.json"), StandardCharsets.UTF_8);
		
		// Convert String to HashMap
		ObjectMapper mapper = new ObjectMapper();		// readValue - read json & convert to list of hashMaps
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, 
												new TypeReference<List<HashMap<String, String>>>() {});
		return data;
		// {map , map1}  // like {0 ,1} indexes , returns as Lists of HashMaps
	}

}
