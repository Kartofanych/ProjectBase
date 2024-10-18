package com.search.impl.di

import com.search.impl.ui.ListViewModel
import javax.inject.Inject

class ListDependencies @Inject constructor(
    val listViewModel: ListViewModel,
)