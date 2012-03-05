package com.valtech;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class DerbyStoredProcedureTest {
	private JdbcTemplate jdbcTemplate;

	@Before
	public void setup() throws ClassNotFoundException {
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource("jdbc:derby:memory:chapter02DB;create=true", false));
	}

	/**
	 * On fait un accès bidon à la base pour vérifier que la tuyauterie est en place
	 */
	@Test
	public void jePeuxCreerUneBase() {
		List<Integer> resultat = jdbcTemplate.query("SELECT 1 FROM SYSIBM.SYSDUMMY1", //
				new RowMapper<Integer>() {
					@Override
					public Integer mapRow(ResultSet rs, int idx) throws SQLException {
						return rs.getInt(1);
					}
				});
		assertEquals(asList(1), resultat);
	}

	/**
	 * Implémentation de la procédure stockée RETURN_ONE
	 * 
	 * @param rs
	 *            recevra le resultset retourné par la procédure stockée
	 * @throws SQLException
	 */
	public static void returnOne(ResultSet[] rs) throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:default:connection");
		Statement stmt = null;
		stmt = con.createStatement();
		rs[0] = stmt.executeQuery("SELECT 1 FROM SYSIBM.SYSDUMMY1");
	}

	/**
	 * On crée une procédure stockée sans paramétres, on l'exécute et on vérifie qu'on arrive à lire son resultset.
	 */
	@Test
	public void jePeuxCreerUneProcedureStockee() {
		jdbcTemplate.execute("CREATE PROCEDURE RETURN_ONE() " + "PARAMETER STYLE JAVA " + "LANGUAGE JAVA " + "DYNAMIC RESULT SETS 1 " + "EXTERNAL NAME 'com.valtech.derby.DerbyStoredProcedureTest.returnOne'");

		List<Integer> resultat = jdbcTemplate.query("CALL RETURN_ONE()", new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int idx) throws SQLException {
				return rs.getInt(1);
			}
		});

		assertEquals(asList(1), resultat);
	}

	/**
	 * Implémentation de la procédure stockée RETURN_ONE_WITH_SPRING. Pour montrer qu'on peut utiliser jdbcTemplate à l'interieur d'une proc stoc. Pour varier les plaisir, on utilise un out param au lieu de renvoyer un resultset.
	 * 
	 * @param result
	 *            recevra la valeur du out param
	 * @throws SQLException
	 */
	public static void returnOneWithSpring(final int[] result) throws SQLException {
		DataSource dataSource = new SingleConnectionDataSource("jdbc:default:connection", false);
		JdbcTemplate currentJdbcTemplate = new JdbcTemplate(dataSource);
		currentJdbcTemplate.query("SELECT 1 FROM SYSIBM.SYSDUMMY1", new RowMapper<Void>() {
			@Override
			public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
				result[0] = rs.getInt(1);
				return null;
			}
		});
	}

	/**
	 * On crée une procédure stockée sans paramétres, on l'exécute et on vérifie qu'on arrive à lire son resultset. Bien entendu, du point de vue de l'appelant, le fait que la proc stoc utilise spring est totalement invisible.
	 */
	@Test
	public void uneProcedureStockeePeutUtiliserJdbcTemplate() {
		jdbcTemplate.execute("CREATE PROCEDURE RETURN_ONE_WITH_SPRING(OUT CONSTANT_RESULT int) " + "PARAMETER STYLE JAVA " + "LANGUAGE JAVA " + "DYNAMIC RESULT SETS 0 " + "EXTERNAL NAME 'com.valtech.derby.DerbyStoredProcedureTest.returnOneWithSpring'");

		SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withProcedureName("RETURN_ONE_WITH_SPRING");
		Map<String, Object> out = call.execute(new HashMap<String, Void>());
		assertEquals(1, out.get("CONSTANT_RESULT"));
	}
}
