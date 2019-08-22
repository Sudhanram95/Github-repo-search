package com.sudhan.mapprrassgn.searchrepo.view

import com.sudhan.mapprrassgn.searchrepo.model.SearchItemModel

interface ISearchRepoView {
    fun onSearchRepoApiSuccess(searchItems:List<SearchItemModel>)
    fun onSearchRepoApiError(errorMessage:String)
    fun showLanguageFilterOptions(languageList:Array<String>)
    fun onShowFilteredReposResult(filteredItems:List<SearchItemModel>)
}