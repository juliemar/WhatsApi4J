package net.sumppen.whatsapi4j;

import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractEventManager implements EventManager {

	private static final String EVENT_GROUPS_PARTICIPANTS_ADD = "GROUPS_PARTICIPANTS_ADD";
	private static final String EVENT_GROUPS_PARTICIPANTS_REMOVE = "GROUPS_PARTICIPANTS_REMOVE";
	private static final String EVENT_PONG = "PONG";
	private static final String PHONE_NUMBER = "phoneNumber";
	private static final String MSG_ID = "msgId";
	private static final String GROUP_ID = "groupId";
	private static final String JID = "jid";
	private static final String JID2 = "jid2";
	private static final String EVENT_PRESENCE = null;
	private static final String FROM = null;
	private static final String TYPE = null;

	public abstract void fireEvent(String eventPong, Map<String, String> eventData);

	/* (non-Javadoc)
	 * @see net.sumppen.whatsapi4j.EventManager#fireSendPong(java.lang.String, java.lang.String)
	 */
	public void fireSendPong(String phone, String msgid) {
		Map<String,String> eventData = new HashMap<String, String>();
		eventData.put(PHONE_NUMBER, phone);
		eventData.put(MSG_ID, msgid);
		fireEvent(EVENT_PONG, eventData);
	}


	/* (non-Javadoc)
	 * @see net.sumppen.whatsapi4j.EventManager#fireGroupsParticipantsAdd(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void fireGroupsParticipantsAdd(String phone, String groupId,
			String parseJID) {
		Map<String,String> eventData = new HashMap<String, String>();
		eventData.put(PHONE_NUMBER, phone);
		eventData.put(GROUP_ID, groupId);
		eventData.put(JID, parseJID);
		fireEvent(EVENT_GROUPS_PARTICIPANTS_ADD, eventData);
	}

	/* (non-Javadoc)
	 * @see net.sumppen.whatsapi4j.EventManager#fireGroupsParticipantsRemove(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void fireGroupsParticipantsRemove(String phone,
			String groupId, String parseJID, String parseJID2) {
		Map<String,String> eventData = new HashMap<String, String>();
		eventData.put(PHONE_NUMBER, phone);
		eventData.put(GROUP_ID, groupId);
		eventData.put(JID, parseJID);
		eventData.put(JID2, parseJID2);
		fireEvent(EVENT_GROUPS_PARTICIPANTS_REMOVE, eventData);
	}

	/* (non-Javadoc)
	 * @see net.sumppen.whatsapi4j.EventManager#firePresence(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void firePresence(String phone, String from,
			String type) {
		Map<String,String> eventData = new HashMap<String, String>();
		eventData.put(PHONE_NUMBER, phone);
		eventData.put(FROM, from);
		eventData.put(TYPE, type);
		fireEvent(EVENT_PRESENCE, eventData);
	}

	public void fireClose(String phone, String error) {
		// TODO Auto-generated method stub
		
	}

	public void fireCodeRegister(String phone, String login, String pw,
			String type, String expiration, String kind, String price,
			String cost, String currency, String price_expiration) {
		// TODO Auto-generated method stub
		
	}

	public void fireCodeRegisterFailed(String phone, String status,
			String reason, String retry_after) {
		// TODO Auto-generated method stub
		
	}

	public void fireCodeRequest(String phone, String method, String length) {
		// TODO Auto-generated method stub
		
	}

	public void fireCodeRequestFailed(String phone, String method,
			String reason, String value) {
		// TODO Auto-generated method stub
		
	}

	public void fireCodeRequestFailedTooRecent(String phone, String method,
			String reason, String retry_after) {
		// TODO Auto-generated method stub
		
	}

	public void fireConnect(String phone, String socket) {
		// TODO Auto-generated method stub
		
	}

	public void fireConnectError(String phone, String socket) {
		// TODO Auto-generated method stub
		
	}

	public void fireCredentialsBad(String phone, String status, String reason) {
		// TODO Auto-generated method stub
		
	}

	public void fireCredentialsGood(String phone, String login, String pw,
			String type, String expiration, String kind, String price,
			String cost, String currency, String price_expiration) {
		// TODO Auto-generated method stub
		
	}

	public void fireDisconnect(String phone, Socket socket) {
		// TODO Auto-generated method stub
		
	}

	public void fireDissectPhone(String phone, String country, String cc,
			String mcc, String lc, String lg) {
		// TODO Auto-generated method stub
		
	}

	public void fireDissectPhoneFailed(String phone) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetAudio(String phone, String from, String msgid,
			String type, String time, String name, String size, String url,
			String file, String mimetype, String filehash, String duration,
			String acodec) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetError(String phone, String id, String error) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetGroups(String phone, Map<String, String> groupList) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetGroupsInfo(String phone, Map<String, String> groupList) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetGroupsSubject(String phone, String[] reset_from,
			String time, String[] reset_author, String[] reset_author2,
			String name, byte[] bs) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetImage(String phone, String from, String msgid,
			String type, String time, String name, String size, String url,
			String file, String mimetype, String filehash, String width,
			String height, byte[] bs) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetLocation(String phone, String from, String msgid,
			String type, String time, String name, String place_name,
			String longitude, String latitude, String url, byte[] bs) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetMessage(String phone, String from, String msgid,
			String type, String time, String name, byte[] bs) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetGroupMessage(String phone, String from, String author,
			String msgid, String type, String time, String name, byte[] bs) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetGroupParticipants(String phone, String groupId,
			Map<String, String> groupList) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetPrivacyBlockedList(String phone, List<ProtocolNode> list) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetProfilePicture(String phone, String from, String type,
			byte[] bs) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetRequestLastSeen(String phone, String from, String msgid,
			String sec) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetServerProperties(String phone, String version,
			Map<String, String> props) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetStatus(String phone, String from, String type,
			String id, String t, byte[] bs) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetvCard(String phone, String from, String msgid,
			String type, String time, String name, String contact, byte[] bs) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetVideo(String phone, String from, String msgid,
			String type, String time, String name, String url, String file,
			String size, String mimetype, String filehash, String duration,
			String vcodec, String acodec, byte[] bs) {
		// TODO Auto-generated method stub
		
	}

	public void fireGroupsChatCreate(String phone, String gId) {
		// TODO Auto-generated method stub
		
	}

	public void fireGroupsChatEnd(String phone, String gId) {
		// TODO Auto-generated method stub
		
	}

	public void fireLogin(String phone) {
		// TODO Auto-generated method stub
		
	}

	public void fireLoginFailed(String phone, String tag) {
		// TODO Auto-generated method stub
		
	}

	public void fireMediaMessageSent(String phone, String to, String id,
			String filetype, String url, String filename, String filesize,
			byte[] icon) {
		// TODO Auto-generated method stub
		
	}

	public void fireMediaUploadFailed(String phone, String id,
			ProtocolNode node, Map<String, Object> messageNode, String reason) {
		// TODO Auto-generated method stub
		
	}

	public void fireMessageComposing(String phone, String from, String msgid,
			String type, String time) {
		// TODO Auto-generated method stub
		
	}

	public void fireMessagePaused(String phone, String from, String msgid,
			String type, String time) {
		// TODO Auto-generated method stub
		
	}

	public void fireMessageReceivedClient(String phone, String from,
			String msgid, String type, String time) {
		// TODO Auto-generated method stub
		
	}

	public void fireMessageReceivedServer(String phone, String from,
			String msgid, String type, String t) {
		// TODO Auto-generated method stub
		
	}

	public void firePing(String phone, String msgid) {
		// TODO Auto-generated method stub
		
	}

	public void fireProfilePictureChanged(String phone, String from, String id,
			String t) {
		// TODO Auto-generated method stub
		
	}

	public void fireProfilePictureDeleted(String phone, String from, String id,
			String t) {
		// TODO Auto-generated method stub
		
	}

	public void fireSendMessage(String phone, String targets, String id,
			ProtocolNode node) {
		// TODO Auto-generated method stub
		
	}

	public void fireSendMessageReceived(String phone, String from, String type) {
		// TODO Auto-generated method stub
		
	}

	public void fireSendPresence(String phone, String type, String name) {
		// TODO Auto-generated method stub
		
	}

	public void fireSendStatusUpdate(String phone, String msg) {
		// TODO Auto-generated method stub
		
	}

	public void fireUploadFile(String phone, String name, String url) {
		// TODO Auto-generated method stub
		
	}

	public void fireUploadFileFailed(String phone, String name) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetSyncResult(String result) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetReceipt(String from, String id, String offline,
			String retry) {
		// TODO Auto-generated method stub
		
	}
}
