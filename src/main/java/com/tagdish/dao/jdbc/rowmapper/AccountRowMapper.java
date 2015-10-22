package com.tagdish.dao.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tagdish.domain.db.AccountDB;
import com.tagdish.domain.db.GeoTargetDB;
import com.tagdish.domain.location.Location;

public class AccountRowMapper implements RowMapper<AccountDB>{

	public AccountDB mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		AccountDB accountDB = new AccountDB();

    	accountDB.setAccountId(rs.getLong("account.id"));    	
    	accountDB.setAccountName(rs.getString("name"));
    	accountDB.setAccountType(rs.getString("acType"));
    	
    	accountDB.setMarket(rs.getString("market"));
    	accountDB.setCurrency(rs.getString("currency"));
    	accountDB.setLanguage(rs.getString("language"));
    	accountDB.setTimezone(rs.getString("timezone"));
    	accountDB.setStatus(rs.getString("status"));
    	
    	accountDB.setCreatedDate(rs.getTimestamp("account.createdDate"));
    	accountDB.setUpdatedDate(rs.getTimestamp("account.updatedDate"));
        
        accountDB.setPhoneNumber(rs.getString("phone"));
        accountDB.setGenre(rs.getString("genre"));
        
        accountDB.setAvgCostPerPerson(rs.getFloat("averageCost"));
        accountDB.setPaymentOptions(rs.getString("paymentOptions"));
        accountDB.setDressCode(rs.getString("dressCode"));
        accountDB.setParkingDesc(rs.getString("parkingType"));
        accountDB.setParkingFee(rs.getFloat("parkingCost"));        
        
        accountDB.setImageUrl(rs.getString("logoPath"));		
        accountDB.setAdditionalDetails(rs.getString("additionalDetails"));
        accountDB.setDeleted(rs.getInt("account.del"));
        
        GeoTargetDB geoTargetDB = new GeoTargetDB();
        geoTargetDB.setId(rs.getLong("geotarget.id"));
        geoTargetDB.setAccountId(rs.getLong("adGroup_id"));
        geoTargetDB.setAddress1(rs.getString("address1"));
        geoTargetDB.setAddress2(rs.getString("address2"));
        geoTargetDB.setCity(rs.getString("city"));
        geoTargetDB.setState(rs.getString("state"));
        geoTargetDB.setZipcode(rs.getLong("zipcode"));
        
        Location location = new Location();
        location.setLatitude(rs.getString("lat"));
        location.setLongitude(rs.getString("lng"));
        geoTargetDB.setLocation(location);
        
        accountDB.setGeoTargetDB(geoTargetDB);
		return accountDB;
	}
}
