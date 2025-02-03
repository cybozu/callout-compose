package com.cybozu.android.callout.compose.core.data

public sealed interface CalloutAlignment {
    public sealed class Vertical : CalloutAlignment {
        public sealed class Inner : Vertical()
        public sealed class Outer : Vertical()

        public data object Top : Inner() {
            public fun outer(): Outer = TopOuter
        }

        public data object Center : Inner()
        public data object Bottom : Inner() {
            public fun outer(): Outer = BottomOuter
        }

        internal data object TopOuter : Outer()
        internal data object BottomOuter : Outer()
    }

    public sealed class Horizontal : CalloutAlignment {
        public sealed class Inner : Horizontal()
        public sealed class Outer : Horizontal()

        public data object Start : Inner() {
            public fun outer(): Outer = StartOuter
        }

        public data object Center : Inner()
        public data object End : Inner() {
            public fun outer(): Outer = EndOuter
        }

        internal data object StartOuter : Outer()
        internal data object EndOuter : Outer()
    }
}

internal sealed interface CalloutAlignmentContext {
    val vertical: CalloutAlignment.Vertical
    val horizontal: CalloutAlignment.Horizontal

    data class VerticalOuter(
        override val vertical: CalloutAlignment.Vertical.Outer,
        override val horizontal: CalloutAlignment.Horizontal.Inner,
    ) : CalloutAlignmentContext

    data class HorizontalOuter(
        override val vertical: CalloutAlignment.Vertical.Inner,
        override val horizontal: CalloutAlignment.Horizontal.Outer,
    ) : CalloutAlignmentContext
}

internal fun CalloutAlignmentContext(
    vertical: CalloutAlignment.Vertical.Outer,
    horizontal: CalloutAlignment.Horizontal.Inner,
): CalloutAlignmentContext = CalloutAlignmentContext.VerticalOuter(
    vertical = vertical,
    horizontal = horizontal
)

internal fun CalloutAlignmentContext(
    vertical: CalloutAlignment.Vertical.Inner,
    horizontal: CalloutAlignment.Horizontal.Outer,
): CalloutAlignmentContext = CalloutAlignmentContext.HorizontalOuter(
    vertical = vertical,
    horizontal = horizontal
)
