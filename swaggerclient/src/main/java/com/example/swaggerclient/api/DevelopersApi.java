package com.example.swaggerclient.api;


import com.example.swaggerclient.model.InventoryItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DevelopersApi {
  /**
   * searches inventory
   * By passing in the appropriate options, you can search for available inventory in the system 
   * @param searchString pass an optional search string for looking up inventory (optional)
   * @param skip number of records to skip for pagination (optional)
   * @param limit maximum number of records to return (optional)
   * @return Call&lt;List&lt;InventoryItem&gt;&gt;
   */
  @GET("inventory")
  Call<List<InventoryItem>> searchInventory(
          @Query("searchString") String searchString, @Query("skip") Integer skip, @Query("limit") Integer limit
  );

}
