package com.attraction.impl.di

import com.attraction.impl.data.AttractionApi
import com.example.multimodulepractice.common.data.mappers.CategoryMapper
import javax.inject.Inject

class AttractionDependencies @Inject constructor(
    val categoryMapper: CategoryMapper,
    val attractionApi: AttractionApi
)