package pl.kwi.chrisblog.comparators;

import java.util.Comparator;

import pl.kwi.chrisblog.entities.ArticleTagEntity;

/**
 * Class of comparator for object ArticleTagEntity. These objects are
 * compared through unique name.
 * 
 * @author Krzysztof Wisniewski
 */
public class ArticleTagUniqueNameComparator implements Comparator<ArticleTagEntity>{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(ArticleTagEntity articleTag1, ArticleTagEntity articleTag2) {
		
		String uniqueName1 = articleTag1.getUniqueName();
		String uniqueName2 = articleTag2.getUniqueName();
		
		return uniqueName1.compareTo(uniqueName2);
		
	}

}
