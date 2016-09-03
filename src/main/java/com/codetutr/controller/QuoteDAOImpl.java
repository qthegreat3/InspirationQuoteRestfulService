package com.codetutr.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class QuoteDAOImpl implements QuoteDAO {
	@Autowired
	private DataSource _datasource;
	
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(QuoteDAOImpl.class);
	
	public QuoteDAOImpl(DataSource datasource)
	{
		jdbcTemplate = new JdbcTemplate(datasource);
		_datasource = datasource;
	}
	
	@Override
	public void saveOrUpdate(final Quote quote)
	{
        String sql = "INSERT INTO quotes (quote)"
                + " VALUES (?)";
        
        log.debug("saveOrUpdate: Executing sql " + sql);
        
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws
            SQLException {
              preparedStatement.setString(1, quote.getQuote());
          }
        });
	}
	
	@Override
	public Quote getQuote(final int id)
	{
		String sql = "Select * from quotes where " + " id = ?";
		return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws
            SQLException {
              preparedStatement.setInt(1, id);
          }
        }, new ResultSetExtractor<Quote>()
				{
			        @Override
			        public Quote extractData(ResultSet rs) throws SQLException,
			                DataAccessException {
			            if (rs.next()) {
			                Quote quote = new Quote();
			                quote.setId(rs.getInt("id"));
			                quote.setQuote(rs.getString("quote"));			                
			                return quote;
			            }
			 
			            return new Quote();
			        }
				
				});
	}
	
	@Override
	public int numberOfRows(){
		String sql = "SELECT COUNT(*) as rowCount from quotes";		
		
		int numberOfRows = 0;
		
		try (Connection conn =  _datasource.getConnection();) {			
			Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			log.debug("Sql executed: " + sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next())
			{
				numberOfRows = rs.getInt("rowCount");
			}
		}
		catch (Exception e)
		{
			log.error("Error in numberOfRows with sql: " + sql + " " + e, e);
		}
		
		return numberOfRows;
	}
}
