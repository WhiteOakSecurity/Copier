package com.whiteoaksecurity.copier.components;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.ui.editor.extension.EditorCreationContext;
import burp.api.montoya.ui.editor.extension.ExtensionProvidedHttpRequestEditor;
import burp.api.montoya.ui.editor.extension.HttpRequestEditorProvider;
import com.whiteoaksecurity.copier.CopyProfile;
import javax.swing.JComboBox;

public class CopyRequestEditorProvider implements HttpRequestEditorProvider {
	
	private MontoyaApi api;
	private JComboBox<CopyProfile> profiles;
	
	public CopyRequestEditorProvider(MontoyaApi api, JComboBox<CopyProfile> profiles) {
		this.api = api;
		this.profiles = profiles;
	}

	@Override
	public ExtensionProvidedHttpRequestEditor provideHttpRequestEditor(EditorCreationContext creationContext) {
		return new CopyRequestEditor(this.api, this.profiles, creationContext);
	}

}
