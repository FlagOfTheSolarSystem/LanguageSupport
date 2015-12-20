package LanguageSupport.api;

public class Language {

	private boolean enabled;
	private String name, nativeName;
	private String[] charName, charCode;

	public Language(
			boolean en,
			String name,
			String nativeName,
			String[] charName,
			String[] charCode
				   )
	{
		this.setEnabled(en);
		this.setName(name);
		this.setNativeName(nativeName);
		this.setCharName(charName);
		this.setCharCode(charCode);

	}

	public boolean isEnabled() {return enabled;}
	public void setEnabled(boolean enabled) {this.enabled = enabled;}
	public String getNativeName() {return nativeName;}
	public void setNativeName(String nativeName) {this.nativeName = nativeName;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String[] getCharName() {return charName;}
	public void setCharName(String[] charName) {this.charName = charName;}
	public String[] getCharCode() {return charCode;}
	public void setCharCode(String[] charCode) {this.charCode = charCode;}

}
