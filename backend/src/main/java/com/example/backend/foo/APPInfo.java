package com.example.backend.foo;

public class APPInfo {

	private String group;
	
	private String app;
	
	private String version;

	public APPInfo(String group, String app, String version) {
		super();
		this.group = group;
		this.app = app;
		this.version = version;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "{group=" + group + ", app=" + app + ", version=" + version + "}\n";
	}
}
