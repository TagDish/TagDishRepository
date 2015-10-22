package com.tagdish.dao.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tagdish.domain.db.DishDB;

public class DishRowMapper implements RowMapper<DishDB>{

	public DishDB mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		DishDB dishDB = new DishDB();

    	dishDB.setDishId(rs.getLong("id"));
    	
    	dishDB.setMenuId(rs.getLong("menu_id"));
    	dishDB.setDishName(rs.getString("name"));
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
        dishDB.setDeleteFlag(rs.getInt("del"));
		
		return dishDB;
	}
}
