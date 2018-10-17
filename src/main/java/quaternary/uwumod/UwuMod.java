package quaternary.uwumod;

import com.mojang.text2speech.Narrator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.ITickableSound;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.translation.LanguageMap;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;

@Mod(modid = UwuMod.MODID, name = UwuMod.NAME, version = UwuMod.VERSION, clientSideOnly = true)
@Mod.EventBusSubscriber(modid = UwuMod.MODID)
public class UwuMod {
	public static final String MODID = "uwu_mod";
	public static final String NAME = "uwu mod";
	public static final String VERSION = "GRADLE:VERSION";
	
	public static final ResourceLocation hell = new ResourceLocation(MODID, "uwu");
	
	@Config(modid = MODID)
	public static class ModConfig {
		@Config.Name("Buggy tickable sounds")
		@Config.Comment("If you turn this on try using a minecart")
		public static boolean brokenTickableSounds = false;
		
		@Config.Name("Pitch multiplier")
		@Config.RangeDouble(min = 0.2, max = 5)
		public static double pitchMultiplier = 1;
		
		@Config.Name("Narrator only says uwu")
		public static boolean narratorUwu = true;
		
		@Config.Name("What the narrator actually says")
		public static String narratorSays = "ooh wu";
	}
	
	@Mod.EventHandler
	public void construction(FMLConstructionEvent e) {
		try {
			Field localeField = ReflectionHelper.findField(I18n.class, "i18nLocale", "field_135054_a");
			localeField.set(null, new UwuLocale());
			LanguageMap languageMap = new UwuLanguageMap();
			Field languageMapField = ReflectionHelper.findField(net.minecraft.util.text.translation.I18n.class, "localizedName", "field_74839_a");
			EnumHelper.setFailsafeFieldValue(languageMapField, null, languageMap);
			Field fallbackMapField = ReflectionHelper.findField(net.minecraft.util.text.translation.I18n.class, "fallbackTranslator", "field_150828_b ");
			EnumHelper.setFailsafeFieldValue(fallbackMapField, null, languageMap);
		} catch (Exception uwu) {
			LogManager.getLogger(NAME).error("Problem making uwu locale", uwu);
		}
	}

	@Mod.EventHandler
	public void postinit(FMLPostInitializationEvent e) {
		try {
			Field narratorField = ReflectionHelper.findField(NarratorChatListener.class, "narrator", "field_192580_a");
			Narrator originalNarrator = (Narrator) narratorField.get(NarratorChatListener.INSTANCE);
			EnumHelper.setFailsafeFieldValue(narratorField, NarratorChatListener.INSTANCE, new UwuNarrator(originalNarrator));
		} catch (Exception uwu) {
			LogManager.getLogger(NAME).error("Problem making uwu narrator", uwu);
		}
	}
	
	@SubscribeEvent
	public static void configMemesThanksForgeYoureTheBestUghJustDoThisYourselfWhyWouldI_Want_ToHaveABrokenConfigUIOnPurpose(ConfigChangedEvent.OnConfigChangedEvent e) {
		if(e.getModID().equals(MODID)) {
			ConfigManager.sync(MODID, Config.Type.INSTANCE);
		}
	}
	
	@SubscribeEvent(priority = EventPriority.LOW)
	public static void uwuwuwuwuwu(PlaySoundEvent e) {
		ISound original = e.getResultSound();
		
		//idk why but this makes PositionedSounds populate this field called just "sound"
		//which otherwise makes getVolume npe
		//mojang spaghetti i imagine
		original.createAccessor(Minecraft.getMinecraft().getSoundHandler());
		
		if(!ModConfig.brokenTickableSounds && original instanceof ITickableSound) {
			e.setResultSound(new Tickableuwu((ITickableSound)original));
		} else {
			e.setResultSound(new uwu(original));
		}
	}
	
	public static class uwu implements ISound {
		private uwu(ISound uwu) {
			this.uwu = uwu;
			no = new Sound(hell.toString(), uwu.getSound().getVolume(), uwu.getPitch(), uwu.getSound().getWeight(), uwu.getSound().getType(), uwu.getSound().isStreaming());
		}
		
		final ISound uwu;
		final Sound no;
		
		@Nonnull
		@Override
		public ResourceLocation getSoundLocation() {
			return hell; //hell
		}
		
		@Nullable
		@Override
		public SoundEventAccessor createAccessor(@Nonnull SoundHandler handler) {
			return new SoundEventAccessor(hell, "uwu_mod.uwu");
		}
		
		@Nonnull
		@Override
		public Sound getSound() {
			return no;
		}
		
		@Nonnull
		@Override
		public SoundCategory getCategory() {
			return uwu.getCategory();
		}
		
		@Override
		public boolean canRepeat() {
			return uwu.canRepeat();
		}
		
		@Override
		public int getRepeatDelay() {
			return uwu.getRepeatDelay();
		}
		
		@Override
		public float getVolume() {
			return uwu.getVolume();
		}
		
		@Override
		public float getPitch() {
			return uwu.getPitch() * (float) ModConfig.pitchMultiplier;
		}
		
		@Override
		public float getXPosF() {
			return uwu.getXPosF();
		}
		
		@Override
		public float getYPosF() {
			return uwu.getYPosF();
		}
		
		@Override
		public float getZPosF() {
			return uwu.getZPosF();
		}
		
		@Nonnull
		@Override
		public AttenuationType getAttenuationType() {
			return uwu.getAttenuationType();
		}
	}
	
	public static class Tickableuwu extends uwu implements ITickableSound {
		public Tickableuwu(ITickableSound uwu) {
			super(uwu);
		}
		
		@Override
		public boolean isDonePlaying() {
			return ((ITickableSound)uwu).isDonePlaying();
		}
		
		@Override
		public void update() {
			((ITickableSound)uwu).update();
		}
	}
}
