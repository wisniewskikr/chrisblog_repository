package pl.kwi.chrisblog.editors;

import java.beans.PropertyEditorSupport;

import pl.kwi.chrisblog.entities.ArticleTagEntity;

/**
 * Class edites object ArticleTagEntity from form.
 * 
 * @author Krzysztof Wisniewski
 */
public class ArticleTagEditor extends PropertyEditorSupport{
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		ArticleTagEntity tag = new ArticleTagEntity();
		tag.setId(Long.valueOf(text));
		setValue(tag);
	}

}
