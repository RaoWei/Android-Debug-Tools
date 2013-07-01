package com.androidesk.util;

import java.io.File;

import android.net.Uri;

/**
 * 各种变量和常量
 */
public final class C {

	private C() {
	}

	public static class NETWORK {
		public static final String DISABLED = "DISABLED"; // 网络不可用
		public static final String WIFI = "wifi"; // wifi网络
		public static final String MOBILE = "3g"; // 电信,移动,联通,等mobile网络
		public static final String CMWAP = "CMWAP"; // 移动wap
		public static final String UNIWAP = "UNIWAP"; // 联通wap
		public static final String CTWAP = "CTWAP"; // 电信wap
		public static final String OTHER = "OTHR"; // 其它未知网络
		public static final String GPRS = "GPRS";// 2G/3G 网络

	}

	public static class URL {
		// public static final String HOST_MAIN = "http://gmcenter.sinaapp.com";
	}

	public static class HTTP {
		public static final int BUFFER_SIZE = 8192;
		public static final int CONNECT_TIMEOUT = 10000 * 2;
		public static final int SO_TIMEOUT = 15000 * 2;
		public static final int MAX_IMAGE_LIMIT = 50;
	}


	

	public static class FILE {

		public static final int BUFFER_SIZE = 1024 * 4;
		public static final String APK_EXTENSION = ".apk";
		public static final String JPG_EXTENSION = ".jpg";
	}

	public static class OP {
		public static final int ERR_OME = 0x100;
		public static final int ERR_NOSD = 0x101;
		public static final int ERR_NET = 0x102;
		public static final int ERR_IO = 0x103;
		public static final int ERR_SYS = 0x104;
		public static final int FAIL = 0x105;
		public static final int SUCC = 0x106;
		public static final int HELP = 0x108;
		public static final int CANCEL = 0x110;
		public static final int HALF = 0x113;
		public static final int NO_UPDATE = 0x115;
		public static final int REQUIRE_UPDATE = 0x116;
		public static final int OUTOF_SD_STORE = 0x117;
		public static final int NO_FOUND = 0x118;
		public static final int NO_LOCALWALLPAPER = 0x119;

	}

	public static class LOCAL {
		public static Uri URI_PREFERAPN = Uri
				.parse("content://telephony/carriers/preferapn");
		public static final String INSTALL_CMD = "application/vnd.android.package-archive"; // 安装软件uri

	}

	public static class TASK {
		public static final int SPLASH_SECOND = 1;
	}

}
