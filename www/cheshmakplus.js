var exec = require('cordova/exec');
module.exports = {
	Position: {
        TOP: 0,
        BOTTOM: 1,
		XY: 2
    },
    initialize: function() {
        cordova.exec(
			function (result) {
				console.log('initialize Success.');
			},
			null,
            'CheshmakPlusCordova',
            'initialize',
            []
        ); 
    },
    setTestMode: function(test) {
        cordova.exec(
			function (result) {
				console.log('SetTest Success.');
			},
			null,
            'CheshmakPlusCordova',
            'setTestMode',
            [test]
        ); 
    },
    addBanner: function(position, x, y) {
        cordova.exec(
			function (result) {
				console.log('addBanner Success.');
			},
			null,
            'CheshmakPlusCordova',
            'addBanner',
            [ position, x, y ]
        ); 
    },
    removeBanner: function() {
        cordova.exec(
			function (result) {
				console.log('removeBanner Success.');
			},
			null,
            'CheshmakPlusCordova',
            'removeBanner',
            []
        ); 
    },
    loadInterstitialAd: function () {
        var self = this;
        cordova.exec(
            function (result) {
                if (typeof result == "string") {
                    if (result == "onAdLoaded") {
                        if (self.onAdLoaded) {
                            self.onAdLoaded();
                        }
                    }
                    if (result == "onAdOpened") {
                        if (self.onAdOpened) {
                            self.onAdOpened();
                        }
                    }
                    if (result == "onAdFailedToLoad") {
                        if (self.onAdFailedToLoad) {
                            self.onAdFailedToLoad();
                        }
                    }
                    if (result == "onAdClosed") {
                        if (self.onAdClosed) {
                            self.onAdClosed();
                        }
                    }
                }
            },
            null,
            'CheshmakPlusCordova',
            'loadInterstitialAd',
            []
        );
    },
    showInterstitialAd: function () {
        cordova.exec(
            function (result) {
				console.log('showInterstitialAd Success.');
			},
            function (error) {
				console.log(error);
			},
            'CheshmakPlusCordova',
            'showInterstitialAd',
            []
        );
    },
    loadRewardedAd: function () {
        var self = this;
        cordova.exec(
            function (result) {
                if (typeof result == "string") {
                    if (result == "onRewardedVideoAdLoaded") {
                        if (self.onRewardedVideoAdLoaded) {
                            self.onRewardedVideoAdLoaded();
                        }
                    }
                    if (result == "onRewardedVideoAdOpened") {
                        if (self.onRewardedVideoAdOpened) {
                            self.onRewardedVideoAdOpened();
                        }
                    }
                    if (result == "onRewardedVideoAdFailedToLoad") {
                        if (self.onRewardedVideoAdFailedToLoad) {
                            self.onRewardedVideoAdFailedToLoad();
                        }
                    }
                    if (result == "onRewarded") {
                        if (self.onRewarded) {
                            self.onRewarded();
                        }
                    }
                    if (result == "onRewardedVideoAdClosed") {
                        if (self.onRewardedVideoAdClosed) {
                            self.onRewardedVideoAdClosed();
                        }
                    }
                }
            },
            null,
            'CheshmakPlusCordova',
            'loadRewardedAd',
            []
        );
    },
    showRewardedAd: function () {
        cordova.exec(
            function (result) {
				console.log('showRewardedAd Success.');
			},
            function (error) {
				console.log(error);
			},
            'CheshmakPlusCordova',
            'showRewardedAd',
            []
        );
    },
    onAdLoaded: null,
    onAdOpened: null,
    onAdFailedToLoad: null,
    onAdClosed: null,
    onRewardedVideoAdLoaded: null,
    onRewardedVideoAdOpened: null,
    onRewardedVideoAdFailedToLoad: null,
    onRewarded: null,
    onRewardedVideoAdClosed: null
};