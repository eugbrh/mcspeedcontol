
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.speedcontrol.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import net.mcreator.speedcontrol.network.KeyResetMessage;
import net.mcreator.speedcontrol.network.KeyCustomMessage;
import net.mcreator.speedcontrol.network.Key2Message;
import net.mcreator.speedcontrol.network.Key1Message;
import net.mcreator.speedcontrol.SpeedcontrolMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class SpeedcontrolModKeyMappings {
	public static final KeyMapping KEY_2 = new KeyMapping("key.speedcontrol.key_2", GLFW.GLFW_KEY_END, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				SpeedcontrolMod.PACKET_HANDLER.sendToServer(new Key2Message(0, 0));
				Key2Message.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping KEY_1 = new KeyMapping("key.speedcontrol.key_1", GLFW.GLFW_KEY_HOME, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				SpeedcontrolMod.PACKET_HANDLER.sendToServer(new Key1Message(0, 0));
				Key1Message.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping KEY_RESET = new KeyMapping("key.speedcontrol.key_reset", GLFW.GLFW_KEY_MINUS, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				SpeedcontrolMod.PACKET_HANDLER.sendToServer(new KeyResetMessage(0, 0));
				KeyResetMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping KEY_CUSTOM = new KeyMapping("key.speedcontrol.key_custom", GLFW.GLFW_KEY_EQUAL, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				SpeedcontrolMod.PACKET_HANDLER.sendToServer(new KeyCustomMessage(0, 0));
				KeyCustomMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(KEY_2);
		event.register(KEY_1);
		event.register(KEY_RESET);
		event.register(KEY_CUSTOM);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				KEY_2.consumeClick();
				KEY_1.consumeClick();
				KEY_RESET.consumeClick();
				KEY_CUSTOM.consumeClick();
			}
		}
	}
}
