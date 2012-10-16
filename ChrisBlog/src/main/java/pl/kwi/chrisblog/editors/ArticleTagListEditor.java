package pl.kwi.chrisblog.editors;

import org.springframework.beans.propertyeditors.CustomCollectionEditor;

import pl.kwi.chrisblog.entities.ArticleTagEntity;

/**
 * Class edites list of objects ArticleTagEntity from form.
 * 
 * @author Krzysztof Wisniewski
 */
public class ArticleTagListEditor extends CustomCollectionEditor {

	public ArticleTagListEditor(Class collectionType) {
		super(collectionType);
	}

	@Override
	protected Object convertElement(Object element) {

		Long roleId = null;

		if (element instanceof Long) {
			roleId = (Long) element;
		} else if (element instanceof String) {
			roleId = Long.valueOf((String) element);
		}

		ArticleTagEntity tag = new ArticleTagEntity();
		tag.setId(roleId);

		return tag;

	}

}
