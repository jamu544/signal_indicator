# signal_indicator -- Android Application created by Odwa

Android application that retrieves the RSSI(Received Signal Strength Indicator) for each wiresless network in the 
environment and reports this reading to an imaginary API.

# Get Started
## Overview 
The application scans and retrives wifi in your location.
And the list of wireless netwroks is then sent through the server via POST protocol


## Installation 

Clone this respository and import into **Android Studio**

git clone git@github.com:jamu544/signal_indicator.git

## External Dependencies
implementation com.squareup.retrofit2:retrofit:2.9.0

implementation com.squareup.retrofit2:converter-gson:2.5.0


## Permissions
<!--    To perform network operations in your application, your manifest must include the following permissions:-->
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name = "android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name = "android.permission.CHANGE_WIFI_STATE" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />



## Code snippets
https://developer.android.com/guide/topics/connectivity/wifi-scan:
You can use the Wi-Fi scanning capabilities provided by the WifiManager API to get a list of Wi-Fi access points that are visible from the device.

https://developer.android.com/guide/topics/ui/layout/recyclerview:
RecyclerView makes it easy to efficiently display large sets of data. You supply the data and define how each item looks, and the RecyclerView library dynamically creates the elements when they're needed.


## Sample App

The app has a simple UI screen and is just an example of what the app does.
![Happy Christmas](Christmas.png)










