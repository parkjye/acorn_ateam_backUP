package pension.util;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/*
 *    [ Data Base Connection Pool Bean ]
 *    
 *    ??? ?΄?Ό?€κ°? ???? €λ©? 
 *    
 *    1. Servers/context.xml λ¬Έμ? DB ? ? ? λ³΄κ? ??΄?Ό??€.
 *    
 *    <Resource name="jdbc/myoracle" auth="Container"
              type="javax.sql.DataSource" driverClassName="oracle.jdbc.OracleDriver"
              url="jdbc:oracle:thin:@127.0.0.1:1521:xe"
              username="scott" password="tiger" maxTotal="20" maxIdle="10"
              maxWaitMillis="-1"/>
              
      2. ?λ‘μ ?Έ? WEB-INF/web.xml λ¬Έμ? ??? ?€? ?΄ ??΄?Ό??€.
      
      <resource-ref>
		 <description>Oracle Datasource example</description>
		 <res-ref-name>jdbc/myoracle</res-ref-name>
		 <res-type>javax.sql.DataSource</res-type>
		 <res-auth>Container</res-auth>
	  </resource-ref>
	  
	  3. WEB-INF/lib/ ?΄?? ojdbc6.jar ??Ό? ?£?΄? ?ΌλΉλ₯΄?¬λ¦¬λ?? ?¬?©?  μ€?λΉλ?? ?΄?Ό??€.
	  
	  ?? 3κ°?μ§? ?€? ? ? ??
	  
	  - new DbcpBean().getConn() λ©μ?λ₯? ?ΈμΆνλ©? Connection Poll ??
	  Connection κ°μ²΄κ°? ?? λ¦¬ν΄??€.
	  
	  - Dao ?? Connection κ°μ²΄λ₯? ?¬?©?? .close() λ©μ?λ₯? ?ΈμΆνλ©? 
	    ???Όλ‘? Connection Pool ? Connection κ°μ²΄κ°? λ°ν??€.
 */

public class DbcpBean {
	// ??
	private Connection conn;
	
	// ??±?
	public DbcpBean() {
		// Connection κ°μ²΄? μ°Έμ‘°κ°μ ?»?΄??? ??? ???₯?? ??? ??€.
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
			conn = ds.getConnection();
		//etc.
		}catch(Exception e) { e.printStackTrace();}
	}
	
	// Connection κ°μ²΄λ₯? λ¦¬ν΄?΄μ£Όλ λ©μ?
	public Connection getConn() {
		return conn;
	}
}
