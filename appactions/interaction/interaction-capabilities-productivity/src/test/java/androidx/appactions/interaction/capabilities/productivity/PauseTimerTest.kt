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

package androidx.appactions.interaction.capabilities.productivity

import android.util.SizeF
import androidx.appactions.builtintypes.types.GenericErrorStatus
import androidx.appactions.builtintypes.types.Timer
import androidx.appactions.interaction.capabilities.core.ExecutionCallback
import androidx.appactions.interaction.capabilities.core.ExecutionResult
import androidx.appactions.interaction.capabilities.core.HostProperties
import androidx.appactions.interaction.capabilities.core.properties.Property
import androidx.appactions.interaction.capabilities.productivity.PauseTimer.Arguments
import androidx.appactions.interaction.capabilities.productivity.PauseTimer.Output
import androidx.appactions.interaction.capabilities.testing.internal.ArgumentUtils
import androidx.appactions.interaction.capabilities.testing.internal.FakeCallbackInternal
import androidx.appactions.interaction.capabilities.testing.internal.TestingUtils.awaitSync
import androidx.appactions.interaction.proto.AppActionsContext.AppAction
import androidx.appactions.interaction.proto.AppActionsContext.IntentParameter
import androidx.appactions.interaction.proto.FulfillmentResponse
import androidx.appactions.interaction.proto.FulfillmentResponse.StructuredOutput
import androidx.appactions.interaction.proto.FulfillmentResponse.StructuredOutput.OutputValue
import androidx.appactions.interaction.proto.ParamValue
import androidx.appactions.interaction.proto.TaskInfo
import androidx.appactions.interaction.protobuf.Struct
import androidx.appactions.interaction.protobuf.Value
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.CompletableDeferred
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PauseTimerTest {
    private val hostProperties =
        HostProperties.Builder().setMaxHostSizeDp(SizeF(300f, 500f)).build()

    @Test
    fun createCapability_expectedResult() {
        val argsDeferred = CompletableDeferred<Arguments>()
        val capability = PauseTimer.CapabilityBuilder()
            .setId("pause timer")
            .setTimerProperty(Property<Timer>(isRequiredForExecution = true))
            .setExecutionCallback(
                ExecutionCallback {
                    argsDeferred.complete(it)
                    ExecutionResult.Builder<Output>().setOutput(
                        Output.Builder().setExecutionStatus(
                            GenericErrorStatus.Builder().build()
                        ).build()
                    ).build()
                })
            .build()
        val capabilitySession = capability.createSession("fakeSessionId", hostProperties)
        val args: MutableMap<String, ParamValue> = mutableMapOf(
            "timer" to ParamValue.newBuilder().setStructValue(
                Struct.newBuilder()
                    .putFields("@type", Value.newBuilder().setStringValue("Timer").build())
                    .putFields("identifier", Value.newBuilder().setStringValue("abc").build())
            )
                .build()
        )
        val callback = FakeCallbackInternal()
        capabilitySession.execute(ArgumentUtils.buildArgs(args), callback)

        assertThat(capability.appAction)
            .isEqualTo(
                AppAction.newBuilder()
                    .setIdentifier("pause timer")
                    .setName("actions.intent.PAUSE_TIMER")
                    .addParams(IntentParameter.newBuilder().setName("timer").setIsRequired(true))
                    .setTaskInfo(TaskInfo.getDefaultInstance())
                    .build()
            )
        assertThat(argsDeferred.awaitSync())
            .isEqualTo(
                Arguments.Builder()
                    .setTimerList(
                        listOf(TimerReference(Timer.Builder().setIdentifier("abc").build()))
                    )
                    .build()
            )
        assertThat(callback.receiveResponse().fulfillmentResponse).isEqualTo(
            FulfillmentResponse.newBuilder().setExecutionOutput(
                StructuredOutput.newBuilder().addOutputValues(
                    OutputValue.newBuilder().setName("executionStatus").addValues(
                        ParamValue.newBuilder().setStructValue(
                            Struct.newBuilder().putFields(
                                "@type",
                                Value.newBuilder().setStringValue("GenericErrorStatus").build()
                            )
                        )
                    )
                )
            ).build()
        )
    }
}