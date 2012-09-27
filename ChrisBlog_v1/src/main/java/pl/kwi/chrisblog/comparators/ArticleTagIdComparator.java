package pl.kwi.chrisblog.comparators;

import java.util.Comparator;

import pl.kwi.chrisblog.entities.ArticleTagEntity;

/**
 * Class of comparator for object ArticleTagEntity. These objects are
 * compared through id.
 * 
 * @author Krzysztof Wisniewski
 */
public class ArticleTagIdComparator implements Comparator<ArticleTagEntity>{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(ArticleTagEntity articleTag1, ArticleTagEntity articleTag2) {
		
		Long id1 = articleTag1.getId();
		Long id2 = articleTag2.getId();
		
		return id1.compareTo(id2);
	}

}
