package com.github.itisme0402.happybirthday

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.github.itisme0402.happybirthday.BirthdayGreetingTheme.ELEPHANT
import com.github.itisme0402.happybirthday.BirthdayGreetingTheme.FOX
import com.github.itisme0402.happybirthday.BirthdayGreetingTheme.PELICAN
import happybirthday.composeapp.generated.resources.Res
import happybirthday.composeapp.generated.resources.bg_elephant
import happybirthday.composeapp.generated.resources.bg_fox
import happybirthday.composeapp.generated.resources.bg_pelican
import happybirthday.composeapp.generated.resources.elephant_avatar_placeholder
import happybirthday.composeapp.generated.resources.fox_avatar_placeholder
import happybirthday.composeapp.generated.resources.greeting_beginning
import happybirthday.composeapp.generated.resources.ic_share
import happybirthday.composeapp.generated.resources.left_swirls
import happybirthday.composeapp.generated.resources.months_old
import happybirthday.composeapp.generated.resources.n1
import happybirthday.composeapp.generated.resources.n10
import happybirthday.composeapp.generated.resources.n11
import happybirthday.composeapp.generated.resources.n12
import happybirthday.composeapp.generated.resources.n2
import happybirthday.composeapp.generated.resources.n3
import happybirthday.composeapp.generated.resources.n4
import happybirthday.composeapp.generated.resources.n5
import happybirthday.composeapp.generated.resources.n6
import happybirthday.composeapp.generated.resources.n7
import happybirthday.composeapp.generated.resources.n8
import happybirthday.composeapp.generated.resources.n9
import happybirthday.composeapp.generated.resources.nanit_logo
import happybirthday.composeapp.generated.resources.pelican_avatar_placeholder
import happybirthday.composeapp.generated.resources.right_swirls
import happybirthday.composeapp.generated.resources.share_the_news
import happybirthday.composeapp.generated.resources.years_old
import io.github.aakira.napier.Napier
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun HappyBirthdayPreview(
) {
    HappyBirthdayScreen(
        onGoBack = {},
        birthdayInfo = BirthdayInfo(
            name = "Christiano Ronaldo",
            date = Clock.System.now()
                .toLocalDateTime(TimeZone.currentSystemDefault())
                .date
                .minus(DatePeriod(years = 3)),
            theme = PELICAN,
        ),
    )
}

@Composable
fun HappyBirthdayScreen(
    onGoBack: () -> Unit,
    birthdayInfo: BirthdayInfo,
) {
    val (ageCount, ageConclusionResId) = remember {
        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        with(Res.plurals) {
            val diff = currentDate - birthdayInfo.date
            if (diff.years < 1) {
                diff.months to months_old
            } else {
                diff.years to years_old
            }
        }
    }

    HappyBirthdayScreen(
        onGoBack = onGoBack,
        backgroundColor = when (birthdayInfo.theme) {
            PELICAN -> Colors.BackgroundPelican
            FOX -> Colors.BackgroundFox
            ELEPHANT -> Colors.BackgroundElephant
        },
        avatarPlaceholder = when (birthdayInfo.theme) {
            PELICAN -> Res.drawable.pelican_avatar_placeholder
            FOX -> Res.drawable.fox_avatar_placeholder
            ELEPHANT -> Res.drawable.elephant_avatar_placeholder
        },
        bgImage = when (birthdayInfo.theme) {
            PELICAN -> Res.drawable.bg_pelican
            FOX -> Res.drawable.bg_fox
            ELEPHANT -> Res.drawable.bg_elephant
        },
        name = birthdayInfo.name,
        ageCount = ageCount,
        ageConclusion = pluralStringResource(ageConclusionResId, ageCount),
    )
}

