package pl.kwi.test.abstr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;

public abstract class AbstrDbUnitJUnitTestIntg {


	private static final Logger LOG = Logger.getLogger(AbstrDbUnitJUnitTestIntg.class);
	
	private static final String PERSISTENCE_UNIT_NAME = "chrisblog-pu";
	private static EntityManager em;
	private static EntityTransaction tx;
	private static IDatabaseConnection connection;
	private static String url;
	private static String driverClassName;
	private static String userName;
	private static String password;


	private static void beforeTransaction() {
		url = System.getProperty("db.url");
		driverClassName = System.getProperty("db.driver");
		userName = System.getProperty("db.username");
		password = System.getProperty("db.password");
		
		openEntityManager();
		openDBUnit();
	}	

	private static void afterTransaction() {
		closeEntityManager();
		closeDBUnit();
	}	

	public void createTransaction() {
		beforeTransaction();
		setTx(getEm().getTransaction());
		getTx().begin();
	}

	public void closeTransaction() {
		getTx().commit();
		afterTransaction();
	}

	protected static void openEntityManager(){
		setEm(Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
				.createEntityManager());
	}

	protected static void closeEntityManager(){
		getEm().close();
	}

	protected static void openDBUnit() {
		
		Connection jdbcConnection = null;

		try {
			
			Class.forName(driverClassName);
			jdbcConnection = DriverManager.getConnection(url, userName, password);	
			connection = new DatabaseConnection(jdbcConnection);
			
		} catch (Exception e) {
			LOG.error("Problem with opening DbUnit connection", e);
		}
		
	}

	protected static void closeDBUnit() {
		try {
			connection.close();
		} catch (SQLException e) {
			LOG.error("Problem with closing DbUnit connection", e);
		}
	}

	protected void executeDataFile(String path) {

		try {
			IDataSet dataSet = new FlatXmlDataSet(this.getClass()
					.getClassLoader().getResourceAsStream(path));

			DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);

		} catch (Exception e) {
			LOG.error("Problem with executing DbUnit data file", e);
		}

	}	


	// *************************************************** //
	// ************* GETTERS AND SETTERS ***************** //
	// *************************************************** //


	protected static EntityManager getEm() {
		return em;
	}
	protected static void setEm(EntityManager em) {
		AbstrDbUnitJUnitTestIntg.em = em;
	}

	protected static EntityTransaction getTx() {
		return tx;
	}
	protected static void setTx(EntityTransaction tx) {
		AbstrDbUnitJUnitTestIntg.tx = tx;
	}

}
