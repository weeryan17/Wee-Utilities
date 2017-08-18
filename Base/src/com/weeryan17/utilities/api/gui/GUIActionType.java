package com.weeryan17.utilities.api.gui;

public enum GUIActionType {
	/**
	 * Value representing if it should open another GUI.
	 */
	OPEN_GUI,
	
	/**
	 * Value representing if it could close the GUI.
	 */
	CLOSE_GUI,
	
	/**
	 * Value representing if the plugin should get chat input.
	 */
	CHAT_INPUT,
	
	/**
	 * Value representing if the spot should be an item input.
	 * Only should be used on air spot.
	 */
	ITEM_INPUT;
	
}
