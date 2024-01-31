# PortalsSample
E-commerce Demo App using Ionic Portals and Jetpack compose based on an original [Ionic Portals demo](https://github.com/ionic-team/portals-ecommerce-demo)

This is an example application built in Android that uses web resources for its Portals.

## Portals Registration Key

To try the demo, the Android applications requires you to input a Portals registration key. You may get a key by going to [ionic.io/register-portals](https://ionic.io/register-portals). Follow the instructions below to add your key to each platform demo application.

### Portals registration key

Get you registration key from [ionic.io/register-portals](https://ionic.io/register-portals), then replace `YOUR_KEY_HERE` with your key inside app/build.gradle.kts.

Build and run the Android app

## Web
### Building

Before you build the Android source you will need to build the web resources in the `web` directory:
```bash
cd ./web
npm install
npm run build

cd ../featured-component
npm install
npm run build
```
