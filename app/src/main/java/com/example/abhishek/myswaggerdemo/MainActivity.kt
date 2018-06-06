package com.example.abhishek.myswaggerdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.swaggerclient.ApiClient
import com.example.swaggerclient.api.AdminsApi
import com.example.swaggerclient.api.DevelopersApi
import com.example.swaggerclient.model.InventoryItem
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class MainActivity : AppCompatActivity() {

    lateinit var api: ApiClient
     val searchString:String="Widget Adapter"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
           // apiCall()
            search(searchString)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun apiCall() {

        val apiClient: ApiClient
        var addInv: InventoryItem;
        apiClient = ApiClient()

        val adminsApi: AdminsApi = apiClient.createService(AdminsApi::class.java)
        addInv = InventoryItem();

        //addInv.manufacturer;
        addInv.name="xyx"
        addInv.createDate="12/11/2018"
        addInv.releaseDate="12/11/2018"
        addInv.manufacturer= null
        val inventoryResponse = adminsApi.addInventory(addInv)

        inventoryResponse.enqueue(object : Callback<Void> {

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {

                    val res = response.body()
                    Log.d("Response",""+response.body().toString());

                } else {
                    Log.d("Response",""+response.errorBody().toString());
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("ErrorResoponse",t.message);
            }
        })

    }


    fun search(value:String) {
        val apiClient: ApiClient
        apiClient = ApiClient()

        val developersApi: DevelopersApi = apiClient.createService(DevelopersApi::class.java)

        val searchResult = developersApi.searchInventory(value,null,null)
          searchResult.enqueue(object :Callback<List<InventoryItem>>{
              override fun onFailure(call: Call<List<InventoryItem>>?, t: Throwable?) {
                  Log.d("ErrorResoponse",t?.message);
              }

              override fun onResponse(call: Call<List<InventoryItem>>?, response: Response<List<InventoryItem>>?) {
               if(response?.isSuccessful!!){
                   Log.d("Response",""+response.body().toString());
               }else{
                   Log.d("Response",""+response.errorBody().toString());
               }
              }
          })

    }

}











