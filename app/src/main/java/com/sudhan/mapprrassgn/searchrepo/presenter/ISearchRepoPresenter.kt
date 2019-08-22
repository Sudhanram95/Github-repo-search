package com.sudhan.mapprrassgn.searchrepo.presenter

import com.sudhan.mapprrassgn.searchrepo.model.SearchItemModel

interface ISearchRepoPresenter {
    fun onSearchRepoApiCalled(searchKey:String)
    fun dismissKeyboard()
    fun onFilterRepoByLanguage(searchItems: List<SearchItemModel>, language:String)
    fun onGetAllLanguages(searchItems: List<SearchItemModel>)
    fun onRepoItemSelected(item:SearchItemModel)
}