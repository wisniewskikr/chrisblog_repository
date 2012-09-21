package pl.kwi.chrisblog.services.intf;

import java.util.List;

import org.mcavallo.opencloud.Cloud;

import pl.kwi.chrisblog.entities.ArticleEntity;
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
	
	/**
	 * Method gets cloud of tags. Tags are topics connected
	 * with specified article. For instance article "Hello World Servlet"
	 * can have tags like: Java, Servlet, Jsp etc.
	 * 
	 * @param articleList list of articles with articles tags
	 * @return object Cloud with tags cloud
	 * @throws Exception 
	 */
	public Cloud getTagsCloud(List<ArticleEntity> articleList) throws Exception;
	

}
