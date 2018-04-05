package com.qbao.store.huanxin.model;

import java.util.List;

public class TalkChatPayload {
	private List<TalkChatBody>bodies;
	public List<TalkChatBody> getBodies() {
		return bodies;
	}
	public void setBodies(List<TalkChatBody> bodies) {
		this.bodies = bodies;
	}
}
