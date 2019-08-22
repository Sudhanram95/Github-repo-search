package com.sudhan.mapprrassgn.searchrepo.presenter

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.sudhan.mapprrassgn.repodetail.view.RepoDetailActivity
import com.sudhan.mapprrassgn.searchrepo.SearchRepoManager
import com.sudhan.mapprrassgn.searchrepo.model.SearchItemModel
import com.sudhan.mapprrassgn.searchrepo.view.ISearchRepoView
import android.view.inputmethod.InputMethodManager


class SearchRepoPresenter(var context: Context, var iSearchRepoView: ISearchRepoView) : ISearchRepoPresenter {

    override fun onSearchRepoApiCalled(searchKey: String) {
        SearchRepoManager(iSearchRepoView).getRepoList(searchKey)
    }

    override fun dismissKeyboard() {
        val activity = context as Activity
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (null != activity.getCurrentFocus())
            imm.hideSoftInputFromWindow(
                activity.currentFocus
                    .applicationWindowToken, 0
            )
    }

    override fun onFilterRepoByLanguage(searchItems: List<SearchItemModel>, language: String) {
        val filteredItems = searchItems.filter { v -> v.language.equals(language) }
        iSearchRepoView.onShowFilteredReposResult(filteredItems)
    }

    override fun onGetAllLanguages(searchItems: List<SearchItemModel>) {
        val languagesList = ArrayList<String>()
        for (item in searchItems) {
            languagesList.add(item.language)
        }
        val distinctLanguageList = languagesList.distinct()
        iSearchRepoView.showLanguageFilterOptions(distinctLanguageList.toTypedArray())
    }

    override fun onRepoItemSelected(item: SearchItemModel) {
        val intent = Intent(context, RepoDetailActivity::class.java)
        intent.putExtra("repo", item)
        context.startActivity(intent)
    }
}