package com.inno.impl.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.example.common.common_composables.TopShadow
import com.example.common.theme.MultimodulePracticeTheme
import com.example.multimodulepractice.main.impl.R
import com.example.multimodulepractice.main.impl.databinding.MainFragmentBinding
import com.inno.impl.data.local.models.MainTab
import com.inno.impl.di.DaggerMainComponent
import com.inno.impl.di.MainDependencies
import com.inno.impl.ui.compose_elements.Tab
import com.inno.impl.ui.fragments.list.ListFragment
import com.inno.impl.ui.fragments.map.MapFragment
import com.inno.impl.ui.fragments.profile.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val mapFragment by lazy {
        MapFragment()
    }

    private val listFragment by lazy {
        ListFragment()
    }

    private val profileFragment by lazy {
        ProfileFragment()
    }

    @Inject
    lateinit var mainDependencies: MainDependencies

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val baseInflater = LayoutInflater.from(requireActivity())
        return MainFragmentBinding.inflate(baseInflater).also {
            it.composeView.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    MultimodulePracticeTheme {
                        val activeFragment = remember { mutableStateOf<Fragment>(mapFragment) }
                        val currentTab = remember { mutableStateOf(MainTab.MAP) }

                        Box(modifier = Modifier.fillMaxSize()) {
                            TopShadow(
                                modifier = Modifier
                                    .align(Alignment.TopCenter)
                                    .height(20.dp)
                                    .fillMaxWidth(),
                                alpha = 0.2f
                            )
                            Row(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                                    .background(Color.White),
                                horizontalArrangement = Arrangement.SpaceAround,
                            ) {
                                Tab(
                                    modifier = Modifier.weight(1f),
                                    tabValue = MainTab.MAP,
                                    currentTab = currentTab,
                                ) {
                                    childFragmentManager.beginTransaction()
                                        .hide(activeFragment.value)
                                        .show(mapFragment).commit()
                                    activeFragment.value = mapFragment
                                    currentTab.value = MainTab.MAP
                                }
                                Tab(
                                    modifier = Modifier.weight(1f),
                                    tabValue = MainTab.LIST,
                                    currentTab = currentTab,
                                ) {
                                    childFragmentManager.beginTransaction()
                                        .hide(activeFragment.value)
                                        .show(listFragment).commit()
                                    activeFragment.value = listFragment
                                    currentTab.value = MainTab.LIST
                                }
                                Tab(
                                    modifier = Modifier.weight(1f),
                                    tabValue = MainTab.PROFILE,
                                    currentTab = currentTab,
                                ) {
                                    childFragmentManager.beginTransaction()
                                        .hide(activeFragment.value)
                                        .show(profileFragment).commit()
                                    activeFragment.value = profileFragment
                                    currentTab.value = MainTab.PROFILE
                                }
                            }
                        }
                    }
                }
            }
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerMainComponent.factory().create(mainDependencies).inject(this)

        childFragmentManager.beginTransaction().apply {
            add(R.id.bottom_fragment_container, mapFragment)
            add(R.id.bottom_fragment_container, listFragment).hide(listFragment)
            add(R.id.bottom_fragment_container, profileFragment).hide(profileFragment)
        }.commitNow()
    }

    companion object {
        const val MAP_TITLE = "Карты"
        const val LIST_TITLE = "Список"
        const val PROFILE_TITLE = "Профиль"
    }

}