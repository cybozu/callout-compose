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

## How to Use

### Create callout state

```kotlin
val calloutState = rememberCalloutState()
```

### Call Modifier.anchoredCallout to attach a callout to any Composable.

```kotlin
Text(
    text = "Anchored Text",
    modifier = Modifier
        .anchoredCallout(
            state = calloutState
        )
)
```

### Wrap the Composable you want to display in the callout with Callout

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


The available combinations of VerticalAlignment and HorizontalAlignment is:
 - Inner（Vertical） × Outer（Horizontal）
 - Outer（Vertical） × Inner（Horizontal）

### Show up Callout
```kotlin
LaunchedEffect(Unit) {
    calloutState.show()
}
```

### Complete Example
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

## Demo

This repository includes demonstration app.

if you use material2, open [sample-app-material2](https://github.com/cybozu/callout-compose/tree/main/sample-app-material2) and Run it.

if you use material3, open [sample-app-material3](https://github.com/cybozu/callout-compose/tree/main/sample-app-material3) and Run it.

## License
[License](https://github.com/cybozu/callout-compose/blob/main/LICENSE)
