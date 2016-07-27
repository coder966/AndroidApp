AndroidApp
===
Android library that features these extra methods for `Application` class:
* `onCreate(int, String)`
* `onUpgradeBeforeOnCreate(int, String)`
* `onUpgradeAfterOnCreate(int, String)`

Installation
---
This library requires at minimum Android 2.3.

**Maven**
```xml
<dependency>
  <groupId>net.coder966.AndroidApp</groupId>
  <artifactId>library</artifactId>
  <version>2.0.0</version>
  <type>pom</type>
</dependency>
```

**Gradle**
```gradle
compile 'net.coder966.AndroidApp:library:2.0.0'
```

Usage
---
* Inherit `AndroidApp` class instead of `Application`
* Override `onCreate(int, String)` method instead of `onCreate()`
* When the application gets updated, the method `onUpgradeBeforeOnCreate(int, int)` will be called right before your `onCreate(int, String)`
* When the application gets updated, the method `onUpgradeAfterOnCreate(int, int)` will be called right after your `onCreate(int, String)`

License
---
```
Copyright 2016 Khalid H. Alharisi

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
