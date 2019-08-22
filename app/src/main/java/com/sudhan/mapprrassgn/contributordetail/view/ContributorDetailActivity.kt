package com.sudhan.mapprrassgn.contributordetail.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.sudhan.mapprrassgn.R
import com.sudhan.mapprrassgn.contributordetail.model.RepoModel
import com.sudhan.mapprrassgn.contributordetail.presenter.ContributorDetailPresenter
import com.sudhan.mapprrassgn.contributordetail.presenter.IContributorDetailPresenter

class ContributorDetailActivity : AppCompatActivity(), IContributorDetailView {

    lateinit var iContributorDetailPresenter: IContributorDetailPresenter
    lateinit var llContributorDetail:LinearLayout
    lateinit var txtLoading:TextView
    lateinit var txtLoginName:TextView
    lateinit var imgContributor:ImageView
    lateinit var rvRepos:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contributor_detail)
        iContributorDetailPresenter = ContributorDetailPresenter(this)
        llContributorDetail = findViewById(R.id.ll_contributor_detail)
        txtLoading = findViewById(R.id.txt_loading)
        txtLoginName = findViewById(R.id.txt_login_name)
        imgContributor = findViewById(R.id.img_contributor)
        rvRepos = findViewById(R.id.rv_repos)

        val login = intent.getStringExtra("login")
        iContributorDetailPresenter.onGetContributorRepos(login)
    }

    override fun onGetContributorRepoListSuccess(repos: List<RepoModel>) {
        llContributorDetail.visibility = View.VISIBLE
        txtLoading.visibility = View.GONE
        txtLoginName.text = repos.get(0).owner.login
        Glide.with(this@ContributorDetailActivity).load(repos.get(0).owner.avatarUrl).into(imgContributor)

        val repoAdapter = RepoAdapter(repos)
        rvRepos.layoutManager = LinearLayoutManager(this@ContributorDetailActivity, LinearLayoutManager.VERTICAL, false)
        rvRepos.adapter = repoAdapter
    }

    override fun onGetContributorRepoListError(errorMessage: String) {
        txtLoading.text = errorMessage
    }
}
