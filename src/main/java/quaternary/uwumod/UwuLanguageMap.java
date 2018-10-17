package quaternary.uwumod;

import net.minecraft.util.text.translation.LanguageMap;

public class UwuLanguageMap extends LanguageMap {

	@Override
	public synchronized String translateKey(String key) {
		return "uwu";
	}

	@Override
	public synchronized String translateKeyFormat(String key, Object... format) {
		return "uwu";
	}
}
