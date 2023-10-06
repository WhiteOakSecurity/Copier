package com.whiteoaksecurity.copier.listeners;

import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.ui.contextmenu.ContextMenuEvent;
import com.whiteoaksecurity.copier.CopyProfile;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CopyContentMenuListener implements ActionListener {

	private CopyProfile profile;
	private boolean copyRequest;
	private boolean copyResponse;
	private ContextMenuEvent contextEvent;
	
	public CopyContentMenuListener(CopyProfile profile, boolean copyRequest, boolean copyResponse, ContextMenuEvent contextEvent) {
		this.profile = profile;
		this.copyRequest = copyRequest;
		this.copyResponse = copyResponse;
		this.contextEvent = contextEvent;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		StringBuilder copyBuffer = new StringBuilder();
		
		int counter = 1;
		ArrayList<HttpRequestResponse> requestResponses = new ArrayList<>();
		
		if (!this.contextEvent.selectedRequestResponses().isEmpty()) {
			requestResponses.addAll(this.profile.replace(this.contextEvent.selectedRequestResponses(), this.copyRequest, this.copyResponse));
		} else if (!this.contextEvent.messageEditorRequestResponse().isEmpty()) {
			requestResponses.add(this.profile.replace(this.contextEvent.messageEditorRequestResponse().get().requestResponse(), this.copyRequest, this.copyResponse));
		}
		
		for (HttpRequestResponse httpRequestResponse : requestResponses) {
			if (this.copyRequest) {
				copyBuffer.append(new String(httpRequestResponse.request().toByteArray().getBytes(), StandardCharsets.UTF_8));
			}
			
			if (this.copyRequest && this.copyResponse) {
				if (httpRequestResponse.request().body().length() > 0) {
					copyBuffer.append("\n\n");
				}
			}
			
			if (this.copyResponse) {
				copyBuffer.append(new String(httpRequestResponse.response().toByteArray().getBytes(), StandardCharsets.UTF_8));
			}
			
			if (counter != requestResponses.size()) {
				copyBuffer.append("\n\n\n");
			}
			
			counter += 1;
		}
		
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(copyBuffer.toString()), null);
	}

}
