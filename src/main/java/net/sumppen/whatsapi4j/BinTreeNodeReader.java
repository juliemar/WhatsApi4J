package net.sumppen.whatsapi4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.apache.log4j.Logger;

public class BinTreeNodeReader {

	private String[] dictionary;
	private KeyStream key;
	private byte[] input;
	private final Logger log = Logger.getLogger(BinTreeNodeReader.class);

	public BinTreeNodeReader(String[] dictionary) {
		this.dictionary = dictionary;
	}
	
	public void resetKey() {
		this.key = null;
	}
	
	public void setKey(KeyStream key) {
		this.key = key;
	}

	/**
	 * 
	 * @param readData
	 * @return
	 * @throws IncompleteMessageException 
	 * @throws InvalidMessageException 
	 * @throws InvalidTokenException 
	 * @throws IOException 
	 */
	public ProtocolNode nextTree(byte[] readData) throws IncompleteMessageException, InvalidMessageException, InvalidTokenException, IOException {
		if(readData != null) {
			input = readData;
//			log.debug("Input ="+new String(readData));
		}
		
		int stanzaFlag = (peekInt8(0) & 0xF0) >> 4;
		int stanzaSize = peekInt16(1);
		if(stanzaSize > input.length) {
			throw new IncompleteMessageException("incomplete message");
		}
	    readInt24();
	    if ((stanzaFlag & 8) != 0) {
	    	if (key != null) {
	    		byte[] remainingData = Arrays.copyOfRange(input, stanzaSize, input.length+1);
	            byte[] decoded = key.decode(input, 0, stanzaSize);
	            input = Arrays.copyOf(decoded, decoded.length+remainingData.length);
	            System.arraycopy(remainingData, 0, input, decoded.length, remainingData.length);
	        } else {
	            throw new InvalidMessageException("Encountered encrypted message, missing key");
	        }
	    }
	    if (stanzaSize > 0) {
	        return nextTreeInternal();
	    }
		return null;
	}


	private ProtocolNode nextTreeInternal() throws InvalidTokenException, IOException {
        int token = readInt8();
        int size = readListSize(token);
        token = readInt8();
        if (token == 1) {
            Map<String, String> attributes = readAttributes(size);

            return new ProtocolNode("start", attributes, null, null);
        } 
        if (token == 2) {
            return null;
        }
        String tag = new String(readString(token));
        Map<String, String> attributes = readAttributes(size);
        if ((size % 2) == 1) {
            return new ProtocolNode(tag, attributes, null, null);
        }
        token = readInt8();
        if (isListTag(token)) {
            return new ProtocolNode(tag, attributes, readList(token), null);
        }

        return new ProtocolNode(tag, attributes, null, readString(token));
    }

	private LinkedList<ProtocolNode> readList(int token) throws InvalidTokenException, IOException {
        int size = readListSize(token);
        LinkedList<ProtocolNode> ret = new LinkedList<ProtocolNode>();
        for (int i = 0; i < size; i++) {
            ret.add(nextTreeInternal());
        }

        return ret;	
    }

	private boolean isListTag(int token) {
		return ((token == 248) || (token == 0) || (token == 249));	}

	private byte[] readString(int token) throws IOException, InvalidTokenException {
        ByteArrayOutputStream ret = new ByteArrayOutputStream();
        int size;
        if (token == -1) {
            throw new InvalidTokenException("BinTreeNodeReader->readString: Invalid token $token");
        }
        switch(token) {
        case 0:
        	break;
        case 0xfc:
            size = readInt8();
            ret.write(fillArray(size));
            break;
        case 0xfd:
            size = readInt24();
            ret.write(fillArray(size));
            break;
        case 0xfe:
            token = readInt8();
            ret.write(getToken(token + 0xf5));
            break;
        case 0xfa:
            byte[] user = readString(readInt8());
            byte[] server = readString(readInt8());
            if((user.length > 0) && (server.length > 0)) {
                ret.write(user);
                ret.write('@');
                ret.write(server);
            } else {
            	if (server.length > 0) {
            		ret.write(server);
            	}
            }
        default:
            if(token > 4 && token < 0xf5) {
            	ret.write(getToken(token));
            }
            break;

        }

        return ret.toByteArray();
    }

	private byte[] fillArray(int size) {
        byte[] ret = null;
        if (input.length >= size) {
            ret = Arrays.copyOfRange(input, 0, size);
            input = Arrays.copyOfRange(input, size, input.length);
        }

        return ret;
    }

	private byte[] getToken(int token) throws InvalidTokenException {
        String ret = "";
        if ((token >= 0) && (token < dictionary.length)) {
            ret = dictionary[token];
        } else {
            throw new InvalidTokenException("BinTreeNodeReader->getToken: Invalid token "+token);
        }

        return ret.getBytes();
    }

	private int readListSize(int token) throws InvalidTokenException {
        int size = 0;
        switch(token) {
        case 0xf8:
        case -8:
        	size = readInt8();
        	break;
        case 0xf9:
        case -7:
        	size = readInt16();
        	break;
        default:
        	throw new InvalidTokenException("BinTreeNodeReader->readListSize: Invalid token "+token);
        }
        return size;
    }

	private Map<String, String> readAttributes(int size) throws IOException, InvalidTokenException {
        Map<String, String> attributes = new LinkedHashMap<String, String>();
        int attribCount = (size - 2 + size % 2) / 2;
        for (int i = 0; i < attribCount; i++) {
            String key = new String(readString(readInt8()));
            String value = new String(readString(readInt8()));
            attributes.put(key, value);
        }

        return attributes;	
    }
	
	private int readInt24() {
		int ret = peekInt24(0);
		if (input.length >= 3) {
			input = Arrays.copyOfRange(input, 3, input.length+1);
		}
		
		return ret;
	}
	
	private int peekInt24(int offset) {
		int ret = 0;
		if (input.length >= (3 + offset)) {
			ret = (input[offset]&0xFF) << 16;
			ret |= (input[offset + 1]&0xFF) << 8;
			ret |= (input[offset + 2]&0xFF) << 0;
		}
		
		return ret;	
	}

	private int readInt8() {
		int ret = peekInt8(0);
		if (input.length >= 1) {
			input = Arrays.copyOfRange(input, 1, input.length+1);
		}
		
		return ret;
	}

	private int peekInt8(int offset) {
		if(input.length >= (1+offset)) {
			return input[offset] & 0xFF;
		}
		return 0;
	}

	private int readInt16() {
		int ret = peekInt16(0);
		if (input.length >= 2) {
			input = Arrays.copyOfRange(input, 2, input.length+1);
		}
		
		return ret;
	}

	private int peekInt16(int offset) {
		int ret = 0;
		if(input.length >= (2+offset)) {
			ret += (input[offset]&0xFF) << 8;
			ret += (input[offset+1]&0xFF) << 0;
		}
		return ret;
	}
}
