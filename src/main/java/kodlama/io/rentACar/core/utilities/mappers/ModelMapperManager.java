package kodlama.io.rentACar.core.utilities.mappers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class ModelMapperManager implements ModelMapperService {
	 	private ModelMapper modelmapper;
	 	
	@Override
	public ModelMapper forResponse() {
		this.modelmapper.getConfiguration() // VERİTABAINDAN RESPONSE YAPARKEN yani müşteriye bilgi verirken gettallresponse 
		.setAmbiguityIgnored(true)			// ile brand nesnesindeki birbirine benzeyenleri ver istenilenleri eşitle 
		.setMatchingStrategy(MatchingStrategies.LOOSE);//kalanları bırak (loose=gevşek bağlı demek)
		
		return this.modelmapper;
	}

	@Override
	public ModelMapper forRequest() {
		this.modelmapper.getConfiguration()
		.setAmbiguityIgnored(true)
		.setMatchingStrategy(MatchingStrategies.STANDARD); // hepsini eşitle 
		
		return this.modelmapper;
	}

}
