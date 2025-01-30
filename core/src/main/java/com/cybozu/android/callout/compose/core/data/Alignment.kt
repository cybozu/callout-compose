package com.cybozu.android.callout.compose.core.data

public sealed interface Alignment {
    public sealed class Vertical : Alignment {
        public sealed class Inner : Vertical()
        public sealed class Over : Vertical()

        public data object Top : Inner() {
            public fun over(): Over = TopOver
        }

        public data object Center : Inner()
        public data object Bottom : Inner() {
            public fun over(): Over = BottomOver
        }

        internal data object TopOver : Over()
        internal data object BottomOver : Over()
    }

    public sealed class Horizontal : Alignment {
        public sealed class Inner : Horizontal()
        public sealed class Over : Horizontal()

        public data object Start : Inner() {
            public fun over(): Over = StartOver
        }

        public data object Center : Inner()
        public data object End : Inner() {
            public fun over(): Over = EndOver
        }

        internal data object StartOver : Over()
        internal data object EndOver : Over()
    }
}

internal sealed interface AlignmentContext {
    val vertical: Alignment.Vertical
    val horizontal: Alignment.Horizontal

    data class VerticalOver(
        override val vertical: Alignment.Vertical.Over,
        override val horizontal: Alignment.Horizontal.Inner,
    ) : AlignmentContext

    data class HorizontalOver(
        override val vertical: Alignment.Vertical.Inner,
        override val horizontal: Alignment.Horizontal.Over,
    ) : AlignmentContext
}

internal fun AlignmentContext(
    vertical: Alignment.Vertical.Over,
    horizontal: Alignment.Horizontal.Inner,
): AlignmentContext = AlignmentContext.VerticalOver(
    vertical = vertical,
    horizontal = horizontal
)

internal fun AlignmentContext(
    vertical: Alignment.Vertical.Inner,
    horizontal: Alignment.Horizontal.Over,
): AlignmentContext = AlignmentContext.HorizontalOver(
    vertical = vertical,
    horizontal = horizontal
)
