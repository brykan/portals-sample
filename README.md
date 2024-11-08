# PortalsSample
Demo App using Ionic Portals and Jetpack compose based on an original [Ionic Portals demo](https://github.com/ionic-team/portals-ecommerce-demo)

This is an example application built in Android that uses web resources for its Portals.

## Portals registration key

To try the demo, the Android applications requires you to input a Portals registration key. You may get a key by going to [ionic.io/register-portals](https://ionic.io/register-portals)., then replace `""""YOUR_KEY_HERE""""` with your key inside app/build.gradle.kts.

Build and run the Android app

## Web
### Building

Before you build the Android source you will need to build the web resources in the `web` directory:
```bash
cd ./web
npm install
npm run build
```
Once you build your web application, create a folder in the Android project assets directory and copy the contents of the build directory.
