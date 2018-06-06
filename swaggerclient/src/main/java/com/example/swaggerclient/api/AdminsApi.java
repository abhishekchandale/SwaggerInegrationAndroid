package com.example.swaggerclient.api;


import com.example.swaggerclient.model.InventoryItem;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AdminsApi {
  /**
   * adds an inventory item
   * Adds an item to the system
   * @param inventoryItem Inventory item to add (optional)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("inventory")
  Call<Void> addInventory(
          @Body InventoryItem inventoryItem
  );

}
