package com.tagdish.dao.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tagdish.domain.db.AccountDB;

public class AccountRowMapper implements RowMapper<AccountDB>{

	public AccountDB mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		AccountDB accountDB = new AccountDB();

    	accountDB.setAccountId(rs.getLong("id"));    	
    	accountDB.setAccountName(rs.getString("name"));
    	accountDB.setAccountType(rs.getString("acType"));
    	
    	accountDB.setMarket(rs.getString("market"));
    	accountDB.setCurrency(rs.getString("currency"));
    	accountDB.setLanguage(rs.getString("language"));
    	accountDB.setTimezone(rs.getString("timezone"));
    	accountDB.setStatus(rs.getString("status"));
    	
    	accountDB.setCreatedDate(rs.getTimestamp("createdDate"));
    	accountDB.setUpdatedDate(rs.getTimestamp("updatedDate"));
        
        accountDB.setPhoneNumber(rs.getString("phone"));
        accountDB.setGenre(rs.getString("genre"));
        
        accountDB.setAvgCostPerPerson(rs.getFloat("averageCost"));
        accountDB.setPaymentOptions(rs.getString("paymentOptions"));
        accountDB.setDressCode(rs.getString("dressCode"));
        accountDB.setParkingDesc(rs.getString("parkingType"));
        accountDB.setParkingFee(rs.getFloat("parkingCost"));        
        
        accountDB.setImageUrl(rs.getString("logoPath"));		
        accountDB.setAdditionalDetails(rs.getString("additionalDetails"));
        accountDB.setDeleted(rs.getInt("del"));
		
		return accountDB;
	}
}
