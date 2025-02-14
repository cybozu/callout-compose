## callout-compose
callout-compose is a Jetpack Compose library that allows you to easily display callout

## Requirements
 - minSdk version 27

## Usage
Using callout-compose, you can display callouts anywhere you want.

## Including in your project

### Gradle

#### material2
TODO

#### material3
TODO

### How to Use

#### Create callout state

```kotlin
val calloutState = rememberCalloutState()
```

#### Call Modifier.anchoredCallout to attach a callout to any Composable.

```kotlin
Text(
    text = "Anchored Text",
    modifier = Modifier
        .anchoredCallout(
            state = calloutState
        )
)
```

#### Wrap the Composable you want to display in the callout with Callout

```kotlin
Callout(
    calloutState = calloutState,
    verticalAlignment = CalloutAlignment.Vertical.Top,
    horizontalAlignment = CalloutAlignment.Horizontal.End.outer()
) {
    Text(text = "Hello, Callout!")
}
```

You can choose the following combinations for verticalAlignment and horizontalAlignment:

VerticalAlignment is divided into Inner and Outer:

 - Inner: Top, Center, Bottom
 - Outer: Top.outer(), Bottom.outer()

HorizontalAlignment is divided into Inner and Outer:

 - Inner: Start, Center, End
 - Outer: Start.outer(), End.outer()


The combinations of VerticalAlignment and HorizontalAlignment can be specified as:
 - Inner × Outer
 - Outer × Inner"

#### Show up Callout
```kotlin
LaunchedEffect(Unit) {
    calloutState.show()
}
```

#### Complete Example
```kotlin

val calloutState = rememberCalloutState()

Text(
    text = "Anchored Text",
    modifier = Modifier
        .anchoredCallout(
            state = calloutState
        )
)

Callout(
    calloutState = calloutState,
    verticalAlignment = CalloutAlignment.Vertical.Top,
    horizontalAlignment = CalloutAlignment.Horizontal.End.outer()
) {
    Text(text = "Hello, Callout!")
}

LaunchedEffect(Unit) {
    calloutState.show()
}
```

### License
[License](https://github.com/cybozu/callout-compose/blob/main/LICENSE)
