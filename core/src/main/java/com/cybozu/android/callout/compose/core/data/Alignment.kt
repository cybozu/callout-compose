package com.cybozu.android.callout.compose.core.data

public sealed interface Alignment

public sealed interface VerticalAlignment : Alignment {
    public sealed interface Over : VerticalAlignment

    public data object Top : VerticalAlignment
    public data object TopOver : VerticalAlignment, Over
    public data object Center : VerticalAlignment
    public data object Bottom : VerticalAlignment
    public data object BottomOver : VerticalAlignment, Over
}

public sealed interface HorizontalAlignment : Alignment {
    public sealed interface Over : HorizontalAlignment

    public data object Start : HorizontalAlignment, Over
    public data object StartOver : HorizontalAlignment
    public data object Center : HorizontalAlignment
    public data object End : HorizontalAlignment
    public data object EndOver : HorizontalAlignment, Over
}

internal data class AlignmentContext private constructor(
    val vertical: VerticalAlignment,
    val horizontal: HorizontalAlignment,
) {
    public constructor(
        vertical: VerticalAlignment,
        horizontalOver: HorizontalAlignment.Over,
    ) : this(
        vertical = vertical,
        horizontal = horizontalOver
    )

    public constructor(
        verticalOver: VerticalAlignment.Over,
        horizontal: HorizontalAlignment,
    ) : this(
        vertical = verticalOver,
        horizontal = horizontal
    )
}
