[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-KerningViews-green.svg?style=true)](https://android-arsenal.com/details/1/3662)

# KerningViews

![KerningViews](https://raw.githubusercontent.com/aritraroy/KerningViews/master/images/kerning.png)

KerningViews provides a set of views (currently only TextView and Button) which lets you adjust the spacing between the characters in the text of that view also know as **Kerning**.

Kerning is a common feature in designing apps like Photoshop or Sketch, but Android natively doesn't allow Kerning. Since API level 21, Android allows you to do this using ```setLetterSpacing``` attribute , but there is no way to achieve this in pre-21 devices.

KerningViews provides a simple API to achieve this feature in TextView and Button text accross all API levels in Android.

# Screenshot
This screenshot is from the sample app showing different text with different Kerning factors.
![Screenshot](https://raw.githubusercontent.com/aritraroy/KerningViews/master/images/screenshot.png)

# Download

This library is available in jCenter which is the default Maven repository used in Android Studio.

```gradle
dependencies {
    // other dependencies here
    
    compile 'com.andrognito.kerningview:kerningview:1.0.0'
}
```

# Usage
You should see the sample app for complete usage details. You can either use these views and set the attribute via XML or programatically change the kerning factor as required.

### XML

Just use the  ```KerningTextView ``` or  ```KerningButton ``` in your XML layouts. If you do not put the  ```kv_spacing ``` attribute then it will behave as a normal TextView with no letter spacing. 

You can customize the letter spacing by using the ```kv_spacing ``` atribute with different kerning facctor (including floating point values).

 ```java
<com.andrognito.kerningview.KerningTextView
        android:id="@+id/text1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:fontFamily="sans-serif"
        android:text="@string/sample_text_1"
        android:textSize="@dimen/text_size_xlarge"
        app:kv_spacing="10" />
 ```
 
### JAVA

Just reference the views from your layout and use the setter to change the letter spacing of the texts.

 ```java
KerningTextView mText = (KerningTextView) findViewById(R.id.text);

if (mText != null) {
   mText.setKerningFactor(3.5f);
}
```

# Contribution

This is a very simple and tiny library that solves a simple usecase. But I would love to accept contributions to improve or add functionality to it.

# About The Author

### Aritra Roy

Android & Backend Developer. Blogger. Designer. Fitness Enthusiast.

<a href="https://play.google.com/store/apps/details?id=com.codexapps.andrognito&hl=en" target="_blank"><img src="https://github.com/aritraroy/social-icons/blob/master/play-store-icon.png?raw=true" width="60"></a> <a href="https://blog.aritraroy.in/" target="_blank"><img src="https://github.com/aritraroy/social-icons/blob/master/medium-icon.png?raw=true" width="60"></a>
<a href="http://stackoverflow.com/users/2858654/aritra-roy" target="_blank"><img src="https://github.com/aritraroy/social-icons/blob/master/stackoverflow-icon.png?raw=true" width="60"></a>
<a href="https://twitter.com/aritraroy" target="_blank"><img src="https://github.com/aritraroy/social-icons/blob/master/twitter-icon.png?raw=true" width="60"></a>
<a href="http://linkedin.com/in/aritra-roy"><img src="https://github.com/aritraroy/social-icons/blob/master/linkedin-icon.png?raw=true" width="60"></a>


# License

```
Copyright 2016 aritraroy

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
