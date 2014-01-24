package net.sumppen.whatsapi4j;

public enum ProtocolTag {
	START("start"),CHALLENGE("challenge"),
	SUCCESS("success"), FAILURE("failure"), MESSAGE("message"), PRESENCE("presence"), IQ("iq"),PING("ping"),QUERY("query"), UNKNOWN("unknown"), STREAM_ERROR("stream:error");
	
	private String tag;

	private ProtocolTag(String tag) {
		this.tag = tag;
	}
	@Override
	public String toString() {
		return tag;
	}
	
}
