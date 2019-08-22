package com.sudhan.mapprrassgn

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.sudhan.mapprrassgn.network.ApiEndpoints
import com.sudhan.mapprrassgn.network.NetworkUtil
import com.sudhan.mapprrassgn.searchrepo.model.SearchRepoResponse
import com.sudhan.mapprrassgn.searchrepo.view.SearchRepoActivity
import io.reactivex.observers.TestObserver
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
open class SearchRepoApiTest {
    val mockServer : MockWebServer = MockWebServer()
    var apiEndpoints : ApiEndpoints? = null

    @Before
    fun setUp() {
        mockServer.start()
        apiEndpoints = NetworkUtil.retrofitHelper()?.create(ApiEndpoints::class.java)
    }

    @Test
    fun testSearchRepoApi() {
        val testObserver = TestObserver<SearchRepoResponse>()

        val path = "search/repositories"

        val mockReponse = MockResponse()
            .setResponseCode(200)
            .setBody(getJSONOutput("search_repo.json"))

        mockServer.enqueue(mockReponse)

        apiEndpoints?.getRepoList("retrofit", "stars")?.subscribe(testObserver)
        testObserver.awaitTerminalEvent(60, TimeUnit.SECONDS)
        testObserver.assertNoErrors()

        val request = mockServer.takeRequest()
        Assert.assertEquals(path, request.path)
    }

    fun getJSONOutput(path:String):String {
        val uri = this.javaClass.classLoader.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }

    @After
    fun tearDown() {
        mockServer.shutdown()
    }
}