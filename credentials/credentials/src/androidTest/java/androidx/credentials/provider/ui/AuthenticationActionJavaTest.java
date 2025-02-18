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

package androidx.credentials.provider.ui;

import static com.google.common.truth.Truth.assertThat;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import android.app.PendingIntent;
import android.app.slice.Slice;
import android.content.Context;
import android.content.Intent;

import androidx.core.os.BuildCompat;
import androidx.credentials.provider.AuthenticationAction;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SdkSuppress;
import androidx.test.filters.SmallTest;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SmallTest
@SdkSuppress(minSdkVersion = 34, codeName = "UpsideDownCake")
public class AuthenticationActionJavaTest {
    private static final CharSequence TITLE = "title";
    private final Context mContext = ApplicationProvider.getApplicationContext();
    private final Intent mIntent = new Intent();
    private final PendingIntent mPendingIntent =
            PendingIntent.getActivity(mContext, 0, mIntent, PendingIntent.FLAG_IMMUTABLE);

    @Test
    public void constructor_success() {
        if (!BuildCompat.isAtLeastU()) {
            return;
        }
        AuthenticationAction action = new AuthenticationAction(TITLE, mPendingIntent);

        assertThat(mPendingIntent == action.getPendingIntent());
    }

    @Test
    public void build_success() {
        if (!BuildCompat.isAtLeastU()) {
            return;
        }
        AuthenticationAction action =
                new AuthenticationAction.Builder(TITLE, mPendingIntent).build();

        assertThat(mPendingIntent == action.getPendingIntent());
    }

    @Test
    public void constructor_nullPendingIntent_throwsNPE() {
        if (!BuildCompat.isAtLeastU()) {
            return;
        }
        assertThrows("Expected null pending intent to throw NPE",
                NullPointerException.class,
                () -> new AuthenticationAction(TITLE, null));
    }

    @Test
    public void constructor_emptyTitle_throwsIllegalArgumentException() {
        if (!BuildCompat.isAtLeastU()) {
            return;
        }
        assertThrows("Expected empty title to throw IAE",
                IllegalArgumentException.class,
                () -> new AuthenticationAction("", mPendingIntent));
    }

    @Test
    public void fromSlice_success() {
        if (!BuildCompat.isAtLeastU()) {
            return;
        }
        AuthenticationAction originalAction = new AuthenticationAction(TITLE, mPendingIntent);
        Slice slice = AuthenticationAction.toSlice(originalAction);

        AuthenticationAction fromSlice = AuthenticationAction.fromSlice(slice);

        assertNotNull(fromSlice);
        assertThat(fromSlice.getPendingIntent()).isEqualTo(mPendingIntent);
    }
}
