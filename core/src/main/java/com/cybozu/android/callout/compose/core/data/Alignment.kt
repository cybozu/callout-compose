package com.cybozu.android.callout.compose.core.data

public sealed interface Alignment

public sealed interface VerticalAlignment : Alignment {
    public data object Top : VerticalAlignment
    public data object Center : VerticalAlignment
    public data object Bottom : VerticalAlignment
}

public sealed interface HorizontalAlignment : Alignment {
    public data class Start(val isOver: Boolean = false) : HorizontalAlignment {
        public fun over(): Start = copy(isOver = true)
    }

    public data object Center : HorizontalAlignment
    public data class End(val isOver: Boolean = false) : HorizontalAlignment {
        public fun over(): End = copy(isOver = true)
    }
}

internal data class AlignmentContext(
    val vertical: VerticalAlignment,
    val horizontal: HorizontalAlignment,
)
