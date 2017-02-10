package com.bupt.vouching.type;

import java.util.HashMap;
import java.util.Map;

/**
 * 竞技积分规则
 * 
 * @author Hogan
 * 
 */
public enum Credit {

	/**
	 * 第一名积分
	 */
	FIRST(1, 3),
	/**
	 * 第二名积分
	 */
	SECOND(2, 2),
	/**
	 * 第三名积分
	 */
	THIRD(3, 1),
	/**
	 * 第四名积分
	 */
	FOURTH(4, 0),
	/**
	 * 第五名积分
	 */
	FIFTH(5, 0),
	;

	private Integer rank;
	private Integer credit;

	Credit(Integer rank, Integer credit) {
		this.rank = rank;
		this.credit = credit;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	private static final Map<Integer, Credit> DIRC = new HashMap<Integer, Credit>();

	static {
		for (Credit e : Credit.values()) {
			DIRC.put(e.getRank(), e);
		}
	}

	public static Credit byRank(int rank) {
		return DIRC.get(rank);
	}

}
