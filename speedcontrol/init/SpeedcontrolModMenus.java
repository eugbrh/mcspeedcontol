
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.speedcontrol.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import net.mcreator.speedcontrol.world.inventory.CustomMenu;
import net.mcreator.speedcontrol.SpeedcontrolMod;

public class SpeedcontrolModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, SpeedcontrolMod.MODID);
	public static final RegistryObject<MenuType<CustomMenu>> CUSTOM = REGISTRY.register("custom", () -> IForgeMenuType.create(CustomMenu::new));
}
