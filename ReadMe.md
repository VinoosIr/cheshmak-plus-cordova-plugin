# Cordova Cheshmak Plus plugin

for Android, by [Milad Mohammadi Rezagah](http://www.miladesign.ir)

This is a plugin for Cheshmak plus android library for Cordova and other Cordova-base users (like Ionic and PhoneGap) which enables them to integrate Cheshmak plus into their applications.

## 0. Index

1. [How to use](#1-how-to-use)
2. [Banner Ad](#2-banner-ad)
3. [Interstitial Ad](#3-interstitial-ad)
4. [Rewarded Ad](#4-rewarded-ad)
5. [Designer](#5-designer)

## 1. How to use

if you are using cordova:

``cordova plugin add <path-to-this-plugin-on-your-computer>``

For example, if you download it to *D:/development/cheshmak-plus-cordova* and *cheshmak-plus-cordova* contains the plugin files, add plugin command become:

``cordova plugin add D:/development/cheshmak-plus-cordova-plugin``

- Login to your panel on cheshmak.me and create a new application.

- Add below lines to your app's *index.html* file. Please note that this is an example.

```js
    <script type="text/javascript">
        document.addEventListener("deviceready", deviceReady, true);
        function deviceReady() {
			window.cheshmakplus.initialize();
        }
    </script>
```

## 2. Banner Ad

### Add Banner:

``window.cheshmakplus.addBanner(position, x, y);``

- Example:
```js
    window.cheshmakplus.addBanner(window.cheshmakplus.TOP, 0, 0);
    window.cheshmakplus.addBanner(window.cheshmakplus.BOTTOM, 0, 0);
    window.cheshmakplus.addBanner(window.cheshmakplus.XY, 20, 100);
```

### Remove Banner:

``window.cheshmakplus.removeBanner();``

## 3. Interstitial Ad

To use interstitial ad you need to load interstitial ad first:
``window.cheshmakplus.loadInterstitialAd();``

after that this events will raised:
```js
	window.cheshmakplus.onAdLoaded = function () {
		console.log('onAdLoaded');
	};
	window.cheshmakplus.onAdOpened = function () {
		console.log('onAdOpened');
	};
	window.cheshmakplus.onAdFailedToLoad = function () {
		console.log('onAdFailedToLoad');
	};
	window.cheshmakplus.onAdClosed = function () {
		console.log('onAdClosed');
	};
```

to show ad use this code:
```js
window.cheshmakplus.isInterstitialAdLoaded(function(isLoaded){
 if (isLoaded) {
  window.cheshmakplus.showInterstitialAd();
 }
});
```

## 4. Rewarded Ad

To use rewarded ad you need to load rewarded ad first:
``window.cheshmakplus.loadRewardedAd();``

after that this events will raised:
```js
	window.cheshmakplus.onRewardedVideoAdLoaded = function () {
		console.log('onRewardedVideoAdLoaded');
	};
	window.cheshmakplus.onRewardedVideoAdOpened = function () {
		console.log('onRewardedVideoAdOpened');
	};
	window.cheshmakplus.onRewardedVideoAdFailedToLoad = function () {
		console.log('onRewardedVideoAdFailedToLoad');
	};
	window.cheshmakplus.onRewarded = function () {
		console.log('onRewarded');
	};
	window.cheshmakplus.onRewardedVideoAdClosed = function () {
		console.log('onRewardedVideoAdClosed');
	};
```

to show ad use this code:
```js
window.cheshmakplus.isRewardedAdLoaded(function(isLoaded){
 if (isLoaded) {
  window.cheshmakplus.showRewardedAd();
 }
});
```

## 5. Designer

- Milad Mohammadi Rezagah
- www.miladesign.ir
- Rezagah.Milad@Gmail.com