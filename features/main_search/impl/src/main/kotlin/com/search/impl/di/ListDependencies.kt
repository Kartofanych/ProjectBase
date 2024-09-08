package com.search.impl.di

import com.example.multimodulepractice.geo.repository.GeoRepository
import com.main.common.data.MainApi
import com.main.common.domain.AttractionRepository
import com.main.common.domain.ColorMapper
import com.main.common.domain.LandmarkMapper
import com.main.common.domain.RecommendedAttractionsRepository
import com.search.impl.ui.ListViewModel
import javax.inject.Inject

class ListDependencies @Inject constructor(
    val mainApi: MainApi,
    val attractionRepository: AttractionRepository,
    val recommendedAttractionsRepository: RecommendedAttractionsRepository,
    val geoRepository: GeoRepository,
    val colorMapper: ColorMapper,
    val landmarkMapper: LandmarkMapper,
    val listViewModel: ListViewModel
)