package me.cheshmak.cordova;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import me.cheshmak.cheshmakplussdk.advertise.CheshmakBannerAd;
import me.cheshmak.cheshmakplussdk.advertise.CheshmakInterstitialAd;
import me.cheshmak.cheshmakplussdk.advertise.CheshmakRewardedAd;
import me.cheshmak.cheshmakplussdk.advertise.InterstitialCallback;
import me.cheshmak.cheshmakplussdk.advertise.RewardedCallback;
import me.cheshmak.cheshmakplussdk.core.CheshmakPlus;

public class CheshmakPlusCordova extends CordovaPlugin {
    private static final String TAG = "Cheshmak Plus Cordova";
    public CordovaInterface cordova = null;
    private FrameLayout bannerLayout;
    private CheshmakInterstitialAd cheshmakInterstitialAd;
    private CheshmakRewardedAd cheshmakRewardedAd;
    private static final int POSITION_TOP = 0;
    private static final int POSITION_BOTTOM = 1;
    private static final int POSITION_XY = 2;

    @Override
    public void initialize(CordovaInterface initCordova, CordovaWebView webView) {
        cordova = initCordova;
        Log.e(TAG, "initialize");
        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("initialize".equals(action)) {
        	initialize(callbackContext);
            return true;
        }
        if ("setTestMode".equals(action)) {
            setTestMode(args, callbackContext);
            return true;
        }
        if ("addBanner".equals(action)) {
            addBanner(args, callbackContext);
            return true;
        }
        if ("removeBanner".equals(action)) {
        	removeBanner(callbackContext);
            return true;
        }
        if ("loadInterstitialAd".equals(action)) {
            loadInterstitialAd(callbackContext);
            return true;
        }
        if ("showInterstitialAd".equals(action)) {
            showInterstitialAd(callbackContext);
            return true;
        }
        if ("loadRewardedAd".equals(action)) {
        	loadRewardedAd(callbackContext);
            return true;
        }
        if ("showRewardedAd".equals(action)) {
        	showRewardedAd(callbackContext);
            return true;
        }
        return false;
    }

    public void initialize(final CallbackContext callbackContext) throws JSONException {
    	CheshmakPlus.with(cordova.getActivity());
        callbackContext.success();
    	
    }

    public void setTestMode(JSONArray args, final CallbackContext callbackContext) throws JSONException {
        try {
            boolean test = args.getBoolean(0);
            CheshmakPlus.setTestMode(test);
            callbackContext.success();
        } catch (JSONException e) {
            callbackContext.error(e.getMessage());
        }
    }

