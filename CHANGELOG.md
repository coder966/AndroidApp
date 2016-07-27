Changelog
-

### v2.0.1 (27-7-2016)
* [Fixed] onCreate(int, int) gets called twice after fresh installation

### v2.0.0 (27-7-2016)
* Change the license to Apache License, Version 2.0
* This release breaks "code" backward compatibility but the "saved data" (the previous installed version code) still be okay.
* [Add] onCreate(int, String).
* [Add] onUpgradeBeforeOnCreate(int, int).
* [Add] onUpgradeAfterOnCreate(int, int).
* [Remove] onUpgrade(int, int).
* onCreate() is now final; therefore cannot be overridden. Override onCreate(int, int) instead.

### v1.0.0 (13-6-2016)
* The very first initial version
