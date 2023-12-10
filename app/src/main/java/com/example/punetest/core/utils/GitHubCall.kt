package com.example.punetest.core.utils


import com.example.punetest.model.ClosedIssue
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubCall {

    @GET("/repos/{owner}/{repo}/issues")

    fun getClosedIssues(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("state") state: String = "closed"
    ): Call<List<ClosedIssue>>
}