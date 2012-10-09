package pl.kwi.chrisblog.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import pl.kwi.db.abstr.AbstractEntity;

@NamedQueries(value = { 
		@NamedQuery(name="ArticleEntity.findAllSortedByDateDesc", 
			query="SELECT a FROM ArticleEntity a ORDER BY a.creationDate DESC"),
		@NamedQuery(name="ArticleEntity.findAllWithTagsSortedByDateDesc", 
			query="SELECT a FROM ArticleEntity AS a " +
					"JOIN a.articleTagList AS t " +
					"WHERE t IN (:articleTagList) " +
					"GROUP BY a.id " +
					"ORDER BY a.creationDate DESC"
					),
		@NamedQuery(name="ArticleEntity.getArticleByUniqueName", 
			query="SELECT a FROM ArticleEntity a WHERE a.uniqueName = :uniqueName"),
		@NamedQuery(name="ArticleEntity.getCountOfAllArticles", 		
			query="SELECT COUNT(a) FROM ArticleEntity a"),
		@NamedQuery(name="ArticleEntity.getCountArticlesWithTags", 
			query="SELECT COUNT(a) FROM ArticleEntity AS a " +
					"JOIN a.articleTagList AS t " +
					"WHERE t IN (:articleTagList) " +
					"GROUP BY t.id")
	})

@Entity
@Table(name="articles")
public class ArticleEntity extends AbstractEntity{	
	
	
	private static final long serialVersionUID = 1L;
	
	private String uniqueName;
	private String title;
	private String description;
	private String content;
	private Integer pagesCount;
	private Calendar creationDate;
	private String author;
	private String demoName;
	private String exampleFileName;
	private String sourceFileName;
	private List<ArticleTagEntity> articleTagList = new ArrayList<ArticleTagEntity>();
		
	// Transient
	private String demoPath;
	private String examplePath;
	private String sourcePath;
	private String creationDateAsString;
	
	
	// ************************************************************************************************************ //
	// *********************************************** GETTERS AND SETTERS **************************************** //
	// ************************************************************************************************************ //
	
	
	@Column(name="UNIQUE_NAME", length=100, nullable=false)
	public String getUniqueName() {
		return uniqueName;
	}
	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}	

	@Column(name="TITLE", length=100, nullable=false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="DESCRIPTION", length=100, nullable=false)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="CONTENT", length=100, nullable=false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name="PAGES_COUNT", nullable=false)
	public Integer getPagesCount() {
		return pagesCount;
	}
	public void setPagesCount(Integer pagesCount) {
		this.pagesCount = pagesCount;
	}
	
	@Column(name="CREATION_DATE", nullable=false)
	public Calendar getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}
	
	@Column(name="AUTHOR", length=50, nullable=false)
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}	
	
	@Column(name="DEMO_NAME", length=100, nullable=true)
	public String getDemoName() {
		return demoName;
	}
	public void setDemoName(String demoName) {
		this.demoName = demoName;
	}

	@Column(name="EXAMPLE_FILE_NAME", length=100, nullable=true)
	public String getExampleFileName() {
		return exampleFileName;
	}
	public void setExampleFileName(String exampleFileName) {
		this.exampleFileName = exampleFileName;
	}

	@Column(name="SOURCE_FILE_NAME", length=100, nullable=true)
	public String getSourceFileName() {
		return sourceFileName;
	}
	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}
	
	@ManyToMany(cascade=(CascadeType.ALL), fetch=FetchType.EAGER)
	@JoinTable(
			name="join_article_and_tag",
			joinColumns={@JoinColumn(name="ARTICLE_ID", referencedColumnName="ID")},
			inverseJoinColumns={@JoinColumn(name="ARTICLE_TAG_ID", referencedColumnName="ID")}
			)
	public List<ArticleTagEntity> getArticleTagList() {
		return articleTagList;
	}
	public void setArticleTagList(List<ArticleTagEntity> articleTagList) {
		this.articleTagList = articleTagList;
	}
	
	@Transient
	public String getDemoPath() {
		return demoPath;
	}
	public void setDemoPath(String demoPath) {
		this.demoPath = demoPath;
	}
	
	@Transient
	public String getExamplePath() {
		return examplePath;
	}
	public void setExamplePath(String examplePath) {
		this.examplePath = examplePath;
	}
	
	@Transient
	public String getSourcePath() {
		return sourcePath;
	}
	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}
	
	@Transient
	public String getCreationDateAsString() {
		return creationDateAsString;
	}
	public void setCreationDateAsString(String creationDateAsString) {
		this.creationDateAsString = creationDateAsString;
	}

	
}
