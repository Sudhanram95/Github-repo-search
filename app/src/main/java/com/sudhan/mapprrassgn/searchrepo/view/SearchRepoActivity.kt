package com.sudhan.mapprrassgn.searchrepo.view

import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.Window
import android.widget.*
import com.sudhan.mapprrassgn.R
import com.sudhan.mapprrassgn.searchrepo.model.SearchItemModel
import com.sudhan.mapprrassgn.searchrepo.presenter.ISearchRepoPresenter
import com.sudhan.mapprrassgn.searchrepo.presenter.SearchRepoPresenter
import android.widget.AdapterView.OnItemClickListener



class SearchRepoActivity : AppCompatActivity(), ISearchRepoView, TextWatcher {
    private val TAG = "SearchRepoActivity"
    lateinit var rvRepo:RecyclerView
    lateinit var edtSearch:EditText
    lateinit var txtLoading:TextView
    lateinit var imgFilter:ImageView

    lateinit var iSearchRepoPresenter: ISearchRepoPresenter
    lateinit var searchItemList:List<SearchItemModel>
    lateinit var repoAdapter:SearchRepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_repo)
        iSearchRepoPresenter = SearchRepoPresenter(this@SearchRepoActivity, this)

        rvRepo = findViewById(R.id.rv_repo)
        edtSearch = findViewById(R.id.edt_search)
        txtLoading = findViewById(R.id.txt_loading)
        imgFilter = findViewById(R.id.img_filter)
        edtSearch.addTextChangedListener(this)

        imgFilter.setOnClickListener(View.OnClickListener {
            iSearchRepoPresenter.dismissKeyboard()
            iSearchRepoPresenter.onGetAllLanguages(searchItemList)
        })
    }

    override fun afterTextChanged(p0: Editable?) {
        txtLoading.text = "Loading, Please wait..."
        iSearchRepoPresenter.onSearchRepoApiCalled(p0.toString())
    }

    override fun onSearchRepoApiSuccess(searchItems: List<SearchItemModel>) {
        txtLoading.visibility = View.GONE
        imgFilter.visibility = View.VISIBLE
        searchItemList = searchItems
        setSearchRepoAdapter(searchItemList)
    }

    override fun onSearchRepoApiError(errorMessage: String) {
        txtLoading.visibility = View.VISIBLE
        imgFilter.visibility = View.GONE
        rvRepo.visibility = View.GONE
        txtLoading.text = errorMessage
    }

    override fun showLanguageFilterOptions(languageList: Array<String>) {
        val dialog = Dialog(this@SearchRepoActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.filter_dialog)
        val languageListView = dialog.findViewById<ListView>(R.id.list_language)
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, languageList)
        languageListView.adapter = arrayAdapter

        languageListView.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            iSearchRepoPresenter.onFilterRepoByLanguage(searchItemList, languageList[position])
            dialog.dismiss()
        })
        dialog.show()
    }

    override fun onShowFilteredReposResult(filteredItems: List<SearchItemModel>) {
        setSearchRepoAdapter(filteredItems)
    }

    private fun setSearchRepoAdapter(searchItems:List<SearchItemModel>) {
        repoAdapter = SearchRepoAdapter(this@SearchRepoActivity, iSearchRepoPresenter, searchItems)
        rvRepo.layoutManager = LinearLayoutManager(this@SearchRepoActivity, LinearLayoutManager.VERTICAL, false)
        rvRepo.adapter = repoAdapter
        repoAdapter.notifyDataSetChanged()
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }
}
