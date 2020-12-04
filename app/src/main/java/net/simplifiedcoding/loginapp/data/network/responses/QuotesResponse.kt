package net.simplifiedcoding.loginapp.data.network.responses

import net.simplifiedcoding.loginapp.data.db.entities.Quote

data class QuotesResponse (
    val isSuccessful: Boolean,
    val quotes: List<Quote>
)