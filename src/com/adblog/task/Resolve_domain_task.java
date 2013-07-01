package com.adblog.task;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.adblog.control.Util;

public class Resolve_domain_task extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Util.resolve_domain = getresolve_domain();
		super.run();
	}

	private StringBuffer getresolve_domain() {
		StringBuffer buffer = new StringBuffer();
		try {
			InetAddress address = InetAddress.getByName("aoi.andoidesk.com");
			InetAddress[] addresses = address
					.getAllByName("aoi.androidesk.com");
			buffer.append("\nName:  " + "aoi.androidesk.com");
			for (int i = 0; i < addresses.length; i++) {
				buffer.append("\nAddress" + i + ":  "
						+ addresses[i].getHostAddress());
			}

			address = InetAddress.getByName("service.andoidesk.com");
			addresses = address.getAllByName("service.androidesk.com");
			buffer.append("\nName:  " + "service.androidesk.com");
			for (int i = 0; i < addresses.length; i++) {
				buffer.append("\nAddress" + i + ":  "
						+ addresses[i].getHostAddress());
			}

			address = InetAddress.getByName("s.andoidesk.com");
			addresses = address.getAllByName("s.androidesk.com");
			buffer.append("\nName:  " + "s.androidesk.com");
			for (int i = 0; i < addresses.length; i++) {
				buffer.append("\nAddress" + i + ":  "
						+ addresses[i].getHostAddress());
			}

			address = InetAddress.getByName("img0.andoidesk.com");
			addresses = address.getAllByName("img0.androidesk.com");
			buffer.append("\nName:  " + "img0.androidesk.com");
			for (int i = 0; i < addresses.length; i++) {
				buffer.append("\nAddress" + i + ":  "
						+ addresses[i].getHostAddress());
			}
           buffer.append("\n域名解析结束****************************************");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buffer;
	}

}
