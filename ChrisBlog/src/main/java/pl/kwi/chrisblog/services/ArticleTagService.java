package pl.kwi.chrisblog.services;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.mcavallo.opencloud.Cloud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kwi.chrisblog.daos.ArticleTagDao;
import pl.kwi.chrisblog.entities.ArticleEntity;
import pl.kwi.chrisblog.entities.ArticleTagEntity;
import pl.kwi.chrisblog.exceptions.ArticleTagException;

/**
 * Class implementing interface IArticleTagService.
 * 
 * @author Krzysztof Wisniewski
 */
@Service
public class ArticleTagService {
	
	
	@Autowired
	private ArticleTagDao dao;
	
	
	/**
	 * Method gets article tag with unique name.
	 * 
	 * @param uniqueName object String with unique name of selected article
	 * @return object ArticleTagEntity with article tag with specified unique name
	 */
	public ArticleTagEntity getArticleTagByUniqueName(String uniqueName) {
		
		ArticleTagEntity result = null;
		
		if(uniqueName == null){
			return result;
		}
		
		List<String> uniqueNameList = new ArrayList<String>();
		uniqueNameList.add(uniqueName);		
		
		List<ArticleTagEntity> articleTagList = getArticleTagListByUniqueNameList(uniqueNameList);
		
		if(!articleTagList.isEmpty()){
			result = articleTagList.get(0);
		}
		
		return result;
	}
	
	/**
	 * Method gest list of article tag by list of unique names these articles.
	 * 
	 * @param uniqueNameList list of object String with unique name selected articles
	 * @return list of object ArticleTagEntity with article tags with specified unique names
	 */
	public List<ArticleTagEntity> getArticleTagListByUniqueNameList(List<String> uniqueNameList) {
		
		List<ArticleTagEntity> resultList = new ArrayList<ArticleTagEntity>();
		
		if(uniqueNameList == null || uniqueNameList.isEmpty()){
			return resultList;
		}
		
		return dao.findByUniqueNameList(uniqueNameList);
	}
	
	/**
	 * Method gets cloud of tags for footer. Tags are topics connected
	 * with specified article. For instance article "Hello World Servlet"
	 * can have tags like: Java, Servlet, Jsp etc.
	 * 
	 * @param articleList list of articles with articles tags
	 * @return object Cloud with tags cloud
	 * @throws Exception 
	 */
	public Cloud getTagsCloudFooter(List<ArticleEntity> articleList) throws Exception {
		
		if(articleList == null){
			throw new ArticleTagException("Error article tags handling. List of articles is null");
		}
		
		Cloud cloud = new Cloud();
		cloud.setMinWeight(10.0);
		cloud.setMaxWeight(20.0);
		
		List<ArticleTagEntity> articleTagList = new ArrayList<ArticleTagEntity>();
				
		for (ArticleEntity article : articleList) {
			
			if(article.getArticleTagList() == null || article.getArticleTagList().isEmpty()){
				throw new ArticleTagException(MessageFormat.format("Article with unique name: {0} has no article tag", article.getUniqueName()));
			}			
			articleTagList.addAll(article.getArticleTagList());
		}
		
		for (ArticleTagEntity articleTag : articleTagList) {
			cloud.addTag(articleTag.getName());
		}
		
		return cloud;
		
	}
	
	/**
	 * Method gets cloud of tags for right space. Tags are topics connected
	 * with specified article. For instance article "Hello World Servlet"
	 * can have tags like: Java, Servlet, Jsp etc.
	 * 
	 * @param articleList list of articles with articles tags
	 * @return object Cloud with tags cloud
	 * @throws Exception 
	 */
	public Cloud getTagsCloudRightSpace(List<ArticleEntity> articleList) throws Exception {
		
		if(articleList == null){
			throw new ArticleTagException("Error article tags handling. List of articles is null");
		}
		
		Cloud cloud = new Cloud();
		cloud.setMinWeight(10.0);
		cloud.setMaxWeight(20.0);
		
		List<ArticleTagEntity> articleTagList = new ArrayList<ArticleTagEntity>();
				
		for (ArticleEntity article : articleList) {
			articleTagList.addAll(article.getArticleTagList());
		}
		
		for (ArticleTagEntity articleTag : articleTagList) {
			String name = articleTag.getName() + " (" + articleTag.getOccurencesCount() + ")";
			String link = "tag/" + articleTag.getUniqueName();
			cloud.addTag(name, link);
		}
			
		return cloud;
		
	}
	
	/**
	 * Method finds all tags from data base.
	 * 
	 * @return list of all tags from data base
	 */
	public List<ArticleTagEntity> findAll() {

		return dao.findAll();

	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** GETTERS AND SETTERS **************************************** //
	// ************************************************************************************************************ //
	
	
	public void setDao(ArticleTagDao dao) {
		this.dao = dao;
	}
	
}
