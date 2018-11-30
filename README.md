<a href="https://android-arsenal.com/details/1/5062"><img src="https://img.shields.io/badge/Android%20Arsenal-EasyFeedback-blue.svg?style=flat-square" border="0" alt="Android Arsenal"></a>
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=102)](http://www.apache.org/licenses/LICENSE-2.0)
[![License](https://img.shields.io/badge/License-Apache--2.0-brightgreen.svg)](https://github.com/webianks/EasyFeedback/blob/master/LICENCE)

# EasyFeedback
So you have developed full Application, Now the part comes when you have to create a FeedbackActivity to gather feedback but you feel terrible for creating something boilerplate. Now don't feel just write your email-id and you are done.
<br><br>

<img src="https://github.com/webianks/EasyFeedback/blob/master/screenshots/screen_one.png" height="700" width="400" >

# What you get

<ul>
<li>Text Feedback</li>
<li>System Info - Device Name, App Version, Android Version etc.</li>
<li>Log Report</li>
<li>Screenshot</li>
</ul>

# Min SDK
15

# Download

**Gradle**

```groovy
implementation 'com.webianks.library:easy-feedback:1.0.5'
```
**Maven**

```xml
<dependency>
  <groupId>com.webianks.library</groupId>
  <artifactId>easy-feedback</artifactId>
  <version>1.0.5</version>
  <type>pom</type>
</dependency>
```


# Usage

Use this piece of code inside the onClick method of the Activity/Fragment from where you want to open the FeedbackActivity.

```java

   new EasyFeedback.Builder(this)
            .withEmail("webianks@gmail.com")
            .withSystemInfo()
            .build()
            .start();
```
# Method Explanations

**withEmail(String email)**

Takes the string email-id of the developer on which you want to get the feedback/report.

**withSystemInfo()**

Add this method in the builder when you want the System Info & Log reports also.

**Note**

Sceenshot is optional.

# LICENSE

```
 Copyright 2017 Ramankit Singh 

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
