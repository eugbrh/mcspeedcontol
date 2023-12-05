package net.mcreator.speedcontrol.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;

import net.mcreator.speedcontrol.world.inventory.CustomMenu;
import net.mcreator.speedcontrol.network.CustomButtonMessage;
import net.mcreator.speedcontrol.SpeedcontrolMod;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class CustomScreen extends AbstractContainerScreen<CustomMenu> {
	private final static HashMap<String, Object> guistate = CustomMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox Multiplier;
	Button button_primienit;
	Button button_viernut;

	public CustomScreen(CustomMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 170;
		this.imageHeight = 80;
	}

	private static final ResourceLocation texture = new ResourceLocation("speedcontrol:textures/screens/custom.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
		Multiplier.render(ms, mouseX, mouseY, partialTicks);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (Multiplier.isFocused())
			return Multiplier.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		Multiplier.tick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, Component.translatable("gui.speedcontrol.custom.label_vvieditie_mnozhitiel_skorosti"), 15, 8, -12829636);
	}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();
		Multiplier = new EditBox(this.font, this.leftPos + 24, this.topPos + 23, 120, 20, Component.translatable("gui.speedcontrol.custom.Multiplier"));
		Multiplier.setMaxLength(32767);
		guistate.put("text:Multiplier", Multiplier);
		this.addWidget(this.Multiplier);
		button_primienit = Button.builder(Component.translatable("gui.speedcontrol.custom.button_primienit"), e -> {
			if (true) {
				SpeedcontrolMod.PACKET_HANDLER.sendToServer(new CustomButtonMessage(0, x, y, z));
				CustomButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 13, this.topPos + 49, 72, 20).build();
		guistate.put("button:button_primienit", button_primienit);
		this.addRenderableWidget(button_primienit);
		button_viernut = Button.builder(Component.translatable("gui.speedcontrol.custom.button_viernut"), e -> {
			if (true) {
				SpeedcontrolMod.PACKET_HANDLER.sendToServer(new CustomButtonMessage(1, x, y, z));
				CustomButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 94, this.topPos + 49, 61, 20).build();
		guistate.put("button:button_viernut", button_viernut);
		this.addRenderableWidget(button_viernut);
	}
}
