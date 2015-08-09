package com.codetutr.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import javax.sql.DataSource;  
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class QuoteDAOImpl implements QuoteDAO {

	private JdbcTemplate jdbcTemplate;
	
	public QuoteDAOImpl(Datasource datasource)
	{
		jdbcTemplate = new JdbcTemplate(datasource);
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
		jdbcTemplate.query(sql, new ResultSetExtractor<Quote>()
				{
			        @Override
			        public Contact extractData(ResultSet rs) throws SQLException,
			                DataAccessException {
			            if (rs.next()) {
			                Quote quote = new Quote();
			                quote.setId(rs.getInt("id"));
			                qupte.setQuote(rs.getString("quote"));			                
			                return quote;
			            }
			 
			            return null;
			        }
				
				});
	}
}
