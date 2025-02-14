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

## Preview
<table style="width: 100%; table-layout: fixed;">
    <tr>
        <th style="width: 16.6%"></th>
        <th style="width: 16.6%">Top</th>
        <th style="width: 16.6%">Center</th>
        <th style="width: 16.6%">Bottom</th>
        <th style="width: 16.6%">Top.outer()</th>
        <th style="width: 16.6%">Bottom.outer()</th>
    </tr>
    <tr>
        <td>Start</td>
        <td>×</td>
        <td>×</td>
        <td>×</td>
        <td><img src="https://github.com/user-attachments/assets/dad889c9-74bb-4951-93e6-2e728765ced5" width="150"/></td>
        <td><img src="https://github.com/user-attachments/assets/64235d7d-1672-4335-99ef-3a9ca2749c83" width="150"/></td>
    </tr>
    <tr>
        <td>Center</td>
        <td>×</td>
        <td>×</td>
        <td>×</td>
        <td><img src="https://github.com/user-attachments/assets/fc370c4b-2885-4bb8-87c2-2e58372147aa" width="150"/></td>
        <td><img src="https://github.com/user-attachments/assets/e695aa94-138b-4212-a8fe-14a3fe8971d2" width="150"/></td>
    </tr>
    <tr>
        <td>End</td>
        <td>×</td>
        <td>×</td>
        <td>×</td>
        <td><img src="https://github.com/user-attachments/assets/10a8b348-7ef7-4c50-8042-0522cdbda226" width="150"/></td>
        <td><img src="https://github.com/user-attachments/assets/315fe883-8b54-47d0-964e-7dc1b8814d54" width="150"/></td>
    </tr>
    <tr>
        <td>Start.outer()</td>
        <td><img src="https://github.com/user-attachments/assets/b89f8978-d4e5-44b0-8cbf-e3378c1b0c56" width="150"/></td>
        <td><img src="https://github.com/user-attachments/assets/f247abe8-5313-4cc7-9873-4a76c6b5f5f6" width="150"/></td>
        <td><img src="https://github.com/user-attachments/assets/d8921dde-7be6-4361-a7ae-741c099606a9" width="150"/></td>
        <td>×</td>
        <td>×</td>
    </tr>
    <tr>
        <td>End.outer()</td>
        <td><img src="https://github.com/user-attachments/assets/38ae9211-0e2d-4c95-ad89-305fe0c2cb86" width="150"/></td>
        <td><img src="https://github.com/user-attachments/assets/58c1001b-76fd-4cab-8c18-cbca32d0a588" width="150"/></td>
        <td><img src="https://github.com/user-attachments/assets/24a62935-dd08-41a7-9538-cac98f97d7a2" width="150"/></td>
        <td>×</td>
        <td>×</td>
    </tr>
</table>

## Demo

This repository includes demonstration app.

if you use material2, open [sample-app-material2](https://github.com/cybozu/callout-compose/tree/main/sample-app-material2) and Run it.

if you use material3, open [sample-app-material3](https://github.com/cybozu/callout-compose/tree/main/sample-app-material3) and Run it.

## License
[License](https://github.com/cybozu/callout-compose/blob/main/LICENSE)
