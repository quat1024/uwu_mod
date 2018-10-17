package quaternary.uwumod;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.Locale;

import java.util.List;

public class UwuLocale extends Locale {

	@Override
	public synchronized void loadLocaleDataFiles(IResourceManager resourceManager, List<String> languageList) {
		// no-op
	}

	@Override
	public String formatMessage(String translateKey, Object[] parameters) {
		return "uwu";
	}
}
