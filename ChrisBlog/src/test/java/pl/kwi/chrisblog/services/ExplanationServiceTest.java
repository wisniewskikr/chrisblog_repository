package pl.kwi.chrisblog.services;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import pl.kwi.chrisblog.entities.ExplanationEntity;
import pl.kwi.chrisblog.exceptions.ExplanationException;
import pl.kwi.chrisblog.services.ExplanationService;

/**
 * Class with test for class ExplanationService.
 * 
 * @author Krzysztof Wisniewski
 */
public class ExplanationServiceTest {
	
	
	private ExplanationService service;
	
	
	@Before
	public void setUp(){
		service = new ExplanationService();
		service.setCompleteExplanationList(mockCompleteExplanationList());
	}
	
	@Test
	public void getExplanationByUniqueName() throws Exception{
		
		String uniqueName = "web_application";
		
		ExplanationEntity dictionary = service.getExplanationByUniqueName(uniqueName);
		
		Assert.assertEquals(Long.valueOf(1), dictionary.getId());
		Assert.assertEquals("web_application", dictionary.getUniqueName());
		Assert.assertEquals("Web Application", dictionary.getTitle());
		Assert.assertEquals("Web Application explanation", dictionary.getContent());
		
	}
	
	@Test(expected = ExplanationException.class)
	public void getExplanationByUniqueName_UniqueNameNull() throws Exception{
		
		String uniqueName = null;
		
		service.getExplanationByUniqueName(uniqueName);
				
	}
	
	@Test
	public void getExplanationByUniqueName_NoExplanation() throws Exception{
		
		String uniqueName = "web_application_another";
		
		ExplanationEntity dictionary = service.getExplanationByUniqueName(uniqueName);
		
		Assert.assertNull(dictionary);
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
	public List<ExplanationEntity> mockCompleteExplanationList(){
		
		List<ExplanationEntity> explanationList = new ArrayList<ExplanationEntity>();
		ExplanationEntity explanation;
		
		explanation = new ExplanationEntity();
		explanation.setId(1L);
		explanation.setUniqueName("web_application");
		explanation.setTitle("Web Application");
		explanation.setContent("Web Application explanation");
		explanationList.add(explanation);
		
		explanation = new ExplanationEntity();
		explanation.setId(2L);
		explanation.setUniqueName("java_servlets_technology");
		explanation.setTitle("Java Servlets Technology");
		explanation.setContent("Java Servlets technology explanation");
		explanationList.add(explanation);
		
		return explanationList;
		
	}

}