    private void addBanner(final JSONArray args, final CallbackContext callbackContext) throws JSONException {
        final int position = args.getInt(0);
        final int x = args.getInt(1);
        final int y = args.getInt(2);
        final Activity mActivity = cordova.getActivity();

        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (bannerLayout != null) {
                    removeBanner(callbackContext);
                }
                bannerLayout = new FrameLayout(mActivity);
                FrameLayout.LayoutParams fLayoutParams = new FrameLayout.LayoutParams(-2, -2);
                switch (position) {
                    case POSITION_TOP:
                        fLayoutParams.gravity = Gravity.TOP | Gravity.CENTER;
                        break;
                    case POSITION_BOTTOM:
                        fLayoutParams.gravity = Gravity.BOTTOM | Gravity.CENTER;
                    case POSITION_XY:
                        fLayoutParams.leftMargin = x;
                        fLayoutParams.topMargin = y;
                }
                bannerLayout.setLayoutParams(fLayoutParams);
                ((ViewGroup) getParentGroup().getParent()).addView(bannerLayout, 1);
                CheshmakBannerAd banner = new CheshmakBannerAd(cordova.getActivity());
                bannerLayout.addView(banner);
                callbackContext.success();
            }
        });
    }

    private void loadInterstitialAd(final CallbackContext callbackContext) {
    	final Activity mActivity = cordova.getActivity();

        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
            	cheshmakInterstitialAd = new CheshmakInterstitialAd(mActivity, new InterstitialCallback() {
                    @Override
                    public void onAdLoaded() {
                        PluginResult pr = new PluginResult(PluginResult.Status.OK, "onAdLoaded");
                        pr.setKeepCallback(true);
                        callbackContext.sendPluginResult(pr);
                    }

                    @Override
                    public void onAdOpened() {
                        PluginResult pr = new PluginResult(PluginResult.Status.OK, "onAdOpened");
                        pr.setKeepCallback(true);
                        callbackContext.sendPluginResult(pr);
                    }

                    @Override
                    public void onAdFailedToLoad() {
                        PluginResult pr = new PluginResult(PluginResult.Status.OK, "onAdFailedToLoad");
                        pr.setKeepCallback(true);
                        callbackContext.sendPluginResult(pr);
                    }

					@Override
					public void onAdClosed() {
                        PluginResult pr = new PluginResult(PluginResult.Status.OK, "onAdClosed");
                        pr.setKeepCallback(true);
                        callbackContext.sendPluginResult(pr);
					}
                });
            }
        });
    }

    private void showInterstitialAd(final CallbackContext callbackContext) {
    	cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
            	if (cheshmakInterstitialAd.isLoaded()) {
            		cheshmakInterstitialAd.show();
            		callbackContext.success();
            	} else {
            		callbackContext.error("interstitialAd is not loaded.");
            	}
            }
        });
    }

    private void loadRewardedAd(final CallbackContext callbackContext) {
    	final Activity mActivity = cordova.getActivity();

        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
            	cheshmakRewardedAd = new CheshmakRewardedAd(cordova.getActivity(), new RewardedCallback() {
					@Override
					public void onRewardedVideoAdLoaded() {
		                PluginResult pr = new PluginResult(PluginResult.Status.OK, "onRewardedVideoAdLoaded");
		                pr.setKeepCallback(true);
		                callbackContext.sendPluginResult(pr);
					}

					@Override
					public void onRewardedVideoAdOpened() {
		                PluginResult pr = new PluginResult(PluginResult.Status.OK, "onRewardedVideoAdOpened");
		                pr.setKeepCallback(true);
		                callbackContext.sendPluginResult(pr);
					}

					@Override
					public void onRewardedVideoAdFailedToLoad() {
		                PluginResult pr = new PluginResult(PluginResult.Status.OK, "onRewardedVideoAdFailedToLoad");
		                pr.setKeepCallback(true);
		                callbackContext.sendPluginResult(pr);
					}

					@Override
					public void onRewarded() {
		                PluginResult pr = new PluginResult(PluginResult.Status.OK, "onRewarded");
		                pr.setKeepCallback(true);
		                callbackContext.sendPluginResult(pr);
					}

					@Override
					public void onRewardedVideoAdClosed() {
		                PluginResult pr = new PluginResult(PluginResult.Status.OK, "onRewardedVideoAdClosed");
		                pr.setKeepCallback(true);
		                callbackContext.sendPluginResult(pr);
					}
		        });
            }
        });
    }

    private void showRewardedAd(final CallbackContext callbackContext) {
    	cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
            	if (cheshmakRewardedAd.isLoaded()) {
            		cheshmakRewardedAd.show();
            		callbackContext.success();
            	} else {
            		callbackContext.error("rewardedAd is not loaded.");
            	}
            }
        });
    }

    private ViewGroup getParentGroup() {
        try {
            return (ViewGroup) this.webView.getClass().getMethod("getView", new Class[0]).invoke(this.webView, new Object[0]);
        } catch (Exception ex) {
            try {
                return (ViewGroup) this.webView.getClass().getMethod("getParent", new Class[0]).invoke(this.webView, new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void removeBanner(CallbackContext callbackContext) {
        Activity mActivity = cordova.getActivity();
        if (bannerLayout == null)
            return;
        if (mActivity != null) {
            mActivity.runOnUiThread(new Runnable() {
                public void run() {
                    ViewGroup viewGroup;
                    if (((viewGroup = getParentGroup()) != null) && ((viewGroup instanceof ViewGroup)) && (((ViewGroup) viewGroup.getParent()).getChildAt(1) != null))
                        ((ViewGroup) viewGroup.getParent()).removeViewAt(1);
                }
            });
            if (callbackContext != null) {
                callbackContext.success();
            }
        }
    }
}