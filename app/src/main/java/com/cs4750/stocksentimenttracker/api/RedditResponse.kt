package com.cs4750.stocksentimenttracker.api

//maps to outermost object in JSON data and then maps to comments
class RedditResponse {
    lateinit var comments: CommentResponse
}