package com.stevekung.originrealmscatia.event.handler;

import java.util.Locale;

import org.lwjgl.glfw.GLFW;

import com.mojang.brigadier.StringReader;
import com.stevekung.stevekungslib.utils.client.ClientUtils;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.command.arguments.BlockStateArgument;
import net.minecraft.command.arguments.BlockStateInput;
import net.minecraft.command.arguments.BlockStateParser;
import net.minecraft.util.math.BlockRayTraceResult;
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
        if (ClientUtils.isKeyDown(GLFW.GLFW_KEY_F7))
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
                    
                    try
                    {
                        BlockStateInput input = BlockStateArgument.blockState().parse(new StringReader("minecraft:tripwire[attached=false,disarmed=false,east=false,north=true,powered=false,south=false,west=false]"));
                        
                        if (state.equals(input.getState()))
                        {
                            System.out.println(input.getState());
                        }
                    }
                    catch (Exception e)
                    {

                    }
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