package com.search.impl.di

import com.example.multimodulepractice.common.data.mappers.CategoryMapper
import com.example.multimodulepractice.geo.repository.GeoRepository
import com.main.common.data.MainApi
import com.example.multimodulepractice.common.domain.ColorMapper
import com.main.common.domain.RecommendedAttractionsRepository
import com.search.impl.ui.ListViewModel
import javax.inject.Inject

class ListDependencies @Inject constructor(
    val mainApi: MainApi,
    val recommendedAttractionsRepository: RecommendedAttractionsRepository,
    val geoRepository: GeoRepository,
    val colorMapper: ColorMapper,
    val categoryMapper: CategoryMapper,
    val listViewModel: ListViewModel
)