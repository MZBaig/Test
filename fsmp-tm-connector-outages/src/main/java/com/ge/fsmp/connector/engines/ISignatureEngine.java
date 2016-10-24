package com.ge.fsmp.connector.engines;

public interface ISignatureEngine {
	String getSignature(String key, String secret, long epoch);

}
