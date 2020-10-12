package com.fm.lastfm

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment(),CellClickListener {

    var dataList = ArrayList < AlbumItem > ()
    lateinit
    var recyclerView: RecyclerView
    private
    var myAdapter: DataAdpter ? = null

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>


    lateinit var noResultFound : TextView

    lateinit var searchbrandedit : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.brandlist)
        searchbrandedit = view.findViewById(R.id.searchbrandedit)
        noResultFound = view.findViewById(R.id.noResultFound)
        myAdapter = DataAdpter(dataList, requireContext(),this)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        getDat1a()

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.HORIZONTAL
            )
        )

        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        divider.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.item_separator)!!)
        recyclerView.addItemDecoration(divider)

        searchbrandedit.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString())
            }
        })
    }


    private fun getDat1a() {
        val call: Call<SearchResponse> = ApiClient.getClient.getPhotos(
            METHOD,
            ALBUM,
            API_KEY,
            JSON_FORMAT
        )
        call.enqueue(object: Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>?, response : Response<SearchResponse>? ) {
                //    progerssProgressDialog.dismiss()
                //val searchResponse = Gson().fromJson<SearchResponse>(response?.body(), SearchResponse::class.java)
                response?.body()?.results?.albummatches?.album?.let {
                    dataList.addAll( it )
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<SearchResponse>?, t : Throwable ? ) {
                // progerssProgressDialog.dismiss()
                showToast(t?.message)
                Log.d(TAG, "The error msg is: ${Log.getStackTraceString(t)}")
            }
        })
    }

    private fun showToast(msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()

    }



    private fun filter(text: String) {
        //new array list that will hold the filtered data
        val filteredNames = ArrayList < AlbumItem > ()
        //looping through existing elements and adding the element to filtered list
        dataList!!.filterTo(filteredNames) {
            //if the existing elements contains the search input
            it.name?.toLowerCase()?.contains(text.toLowerCase()) ?: false //|| it.url?.toLowerCase()?.contains(text.toLowerCase())
        }
        //calling a method of the adapter class and passing the filtered list
        if (filteredNames != null) {
            myAdapter!!.filterList(filteredNames)
            noResultFound.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE

        }else{
            recyclerView.visibility = View.GONE
            noResultFound.visibility = View.VISIBLE
        }
    }

    companion object {
        const val TAG = "MyFm"
        const val METHOD = "album.search"
        const val ALBUM = "believe"
        const val API_KEY = "5c6343105a3e010ac876dca899f2887f"
        const val JSON_FORMAT = "json"
        @JvmStatic
        fun newInstance() = ListFragment()
    }



    override fun onCellClickListener(
        data: String?,
        data1: String?,
        data2: String?,
        data3: String?
    ) {
        val view = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)
        val dialog = context?.let { BottomSheetDialog(it) }
        dialog?.setContentView(view)

        var textViewFacebook : ImageView = view.findViewById(R.id.imageAlbum)
        var textName : TextView = view.findViewById(R.id.textName)
        var textArtist : TextView = view.findViewById(R.id.textArtist)
        var textLink : TextView = view.findViewById(R.id.textLink)

        textName.setText(Html.fromHtml("<font><b>"+"NAME : "+"</b></font>"+data1.toString()))
        textArtist.setText(Html.fromHtml("<font><b>"+"ARTIST : "+"</b></font>"+data2.toString()))
        textLink.setText(Html.fromHtml("<font><b>"+"URL : "+"</b></font>"+data3.toString()))
        Glide.with(context)
            .load(data.toString())
            .into(textViewFacebook);
        dialog?.show()
    }

}