# Onboarding to Compose for TV libraries

## Getting Started

1. See what Google suggests are [good design patterns][good-design-patterns]
   and [offered components][tv-components]
2. Consult the documentation for information on the packages and various components that are
   offered.
    * [tv-foundation][tv-foundation]
    * [tv-foundation-lazy-list][tv-foundation-lazy-list]
    * [tv-foundation-lazy-grid][tv-foundation-lazy-grid]
    * [tv-material][tv-material]
3. Read documentation and examples on [developer.android.com][dac]
4. Read up on the [codelabs][codelabs]
5. Ensure that you are on the latest version of [Compose for TV libraries][compose-for-tv-libraries]
   and Jetpack compose.
6. Read the FAQs below
7. Check with the community
    * [stack overflow][stackoverflow]
    * slack (TBD)
    * discord (TBD)
8. Check if there is a bug already reported on [issue-tracker][issue-tracker]
9. [File a bug on issue-tracker][issue-tracker-file-a-bug]
10. Contact a Developer Relations partner from Google who can involve someone from the engineering
    team as necessary

## FAQs

1. How can I improve the performance of my app written using tv-compose?
    * Any [performance improvements][improve-performance] suggested for a Compose app would
      generally apply to apps built with Compose for TV libraries too.
    * Use [baseline profiles][baseline-profiles] as recommended
      in [Jetpack Compose Performance guide][jetpack-compose-performance].
      Watch [Making apps blazing fast with Baseline Profiles][making-apps-blazing-fast-with-baseline-profiles]
    * Checkout [Interpreting Compose Compiler Metrics][interpreting-compose-compiler-metrics].
2. My app is crashing!
    * Ensure that you are on the latest alpha version
      of [Compose for TV libraries][compose-for-tv-libraries] and Jetpack Compose
    * Check if there is a bug already reported on [issue-tracker][issue-tracker]
    * [File a bug on issue-tracker][issue-tracker-file-a-bug]
3. The Navigation drawer is pushing my content aside. I don’t like it.
   Consider using a [Modal Navigation Drawer][modal-navigation-drawer] provided
   in [Compose for TV library][compose-for-tv-modal-navigation-drawer]

[good-design-patterns]: https://developer.android.com/design/ui/tv

[dac]: https://developer.android.com/training/tv/playback/compose

[modal-navigation-drawer]: https://m3.material.io/components/navigation-drawer/overview#15a3aa10-1be4-4be4-8370-36a1779f65e5

[compose-for-tv-modal-navigation-drawer]: https://developer.android.com/reference/kotlin/androidx/tv/material3/package-summary#ModalNavigationDrawer(kotlin.Function1,androidx.compose.ui.Modifier,androidx.tv.material3.DrawerState,androidx.compose.ui.graphics.Color,kotlin.Function0)

[jetpack-compose-performance]: https://developer.android.com/jetpack/compose/performance

[improve-performance]: https://developer.android.com/topic/performance/improving-overview

[baseline-profiles]: https://developer.android.com/topic/performance/baselineprofiles/overview

[making-apps-blazing-fast-with-baseline-profiles]: https://youtu.be/yJm5On5Gp4c

[interpreting-compose-compiler-metrics]: https://github.com/androidx/androidx/blob/androidx-main/compose/compiler/design/compiler-metrics.md

[tv-components]: https://developer.android.com/design/ui/tv/guides/components

[codelabs]: https://developer.android.com/codelabs/compose-for-tv-introduction

[stackoverflow]: https://stackoverflow.com/tags/android-jetpack-compose-tv/info

[issue-tracker]: https://issuetracker.google.com/issues?q=componentid:1254578%20status:open

[issue-tracker-file-a-bug]: https://issuetracker.google.com/issues/new?component=1254578&template=1739419

[compose-for-tv-libraries]: https://developer.android.com/jetpack/androidx/releases/tv

[tv-foundation]: https://developer.android.com/reference/kotlin/androidx/tv/foundation/package-summary.html

[tv-foundation-lazy-list]: https://developer.android.com/reference/kotlin/androidx/tv/foundation/lazy/list/package-summary

[tv-foundation-lazy-grid]: https://developer.android.com/reference/kotlin/androidx/tv/foundation/lazy/grid/package-summary

[tv-material]: https://developer.android.com/reference/kotlin/androidx/tv/material3/package-summary
