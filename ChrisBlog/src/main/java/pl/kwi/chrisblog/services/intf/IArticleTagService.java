package pl.kwi.chrisblog.services.intf;

import java.util.List;
import pl.kwi.chrisblog.entities.ArticleTagEntity;

/**
 * Interface with methods handling object ArticleTagEntity.
 * 
 * @author Krzysztof Wisniewski
 */
public interface IArticleTagService {
	
	
	/**
	 * Method gest list of article tag by list of id these articles.
	 * 
	 * @param idList list of object Long with id selected articles
	 * @return list of object ArticleTagEntity with article tags with specified ids
	 */
	public List<ArticleTagEntity> getArticleTagListByIdList(List<Long> idList);
	
	/**
	 * Method gest list of article tag by list of unique names these articles.
	 * 
	 * @param uniqueNameList list of object String with unique name selected articles
	 * @return list of object ArticleTagEntity with article tags with specified unique names
	 */
	public List<ArticleTagEntity> getArticleTagListByUniqueNameList(List<String> uniqueNameList);
	

}