@Composable
fun HappyBirthdayScreen(
    onGoBack: () -> Unit,
    backgroundColor: Color,
    avatarPlaceholder: DrawableResource,
    bgImage: DrawableResource,
    name: String,
    ageCount: Int,
    ageConclusion: String,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        val (
            backgroundImage,
            backButton,
            greetingBeginningText,
            ageCountImage,
            ageConclusionText,
            numberTopMargin,
            numberBottomMargin,
            leftSwirls,
            rightSwirls,
            prettyPic,
            logo,
            shareButton,
        ) = createRefs()

        Image(
            painter = painterResource(bgImage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(backgroundImage) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxSize()
                .zIndex(2f),
        )

        IconButton(
            onClick = onGoBack,
            modifier = Modifier
                .constrainAs(backButton) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .zIndex(2f),
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
            )
        }

        val topContentChain = createVerticalChain(
            greetingBeginningText,
            numberTopMargin,
            ageCountImage,
            numberBottomMargin,
            ageConclusionText,
            chainStyle = ChainStyle.Packed,
        )
        constrain(topContentChain) {
            top.linkTo(parent.top, margin = 20.dp)
            bottom.linkTo(prettyPic.top, margin = 20.dp)
        }

        Text(
            text = stringResource(Res.string.greeting_beginning, name).toUpperCase(Locale.current),
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .constrainAs(greetingBeginningText) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .zIndex(2f),
        )

        Spacer(
            modifier = Modifier
                .constrainAs(numberTopMargin) {
                    top.linkTo(greetingBeginningText.bottom)
                    bottom.linkTo(ageCountImage.top)
                }
                .height(13.dp),
        )

        Image(
            painter = painterResource(Res.drawable.left_swirls),
            contentDescription = null,
            modifier = Modifier.constrainAs(leftSwirls) {
                top.linkTo(ageCountImage.top)
                bottom.linkTo(ageCountImage.bottom)
                absoluteRight.linkTo(ageCountImage.absoluteLeft, margin = 22.dp)
            },
        )

        Image(
            painter = painterResource(
                with(Res.drawable) {
                    when (ageCount) {
                        1 -> n1
                        2 -> n2
                        3 -> n3
                        4 -> n4
                        5 -> n5
                        6 -> n6
                        7 -> n7
                        8 -> n8
                        9 -> n9
                        10 -> n10
                        11 -> n11
                        12 -> n12
                        else -> {
                            Napier.e("Rendering invalid age count: $ageCount")
                            n12
                        }
                    }
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(ageCountImage) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .zIndex(2f),
        )

        Image(
            painter = painterResource(Res.drawable.right_swirls),
            contentDescription = null,
            modifier = Modifier.constrainAs(rightSwirls) {
                top.linkTo(ageCountImage.top)
                bottom.linkTo(ageCountImage.bottom)
                absoluteLeft.linkTo(ageCountImage.absoluteRight, margin = 22.dp)
            },
        )

        Spacer(
            modifier = Modifier
                .constrainAs(numberBottomMargin) {
                }
                .height(14.dp),
        )

        Text(
            text = ageConclusion.toUpperCase(Locale.current),
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .constrainAs(ageConclusionText) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .zIndex(2f),
        )

        Image(
            painter = painterResource(avatarPlaceholder),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(prettyPic) {
                    width = Dimension.fillToConstraints
                    height = Dimension.ratio("1:1")
                    bottom.linkTo(logo.top, margin = 15.dp)
                    start.linkTo(parent.start, margin = 50.dp)
                    end.linkTo(parent.end, margin = 50.dp)
                    verticalBias = 1f
                }
                .zIndex(1f),
        )

        Image(
            painter = painterResource(Res.drawable.nanit_logo),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(logo) {
                    bottom.linkTo(shareButton.top, margin = 53.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .zIndex(2f),
        )

        Button(
            onClick = { /* TODO */ },
            elevation = null,
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .constrainAs(shareButton) {
                    bottom.linkTo(parent.bottom, margin = 53.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .zIndex(2f),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(stringResource(Res.string.share_the_news))
                Icon(painter = painterResource(Res.drawable.ic_share), contentDescription = null)
            }
        }
    }
}
