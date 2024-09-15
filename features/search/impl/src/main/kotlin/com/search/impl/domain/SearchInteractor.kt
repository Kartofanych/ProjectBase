package com.search.impl.domain

import com.example.multimodulepractice.common.data.models.local.ActivityEntity
import com.example.multimodulepractice.common.data.models.local.GeoPoint.Companion.toDto
import com.example.multimodulepractice.common.data.models.local.ResponseState
import com.example.multimodulepractice.geo.repository.GeoRepository
import com.search.impl.data.SearchApi
import com.search.impl.data.mappers.SearchMapper
import com.search.impl.data.models.dto.SearchRequest
import com.search.impl.data.models.local.SearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchInteractor @Inject constructor(
    private val api: SearchApi,
    private val geoRepository: GeoRepository,
    private val searchMapper: SearchMapper
) {

    suspend fun search(searchText: String, cursor: String): ResponseState<SearchResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.search(
                    SearchRequest(
                        query = searchText,
                        geoPoint = geoRepository.geoInfoImmediately().currentPoint.toDto(),
                        cursor = cursor
                    )
                )
                ResponseState.Success(searchMapper.map(response))
            } catch (exception: Exception) {
                ResponseState.Success(
                    SearchResponse(
                        cursor = "",
                        items = listOf(
                            ActivityEntity(
                                id = "1",
                                icon = "https://habrastorage.org/getpro/habr/upload_files/8af/7ca/e25/8af7cae256181b07e922a9e68acbb492.jpeg",
                                "Архитектура",
                                type = ActivityEntity.ActivityType.LANDMARK,
                                "Университет Иннополис",
                                "г. Иннополис ~ 1 км от вас",
                                "Университет Иннополис занимается образованием, исследованиями и разработками в област...",
                                starCount = 5,
                                4.5f
                            ),
                            ActivityEntity(
                                id = "2",
                                icon = "https://avatars.mds.yandex.net/i?id=74d9b414000b3d8357d29f8ff027fb05_l-7047516-images-thumbs&n=13",
                                "Достопримечательность",
                                type = ActivityEntity.ActivityType.SERVICE,
                                "Университет Иннополис",
                                "г. Иннополис ~ 1 км от вас",
                                "Университет Иннополис занимается образованием, исследованиями и разработками в област...",
                                starCount = 4,
                                4.3f
                            )
                        )
                    )
                )
            }
        }
    }
}