package com.stevekung.originatia.event.handler;

import java.util.Locale;

import org.lwjgl.glfw.GLFW;

import com.stevekung.stevekungslib.utils.GameProfileUtils;
import com.stevekung.stevekungslib.utils.client.ClientUtils;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.command.arguments.BlockStateParser;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MainEventHandler
{
    private final Minecraft mc;

    public MainEventHandler()
    {
        this.mc = Minecraft.getInstance();
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event)
    {
        if (GameProfileUtils.isSteveKunG() && ClientUtils.isKeyDown(GLFW.GLFW_KEY_F7))
        {
            if (this.mc.objectMouseOver != null)
            {
                if (this.mc.objectMouseOver instanceof BlockRayTraceResult)
                {
                    BlockRayTraceResult result = (BlockRayTraceResult)this.mc.objectMouseOver;
                    BlockState state = this.mc.world.getBlockState(result.getPos());
                    StringBuilder stringbuilder = new StringBuilder(BlockStateParser.toString(state));
                    System.out.println(stringbuilder);
                    this.mc.keyboardListener.setClipboardString(stringbuilder.toString());
                }
                if (this.mc.objectMouseOver instanceof EntityRayTraceResult)
                {
                    EntityRayTraceResult result = (EntityRayTraceResult)this.mc.objectMouseOver;
                    System.out.println(result.getEntity());
                }
            }

            if (this.mc.currentScreen != null && this.mc.currentScreen instanceof ContainerScreen)
            {
                ContainerScreen container = (ContainerScreen)this.mc.currentScreen;
                Slot slot = container.getSlotUnderMouse();

                if (slot != null && slot.getHasStack())
                {
                    ItemStack itemStack = slot.getStack();
                    this.mc.keyboardListener.setClipboardString("/give @s " + itemStack.getItem().getRegistryName() + itemStack.getTag());
                }
            }
        }
    }

    @SubscribeEvent
    public void onInitGui(GuiScreenEvent.InitGuiEvent.Post event)
    {
    }

    @SubscribeEvent
    public void onPostGuiDrawScreen(GuiScreenEvent.DrawScreenEvent.Post event)
    {
    }

    @SubscribeEvent
    public void onClientChatReceived(ClientChatReceivedEvent event)
    {
        ITextComponent component = event.getMessage();

        if (event.getType() == ChatType.SYSTEM)
        {
            if (component.getString().toLowerCase(Locale.ROOT).contains("gg"))
            {
                event.setCanceled(true);
            }
        }
    }
}