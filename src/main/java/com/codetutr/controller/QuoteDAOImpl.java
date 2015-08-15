package com.codetutr.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;  
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Connection;

public class QuoteDAOImpl implements QuoteDAO {

	private JdbcTemplate jdbcTemplate;
	private DataSource _datasource;
	
	public QuoteDAOImpl(DataSource datasource)
	{
		jdbcTemplate = new JdbcTemplate(datasource);
		_datasource = datasource;
	}
	
	@Override
	public void saveOrUpdate(Quote quote)
	{
        String sql = "INSERT INTO quotes (quote)"
                + " VALUES (?)";
        jdbcTemplate.update(sql, quote.getQuote());
	}
	
	@Override
	public Quote getQuote(int id)
	{
		String sql = "Select * from quotes where " + " id = ?";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Quote>()
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
			 
			            return null;
			        }
				
				});
	}
	
	@Override
	public int numberOfRows(){
		String sql = "SELECT COUNT(*) as rowCount from quotes";

		Connection conn = null;
		
		int numberOfRows = 0;
		
		try {
			conn = _datasource.getConnection();
			Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next())
			{
				numberOfRows = rs.getInt("rowCount");
			}
		}catch (Exception e)
		{
			
		}
		finally {
			if (conn != null)
			{
				try {
					conn.close();
				}catch(Exception e)
				{
					
				}
			}
		}
		
		return numberOfRows;
	}
}
