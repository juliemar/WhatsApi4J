package net.sumppen.whatsapi4j;

import java.net.Socket;
import java.util.List;
import java.util.Map;

public class EventManager {

	public void fireSendPong(String phoneNumber, String msgid) {
		// TODO Auto-generated method stub
		
	}

	public void fireGroupsParticipantsAdd(String phoneNumber, String groupId,
			String parseJID) {
		// TODO Auto-generated method stub
		
	}

	public void fireGroupsParticipantsRemove(String phoneNumber,
			String groupId, String parseJID, String parseJID2) {
		// TODO Auto-generated method stub
		
	}

	public void firePresence(String phoneNumber, String attribute,
			String attribute2) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetStatus(String phoneNumber, String attribute,
			String attribute2, String attribute3, String attribute4, byte[] data) {
		// TODO Auto-generated method stub
		
	}

	public void fireMessageComposing(String phoneNumber, String attribute,
			String attribute2, String attribute3, String attribute4) {
		// TODO Auto-generated method stub
		
	}

	public void fireMessagePaused(String phoneNumber, String attribute,
			String attribute2, String attribute3, String attribute4) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetMessage(String phoneNumber, String attribute,
			String attribute2, String attribute3, String attribute4,
			String attribute5, byte[] data) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetGroupMessage(String phoneNumber, String attribute,
			String author, String attribute2, String attribute3,
			String attribute4, String attribute5, byte[] data) {
		// TODO Auto-generated method stub
		
	}

	public void fireProfilePictureChanged(String phoneNumber, String attribute,
			String attribute2, String attribute3) {
		// TODO Auto-generated method stub
		
	}

	public void fireProfilePictureDeleted(String phoneNumber, String attribute,
			String attribute2, String attribute3) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetImage(String phoneNumber, String attribute,
			String attribute2, String attribute3, String attribute4,
			String attribute5, String attribute6, String attribute7,
			String attribute8, String attribute9, String attribute10,
			String attribute11, String attribute12, byte[] data) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetVideo(String phoneNumber, String attribute,
			String attribute2, String attribute3, String attribute4,
			String attribute5, String attribute6, String attribute7,
			String attribute8, String attribute9, String attribute10,
			String attribute11, String attribute12, String attribute13,
			byte[] data) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetAudio(String phoneNumber, String attribute,
			String attribute2, String attribute3, String attribute4,
			String attribute5, String attribute6, String attribute7,
			String attribute8, String attribute9, String attribute10,
			String attribute11, String attribute12) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetvCard(String phoneNumber, String attribute,
			String attribute2, String attribute3, String attribute4,
			String attribute5, String attribute6, byte[] data) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetLocation(String phoneNumber, String attribute,
			String attribute2, String attribute3, String attribute4,
			String attribute5, String name, String attribute6,
			String attribute7, String url, byte[] data) {
		// TODO Auto-generated method stub
		
	}

	public void fireMessageReceivedServer(String phoneNumber, String attribute,
			String attribute2, String attribute3, String attribute4) {
		// TODO Auto-generated method stub
		
	}

	public void fireMessageReceivedClient(String phoneNumber, String attribute,
			String attribute2, String attribute3, String attribute4) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetGroupsSubject(String phoneNumber, String[] reset_from,
			String attribute, String[] reset_author, String[] reset_author2, String attribute2,
			byte[] data) {
		// TODO Auto-generated method stub
		
	}

	public void fireSendMessageReceived(String phoneNumber, String string,
			String attribute) {
		// TODO Auto-generated method stub
		
	}

	public void fireSendPresence(String phoneNumber, String string,
			String string2) {
		// TODO Auto-generated method stub
		
	}

	public void fireSendMessage(String phoneNumber, String jid, String string,
			ProtocolNode node) {
		// TODO Auto-generated method stub
		
	}

	public void firePing(String phoneNumber, String attribute) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetPrivacyBlockedList(String phoneNumber,
			List<ProtocolNode> children) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetRequestLastSeen(String phoneNumber, String attribute,
			String attribute2, String attribute3) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetServerProperties(String phoneNumber, String attribute,
			Map<String, String> props) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetProfilePicture(String phoneNumber, String attribute,
			String attribute2, byte[] data) {
		// TODO Auto-generated method stub
		
	}

	public void fireGroupsChatCreate(String phoneNumber, String groupId) {
		// TODO Auto-generated method stub
		
	}

	public void fireGroupsChatEnd(String phoneNumber, String groupId) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetGroups(String phoneNumber, Map<String, String> groupList) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetGroupsInfo(String phoneNumber,
			Map<String, String> groupList) {
		// TODO Auto-generated method stub
		
	}

	public void fireGetGroupParticipants(String phoneNumber, String groupId,
			Map<String, String> groupList) {
		// TODO Auto-generated method stub
		
	}

	public void fireDisconnect(String phoneNumber, Socket socket) {
		// TODO Auto-generated method stub
		
	}

}
