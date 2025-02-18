/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.wear.compose.material3.test

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.testutils.assertAgainstGolden
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.filters.SdkSuppress
import androidx.test.screenshot.AndroidXScreenshotTestRule
import androidx.wear.compose.material3.FilledIconButton
import androidx.wear.compose.material3.FilledTonalIconButton
import androidx.wear.compose.material3.Icon
import androidx.wear.compose.material3.IconButton
import androidx.wear.compose.material3.IconButtonDefaults
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.OutlinedIconButton
import androidx.wear.compose.material3.SCREENSHOT_GOLDEN_PATH
import androidx.wear.compose.material3.TEST_TAG
import androidx.wear.compose.material3.setContentWithTheme
import androidx.wear.compose.material3.touchTargetAwareSize
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = Build.VERSION_CODES.O)
class IconButtonScreenshotTest {

    @get:Rule
    val rule = createComposeRule()

    @get:Rule
    val screenshotRule = AndroidXScreenshotTestRule(SCREENSHOT_GOLDEN_PATH)

    @get:Rule
    val testName = TestName()

    @Test
    fun filled_icon_button_enabled() = verifyScreenshot {
        sampleFilledIconButton(enabled = true, isCompact = false)
    }

    @Test
    fun filled_icon_button_disabled() = verifyScreenshot("icon_button_disabled") {
        sampleFilledIconButton(enabled = false, isCompact = false)
    }

    @Test
    fun filled_tonal_icon_button_enabled() = verifyScreenshot {
        sampleFilledTonalIconButton(enabled = true, isCompact = false)
    }

    @Test
    fun filled_tonal_icon_button_disabled() = verifyScreenshot("icon_button_disabled") {
        sampleFilledTonalIconButton(enabled = false, isCompact = false)
    }

    @Test
    fun outlined_icon_button_enabled() = verifyScreenshot {
        sampleOutlinedIconButton(enabled = true, isCompact = false)
    }

    @Test
    fun outlined_icon_button_disabled() = verifyScreenshot {
        sampleOutlinedIconButton(enabled = false, isCompact = false)
    }

    @Test
    fun icon_button_enabled() = verifyScreenshot {
        sampleIconButton(enabled = true, isCompact = false)
    }

    @Test
    fun icon_button_disabled() = verifyScreenshot {
        sampleIconButton(enabled = false, isCompact = false)
    }

    @Test
    fun filled_compact_icon_button_enabled() = verifyScreenshot {
        sampleFilledIconButton(enabled = true, isCompact = true)
    }

    @Test
    fun filled_compact_icon_button_disabled() = verifyScreenshot("compact_icon_button_disabled") {
        sampleFilledIconButton(enabled = false, isCompact = true)
    }

    @Test
    fun filled_tonal_compact_icon_button_enabled() = verifyScreenshot {
        sampleFilledTonalIconButton(enabled = true, isCompact = true)
    }

    @Test
    fun filled_tonal_compact_icon_button_disabled() =
        verifyScreenshot("compact_icon_button_disabled") {
            sampleFilledTonalIconButton(enabled = false, isCompact = true)
        }

    @Test
    fun outlined_compact_icon_button_enabled() = verifyScreenshot {
        sampleOutlinedIconButton(enabled = true, isCompact = true)
    }

    @Test
    fun outlined_compact_icon_button_disabled() = verifyScreenshot {
        sampleOutlinedIconButton(enabled = false, isCompact = true)
    }

    @Test
    fun compact_icon_button_enabled() = verifyScreenshot {
        sampleIconButton(enabled = true, isCompact = true)
    }

    @Test
    fun compact_icon_button_disabled() = verifyScreenshot {
        sampleIconButton(enabled = false, isCompact = true)
    }

    @Composable
    private fun sampleFilledIconButton(enabled: Boolean, isCompact: Boolean) {
        FilledIconButton(
            onClick = {}, enabled = enabled, modifier = Modifier
                .testTag(TEST_TAG)
                .then(
                    if (isCompact)
                        Modifier.touchTargetAwareSize(IconButtonDefaults.ExtraSmallButtonSize)
                    else Modifier
                )
        ) {
            Icon(
                imageVector = Icons.Outlined.Home,
                contentDescription = "Home",
                modifier = if (isCompact) Modifier.size(
                    IconButtonDefaults.iconSizeFor(IconButtonDefaults.SmallIconSize)
                )
                else Modifier
            )
        }
    }

    @Composable
    private fun sampleFilledTonalIconButton(enabled: Boolean, isCompact: Boolean) {
        FilledTonalIconButton(
            onClick = {}, enabled = enabled, modifier = Modifier
                .testTag(TEST_TAG)
                .then(
                    if (isCompact)
                        Modifier.touchTargetAwareSize(IconButtonDefaults.ExtraSmallButtonSize)
                    else Modifier
                )
        ) {
            Icon(
                imageVector = Icons.Outlined.Home,
                contentDescription = "Home",
                modifier = if (isCompact) Modifier.size(
                    IconButtonDefaults.iconSizeFor(IconButtonDefaults.SmallIconSize)
                )
                else Modifier
            )
        }
    }

    @Composable
    private fun sampleOutlinedIconButton(enabled: Boolean, isCompact: Boolean) {
        OutlinedIconButton(
            onClick = {}, enabled = enabled, modifier = Modifier
                .testTag(TEST_TAG)
                .then(
                    if (isCompact)
                        Modifier.touchTargetAwareSize(IconButtonDefaults.ExtraSmallButtonSize)
                    else Modifier
                )
        ) {
            Icon(
                imageVector = Icons.Outlined.Home,
                contentDescription = "Home",
                modifier = if (isCompact) Modifier.size(
                    IconButtonDefaults.iconSizeFor(IconButtonDefaults.SmallIconSize)
                )
                else Modifier
            )
        }
    }

    @Composable
    private fun sampleIconButton(enabled: Boolean, isCompact: Boolean) {
        IconButton(
            onClick = {}, enabled = enabled, modifier = Modifier
                .testTag(TEST_TAG)
                .then(
                    if (isCompact)
                        Modifier.touchTargetAwareSize(IconButtonDefaults.ExtraSmallButtonSize)
                    else Modifier
                )
        ) {
            Icon(
                imageVector = Icons.Outlined.Home,
                contentDescription = "Home",
                modifier = if (isCompact) Modifier.size(
                    IconButtonDefaults.iconSizeFor(IconButtonDefaults.SmallIconSize)
                )
                else Modifier
            )
        }
    }

    private fun verifyScreenshot(
        methodName: String = testName.methodName,
        content: @Composable () -> Unit
    ) {
        rule.setContentWithTheme {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                content()
            }
        }

        rule.onNodeWithTag(TEST_TAG).captureToImage()
            .assertAgainstGolden(screenshotRule, methodName)
    }
}
