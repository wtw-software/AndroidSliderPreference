Android Slider Preference Library
=================================

## Overview

* Slider represents a `int` of selectable value (defaults to 0-100)
* Subclass of [`DialogPreference`][diag]
  * Supports all [dialog-specific attributes][datr] such as `android:dialogMessage`
  * Visually-consistent with Android's built-in preferences
  * Less error-prone than displaying the slider directly on the settings screen
* [MIT License](#license)

[diag]: https://developer.android.com/reference/android/preference/DialogPreference.html "DialogPreference"
[datr]: https://developer.android.com/reference/android/preference/DialogPreference.html#lattrs "DialogPreference attributes"

## How To Use

### Add the library to your application

Before you can add a `SliderPreference` to your application, you must first add a library reference:

1. Clone or download a copy of the library
2. Import the library into Eclipse: File menu -> Import -> Existing Project into Workspace
3. Open your application's project properties and [add a library reference][ref] to "SliderPreference"

[ref]: https://developer.android.com/tools/projects/projects-eclipse.html#ReferencingLibraryProject

### Add a slider to your application

``` XML
<!-- preferences.xml -->
<no.wtw.android.preference.SliderPreference
    android:key="my_slider"
    android:title="@string/slider_title"
    android:defaultValue="10"
    android:dialogMessage="@string/slider_message"
    app:minValue="30"
    app:maxValue="60"
    app:unitStringReference="@plurals/seconds_plural" />
```
``` XML
<!-- strings.xml -->
<string name="slider_title">Seconds to display</string>
<string name="slider_message">How many seconds do you want the message on screen</string>
<plurals name="seconds_plural">
    <item quantity="one">%d second</item>
    <item quantity="other">%d seconds</item>
</plurals>
```

## License

This library is licensed under the [MIT License][mit]. A copy of the license is provided in [LICENSE.txt][copy]:

> Copyright 2012 Jay Weisskopf
>
> Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
>
> The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
>
> THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

[copy]: https://raw.github.com/jayschwa/AndroidSliderPreference/master/LICENSE.txt
[mit]: http://opensource.org/licenses/MIT "Open Source Initiative - The MIT License"