package com.cybozu.android.callout.compose.core.data

public sealed interface Alignment

public sealed interface VerticalAlignment : Alignment {
    public sealed interface Inner : VerticalAlignment
    public sealed interface Over : VerticalAlignment

    public data object Top : Inner
    public data object TopOver : Over
    public data object Center : Inner
    public data object Bottom : Inner
    public data object BottomOver : Over
}

public sealed interface HorizontalAlignment : Alignment {
    public sealed interface Inner : HorizontalAlignment
    public sealed interface Over : HorizontalAlignment

    public data object Start : Inner
    public data object StartOver : Over
    public data object Center : Inner
    public data object End : Inner
    public data object EndOver : Over
}

internal sealed interface AlignmentContext {
    val vertical: VerticalAlignment
    val horizontal: HorizontalAlignment

    data class VerticalOver(
        override val vertical: VerticalAlignment.Over,
        override val horizontal: HorizontalAlignment.Inner,
    ) : AlignmentContext

    data class HorizontalOver(
        override val vertical: VerticalAlignment.Inner,
        override val horizontal: HorizontalAlignment.Over,
    ) : AlignmentContext
}

internal fun AlignmentContext(
    vertical: VerticalAlignment.Over,
    horizontal: HorizontalAlignment.Inner,
): AlignmentContext = AlignmentContext.VerticalOver(
    vertical = vertical,
    horizontal = horizontal
)

internal fun AlignmentContext(
    vertical: VerticalAlignment.Inner,
    horizontal: HorizontalAlignment.Over,
): AlignmentContext = AlignmentContext.HorizontalOver(
    vertical = vertical,
    horizontal = horizontal
)
