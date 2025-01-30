package com.cybozu.android.callout.compose.core.data

public sealed interface CalloutAlignment {
    public sealed class Vertical : CalloutAlignment {
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

    public sealed class Horizontal : CalloutAlignment {
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

internal sealed interface CalloutAlignmentContext {
    val vertical: CalloutAlignment.Vertical
    val horizontal: CalloutAlignment.Horizontal

    data class VerticalOver(
        override val vertical: CalloutAlignment.Vertical.Over,
        override val horizontal: CalloutAlignment.Horizontal.Inner,
    ) : CalloutAlignmentContext

    data class HorizontalOver(
        override val vertical: CalloutAlignment.Vertical.Inner,
        override val horizontal: CalloutAlignment.Horizontal.Over,
    ) : CalloutAlignmentContext
}

internal fun CalloutAlignmentContext(
    vertical: CalloutAlignment.Vertical.Over,
    horizontal: CalloutAlignment.Horizontal.Inner,
): CalloutAlignmentContext = CalloutAlignmentContext.VerticalOver(
    vertical = vertical,
    horizontal = horizontal
)

internal fun CalloutAlignmentContext(
    vertical: CalloutAlignment.Vertical.Inner,
    horizontal: CalloutAlignment.Horizontal.Over,
): CalloutAlignmentContext = CalloutAlignmentContext.HorizontalOver(
    vertical = vertical,
    horizontal = horizontal
)
