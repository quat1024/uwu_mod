package quaternary.uwumod;

import com.mojang.text2speech.Narrator;

public class UwuNarrator implements Narrator {
	public UwuNarrator(Narrator delegate) {
		this.delegate = delegate;
	}
	
	Narrator delegate;
	
	@Override
	public void say(String msg) {
		delegate.say(UwuMod.ModConfig.narratorUwu ? UwuMod.ModConfig.narratorSays : msg);
	}
	
	@Override
	public void clear() {
		delegate.clear();
	}
	
	@Override
	public boolean active() {
		return delegate.active();
	}
}
