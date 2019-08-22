package com.sudhan.mapprrassgn.repodetail.view

import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.Window
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson
import com.sudhan.mapprrassgn.R
import com.sudhan.mapprrassgn.repodetail.model.ContributorModel
import com.sudhan.mapprrassgn.repodetail.presenter.IRepoDetailPresenter
import com.sudhan.mapprrassgn.repodetail.presenter.RepoDetailPresenter
import com.sudhan.mapprrassgn.searchrepo.model.SearchItemModel

class RepoDetailActivity : AppCompatActivity(), IRepoDetailView {
    private val TAG = "RepoDetailActivity"
    lateinit var txtProjectName:TextView
    lateinit var txtProjectLink:TextView
    lateinit var txtDescription:TextView
    lateinit var txtLoading:TextView
    lateinit var llRepoDetail: LinearLayout
    lateinit var rvContributors: RecyclerView
    lateinit var iRepoDetailPresenter: IRepoDetailPresenter
    var repoItem:SearchItemModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_detail)
        iRepoDetailPresenter = RepoDetailPresenter(this@RepoDetailActivity, this)

        repoItem = intent.extras?.getParcelable("repo")
        txtProjectName = findViewById(R.id.txt_project_name)
        txtProjectLink = findViewById(R.id.txt_project_link)
        txtDescription = findViewById(R.id.txt_desc)
        txtLoading = findViewById(R.id.txt_loading)
        llRepoDetail = findViewById(R.id.ll_repo_detail)
        rvContributors = findViewById(R.id.rv_contributors)

        iRepoDetailPresenter.onGetContributors(repoItem?.owner?.login, repoItem?.name)

        txtProjectLink.setOnClickListener(View.OnClickListener {
            loadWebPage()
        })
    }

    override fun onGetContributorListSuccess(contributors: List<ContributorModel>) {
        Log.e(TAG, Gson().toJson(contributors))
        llRepoDetail.visibility = View.VISIBLE
        txtLoading.visibility = View.GONE
        txtProjectName.text = repoItem?.fullName
        txtProjectLink.text = repoItem?.htmlUrl
        txtDescription.text = repoItem?.description

        val contributorAdapter = ContributorAdapter(this@RepoDetailActivity, iRepoDetailPresenter, contributors)
        val mLayoutManager = GridLayoutManager(this@RepoDetailActivity, 3)
        rvContributors.layoutManager = mLayoutManager
        rvContributors.adapter = contributorAdapter
    }

    override fun onGetContributorListError(errorMessage: String) {
        Log.e(TAG, errorMessage)
        txtLoading.text = errorMessage
    }

    private fun loadWebPage() {
        val dialog = Dialog(this@RepoDetailActivity, android.R.style.Theme_Holo_NoActionBar_Fullscreen)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.webview_dialog)
        val imgClose = dialog.findViewById<ImageView>(R.id.img_close)
        val txtUrl = dialog.findViewById<TextView>(R.id.txt_url)
        val webView = dialog.findViewById<WebView>(R.id.webview)

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl(repoItem?.htmlUrl)
        txtUrl.text = repoItem?.htmlUrl
        imgClose.setOnClickListener(View.OnClickListener {
            dialog.dismiss()
        })
        dialog.show()
    }
}
