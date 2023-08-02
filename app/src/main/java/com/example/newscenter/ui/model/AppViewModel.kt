package com.example.newscenter.ui.model

import androidx.lifecycle.ViewModel
import com.example.newscenter.spider.NewsItem
import com.example.newscenter.spider.Parser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel : ViewModel() {

    private val _username = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _dialogState = MutableStateFlow(false)
    private val _onMainPage = MutableStateFlow(true)
    private val _newsList = MutableStateFlow(mutableListOf<NewsItem>())
    private val _newsContent: MutableStateFlow<NewsItem?> = MutableStateFlow(null)
    val newsList: StateFlow<MutableList<NewsItem>> = _newsList.asStateFlow()
    val username: StateFlow<String> = _username.asStateFlow()
    val password: StateFlow<String> = _password.asStateFlow()
    val dialogState: StateFlow<Boolean> = _dialogState.asStateFlow()
    val onMainPage: StateFlow<Boolean> = _onMainPage.asStateFlow()
    val newsContent: StateFlow<NewsItem?> = _newsContent.asStateFlow()
    val shareable: MutableStateFlow<Boolean> = MutableStateFlow(false)

    private val parser = Parser()

    fun onNameChange(msg: String) {
        _username.value = msg
    }

    fun onPassChange(msg: String) {
        _password.value = msg
    }

    fun changeDialogState() {
        _dialogState.value = !dialogState.value
    }

    fun setNews(news: List<NewsItem>) {
        _newsList.value = news.toMutableList()
    }

    fun changeNewsContent(newsItem: NewsItem) {
        newsItem.content = parser.getContent(newsItem.docurl)
        _newsContent.value = newsItem
    }
}