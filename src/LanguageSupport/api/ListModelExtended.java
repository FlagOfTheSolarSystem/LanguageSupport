package LanguageSupport.api;

import javax.swing.AbstractListModel;

public class ListModelExtended extends AbstractListModel {

	private static final long serialVersionUID = 5460552438949230656L;

	String[] values = new String[] {};

	@Override
	public int getSize() {
		return values.length;
	}

	@Override
	public String getElementAt(int index) {
		return values[index];
	}

	public String[] getValues(){
		return values;
	}
	
	public void addValue(String string) {
		String[] newValues = new String[values.length + 1];
		for (int i = 0; i < values.length; i++) {
			newValues[i] = values[i];
			newValues[i + 1] = string;
		}
		values = newValues;
	}

	public void setValue(String[] string) {
		values = string;
	}

	public void delValue(int index) {
		if (values.length <= 1) {
			values = new String[0];
		} else {
			String[] newValues = new String[values.length - 1];
			for (int i = 0; i < values.length - 1; i++) {
				if (i == index) {
					newValues[i] = values[i + 1];
				} else if (i > index) {
					newValues[i] = values[i + 1];
				} else if (i < index) {
					newValues[i] = values[i];
				}
			}
			values = newValues;
		}
	}
	
	public boolean isEmpty() {
		return values.length == 0;
	}
}