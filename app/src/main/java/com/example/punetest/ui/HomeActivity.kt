package com.example.punetest.ui


import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.punetest.adapter.IssueAdapter
import com.example.punetest.core.base.BaseActivity
import com.example.punetest.core.utils.AppConstanst
import com.example.punetest.core.utils.GitHubCall
import com.example.punetest.databinding.ActivityHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeActivity : BaseActivity() {
    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchData()
    }

    private fun fetchData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(AppConstanst.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val gitHubCall = retrofit.create(GitHubCall::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val owner = AppConstanst.GITHUB_OWNER
                val repo = AppConstanst.GITHUB_REPO
                val response = gitHubCall.getClosedIssues(owner, repo).execute()
                if (response.isSuccessful) {
                    val closedIssues = response.body()

                    runOnUiThread {
                        val adapter = closedIssues?.let { IssueAdapter(it) }
                        binding.rvLayout.adapter = adapter
                        binding.rvLayout.layoutManager = LinearLayoutManager(applicationContext)

                        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                            override fun onQueryTextSubmit(query: String?): Boolean {
                                return false
                            }

                            override fun onQueryTextChange(newText: String?): Boolean {
                                Log.d("main","change")
                                if (adapter != null) {
                                    val new = adapter.filter.filter(newText)
                                }
                                return true
                            }
                        })

                    }

                } else {
                    Log.d("main","error1");
                }
            } catch (e: Exception) {
                Log.d("main",e.toString());
            }
        }
    }
}
