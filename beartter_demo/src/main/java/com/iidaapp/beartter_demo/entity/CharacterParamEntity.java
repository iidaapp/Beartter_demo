package com.iidaapp.beartter_demo.entity;

/**
 * CharacterParamテーブルエンティティクラス
 * @author iida
 *
 */
public class CharacterParamEntity {

	private String beartterId;
	private int pretty;
	private int knowledge;
	private int art;
	private int cheerful;
	private int nerd;


	public String getBeartterId() {
		return beartterId;
	}


	public int getPretty() {
		return pretty;
	}


	public int getKnowledge() {
		return knowledge;
	}


	public int getArt() {
		return art;
	}


	public int getCheerful() {
		return cheerful;
	}


	public int getNerd() {
		return nerd;
	}


	public void setBeartterId(String beartterId) {
		this.beartterId = beartterId;
	}


	public void setPretty(int pretty) {
		this.pretty = pretty;
	}


	public void setKnowledge(int knowledge) {
		this.knowledge = knowledge;
	}


	public void setArt(int art) {
		this.art = art;
	}


	public void setCheerful(int cheerful) {
		this.cheerful = cheerful;
	}


	public void setNerd(int nerd) {
		this.nerd = nerd;
	}
}
