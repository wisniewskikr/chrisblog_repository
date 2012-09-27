package pl.kwi.chrisblog.services.intf;

import pl.kwi.chrisblog.entities.ExplanationEntity;

/**
 * Interface with methods handling object ExplanationEntity.
 * 
 * @author Krzysztof Wisniewski
 */
public interface IExplanationService {
	
		
	/**
	 * Method gets object ExplanationEntity by unique name. Unique
	 * name is url adjusted: without spaces and unique. For
	 * instance: web_application, java_script etc.
	 * 
	 * @param uniqueName object String with word which is explained
	 * in dictionary. This word is key of dictionary: has to be
	 * unique and have url display (without spaces, for instance
	 * java_script, web_application etc.)
	 * @return object ExplanationEntity with explanation of word
	 * @throws Exception 
	 */
	public ExplanationEntity getExplanationByUniqueName(String uniqueName) throws Exception;
	

}
