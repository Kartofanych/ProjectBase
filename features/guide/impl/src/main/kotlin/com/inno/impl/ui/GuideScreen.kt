package com.inno.impl.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import coil.size.Scale
import com.example.common.composables.shimmerBrush
import com.example.common.theme.mediumTextStyle
import com.example.common.utils.getStatusBarHeight
import com.example.common.utils.pxToDp
import com.example.common.utils.screenWidthDp
import com.example.multimodulepractice.login.R

@Composable
fun GuideScreen(uiState: GuideUiState, onAction: (GuideAction) -> Unit) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            ContentImage()

            Info()
        }

        Header(modifier = Modifier.safeDrawingPadding(), onAction = onAction)

        Footer(modifier = Modifier.align(Alignment.BottomCenter))

    }
}

@Composable
fun Footer(modifier: Modifier) {
    Row(
        modifier = modifier
            .safeDrawingPadding()
            .padding(bottom = 16.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .size(45.dp)
                .background(Color(0xFF737F89), CircleShape)
                .clip(CircleShape)
                .clickable {

                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_play),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 13.dp)
                    .size(18.dp)
                    .rotate(180f)
            )
        }

        Box(
            modifier = Modifier
                .size(45.dp)
                .background(Color(0xFF47D88D), CircleShape)
                .clip(CircleShape)
                .clickable {

                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_play),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 13.dp)
                    .size(18.dp)
            )
        }

    }
}

@Composable
fun Header(modifier: Modifier, onAction: (GuideAction) -> Unit) {
    val context = LocalContext.current
    Row(
        modifier = modifier
            .padding(top = (context.pxToDp(getStatusBarHeight().toFloat()) + 8).dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .size(34.dp)
                .background(Color(0x99FFFFFF), RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                    onAction(GuideAction.OnBackPressed)
                }
                .padding(7.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.icon_back), contentDescription = null)
        }

        Box(
            modifier = Modifier
                .size(34.dp)
                .background(Color(0x99FFFFFF), RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .padding(7.dp)
        ) {
            Text(
                text = "1/4",
                style = mediumTextStyle,
                modifier = Modifier.align(Alignment.Center)
            )
        }

    }
}

@Composable
fun Info() {
    val context = LocalContext.current
    val imageHeight = context.screenWidthDp() - 16.dp

    Box(
        modifier = Modifier
            .padding(top = imageHeight)
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
    ) {
        Text(
            text = "Звезды отражались в её глазах. Раньше, еще месяц назад их не было видно в черте города. Свет фонарей и смог заглушали их слабое мерцание. Все изменилось. И одним из немногих плюсов сложившейся ситуации было мерцание звезд в её глазах, и воздух, кажется стал чище. У всех у нас когда то была работа, и был дом. У некоторых были дети. У Лены была дочка. Она работала барменшой, а по вечерам подрабатывала в \"клубе знакомств\". Попросту говоря - была проституткой. Теперь ей уже не приходится ездить по незнакомым клиентам, каждый раз перед дверью квартиры креститься, и молиться, что бы все прошло как надо. Это тоже плюс. Но теперь у неё нет дочки. Она потерялась в первые дни, как только все это начиналось.\n" +
                    "Лена была \"на вызове\", когда исчезло электричество. Никто еще не знал, что это серьезно. Мобильная связь не работала, город погрузился во тьму за окнами однакомнатной квартиры, в которой возбужденный мужчина кончал в презерватив, а Лена считала секунды до очередного вызова. Она не могла как обычно принять душ, и вызвать такси, и после осознания этого, просто начала одеваться. Белье по привычке было сложено одной кучкой рядом с кроватью. Мужчина, имя которого она не захотела запоминать сказал ей спасибо и открыл дверь, что то проворчав напоследок на \"долбанных электриков\"...\n" +
                    "Лене было очень приятно выйти на свежий воздух, после пропахшей перегаром комнатушки. Она шла по темным улицам города, шла на \"базу\" пешком, и эта непроглядная тьма вокруг для неё сейчас была отражением внутреннего состояния, и поэтому она наслаждалась этой прогулкой. Она еще не знала, что электричество и водоснабжение уже не восстановят. Она не могла подумать, что через три часа её пятилетняя дочка, испугавшись темноты и одиночества, выйдет из квартиры, и пропадет навсегда. Она еще не знала, что её поиски будут бесполезны и опасны... Она просто шла по улице.\n" +
                    "\n" +
                    "Сейчас мы стоим кольцом, решаем что делать. Дмитрий Иванович слева от меня, дплее Сашок, Лена и Дима. На скамейке недалеко сидят Лера и Оля. Лица во тьме еле различимы. У мужчин - покрыты щетиной, у девушек осунулись и устали..\n" +
                    "- На Кирова я слышал выстрелы. Туда идти бесполезно. - Дмитрий Иванович был явно напуган.\n" +
                    "- Да никто тебя туда не заставляет идти! - сказал Сашок.\n" +
                    "- Да, но нам нужно достать еще еды. - ответил я.\n" +
                    "Улицы города были пусты только с виду. На самом деле - в черных глазницах зданий ютились уцелевшие. Агрессивные, озлобленные, готовые на все. Люди кучковались по случайному признаку. Так и наша группа образовалась случайно. Одному сейчас не выжить. Но проблема в том, что мы не доверяли другим. Как впрочем и они нам. У нас еще была еда, её хватило бы на неделю максимум.\n" +
                    "- Кто нибуть следил за этим домом? - я указал на дом 20, корпус 1 по Ленинградской набережной. Это была пятиэтажка, и она горела. Горела уже давно. Из одного подъезда постоянно валил дым. Черный, густой. Но всполохов пламени не было видно.\n" +
                    "- Я следила, никто не входил. Кому нужен это горящее пекло? - Лера подала голос со скамейки.\n" +
                    "- Пойдем туда. - Сашок не спрашивал, он просто констатировал факт.\n" +
                    "Никто не спорил. Все очень устали. И идея обшарить горящий дом всем показалась правильной. Мы передернули затворы, проверили гранаты, и пошли.\n",
            modifier = Modifier.padding(top = 20.dp)
        )
    }

}

@Composable
fun ContentImage() {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("some_url")
            .scale(Scale.FILL)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
    ) {
        val state = painter.state
        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
            Box(
                modifier = Modifier
                    .padding(start = 1.dp, end = 1.dp)
                    .fillMaxSize()
                    .background(
                        shimmerBrush(
                            targetValue = 1300f,
                            showShimmer = true
                        )
                    )
            )
        } else {
            SubcomposeAsyncImageContent()
        }
    }
}