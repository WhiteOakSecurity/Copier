package com.whiteoaksecurity.copier;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@JsonIgnoreProperties({"REGEX", "LITERAL", "pattern"})
public class Rule {
	public static final boolean REGEX = true;
	public static final boolean LITERAL = false;
	
	private boolean enabled;
	private int location;
	private String match;
	private Pattern pattern;
	private String replace;
	private boolean regex;
	private boolean caseSensitive;
	private String comment;
	
	@JsonCreator
	public Rule(@JsonProperty("enabled") boolean enabled,
				@JsonProperty("location") int location,
				@JsonProperty("match") String match,
				@JsonProperty("replace") String replace,
				@JsonProperty("regex") boolean regex,
				@JsonProperty("caseSensitive") boolean caseSensitive,
				@JsonProperty("comment") String comment) {
		this.enabled = enabled;
		this.location = location;
		this.match = match;
		this.replace = replace;
		this.regex = regex;
		this.caseSensitive = caseSensitive;
		this.comment = comment;
		
		int flags = Pattern.DOTALL;
		if (!this.caseSensitive) {
			flags = flags | Pattern.CASE_INSENSITIVE;
		}
		
		if (this.regex == LITERAL) {
			flags = flags | Pattern.LITERAL;
		}
		
		try {
			this.pattern = Pattern.compile(match, flags);
		} catch (PatternSyntaxException e) {
			this.pattern = null;
			this.enabled = false;
		}
	}
	
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public int getLocation() {
		return this.location;
	}
	
	public String getMatch() {
		return this.match;
	}
	
	public Pattern getPattern() {
		return this.pattern;
	}
	
	public String getReplace() {
		return this.replace;
	}
	
	public boolean isRegex() {
		return this.regex;
	}
	
	public boolean isCaseSensitive() {
		return this.caseSensitive;
	}
	
	public String getComment() {
		return this.comment;
	}
	
	public String toString(String[] locations) {
		return locations[this.location] + ": " + this.match + " -> " + this.replace;
	}
	
	public void setIsEnabled(boolean enabled) {
		this.enabled = enabled;
		this.compile(this.match);
	}
	
	public void setLocation(int location) {
		this.location = location;
	}
	
	public void setMatch(String match) {
		this.match = match;
		this.compile(match);
	}
	
	public void setReplace(String replace) {
		this.replace = replace;
	}
	
	public void setIsRegex(boolean type) {
		this.regex = type;
	}
	
	public void setIsCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public void compile(String match) {
		int flags = Pattern.DOTALL;
		if (!this.caseSensitive) {
			flags = flags | Pattern.CASE_INSENSITIVE;
		}
		
		if (this.regex == LITERAL) {
			flags = flags | Pattern.LITERAL;
		}
		
		try {
			this.pattern = Pattern.compile(match, flags);
		} catch (PatternSyntaxException e) {
			this.pattern = null;
			this.enabled = false;
		}
	}
}
