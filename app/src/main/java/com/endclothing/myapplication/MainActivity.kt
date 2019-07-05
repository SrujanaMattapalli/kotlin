package com.endclothing.myapplication

import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.endclothing.myapplication.api.GetShoppingItemsList
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var error_txt :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.shoppingcartlist);
        error_txt = findViewById(R.id.error_txt);689779
        recyclerView.layoutManager = GridLayoutManager(this,2);
        if (verifyAvailableNetwork(applicationContext)) {
            DisplayProgressDialog();
            callWebService();
        }else{
            recyclerView.isVisible = false;
            error_txt.isVisible = true;
            Toast.makeText( applicationContext, "Please check internet connectivity", Toast.LENGTH_SHORT ).show()
        }
    }


    fun callWebService() {
        val apiService = GetShoppingItemsList.create()
        val call = apiService.ShoppinglistResponse()
        Log.d("REQUEST", call.toString() + "")
        call.enqueue(object : Callback<ShopingListResponse> {
            override fun onResponse(call: Call<ShopingListResponse>, response: retrofit2.Response<ShopingListResponse>?) {
                if (response != null) {
                    if (pDialog != null && pDialog?.isShowing()) {
                        pDialog.dismiss()
                    }

                    var list: List<ProductsItem> = response.body()?.products!!
                    if (list.size > 0){
                        recyclerView.isVisible = true;
                        error_txt.isVisible = false;
                    }else{
                        recyclerView.isVisible = false;
                        error_txt.isVisible = true;
                    }
                    val customAdapter = ShoppingAdapter(applicationContext, list)
                    recyclerView.adapter = customAdapter

                }
            }
            override fun onFailure(call: Call<ShopingListResponse>, t: Throwable) {
                if (pDialog != null && pDialog.isShowing()) {
                    pDialog.dismiss()
                }
            }
        })

    }



    lateinit var pDialog: ProgressDialog
    fun DisplayProgressDialog() {
        pDialog = ProgressDialog(this@MainActivity)
        pDialog?.setMessage("Loading..")
        pDialog?.setCancelable(false)
        pDialog?.isIndeterminate = false
        pDialog.show()
    }

    fun verifyAvailableNetwork(activity: Context):Boolean{
        val connectivityManager=activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }
}
