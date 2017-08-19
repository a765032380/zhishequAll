package com.bjxiyang.zhinengshequ.myapplication.manager;

import com.bjxiyang.zhinengshequ.myapplication.bean.HaoYouList;
import com.bjxiyang.zhinengshequ.myapplication.bean.Users;

/**
 * @description 单例管理登陆用户信息
 * @author renzhiqiang
 * @date 2015年8月19日
 */
public class HaoYouListManager {

	private static HaoYouListManager userManager = null;
	private HaoYouList user = null;

	public static HaoYouListManager getInstance() {

		if (userManager == null) {

			synchronized (HaoYouListManager.class) {

				if (userManager == null) {

					userManager = new HaoYouListManager();
				}
				return userManager;
			}
		} else {

			return userManager;
		}
	}

	/**
	 * init the user
	 * 
	 * @param user
	 */
	public void setUser(HaoYouList user) {

		this.user = user;
	}

	public boolean hasLogined() {

		return user == null ? false : true;
	}

	/**
	 * has user info
	 * 
	 * @return
	 */
	public HaoYouList getUser() {

		return this.user;
	}

	/**
	 * remove the user info
	 */
	public void removeUser() {

		this.user = null;
	}
}
