package com.adblog.util;

import android.app.WallpaperManager;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DisplayManager {
	private static DisplayManager instance = null;
	private int displayWidth = 0; // 显示器宽
	private int displayHeight = 0; // 显示器高
	private int desiredWidth = 0; // 壁纸区宽(api版，有可能爲0或者-1什么的，有时靠谱有时不行)
	private int desiredHeight = 0; // 壁纸区高(api版，有可能爲0或者-1什么的，有时靠谱有时不行)
	private int wallpaperWidth = 0; // 壁纸区宽(估算版，如果desiredWidth有问题，则调整成displayWidth或者2倍displayWidth)
	private int wallpaperHeight = 0; // 壁纸区高(估算版,如果desiredHeight有问题，则调整成displayHeight)
	private int densityDpi = 0;// 屏幕密度Dpi（120 / 160 / 240）
	private DisplayMetrics dm = null;

	public static DisplayManager getInstance() {
		if (instance == null) {
			synchronized (DisplayManager.class) {
				if (instance == null) {
					instance = new DisplayManager();
				}
			}
		}
		return instance;
	}

	private DisplayManager() {
	}

	private String init(Context context) {
		try {
			dm = new DisplayMetrics();
			WindowManager wm = (WindowManager) context
					.getSystemService(Context.WINDOW_SERVICE);
			wm.getDefaultDisplay().getMetrics(dm);
			displayWidth = dm.widthPixels;
			displayHeight = dm.heightPixels;
			densityDpi = dm.densityDpi;
		} catch (Exception e) {
		}
		try {
			desiredWidth = WallpaperManager.getInstance(context)
					.getDesiredMinimumWidth();
			desiredHeight = WallpaperManager.getInstance(context)
					.getDesiredMinimumHeight();
		} catch (Exception e) {
		}
		int widthTmp = desiredWidth <= 0 ? displayWidth : desiredWidth;
		int heightTmp = desiredHeight <= 0 ? displayHeight : desiredHeight;
		String styleV = "";
		try {
			if (desiredHeight > 0 && desiredWidth > 0
					&& desiredHeight > desiredWidth) { // 竖屏
				this.wallpaperWidth = widthTmp > heightTmp ? displayWidth
						: widthTmp;
				this.wallpaperHeight = widthTmp > heightTmp ? displayHeight
						: heightTmp;
				styleV = "v:  |  [*]  --- [ ]";
			} else { // 横屏
				this.wallpaperWidth = widthTmp < heightTmp ? widthTmp * 2
						: widthTmp;
				this.wallpaperHeight = heightTmp;
				styleV = "v:  |  [ ]  --- [*]";
			}
		} catch (Exception e) {
			styleV = "v:  |  [X]  --- [X]";
			this.wallpaperWidth = displayWidth;
			this.wallpaperHeight = displayHeight;
		}
		return "dis/des/res: " + displayWidth + "x" + displayHeight + "/"
				+ desiredWidth + "x" + desiredHeight + "/"
				+ this.wallpaperWidth + "x" + this.wallpaperHeight + " >>>>> "
				+ styleV;
	}

	public void refreshWnH(Context context) {
		String res = this.init(context);
	}

	private void refresh(Context context) {
		if (dm != null) {
			return;
		}
		String res = this.init(context);
	}

	public boolean isVerticalScreen(Context context) {
		this.refresh(context);
		return desiredHeight > 0 && desiredWidth > 0
				&& desiredHeight > desiredWidth;
	}

	public int getDisplayWidth(Context context) {
		this.refresh(context);
		return displayWidth;
	}

	public int getDisplayHeight(Context context) {
		this.refresh(context);
		return displayHeight;
	}

	public int getDesiredWidth(Context context) {
		this.refresh(context);
		return desiredWidth;
	}

	public int getDesiredHeight(Context context) {
		this.refresh(context);
		return desiredHeight;
	}

	public int getWallpaperWidth(Context context) {
		this.refresh(context);
		return wallpaperWidth;
	}

	public int getWallpaperHeight(Context context) {
		this.refresh(context);
		return wallpaperHeight;
	}

	public DisplayMetrics getDisplayMetrics(Context context) {
		this.refresh(context);
		return dm;
	}

	public int getDensityDpi() {
		return densityDpi;
	}

	/**
	 * 将整形 1转换成1dp
	 * 
	 * @param context
	 * @param dp
	 * @return
	 */
	public int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
}
