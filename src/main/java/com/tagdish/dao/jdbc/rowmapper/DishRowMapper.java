package com.tagdish.dao.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tagdish.domain.db.AccountDB;
import com.tagdish.domain.db.DishDB;
import com.tagdish.domain.db.MenuDB;

public class DishRowMapper implements RowMapper<DishDB>{
	
	private boolean fetchMenuInfoFlag;
	private boolean fetchAccountInfoFlag;
	
	private AccountRowMapper accountRowMapper;
	
	public DishRowMapper() {
		super();
	}
	
	public DishRowMapper(boolean fetchMenuInfoFlag,
			boolean fetchAccountInfoFlag, AccountRowMapper accountRowMapper) {
		super();
		this.fetchMenuInfoFlag = fetchMenuInfoFlag;
		this.fetchAccountInfoFlag = fetchAccountInfoFlag;
		this.accountRowMapper = accountRowMapper;
	}

	public DishDB mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		DishDB dishDB = new DishDB();

    	dishDB.setDishId(rs.getLong("dish.id"));
    	
    	dishDB.setDishName(rs.getString("dish.name"));
    	dishDB.setDescription(rs.getString("longDesc"));
    	dishDB.setShortDishDesc(rs.getString("shortDesc"));
        dishDB.setPrice(rs.getFloat("price"));
        dishDB.setIngredients(rs.getString("ingredients"));
        
        dishDB.setMethodOfPrepration(rs.getString("cookingMethod"));
        dishDB.setCuisine(rs.getString("cuisine"));
        dishDB.setDishType(rs.getString("type"));
        dishDB.setTasteType(rs.getString("tasteType"));
        dishDB.setRecipeType(rs.getString("recipeType"));
        dishDB.setCalories(rs.getInt("calories"));
        dishDB.setCategory(rs.getString("category"));
        
        dishDB.setSweetLevel(rs.getInt("sweetIndex"));
        dishDB.setSourLevel(rs.getInt("sourIndex"));
        dishDB.setSavoryLevel(rs.getInt("saveryIndex"));
        dishDB.setSpicyLevel(rs.getInt("spicyIndex"));
        
        dishDB.setImageDishUrl(rs.getString("imageUrl"));		
        dishDB.setDeleteFlag(rs.getInt("dish.del"));
        
        dishDB.setCreatedDate(rs.getTimestamp("dish.createdDate"));
        dishDB.setUpdatedDate(rs.getTimestamp("dish.updatedDate"));
        

        fetchMenu(dishDB, rs);

		return dishDB;
	}
	
	private void fetchMenu(DishDB dishDB, ResultSet rs) throws SQLException{
		
		MenuDB menuDB = null;
        if(fetchMenuInfoFlag) {
        	
        	menuDB = new MenuDB();
        	
        	menuDB.setMenuId(rs.getLong("menu.id"));
        	menuDB.setMenuName(rs.getString("menu.name"));
        	menuDB.setAvailableDays(rs.getString("availableDays"));
        	menuDB.setStartDate(rs.getString("startDate"));
        	menuDB.setStartTime(rs.getString("startTime"));
        	menuDB.setEndDate(rs.getString("endDate"));
        	menuDB.setEndTime(rs.getString("endTime"));
        	menuDB.setCreatedDate(rs.getTimestamp("menu.createdDate"));
        	menuDB.setUpdatedDate(rs.getTimestamp("menu.updatedDate"));
        	
        	menuDB.setDeleted(rs.getInt("menu.del"));
        	dishDB.setMenuDB(menuDB);
        	
        	fetchAccount(menuDB, rs);
        }
	}
	
	private void fetchAccount(MenuDB menuDB, ResultSet rs) throws SQLException{
		
		AccountDB accountDB = null;
		if(fetchAccountInfoFlag) {
			accountDB = accountRowMapper.mapRow(rs, 1);
			menuDB.setAccountDB(accountDB);
		}
	}
}
