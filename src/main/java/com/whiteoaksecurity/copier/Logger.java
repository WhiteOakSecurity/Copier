package com.whiteoaksecurity.copier;

import burp.api.montoya.logging.Logging;

public final class Logger {

    private static Logger INSTANCE;
    private Logging logger;
    
	public Logger(Logging logging) {
		this.logger = logging;
		INSTANCE = this;
	}
    
    public static Logging getLogger() {
        return INSTANCE.logger;
    }
}