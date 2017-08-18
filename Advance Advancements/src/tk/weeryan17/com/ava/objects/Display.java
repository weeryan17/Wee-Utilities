package tk.weeryan17.com.ava.objects;

import com.weeryan17.utilities.api.objects.JsonString;

import tk.weeryan17.com.ava.objects.enums.Frames;
import tk.weeryan17.com.ava.objects.enums.Textures;

public class Display {
	private Icon icon;
	
	private JsonString title;
	
	private Frames frame;
	
	private Textures background;
	
	private JsonString description;
	
	private boolean show_toast = true;
	
	private boolean announce_to_chat = true;
	
	private boolean hidden = false;

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public JsonString getTitle() {
		return title;
	}

	public void setTitle(JsonString title) {
		this.title = title;
	}

	public Frames getFrame() {
		return frame;
	}

	public void setFrame(Frames frame) {
		this.frame = frame;
	}

	public Textures getBackground() {
		return background;
	}

	public void setBackground(Textures background) {
		this.background = background;
	}

	public JsonString getDescription() {
		return description;
	}

	public void setDescription(JsonString description) {
		this.description = description;
	}

	public boolean isShow_toast() {
		return show_toast;
	}

	public void setShow_toast(boolean show_toast) {
		this.show_toast = show_toast;
	}

	public boolean isAnnounce_to_chat() {
		return announce_to_chat;
	}

	public void setAnnounce_to_chat(boolean announce_to_chat) {
		this.announce_to_chat = announce_to_chat;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
}
